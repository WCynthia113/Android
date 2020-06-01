package com.wcynthia.roombasic

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wcynthia.roombasic.entity.Word
import kotlinx.android.synthetic.main.cell_normal.view.*

class MyAdapter(private val useCardView: Boolean) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var allWords: MutableList<Word> = arrayListOf()

    fun setData(allWords: MutableList<Word>) {
        this.allWords = allWords
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val resource = if(useCardView) R.layout.cell_card else R.layout.cell_normal
        val itemView = layoutInflater.inflate(resource, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindView(allWords[position],position)
    }

    override fun getItemCount(): Int {
        return allWords.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(word: Word,position: Int) {
            itemView.tv_number.text = (position+1).toString()
            itemView.tv_english.text = word.word
            itemView.tv_chinese.text = word.meaning
            itemView.setOnClickListener {
                val uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=${word.word}")
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                itemView.context.startActivity(intent)
            }
        }
    }
}