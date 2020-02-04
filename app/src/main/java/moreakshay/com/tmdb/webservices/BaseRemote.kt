package moreakshay.com.tmdb.webservices

import moreakshay.com.tmdb.constants.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRemote {

    protected fun create(): ApiService{
        return retrofit().create(ApiService::class.java)
    }

    protected fun <T> create(clazz: Class<T>): T{
        return retrofit().create(clazz)
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}