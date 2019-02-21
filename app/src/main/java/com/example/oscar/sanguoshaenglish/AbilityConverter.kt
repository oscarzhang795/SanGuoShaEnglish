package com.example.oscar.sanguoshaenglish

import com.example.oscar.sanguoshaenglish.entities.Ability
import io.objectbox.converter.PropertyConverter
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class AbilityConverter : PropertyConverter<List<Ability>, String> {

    override fun convertToEntityProperty(databaseValue: String): List<Ability>? = Json.parse(Ability.serializer().list, databaseValue)

    override fun convertToDatabaseValue(entityProperty: List<Ability>): String = Json.stringify(Ability.serializer().list, entityProperty)

}