package com.choco_tyranno.masklocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choco_tyranno.masklocation.model.Store

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView = findViewById(R.id.store_recycler_view)
        val storeAdapter = StoreAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL, false)
            adapter = storeAdapter
        }

        val items = arrayListOf<Store>()
        items.add(
            Store("111","111","111",37.11,110.11,"약국1"
        ,"plenty", "333","333"))
        items.add(Store("111","111","111",37.11,110.11,"약국2"
        ,"plenty", "333","333"))
        items.add(Store("111","111","111",37.11,110.11,"약국3"
        ,"plenty", "333","333"))
        storeAdapter.updateItems(items)
    }
}