package com.multimedia.kotlin_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.ActivityAgentSearchListBinding
import com.multimedia.kotlin_app.modules.NetworkModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@AndroidEntryPoint
class AgentSearchListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentSearchListBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentSearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = NetworkModule.provideRetrofit()
        initUI()
    }

    private fun initUI() {

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByAgentName(query.orEmpty()) //me devuelves un agent existente o todos los que haya (en caso null)

                return false //SIEMPRE se retorna en false
            }

            //esta fun se llama cada vez que escribamos
            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun searchByAgentName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ValorantApiClient::class.java).getAgentName(query)

            if (myResponse.isSuccessful) {
                Log.i("lorena", "funciona :)")

            } else {
                Log.i("lorena", "NO funciona :)")
            }
        }
    }

}