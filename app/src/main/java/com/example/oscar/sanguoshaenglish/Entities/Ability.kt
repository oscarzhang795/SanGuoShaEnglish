package com.example.oscar.sanguoshaenglish.Entities

import com.beust.klaxon.Json

data class Ability(
        val name: String,
        val description: String,
        @Json(name = "emperor_ability")
        val isEmperorAbility: Boolean
)