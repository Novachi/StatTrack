package com.theveloper.stattrack.datamodel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.io.Serializable

abstract class Team<T : Player>(
 val name: String,
 var winLoseRatio: Double,
 val players: List<T>,
 val logoResource: Int
): Parcelable{
    abstract val t: T;

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.createTypedArrayList(T.CREATOR),
        parcel.readInt()
    )


    private val TAG = "TeamClass"



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

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}