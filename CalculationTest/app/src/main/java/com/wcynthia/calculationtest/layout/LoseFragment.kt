package com.wcynthia.calculationtest.layout


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
import com.wcynthia.calculationtest.databinding.FragmentLoseBinding
import com.wcynthia.calculationtest.viewModel.MyViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myViewModel = ViewModelProvider(requireActivity(),
            SavedStateViewModelFactory(requireActivity().application,requireActivity())
        ).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentLoseBinding>(inflater,R.layout.fragment_lose, container, false)
        binding.data = myViewModel
        binding.lifecycleOwner=requireActivity()
        binding.btBack.setOnClickListener {
            myViewModel.getCurrentScore().value = 0
            val controller = Navigation.findNavController(it)
            controller.navigate(R.id.action_loseFragment_to_titleFragment)
        }
        return binding.root
    }


}
