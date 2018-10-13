package com.example.oscar.sanguoshaenglish

import com.beust.klaxon.Klaxon
import com.example.oscar.sanguoshaenglish.Entities.Ability
import io.objectbox.converter.PropertyConverter

class AbilityConverter : PropertyConverter<List<Ability>, String> {
    private val klaxon = Klaxon()

    override fun convertToEntityProperty(databaseValue: String): List<Ability>? = klaxon.parseArray(databaseValue)!!

    override fun convertToDatabaseValue(entityProperty: List<Ability>): String = klaxon.toJsonString(entityProperty)

}