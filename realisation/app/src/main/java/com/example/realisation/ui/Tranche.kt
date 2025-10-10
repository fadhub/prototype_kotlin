package com.example.realisation.ui

data class Tranche(
    val label: String,
    val start: Int,   // minute du jour
    val end: Int,
    val rate: Double  // MAD / heure
)