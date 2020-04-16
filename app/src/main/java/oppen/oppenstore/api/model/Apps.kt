package oppen.oppenstore.api.model

import com.squareup.moshi.JsonClass
import okio.BufferedSource
import oppen.oppenstore.api.MoshiProvider

@JsonClass(generateAdapter = true)
class Apps(
    val store_version: String,
    val apps: List<App>
){
    companion object{
        fun generate(bufferedSource: BufferedSource): Apps? {
            val responseAdapter = MoshiProvider.moshi.adapter(Apps::class.java)
            return responseAdapter.fromJson(bufferedSource)
        }
    }
}