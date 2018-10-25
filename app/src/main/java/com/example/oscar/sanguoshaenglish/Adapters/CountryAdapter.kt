package com.example.oscar.sanguoshaenglish.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.R
import kotlinx.android.synthetic.main.card_country.view.*

class CountryAdapter(private val shuCountry: ArrayList<Character>,
                     private val wuCountry: ArrayList<Character>,
                     private val weiCountry: ArrayList<Character>,
                     private val kingdomlessCountry: ArrayList<Character>,
                     private val mContext: Context) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    class ViewHolder(var cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        var isCollapsed = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_country, parent, false) as CardView
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(position) {
            0 -> holder.cardView.rv_characters_list.adapter = CharactersAdapter(shuCountry, mContext)
            1 -> holder.cardView.rv_characters_list.adapter = CharactersAdapter(wuCountry, mContext)
            2 -> holder.cardView.rv_characters_list.adapter = CharactersAdapter(weiCountry, mContext)
            3 -> holder.cardView.rv_characters_list.adapter = CharactersAdapter(kingdomlessCountry, mContext)
        }

        holder.cardView.rv_characters_list.layoutManager = GridLayoutManager(mContext, 3)
        holder.cardView.rv_characters_list.recycledViewPool = viewPool

        holder.cardView.cv_country_banner.setOnClickListener {
            if (holder.isCollapsed) {
                holder.isCollapsed = false
                holder.cardView.rv_characters_list.visibility = View.VISIBLE
            } else if(!holder.isCollapsed) {
                holder.isCollapsed = true
                holder.cardView.rv_characters_list.visibility = View.GONE
            }
        }

    }

}