package com.prueba.superheroes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    lateinit var heroes : ArrayList<Heroes>

    lateinit var rvAvatar : RecyclerView
    lateinit var rvLista : RecyclerView
    lateinit var searchView: SearchView
    lateinit var progressBar: ProgressBar

    lateinit var layoutMan: LinearLayoutManager
    lateinit var avatarAdapter : AvatarAdapter
    lateinit var listAdapter: ListaAdapter

    var buscando: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAvatar = findViewById(R.id.rvAvatar)
        rvLista = findViewById(R.id.rvHeroes)
        searchView = findViewById(R.id.svBuscar)
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        progressBar = findViewById(R.id.progressBar)

        callRetrofitGetHeroes()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val buscar = searchView.query.toString()
                buscando = true
                rvLista.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
                callBuscarHeroes(buscar)

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        btnBuscar.setOnClickListener(View.OnClickListener {
            val buscar = searchView.query.toString()
            buscando= true
            rvLista.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            callBuscarHeroes(buscar);
        })
    }

    private fun callBuscarHeroes(buscar : String) {
        var buscar = buscar
        val okHttpClient = UnsafeOkHttpClient.unsafeOkHttpClient;
        val retrofit = Retrofit.Builder()
            .baseUrl(this.getString(R.string.base_url))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetDataService::class.java)
        if(buscar.equals("")){
            buscar = "a"
        }

        val call = service.getSearchHero(buscar)
        call.enqueue(object : Callback<Hero> {
            override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
                if (response.code() == 200) {
                    heroes = response.body()!!.results

                    setUpRV()
                }
            }

            override fun onFailure(call: Call<Hero>, t: Throwable) {
                Toast.makeText(applicationContext, "Algo salio mal", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun callRetrofitGetHeroes() {

        val okHttpClient = UnsafeOkHttpClient.unsafeOkHttpClient;
        val retrofit = Retrofit.Builder()
            .baseUrl(this.getString(R.string.base_url))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetDataService::class.java)
        val call = service.getHeroes()
        call.enqueue(object : Callback<Hero> {
            override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
                if (response.code() == 200) {
                    heroes = response.body()!!.results

                    setUpRV()
                }
            }

            override fun onFailure(call: Call<Hero>, t: Throwable) {
                Toast.makeText(applicationContext, "Algo salio mal", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setUpRV() {

        if (!buscando) {
            layoutMan =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            avatarAdapter = AvatarAdapter(heroes, object : AvatarAdapter.OnItemClickListener {
                override fun onItemClick(hero: Heroes) {
                    val intent = Intent(this@MainActivity, DetallesActivity::class.java)
                    intent.putExtra("hero", hero)
                    startActivity(intent)
                }
            })
            rvAvatar.apply {
                layoutManager = layoutMan
                adapter = avatarAdapter
            }
        }


        val layoutList = LinearLayoutManager(this@MainActivity)
        listAdapter = ListaAdapter(heroes, object : ListaAdapter.OnItemClickListener {
            override fun onItemClick(hero: Heroes) {
                val intent = Intent(this@MainActivity, DetallesActivity::class.java)
                intent.putExtra("hero", hero)
                startActivity(intent)
            }
        })


        rvLista.apply {
            layoutManager = layoutList
            adapter = listAdapter
        }

        buscando = false
        progressBar.visibility = View.INVISIBLE
        rvLista.visibility = View.VISIBLE
    }
}