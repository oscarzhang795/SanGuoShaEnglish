package com.example.oscar.sanguoshaenglish.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beust.klaxon.Klaxon
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.Adapters.CountryAdapter
import com.example.oscar.sanguoshaenglish.entities.Character
import com.example.oscar.sanguoshaenglish.entities.CharacterData
import com.example.oscar.sanguoshaenglish.entities.CharacterData_
import com.example.oscar.sanguoshaenglish.R
import com.example.oscar.sanguoshaenglish.SanGuoShaApplication
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch


class CharactersFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var characterBox: Box<CharacterData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainMenuActivity).supportActionBar?.title = "Characters"

        // Create character box instance for objectbox
        characterBox = ((activity as MainMenuActivity).application as SanGuoShaApplication).boxStore.boxFor()

        // Load database if not already setup
        if (characterBox.query().build().findFirst() == null) {
            loadJson()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shuCharacters = characterBox.query().equal(CharacterData_.alignment, "SHU").build().find()
        val wuCharacters = characterBox.query().equal(CharacterData_.alignment, "WU").build().find()
        val weiCharacters = characterBox.query().equal(CharacterData_.alignment, "WEI").build().find()
        val kingdomlesssCharacters = characterBox.query().equal(CharacterData_.alignment, "KINGDOMLESS").build().find()

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = CountryAdapter(shuCharacters, wuCharacters, weiCharacters, kingdomlesssCharacters, this.context!!)
        rv_char_list.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }

    }

    private fun loadJson() {
        val inputStream = this.resources.assets.open("characters.json")
        val klaxon = Klaxon()
        val parsed = klaxon.parseArray<CharacterData>(inputStream)
        parsed?.forEach { it: CharacterData ->
            characterBox.put(it)
        }
    }

}