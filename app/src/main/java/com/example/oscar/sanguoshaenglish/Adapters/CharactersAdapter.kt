package com.example.oscar.sanguoshaenglish.Adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.android.databinding.library.baseAdapters.BR
import com.bumptech.glide.Glide
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.entities.Character
import com.example.oscar.sanguoshaenglish.R
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

class CharactersAdapter(private val cards: ArrayList<Character>, private val mContext: Context) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        val binding: ViewDataBinding = DataBindingUtil.bind(imageView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.imageview_cards, parent, false) as ImageView
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.imageView.setImageResource(cards[position].charImage)
//        holder.binding.setVariable(BR.character, cards[position])
//        holder.binding.executePendingBindings()

        Glide.with(holder.itemView.context).load(cards[position].charImage).into(holder.imageView)

        holder.imageView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("CHARACTER_IMAGE", cards[position].charImage)
            bundle.putString("CHARACTER_ABILITY", cards[position].characterData.toString())
            (mContext as MainMenuActivity).showCharacterCardFragment(bundle)
        }
    }

    override fun getItemCount() = cards.size
}