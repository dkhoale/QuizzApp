package edu.miu.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.miu.quizzapp.databinding.FragmentHomeBinding
import edu.miu.quizzapp.model.QuizViewModel

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainViewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        mainViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        mainViewModel.resetQuizResults()

        binding.apply {
            btnNext.setOnClickListener {
                val directions =
                    HomeFragmentDirections.actionHomeFragmentToQuizFragment()
                findNavController().navigate(directions)
            }
        }

        return binding.root
    }

}