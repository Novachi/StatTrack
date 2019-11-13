package com.theveloper.stattrack.datamodel

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Match(val team1: Team?, val team2: Team?, val team1Score: Int, val team2Score: Int): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Match::team1.javaClass.classLoader),
        parcel.readParcelable(Match::team1.javaClass.classLoader),
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

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match{
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}