package com.example.oscar.sanguoshaenglish.Activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.*
import android.view.View
import android.widget.ImageView
import com.example.oscar.sanguoshaenglish.Fragments.*
import com.example.oscar.sanguoshaenglish.R
import kotlinx.android.synthetic.main.fragment_character_card.view.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        showMainMenuFragment()
    }

    /*************************************************
     * Fragment Displays
     *************************************************/
    fun showCharacterCardFragment(bundle: Bundle, view: View) {
        var fragment = supportFragmentManager.findFragmentByTag(CharacterCardFragment::class.java.simpleName)
        if (fragment == null) {
           fragment = CharacterCardFragment()
        }
        fragment.arguments = bundle
        fragment.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        fragment.sharedElementReturnTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        supportFragmentManager.fragAction {
            setReorderingAllowed(true)
            addSharedElement((view as ImageView), view.transitionName)
            replace(R.id.fl_main_container, fragment, fragment::class.simpleName)
            addToBackStack(fragment::class.java.simpleName)
        }
        supportPostponeEnterTransition()
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
