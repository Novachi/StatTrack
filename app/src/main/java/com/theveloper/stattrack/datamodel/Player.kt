package com.theveloper.stattrack.datamodel

import android.os.Parcel
import android.os.Parcelable


sealed class Player (val firstName: String, val lastName: String, val nickname: String): Parcelable{

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(nickname)
    }

    class CsgoPlayer(firstName:String, lastName: String, nickname: String): Player(firstName, lastName, nickname) {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<CsgoPlayer> {
            override fun createFromParcel(parcel: Parcel): CsgoPlayer {
                return CsgoPlayer(parcel)
            }

            override fun newArray(size: Int): Array<CsgoPlayer?> {
                return arrayOfNulls(size)
            }
        }

    }

    class OverwatchPlayer(firstName: String, lastName: String, nickname: String, val favHero: String) : Player(firstName, lastName, nickname) {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeString(favHero)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<OverwatchPlayer> {
            override fun createFromParcel(parcel: Parcel): OverwatchPlayer {
                return OverwatchPlayer(parcel)
            }

            override fun newArray(size: Int): Array<OverwatchPlayer?> {
                return arrayOfNulls(size)
            }
        }

    }
}