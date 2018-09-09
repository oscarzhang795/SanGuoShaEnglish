package com.example.oscar.sanguoshaenglish.Activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.Fragments.CardsFragment
import com.example.oscar.sanguoshaenglish.Fragments.CharactersFragment
import com.example.oscar.sanguoshaenglish.Fragments.HowToPlayFragment
import com.example.oscar.sanguoshaenglish.Fragments.MainMenuFragment
import com.example.oscar.sanguoshaenglish.R
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import java.io.StringReader

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        showMainMenuFragment()
        loadDB()
    }

    fun loadDB() {
        val inputStream = applicationContext.resources.assets.open("characters.json")
        val klaxon = Klaxon()
        val parsed = klaxon.parseArray<Character>(inputStream)
//        Log.i("HERE", parsed?.size.toString())
        val ref = FirebaseFirestore.getInstance()
        parsed?.forEach {
            when(it.alignment){
                "Shu" -> ref.collection("shucharacters").add(it)
                "Wei" -> ref.collection("weicharacters").add(it)
                "Wu" -> ref.collection("wucharacters").add(it)
                "Kingdomless" -> ref.collection("kingdomlesscharacters").add(it)
            }
        }


    }

    /*************************************************
     * Fragment Displays
     *************************************************/
    fun showHowToPlayFragment() {
        var fragment = supportFragmentManager.findFragmentByTag(HowToPlayFragment::class.java.simpleName)
        if (fragment == null) {
            fragment = HowToPlayFragment()
        }
        supportFragmentManager.fragAction {
            replace(R.id.fl_main_container, fragment, fragment::class.simpleName).addToBackStack(fragment::class.java.simpleName)
        }
    }

    fun showCharactersFragment() {
        var fragment = supportFragmentManager.findFragmentByTag(CharactersFragment::class.java.simpleName)
        if (fragment == null) {
            fragment = CharactersFragment()
        }
        supportFragmentManager.fragAction {
            replace(R.id.fl_main_container, fragment, fragment::class.simpleName).addToBackStack(fragment::class.java.simpleName)
        }
    }

    fun showCardsFragment() {
        var fragment = supportFragmentManager.findFragmentByTag(CardsFragment::class.java.simpleName)
        if (fragment == null) {
            fragment = CardsFragment()
        }
        supportFragmentManager.fragAction {
            replace(R.id.fl_main_container, fragment, fragment::class.simpleName).addToBackStack(fragment::class.java.simpleName)
        }
    }

    fun showMainMenuFragment() {
        var fragment = supportFragmentManager.findFragmentByTag(MainMenuFragment::class.java.simpleName)
        if (fragment == null) {
           fragment = MainMenuFragment()
        }
        supportFragmentManager.fragAction {
            replace(R.id.fl_main_container, fragment, fragment::class.simpleName).addToBackStack(fragment::class.java.simpleName)
        }
    }

    // Extension function for transactions
    private fun FragmentManager.fragAction(action: FragmentTransaction.() -> FragmentTransaction) {
        val trans = this.beginTransaction()
        trans.action()
        trans.commit()
    }
}
