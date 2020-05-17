package moreakshay.com.mine.injection.modules

import android.content.Context
import androidx.room.Room
import com.android.example.github.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import moreakshay.com.mine.BuildConfig
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.injection.qualifiers.ApplicationContext
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.utils.constants.ApiConstants
import moreakshay.com.mine.utils.constants.DBConstants
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
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
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
                    .addQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
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
        return Room.databaseBuilder(context, MineDatabase::class.java, DBConstants.DATABASE_NAME).build()
    }
}