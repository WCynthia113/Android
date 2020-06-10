package com.wcynthia.words

import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wcynthia.words.entity.Word
import com.wcynthia.words.viewModel.WordViewModel
import kotlinx.android.synthetic.main.cell_normal.view.tv_chinese
import kotlinx.android.synthetic.main.cell_normal.view.tv_english
import kotlinx.android.synthetic.main.cell_normal.view.tv_number
import kotlinx.android.synthetic.main.cell_normal_2.view.*

class MyAdapter(private val useCardView: Boolean, private val wordViewModel: WordViewModel) :
    ListAdapter<Word, MyAdapter.MyViewHolder>(object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return (oldItem.word == newItem.word && oldItem.meaning == newItem.meaning && oldItem.chineseInvisible == newItem.chineseInvisible)
        }

    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val resource = if (useCardView) R.layout.cell_card_2 else R.layout.cell_normal_2
        val itemView = layoutInflater.inflate(resource, parent, false)
        val holder = MyViewHolder(itemView)
        holder.setHolderListener(wordViewModel)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = getItem(position)
        holder.itemView.setTag(R.id.word_for_view_holder, word)
        holder.bindView(word, position)
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(word: Word, position: Int) {
            itemView.tv_number.text = (position + 1).toString()
            itemView.tv_english.text = word.word
            itemView.tv_chinese.text = word.meaning
        }

        fun setHolderListener(wordViewModel: WordViewModel) {
            itemView.chinese_invisiable.setOnCheckedChangeListener { _, isChecked ->
                val word = itemView.getTag(R.id.word_for_view_holder) as Word
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
                val uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=${itemView.tv_english}")
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                itemView.context.startActivity(intent)
            }
        }
    }
}