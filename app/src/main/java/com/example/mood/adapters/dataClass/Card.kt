package com.example.mood.adapters.dataClass

data class Card (
    val cardDate: String?,
    val cardHealthy: String?,
    val cardUnhealthy: String?,
    val cardSymptoms: String?,
    val cardCare: String?,
    val cardMood: Float?,
    val cardHealth: Float?,
    val cardStress: Float?,
    val cardOther: String?,
    var id: Int
)