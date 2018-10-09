package com.example.oscar.sanguoshaenglish.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beust.klaxon.Klaxon
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.Adapters.CharactersAdapter
import com.example.oscar.sanguoshaenglish.Entities.Ability
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.Entities.CharacterData
import com.example.oscar.sanguoshaenglish.Entities.CharacterData_
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
    val wuCharacters = ArrayList<CharacterData>()
    val weiCharacters = ArrayList<CharacterData>()
    val kingdomelesssCharacters = ArrayList<CharacterData>()
    val allCharacters = ArrayList<CharacterData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainMenuActivity).supportActionBar?.title = "Characters"
        characterBox = ((activity as MainMenuActivity).application as SanGuoShaApplication).boxStore.boxFor<CharacterData>()

//        loadJson()
        val testChara = characterBox.query().equal(CharacterData_.alignment, "WU").build().find()
//        testChara.get(0).abilities.get(0).description
        Log.i("Ability name", testChara.get(0).abilities.get(0).description)
//        testChara.forEach {
//            shuCharacters.add(Character(it, resources.getIdentifier(it.image_name, "drawable", context!!.packageName)))
//            Log.i("Image name", it.abilities[0].description)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = GridLayoutManager(this.context, 3)
        viewAdapter = CharactersAdapter(shuCharacters, this.context!!)
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
//                when(it.alignment){
//                    "Shu" -> shuCharacters.add(it)
//                    "Wei" -> weiCharacters.add(it)
//                    "Wu" -> wuCharacters.add(it)
//                    "Kingdomless" -> kingdomelesssCharacters.add(it)
//                }
                Log.i("Image name", it.abilities[0].description)
                characterBox.put(it)
                allCharacters.add(it)
            }
            Log.i("Shu", shuCharacters.size.toString())
            Log.i("Wei", weiCharacters.size.toString())
            Log.i("Wu", wuCharacters.size.toString())
            Log.i("Kingdomeless", kingdomelesssCharacters.size.toString())
        }
    }

}