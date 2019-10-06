package com.theveloper.stattrack.datamodel

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Match<T: Team>(val team1: T?, val team2: T?, val team1Score: Int, val team2Score: Int): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Match<T>::team1.javaClass.classLoader),
        parcel.readParcelable(Match<T>::team1.javaClass.classLoader),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(team1, flags)
        parcel.writeParcelable(team2, flags)
        parcel.writeInt(team1Score)
        parcel.writeInt(team2Score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match<Team>> {
        override fun createFromParcel(parcel: Parcel): Match<Team> {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match<Team>?> {
            return arrayOfNulls(size)
        }
    }
}