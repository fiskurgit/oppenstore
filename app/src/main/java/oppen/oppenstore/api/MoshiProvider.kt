package oppen.oppenstore.api

import com.squareup.moshi.Moshi

object MoshiProvider {
    private var mosh: Moshi? = null

    val moshi: Moshi
        get(){
            when (mosh) {
                null -> mosh = Moshi.Builder().build()
            }
            return mosh!!
        }
}