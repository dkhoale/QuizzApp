package edu.miu.quizzapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.miu.quizzapp.R
import edu.miu.quizzapp.databinding.FragmentResultDetailBinding
import edu.miu.quizzapp.model.QuizResult
import kotlinx.coroutines.launch


class ResultDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentResultDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val quizResults = ResultDetailFragmentArgs.fromBundle(it).quizResults
            binding.apply {
                recyclerViewResults.adapter = ResultsAdapter(quizResults?.toList() as List<QuizResult>)
            }
        }
    }

}