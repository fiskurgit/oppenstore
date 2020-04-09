package oppen.oppenstore.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Content(
    val type: String,
    val text: String?,
    val image: String?
)