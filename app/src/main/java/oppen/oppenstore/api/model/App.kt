package oppen.oppenstore.api.model

import com.squareup.moshi.JsonClass
import okio.BufferedSource
import oppen.oppenstore.api.MoshiProvider

@JsonClass(generateAdapter = true)
class App(
    val oppen_id: String,
    val title: String,
    val type: String,
    val version: String,
    val image: String,
    val url: String,
    val short_description: String,
    val full_description: String
)