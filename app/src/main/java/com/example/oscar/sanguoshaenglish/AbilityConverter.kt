package com.example.oscar.sanguoshaenglish

import com.beust.klaxon.Klaxon
import com.example.oscar.sanguoshaenglish.Entities.Ability
import io.objectbox.converter.PropertyConverter

class AbilityConverter : PropertyConverter<Ability, String> {

    private val klaxon = Klaxon()

    override fun convertToEntityProperty(databaseValue: String?): Ability {
        return Ability(0, "fas", "fsa", false)
    }

    override fun convertToDatabaseValue(ability: Ability?): String {
        return klaxon.toJsonString(ability!!)
    }


}