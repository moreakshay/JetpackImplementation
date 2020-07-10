package moreakshay.com.mine.injection.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import moreakshay.com.mine.BuildConfig
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.injection.qualifiers.ApplicationContext
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.utils.constants.API_KEY
import moreakshay.com.mine.utils.constants.BASE_URL
import moreakshay.com.mine.utils.constants.DATABASE_NAME
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @ApplicationScope
    @Provides
    fun proviesHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    @ApplicationScope
    @Provides
    fun proviedHeaderInterceptor(): Interceptor {
        return Interceptor {
            val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                    .build()
            val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()
            return@Interceptor it.proceed(request)
        }
    }

    @ApplicationScope
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MineDatabase {
        return Room.databaseBuilder(context, MineDatabase::class.java, DATABASE_NAME).build()
    }
}