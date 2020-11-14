package kr.goodchoice.imagedownload.repository

import io.reactivex.Single
import kr.goodchoice.imagedownload.domain.ApiService
import kr.goodchoice.imagedownload.model.SearchResponse
import javax.inject.Inject
import javax.inject.Named

class SearchRepository @Inject constructor(
    @Named("kakao") private val api : ApiService){

    fun getApi() : Single<SearchResponse> {
        return api.getApi("asdasd")
            .flatMap { Single.just(it.body()) }
    }

}