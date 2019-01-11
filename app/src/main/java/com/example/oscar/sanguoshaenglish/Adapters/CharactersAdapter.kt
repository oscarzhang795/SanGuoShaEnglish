package com.example.oscar.sanguoshaenglish.Adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.R
import com.example.oscar.sanguoshaenglish.entities.CharacterData

class CharactersAdapter(private val cards: List<CharacterData>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        val binding: ViewDataBinding = DataBindingUtil.bind(imageView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.imageview_cards, parent, false) as ImageView
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageId = holder.itemView.context.resources.getIdentifier(cards[position].image_name, "drawable",  holder.itemView.context!!.packageName)
        Glide.with(holder.itemView.context).load(imageId).into(holder.imageView)

        holder.imageView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("CHARACTER_IMAGE", imageId)
            bundle.putString("CHARACTER_ABILITY", cards[position].toString())
            (holder.itemView.context as MainMenuActivity).showCharacterCardFragment(bundle)
        }
    }

    override fun getItemCount() = cards.size
}