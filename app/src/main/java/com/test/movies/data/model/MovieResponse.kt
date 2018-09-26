package com.test.movies.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MovieResponse() : Parcelable{

    @SerializedName("results")
    var results:Array<Movie> ? = null

    @SerializedName("page")
    var page:Int ? = null

    @SerializedName("total_results")
    var total_results:Int ? = null

    @SerializedName("total_pages")
    var total_pages:Int ? = null

    constructor(parcel: Parcel) : this() {
        results = parcel.createTypedArray(Movie)
        page = parcel.readValue(Int::class.java.classLoader) as? Int
        total_results = parcel.readValue(Int::class.java.classLoader) as? Int
        total_pages = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeArray(results)
            dest.writeSerializable(page)
            dest.writeSerializable(total_results)
            dest.writeSerializable(total_pages)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<MovieResponse> {
        override fun createFromParcel(parcel: Parcel): MovieResponse {
            return MovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<MovieResponse?> {
            return arrayOfNulls(size)
        }
    }
}