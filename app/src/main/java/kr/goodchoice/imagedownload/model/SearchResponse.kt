package kr.goodchoice.imagedownload.model

import org.joda.time.DateTime
import java.io.Serializable

data class SearchResponse(
    val documents: List<Documents>?
) : Serializable

data class Documents(
    val collection: String?,
    val contents: String?,
    val datetime: DateTime?,
    val display_sitename: String?,
    val doc_url: String?,
    val image_url: String?,
    val thumbnail_url: String?,
    val width: Int,
    val height: Int
) : Serializable

