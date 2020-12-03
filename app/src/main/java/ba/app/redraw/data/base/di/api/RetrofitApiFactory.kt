package ba.app.redraw.data.base.di.api

import ba.app.redraw.data.base.config.NetworkConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implementation of [ApiFactory] providing instances using Retrofit2
 */
class RetrofitApiFactory
constructor(
    private val okHttpClient: OkHttpClient,
    private val networkConfig: NetworkConfig
) : ApiFactory {

    /**
     * Should produce implementation of [T] using Retrofit2
     * This requires that it has Retrofit2 annotations
     */
    override fun <T> buildApi(type: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(networkConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(type)
    }
}
