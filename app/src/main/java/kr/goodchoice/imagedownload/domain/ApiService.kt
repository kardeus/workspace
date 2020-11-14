package kr.goodchoice.imagedownload.domain

import io.reactivex.Single
import kr.goodchoice.imagedownload.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //sort=accuracy&page=1&size=10&query=12312" \
    // sort
    // accuracy(정확도순) 또는 recency(최신순)
    @GET("/v2/search/image")
    fun getApi(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10) : Single<Response<SearchResponse>>

}