package de.htwk.gameguro.modules

data class Game(
    val id: Int,
    val name: String,
    val summary: String,
    val coverId: String,
    val rating: Double,
    val screenshots: List<String>,
    val involvedCompanies: List<String>,
    val platforms: List<String>,
)
