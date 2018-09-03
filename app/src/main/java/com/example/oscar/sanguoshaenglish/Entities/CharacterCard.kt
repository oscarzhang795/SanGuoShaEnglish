package com.example.oscar.sanguoshaenglish.Entities

class CharacterCard () {

    lateinit var alignment: Alignment
    lateinit var characterName: String
    val abilities = HashMap<String, String>()

    enum class Alignment(val string: String) {
        SHU("Shu"),
        WEI("Wei"),
        WU("Wu")
    }
}