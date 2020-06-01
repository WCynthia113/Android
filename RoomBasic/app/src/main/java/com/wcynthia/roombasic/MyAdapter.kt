package com.wcynthia.roombasic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wcynthia.roombasic.entity.Word
import kotlinx.android.synthetic.main.cell_normal.view.*

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var allWords: MutableList<Word> = arrayListOf()

    fun setData(allWords: MutableList<Word>) {
        this.allWords = allWords
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(allWords[position])
    }

    override fun getItemCount(): Int {
        return allWords.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(word: Word) {
            itemView.tv_number.text = word.id.toString()
            itemView.tv_english.text = word.word
            itemView.tv_chinese.text = word.meaning
        }
    }
}