package moreakshay.com.mine.injection.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import moreakshay.com.mine.utils.constants.ApiConstants
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.utils.constants.DBConstants
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.local.MineDatabaseImpl
import moreakshay.com.mine.injection.qualifiers.ApplicationContext
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.data.remote.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ApplicationScope
@Module
class NetworkModule {

    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    internal fun provideDatabase(@ApplicationContext context: Context): MineDatabase {
        return synchronized(MineDatabase::class.java) { Room.databaseBuilder(context, MineDatabaseImpl::class.java, DBConstants.DATABASE_NAME).build() }
    }

    @Provides
    internal fun providesMineRepo(local: MineDatabase, remote: ApiService): MineRepository {
        return MineRepository(local, remote)
    }

    @Provides
    internal fun proviedHeaderInterceptor(): Interceptor {
        return Interceptor {
            var url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(ApiConstants.API_KEY, "4396f615b89a743e4145aef1cbc43497")
                    .build()
            var request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()
            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    internal fun proviesHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

}