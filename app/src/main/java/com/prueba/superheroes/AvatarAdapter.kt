package com.prueba.superheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AvatarAdapter (val heroes: List<Heroes>, val listener: OnItemClickListener): RecyclerView.Adapter<AvatarViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(hero: Heroes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return AvatarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        return holder.bind(heroes[position], listener)
    }
}

class AvatarViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    private val foto: CircleImageView = itemView.findViewById(R.id.ivHeroe)
    private val nombre: TextView = itemView.findViewById(R.id.tvNombre)

    fun bind(hero: Heroes, listener: AvatarAdapter.OnItemClickListener) {
        Picasso.get().load(hero.image.url).into(foto)
        nombre.text = hero.name

        itemView.setOnClickListener { listener.onItemClick(hero) }
    }
}

