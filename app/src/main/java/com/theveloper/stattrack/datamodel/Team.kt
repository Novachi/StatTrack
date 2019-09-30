package com.theveloper.stattrack.datamodel

import android.util.Log

class Team<T : Player>(
 val name: String,
 var winLoseRatio: Double,
 val players: List<T>,
 val logoResource: Int
){
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


}