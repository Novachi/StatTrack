package com.theveloper.stattrack.datamodel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.io.Serializable

sealed class Team(val name: String,
                  val winLoseRatio: Double,
                  val players: List<Player>,
                  val logoResource: Int) : Parcelable {
    class OverwatchTeam(
        name: String,
        winLoseRatio: Double,
        players: List<Player.OverwatchPlayer>,
        logoResource: Int
    ) : Team(name,  winLoseRatio, players, logoResource) {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readDouble(),
            parcel.createTypedArrayList(Player.OverwatchPlayer.CREATOR)!!,
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeDouble(winLoseRatio)
            parcel.writeTypedList(players)
            parcel.writeInt(logoResource)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<OverwatchTeam> {
            override fun createFromParcel(parcel: Parcel): OverwatchTeam {
                return OverwatchTeam(parcel)
            }

            override fun newArray(size: Int): Array<OverwatchTeam?> {
                return arrayOfNulls(size)
            }
        }
    }

    class CsgoTeam(
        name: String,
        winLoseRatio: Double,
        players: List<Player.CsgoPlayer>,
        logoResource: Int
    ) : Team(name,  winLoseRatio, players, logoResource) {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readDouble(),
            parcel.createTypedArrayList(Player.CsgoPlayer.CREATOR)!!,
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeDouble(winLoseRatio)
            parcel.writeTypedList(players)
            parcel.writeInt(logoResource)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<CsgoTeam> {
            override fun createFromParcel(parcel: Parcel): CsgoTeam {
                return CsgoTeam(parcel)
            }

            override fun newArray(size: Int): Array<CsgoTeam?> {
                return arrayOfNulls(size)
            }
        }
    }
}