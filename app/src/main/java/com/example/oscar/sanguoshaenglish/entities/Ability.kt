package com.example.oscar.sanguoshaenglish.entities

import com.beust.klaxon.Json
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Ability(
        @Id var id: Long = 0,
        val name: String,
        val description: String,
        @Json(name = "emperor_ability")
        val isEmperorAbility: Boolean
) {
        override fun toString(): String {
               return name + " -" + "\n" + description
        }
}