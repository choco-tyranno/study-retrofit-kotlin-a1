package com.choco_tyranno.masklocation

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choco_tyranno.masklocation.model.Store
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    companion object{
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView = findViewById(R.id.store_recycler_view)
        val storeAdapter = StoreAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL, false)
            adapter = storeAdapter
        }

        viewModel.apply {
            itemLiveData.observe(this@MainActivity, { items ->
                storeAdapter.updateItems(items)
            })
            loadingLiveData.observe(this@MainActivity, { isLoading ->
                val progressBar : ProgressBar = this@MainActivity.findViewById(R.id.progressBar)
                progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE

            })
        }
    }
}