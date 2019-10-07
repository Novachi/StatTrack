package com.theveloper.stattrack.datamodel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.io.Serializable

open class Team(
 val name: String,
 var winLoseRatio: Double,
 val players: List<Player>,
 val logoResource: Int
): Parcelable{

    private val TAG = "TeamClass"

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.createTypedArrayList(Player)!!,
        parcel.readInt()
    ) {
    }

    fun listAllPlayers(){
        val sb = StringBuilder("teamek")
        sb.append("\n===========${this.name}=============\n")
        for((i, player) in players.withIndex()){
            sb.append("${i+1}.${player.firstName} \"${player.nickname}\" ${player.lastName}")
            when(player){
                is OverwatchPlayer -> sb.append(" favourite hero: ${player.favHero}")
            }
        }
        sb.append("==============================")

        Log.d(TAG, sb.toString())
    }

    override fun toString(): String {
        return "teamName = $name, winLoseRatio = $winLoseRatio"
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

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }


}