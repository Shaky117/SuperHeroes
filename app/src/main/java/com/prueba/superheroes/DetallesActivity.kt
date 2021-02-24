package com.prueba.superheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Retrofit
import java.util.concurrent.TimeoutException

class DetallesActivity : AppCompatActivity() {

    lateinit var hero: Heroes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val back = findViewById<ImageView>(R.id.btnBack)

        val bundle = intent.extras

        hero = bundle!!.getSerializable("hero") as Heroes

        setUpViews()

        back.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                finish()
            }

        })
    }

    private fun setUpViews() {

        val nombre = findViewById<TextView>(R.id.tvNombre)
        val imagen = findViewById<CircleImageView>(R.id.ivHeroe)
        val alias = findViewById<TextView>(R.id.tvAliases)
        val publisher = findViewById<TextView>(R.id.tvPublisher)
        val intelligence = findViewById<TextView>(R.id.tvIntelligence)
        val speed = findViewById<TextView>(R.id.tvSpeed)
        val combat = findViewById<TextView>(R.id.tvCombat)
        val strength = findViewById<TextView>(R.id.tvStrength)
        val durability = findViewById<TextView>(R.id.tvDurability)
        val power = findViewById<TextView>(R.id.tvPower)

        var aliases = ""
        for (i in hero.biography.aliases){
            aliases += i + "\n"
        }

        Picasso.get().load(hero.image.url).into(imagen)
        nombre.text = hero.name
        publisher.text = hero.biography.publisher
        alias.text = aliases
        intelligence.text = hero.powerstats.intelligence
        speed.text = hero.powerstats.speed
        combat.text = hero.powerstats.combat
        strength.text = hero.powerstats.strength
        durability.text = hero.powerstats.durability
        power.text = hero.powerstats.power
    }
}