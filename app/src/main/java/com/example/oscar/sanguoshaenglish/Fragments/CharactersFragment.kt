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

    val shuCharacters = ArrayList<Character>()
    val wuCharacters = ArrayList<Character>()
    val weiCharacters = ArrayList<Character>()
    val kingdomlesssCharacters = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainMenuActivity).supportActionBar?.title = "Characters"
        characterBox = ((activity as MainMenuActivity).application as SanGuoShaApplication).boxStore.boxFor()

//                loadJson()
        if (shuCharacters.isEmpty() || wuCharacters.isEmpty() || weiCharacters.isEmpty() || kingdomlesssCharacters.isEmpty()) { queryCharacters() }
    }

    private fun queryCharacters() {
//        GlobalScope.launch {
            val shuData = characterBox.query().equal(CharacterData_.alignment, "SHU").build().find()
            shuData.forEach { shuCharacters.add(Character(it, resources.getIdentifier(it.image_name, "drawable", context!!.packageName))) }

            val wuData = characterBox.query().equal(CharacterData_.alignment, "WU").build().find()
            wuData.forEach { wuCharacters.add(Character(it, resources.getIdentifier(it.image_name, "drawable", context!!.packageName))) }

            val weiData = characterBox.query().equal(CharacterData_.alignment, "WEI").build().find()
            weiData.forEach { weiCharacters.add(Character(it, resources.getIdentifier(it.image_name, "drawable", context!!.packageName))) }

            val kingdomlessData = characterBox.query().equal(CharacterData_.alignment, "KINGDOMLESS").build().find()
            kingdomlessData.forEach { kingdomlesssCharacters.add(Character(it, resources.getIdentifier(it.image_name, "drawable", context!!.packageName))) }
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        GlobalScope.launch {
            val parsed = klaxon.parseArray<CharacterData>(inputStream)
            parsed?.forEach { it: CharacterData ->
                characterBox.put(it)
            }
        }
    }

}