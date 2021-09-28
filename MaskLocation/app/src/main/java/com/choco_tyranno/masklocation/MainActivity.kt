package com.choco_tyranno.masklocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choco_tyranno.masklocation.model.Store

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()

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