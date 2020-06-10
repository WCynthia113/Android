package com.wcynthia.words

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Filter
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wcynthia.words.entity.Word
import com.wcynthia.words.viewModel.WordViewModel
import kotlinx.android.synthetic.main.cell_normal.view.*
import kotlinx.android.synthetic.main.fragment_words.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapterCard: MyAdapter
    private lateinit var adapterNormal: MyAdapter
    private val wordViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WordViewModel::class.java)
    }
    private lateinit var filteredWords: LiveData<MutableList<Word>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clearData -> {
                val builder = AlertDialog.Builder(requireActivity()).setTitle("清空数据")
                    .setPositiveButton("确定", DialogInterface.OnClickListener { _, _ ->
                        wordViewModel.deleteAllWords()
                    }).setNegativeButton("取消", DialogInterface.OnClickListener { _, _ -> }).show()
            }
            R.id.switchViewType -> {
                val shp =
                    requireActivity().getSharedPreferences(VIEW_TYPE_SHP, Context.MODE_PRIVATE)
                val viewType = shp.getBoolean(IS_USING_CARD_VIEW, false)
                val editor = shp.edit()
                if (viewType) {
                    recyclerView.adapter = adapterNormal
                    editor.putBoolean(IS_USING_CARD_VIEW, false)
                } else {
                    recyclerView.adapter = adapterCard
                    editor.putBoolean(IS_USING_CARD_VIEW, true)
                }
                editor.apply()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.maxWidth = 700
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val pattern = (newText ?: "").trim()
                filteredWords.removeObservers(requireActivity())
                filteredWords = wordViewModel.findWordsWithPattern(pattern)
                filteredWords.observe(viewLifecycleOwner, Observer { list ->
                    if (adapterNormal.itemCount != list.size || adapterNormal.itemCount == 0) {
                        adapterCard.submitList(list)
                        adapterNormal.submitList(list)
                    }

                })
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapterNormal = MyAdapter(false, wordViewModel)
        adapterCard = MyAdapter(true, wordViewModel)
        //ListAdapter是局部刷新所以，插入了新的一条不会回滚到第一条，也不会刷新所有的序号。
        recyclerView.itemAnimator = object :DefaultItemAnimator(){
            override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                super.onAnimationFinished(viewHolder)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager!=null){
                    val firstPosition = linearLayoutManager.findFirstVisibleItemPosition()
                    val lastPosition = linearLayoutManager.findLastVisibleItemPosition()
                    for (i in firstPosition..lastPosition){
                        val holder = recyclerView.findViewHolderForAdapterPosition(i) as MyAdapter.MyViewHolder?
                        if (holder != null){
                            holder.itemView.tv_number.text = (i+1).toString()
                        }
                    }
                }
            }
        }
        val shp =
            requireActivity().getSharedPreferences(VIEW_TYPE_SHP, Context.MODE_PRIVATE)
        val viewType = shp.getBoolean(IS_USING_CARD_VIEW, false)
        if (viewType) {
            recyclerView.adapter = adapterCard
        } else {
            recyclerView.adapter = adapterNormal
        }
        filteredWords = wordViewModel.getAllWordsLive()
        filteredWords.observe(viewLifecycleOwner, Observer { list ->
            if (adapterNormal.itemCount != list.size || adapterNormal.itemCount == 0) {
                adapterCard.submitList(list, Runnable {
                    recyclerView.scrollToPosition(0)
                })
                adapterNormal.submitList(list, Runnable {
                    recyclerView.scrollToPosition(0)
                })
            }

        })
        floatingActionButton.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_wordsFragment_to_addFragment)
        }
    }

    override fun onResume() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        super.onResume()
    }

    companion object {
        const val IS_USING_CARD_VIEW = "is_using_card_view"
        const val VIEW_TYPE_SHP = "view_type_shp"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WordsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WordsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}