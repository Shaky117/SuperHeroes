package com.prueba.superheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ListaAdapter (val heroes: List<Heroes>, val listener: OnItemClickListener): RecyclerView.Adapter<ListaViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(hero: Heroes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_heroe, parent, false)
        return ListaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        return holder.bind(heroes[position], listener)
    }
}

class ListaViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    private val foto: CircleImageView = itemView.findViewById(R.id.ivHeroe)
    private val nombre: TextView = itemView.findViewById(R.id.tvNombre)
    private val afiliacion: TextView = itemView.findViewById(R.id.tvAfiliacion)

    fun bind(hero: Heroes, listener: ListaAdapter.OnItemClickListener) {
        Picasso.get().load(hero.image.url).into(foto)
        nombre.text = hero.name
        afiliacion.text = hero.biography.publisher

        itemView.setOnClickListener { listener.onItemClick(hero) }
    }
}

