package com.choco_tyranno.masklocation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.choco_tyranno.masklocation.databinding.ItemStoreBinding
import com.choco_tyranno.masklocation.model.Store


class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding = ItemStoreBinding.bind(itemView)
}

class StoreAdapter : RecyclerView.Adapter<StoreViewHolder?>() {
    private var mItems: List<Store> = ArrayList<Store>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.binding.store = mItems[position]
        holder.binding.distanceTextView.text = "1km"
    }

    override fun getItemCount() = mItems.size

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged()
    }
}

@BindingAdapter("remainStat")
fun setRemainStat(textView: TextView, store: Store) = when (store.remain_stat) {
    "plenty" -> textView.text = "충분"
    "some" -> textView.text = "여유"
    "few" -> textView.text = "매진 임박"
    else -> textView.text = "재고 없음"
}

@BindingAdapter("remainCount")
fun setRemainCount(textView: TextView, store: Store) = when (store.remain_stat) {
    "plenty" -> textView.text = "100개 이상"
    "some" -> textView.text = "30개 이상"
    "few" -> textView.text = "2개 이상"
    else -> textView.text = "1개 이하"
}

@BindingAdapter("remainColor")
fun setRemainColor(textView: TextView, store: Store) = when (store.remain_stat) {
    "plenty" -> textView.setTextColor(Color.GREEN)
    "some" -> textView.setTextColor(Color.YELLOW)
    "few" -> textView.setTextColor(Color.RED)
    else -> textView.setTextColor(Color.GRAY)
}