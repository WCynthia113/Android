package com.wcynthia.calculationtest.Layout


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.wcynthia.calculationtest.R
import com.wcynthia.calculationtest.ViewModel.MyViewModel
import com.wcynthia.calculationtest.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myViewModel = ViewModelProvider(requireActivity(),SavedStateViewModelFactory(requireActivity().application,requireActivity())).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,R.layout.fragment_title, container, false)
        binding.data = myViewModel
        binding.lifecycleOwner=requireActivity()
        binding.button.setOnClickListener {
            val controller = Navigation.findNavController(it)
            controller.navigate(R.id.action_titleFragment_to_questionFragment)
        }
        return binding.root
    }


}
