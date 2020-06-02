package com.wcynthia.roombasic

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wcynthia.roombasic.entity.Word
import com.wcynthia.roombasic.viewModel.WordViewModel
import kotlinx.android.synthetic.main.cell_normal.view.*
import kotlinx.android.synthetic.main.cell_normal.view.tv_chinese
import kotlinx.android.synthetic.main.cell_normal.view.tv_english
import kotlinx.android.synthetic.main.cell_normal.view.tv_number
import kotlinx.android.synthetic.main.cell_normal_2.view.*

class MyAdapter(private val useCardView: Boolean, private val wordViewModel: WordViewModel) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var allWords: MutableList<Word> = arrayListOf()

    fun setData(allWords: MutableList<Word>) {
        this.allWords = allWords
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val resource = if (useCardView) R.layout.cell_card_2 else R.layout.cell_normal_2
        val itemView = layoutInflater.inflate(resource, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindView(allWords[position], position, wordViewModel)
    }

    override fun getItemCount(): Int {
        return allWords.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(word: Word, position: Int, wordViewModel: WordViewModel) {
            itemView.tv_number.text = (position + 1).toString()
            itemView.tv_english.text = word.word
            itemView.tv_chinese.text = word.meaning
            itemView.chinese_invisiable.setOnCheckedChangeListener(null)
            if (word.chineseInvisible) {
                itemView.tv_chinese.visibility = View.GONE
                itemView.chinese_invisiable.isChecked = true
            } else {
                itemView.tv_chinese.visibility = View.VISIBLE
                itemView.chinese_invisiable.isChecked = false
            }
            itemView.chinese_invisiable.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    itemView.tv_chinese.visibility = View.GONE
                    word.chineseInvisible = true
                } else {
                    itemView.tv_chinese.visibility = View.VISIBLE
                    word.chineseInvisible = false
                }
                wordViewModel.updateWords(word)
            }
            itemView.setOnClickListener {
                val uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=${word.word}")
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                itemView.context.startActivity(intent)
            }
        }
    }
}