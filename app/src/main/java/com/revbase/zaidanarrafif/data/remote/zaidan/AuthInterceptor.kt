package com.revbase.zaidanarrafif.data.remote.zaidan

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(
            "Authorization",
            "Bearer 1|FEYssvkbf2sVh6K2pCuToHUPhkfe3psHdAy96tfq"
        )
        return chain.proceed(requestBuilder.build())
    }
}