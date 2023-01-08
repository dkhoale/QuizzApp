package edu.miu.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.miu.quizzapp.databinding.FragmentSummaryBinding
import edu.miu.quizzapp.model.QuizResult


class SummaryFragment : BaseFragment() {
    private lateinit var binding: FragmentSummaryBinding
    private var quizResults: Array<QuizResult>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            quizResults = SummaryFragmentArgs.fromBundle(it).quizResults

            binding.apply {
                btnSummaryResultAnalysis.setOnClickListener {
                    val action = SummaryFragmentDirections.actionSummaryFragmentToResultDetailFragment()
                    action.setQuizResults(quizResults)
                    findNavController().navigate(action)
                }
                btnSummaryTryAgain.setOnClickListener {
                    findNavController().navigate(SummaryFragmentDirections.actionSummaryFragmentToQuizFragment())
                }
            }
        }
    }

}