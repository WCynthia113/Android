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
import com.wcynthia.calculationtest.viewModel.MyViewModel
import com.wcynthia.calculationtest.databinding.FragmentQuestionBinding

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myViewModel = ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, requireActivity())
        ).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater,
            R.layout.fragment_question,
            container,
            false
        )
        myViewModel.generator()
        binding.data = myViewModel
        binding.lifecycleOwner = requireActivity()
        val stringBuffer = StringBuffer()
        val listener = View.OnClickListener {
            when (it.id) {
                R.id.button0 -> {
                    stringBuffer.append("0")
                }
                R.id.button1 -> {
                    stringBuffer.append("1")
                }
                R.id.button2 -> {
                    stringBuffer.append("2")
                }
                R.id.button3 -> {
                    stringBuffer.append("3")
                }
                R.id.button4 -> {
                    stringBuffer.append("4")
                }
                R.id.button5 -> {
                    stringBuffer.append("5")
                }
                R.id.button6 -> {
                    stringBuffer.append("6")
                }
                R.id.button7 -> {
                    stringBuffer.append("7")
                }
                R.id.button8 -> {
                    stringBuffer.append("8")
                }
                R.id.button9 -> {
                    stringBuffer.append("9")
                }
                R.id.buttonClear -> {
                    stringBuffer.setLength(0)
                }
            }
            if (stringBuffer.isEmpty()) {
                binding.tvAnswer.text = getString(R.string.input_indicator)
            } else {
                binding.tvAnswer.text = stringBuffer.toString()
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
        binding.buttonClear.setOnClickListener(listener)
        binding.buttonSubmit.setOnClickListener {
            if (stringBuffer.toString() == ""){
                myViewModel.answerWrong()
                val controller = Navigation.findNavController(it)
                if (myViewModel.win_flag) {
                    controller.navigate(R.id.action_questionFragment_to_winFragment)
                    myViewModel.win_flag = false
                    myViewModel.save()
                } else {
                    controller.navigate(R.id.action_questionFragment_to_loseFragment)
                }
            }
            if (stringBuffer.toString().toInt() == myViewModel.getAnswer().value) {
                myViewModel.answerCorrect()
                stringBuffer.setLength(0)
                binding.tvAnswer.text = getString(R.string.answer_correct_message)
            } else {
                myViewModel.answerWrong()
                val controller = Navigation.findNavController(it)
                if (myViewModel.win_flag) {
                    controller.navigate(R.id.action_questionFragment_to_winFragment)
                    myViewModel.win_flag = false
                    myViewModel.save()
                } else {
                    controller.navigate(R.id.action_questionFragment_to_loseFragment)
                }
            }
        }
        return binding.root
    }

}
