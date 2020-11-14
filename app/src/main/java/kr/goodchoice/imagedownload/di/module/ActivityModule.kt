package kr.goodchoice.imagedownload.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kr.goodchoice.imagedownload.di.AuthInterceptorOkHttpClient
import kr.goodchoice.imagedownload.di.KakaoInterceptorOkHttpCLient
import kr.goodchoice.imagedownload.domain.ApiService
import kr.goodchoice.imagedownload.repository.SearchRepository
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Named("kakao")
    @Provides
    fun provideKakaoService(
       @KakaoInterceptorOkHttpCLient client: OkHttpClient
    ) : ApiService {
        return NetworkModule.retrofit
            .client(client)
            .baseUrl("https://dapi.kakao.com/")
            .build().create(ApiService::class.java)
    }

    @Named("kakao")
    @Provides
    @Singleton
    fun provideKakaoRepository(
        @Named("kakao") service: ApiService
    ) = SearchRepository(service)

}