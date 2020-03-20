package com.wcynthia.pricesearchapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wcynthia.pricesearchapp.R
import com.wcynthia.pricesearchapp.model.PowerTool
import kotlinx.android.synthetic.main.item_search.view.*

class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}

class SearchAdapter(private val powerTools: List<PowerTool>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return powerTools.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.initView(powerTools[position])
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun initView(item: PowerTool) {
            itemView.number_tv.text = item.number.toString()
            itemView.name_tv.text = item.name
            itemView.price_tv.text = item.outPrice.toString()
            itemView.brand_tv.text = item.brand
            itemView.change_tv.setOnClickListener {
                if (itemView.change_tv.text == "Y"){
                    itemView.change_tv.text = "N"
                }else{
                    itemView.change_tv.text = "Y"
                }
            }
        }
    }
}
