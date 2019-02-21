package com.example.oscar.sanguoshaenglish.entities

import com.example.oscar.sanguoshaenglish.AbilityConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.serialization.Serializable
import java.lang.StringBuilder

@Entity
@Serializable
data class CharacterData(
        @Id var id: Long = 0,
        val name: String,
        val title: String,
        val alignment: String,
        val od: Boolean,
        val emperor: Boolean,
        val image_name: String,
        @Convert(converter = AbilityConverter::class, dbType = String::class)
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