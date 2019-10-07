package com.theveloper.stattrack.datamodel

class OverwatchTeam(name: String, winLoseRatio: Double, players: List<OverwatchPlayer>, logoResource: Int): Team(name, winLoseRatio,
    players, logoResource) {
}