package com.example.oscar.sanguoshaenglish.Activities

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.Entities.CharacterData
import com.example.oscar.sanguoshaenglish.Fragments.*
import com.example.oscar.sanguoshaenglish.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_characters.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        showMainMenuFragment()
    }

    /*************************************************
     * Fragment Displays
     *************************************************/
    fun showCharacterCardFragment(bundle: Bundle) {
        var fragment = supportFragmentManager.findFragmentByTag(CharacterCardFragment::class.java.simpleName)
        if (fragment == null) {
           fragment = CharacterCardFragment()
        }
        fragment.arguments = bundle
        supportFragmentManager.fragAction {
            replace(R.id.fl_main_container, fragment, fragment::class.simpleName).addToBackStack(fragment::class.java.simpleName)
        }
    }

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
