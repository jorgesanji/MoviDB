package com.test.movies.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Movie() : Parcelable, Comparable<Movie> {

    @SerializedName("id")
    private var _id:Int? = null

    @SerializedName("vote_count")
    private var _vote_count:Int? = null

    @SerializedName("original_language")
    private var _original_language:String? = null

    @SerializedName("original_title")
    private var _original_title:String? = null

    @SerializedName("title")
    private var _title:String? = null

    @SerializedName("overview")
    private var _overview:String? = null

    @SerializedName("release_date")
    private var _release_date: Date? = Date()

    @SerializedName("poster_path")
    private var _poster_path:String? = null

    @SerializedName("backdrop_path")
    private var _backdrop_path:String? = null

    val id get() = _id

    val title get() = _title

    val overview get() = _overview

    val date get() = _release_date

    val posterImage get() = _poster_path

    val backdropImage get() = _backdrop_path

    constructor(parcel: Parcel) : this() {
        _id = parcel.readSerializable() as? Int
        _vote_count = parcel.readSerializable() as? Int
        _original_language = parcel.readString()
        _original_title = parcel.readString()
        _title = parcel.readString()
        _overview = parcel.readString()
        _release_date = parcel.readSerializable() as? Date
        _poster_path = parcel.readString()
        _backdrop_path = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeSerializable(_id)
            dest.writeSerializable(_vote_count)
            dest.writeString(_original_language)
            dest.writeString(_original_title)
            dest.writeString(_title)
            dest.writeString(_overview)
            dest.writeSerializable(_release_date)
            dest.writeString(_poster_path)
            dest.writeString(_backdrop_path)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(o: Movie): Int {
        return _release_date!!.compareTo(o._release_date)
    }
}