package com.wcynthia.words

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcynthia.words.viewModel.WordViewModel
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

    private lateinit var adapterCard:MyAdapter
    private lateinit var adapterNormal:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val wordViewModel = ViewModelProvider(requireActivity()).get(WordViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapterNormal = MyAdapter(false,wordViewModel)
        adapterCard = MyAdapter(true,wordViewModel)
        recyclerView.adapter = adapterNormal
        wordViewModel.getAllWordsLive().observe(requireActivity(), Observer { list ->
            if (adapterNormal.itemCount != list.size||adapterNormal.itemCount == 0){
                adapterCard.setData(list)
                adapterNormal.setData(list)
                adapterCard.notifyDataSetChanged()
                adapterNormal.notifyDataSetChanged()
            }
        })
        floatingActionButton.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_wordsFragment_to_addFragment)
        }
    }

    override fun onResume() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken,0)
        super.onResume()
    }
    companion object {
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