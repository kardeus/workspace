package kr.goodchoice.imagedownload.domain

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

abstract class BaseInterceptor : Interceptor {

    abstract val headers: Headers

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val requestBuilder = origin.newBuilder().headers(headers)
        Timber.d("headers:${headers}")
        return chain.proceed(requestBuilder.build())
    }
}

class AuthInterceptor : BaseInterceptor() {
    override val headers: Headers
        get() = Headers.Builder()
            .add("test", "1234")
            .build()

}

class KakaoInterceptor : BaseInterceptor() {
    override val headers: Headers
        get() = Headers.Builder()
            .add("test", "1234")
            .add("Authorization", "KakaoAK 117538566f139e95b1f2e556f5031d37")
            .build()

}

class OtherInterceptor : BaseInterceptor() {
    override val headers: Headers
        get() = Headers.Builder()
            .add("test", "1234")
            .build()

}