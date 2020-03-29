package com.wcynthia.calculationtest.Layout


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.wcynthia.calculationtest.R
import com.wcynthia.calculationtest.ViewModel.MyViewModel
import com.wcynthia.calculationtest.databinding.FragmentQuestionBinding

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myViewModel = ViewModelProvider(requireActivity(), SavedStateViewModelFactory(requireActivity().application,requireActivity())
        ).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(inflater,R.layout.fragment_question, container, false)
        binding.data = myViewModel
        binding.lifecycleOwner=requireActivity()
        val stringBuffer = StringBuffer()
        val listener = View.OnClickListener {
            when(it.id){
                R.id.button0->{}
            }
        }
        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        return binding.root
    }


}
