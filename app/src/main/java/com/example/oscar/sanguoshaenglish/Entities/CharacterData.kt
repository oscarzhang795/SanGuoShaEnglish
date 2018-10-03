package com.example.oscar.sanguoshaenglish.Entities

import java.lang.StringBuilder

data class CharacterData(
        val name: String,
        val title: String,
        val alignment: String,
        val od: Boolean,
        val emperor: Boolean,
        val image_name: String,
        val abilities: List<Ability>
) {
    constructor(): this("", "", "", false, false, "", emptyList())

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("$name\n$title\nCountry: $alignment\n\n")
        abilities.forEach {
            sb.append(it.toString())
            sb.append("\n\n")
        }
        return sb.toString()
    }
}