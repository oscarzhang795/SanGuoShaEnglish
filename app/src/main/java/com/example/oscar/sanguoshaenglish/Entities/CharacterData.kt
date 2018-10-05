package com.example.oscar.sanguoshaenglish.Entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.lang.StringBuilder

@Entity
data class CharacterData(
        @Id var id: Long = 0,
        val name: String,
        val title: String,
        val alignment: String,
        val od: Boolean,
        val emperor: Boolean,
        val image_name: String,
        val abilities: List<Ability>
) {

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