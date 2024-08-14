package com.devrhyan.bipa.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.devrhyan.bipa.test.adapter.RecyclerViewAdapter
import com.devrhyan.bipa.test.databinding.ActivityMainBinding
import com.devrhyan.bipa.test.services.api.RetrofitHelper
import com.devrhyan.bipa.test.services.api.iNodes
import com.google.android.material.snackbar.Snackbar
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

        binding.nodeView.adapter = Adapter
        binding.nodeView.layoutManager = LinearLayoutManager(this)

        binding.requestAPI.setOnClickListener {
              CoroutineScope(Dispatchers.IO).launch {
                  withContext(Dispatchers.Main) {
                      isLoading(true)
                      handleApi()
                      isLoading(false)
                  }
              }
        }
    }

    override fun onStart() {
        super.onStart()
        showAlertDialog(binding.main)
    }

    private suspend fun handleApi() {
        val request = retrofit.create(iNodes::class.java)
        val service = request.getNodes()

        if(service.isSuccessful) {
            val body = service.body()

            if (body != null) {
                Adapter.setNodeList(body)
            }

        } else {
            println("Erro ao requisitar API")
        }
    }

    private fun showAlertDialog(view : View) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Bem vindo!")
        alertBuilder.setMessage("Este é um App desenvolvido para teste técnico feito por Rhyan Araujo Chaves solicitado pela Bipa. Clique no botão requisitar para obter a lista dos Nodes atualizada.")
        alertBuilder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}
        alertBuilder.show()
    }

    private fun isLoading(state : Boolean) {
        binding.progressbar.visibility = if (state) View.VISIBLE else View.GONE
    }

}