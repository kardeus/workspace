package kr.goodchoice.imagedownload.model

import kr.goodchoice.imagedownload.app.NetworkConst

data class Search(
    val query : String = "",
    val search : String = NetworkConst.SORT,
    val page : Int = 1,
    val size : Int = 10,
)