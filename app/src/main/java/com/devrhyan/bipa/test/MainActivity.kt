package com.devrhyan.bipa.test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrhyan.bipa.test.adapter.RecyclerViewAdapter
import com.devrhyan.bipa.test.databinding.ActivityMainBinding
import com.devrhyan.bipa.test.services.api.RetrofitHelper
import com.devrhyan.bipa.test.services.api.iNodes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val Adapter  = RecyclerViewAdapter()

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Adapter Configuration and Implementation for RecyclerView
        binding.nodeView.adapter = Adapter
        binding.nodeView.layoutManager = LinearLayoutManager(this)

        binding.requestAPI.setOnClickListener {
              CoroutineScope(Dispatchers.IO).launch {
                  withContext(Dispatchers.Main) {
                      try {
                          isLoading(true)
                          handleApi()
                      } catch (e: Exception) {
                          Toast.makeText(applicationContext, "Erro ao recuperar Nodes, verifique sua conexão a internet e tente novamente.", Toast.LENGTH_LONG).show()
                          Log.e("Coroutine Error", "Error running coroutine: ${e.message}")
                      } finally {
                          isLoading(false)
                      }
                  }
              }
        }
    }

    override fun onStart() {
        super.onStart()
        showAlertDialog()
    }

    //Asynchronous function for data request
    private suspend fun handleApi() {
        val request = retrofit.create(iNodes::class.java)
        val service = request.getNodes()

        try {
            if(service.isSuccessful) {
                val body = service.body()

                if (body != null) {
                    Adapter.setNodeList(body)
                }
            }
            Log.v("Service Status", "Success when requesting API")
        } catch (e : Exception) {
            Log.e("Service Status", "Error when requesting API\n Details: ${e.message}")
        }
    }

    //Initial dialog for manipulating the app
    private fun showAlertDialog() {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Bem vindo!")
        alertBuilder.setMessage("Este é um App desenvolvido para teste técnico feito por Rhyan Araujo Chaves solicitado pela Bipa. Clique no botão requisitar para obter a lista dos Nodes atualizada.")
        alertBuilder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}
        alertBuilder.show()
    }

    //Loading spinner caller
    private fun isLoading(state : Boolean) {
        binding.progressbar.visibility = if (state) View.VISIBLE else View.GONE
    }

}