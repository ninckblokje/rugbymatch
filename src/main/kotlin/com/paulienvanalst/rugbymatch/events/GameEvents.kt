package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.game.LineOut
import com.paulienvanalst.rugbymatch.game.Scrum
import com.paulienvanalst.rugbymatch.game.SetPiece
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent

sealed class SetPieceEvent(source: Any, val setPiece: SetPiece, val winningTeam: TeamName) : ApplicationEvent(source)

class ScrumWasPlayed(source: Any, scrum: SetPiece, winningTeam: TeamName) : SetPieceEvent(source, scrum, winningTeam)

class LineOutWasPlayed(source: Any, lineOut: SetPiece, winningTeam: TeamName): SetPieceEvent(source, lineOut, winningTeam)

fun List<SetPieceEvent>.wonBy(teamName: TeamName): List<SetPieceEvent> {
    return this.filter { it.winningTeam == teamName }
}

fun List<SetPieceEvent>.lostBy(teamName: TeamName) : List<SetPieceEvent> {
    return this.filter { it.winningTeam != teamName }
}

fun List<SetPieceEvent>.scrumEvents(): List<SetPiece> {
    return this.filter { it.setPiece is Scrum }.map { it.setPiece }
}

fun List<SetPieceEvent>.lineOutEvents(): List<SetPiece> {
    return this.filter { it.setPiece is LineOut }.map { it.setPiece }
}