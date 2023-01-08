package edu.miu.quizzapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.quizzapp.databinding.ResultLayoutBinding
import edu.miu.quizzapp.model.QuizResult

class ResultsAdapter(private val quizResults: List<QuizResult>) : RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {
    private lateinit var binding: ResultLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsAdapter.ResultViewHolder {
            binding = ResultLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ResultViewHolder(binding)
    }

    override fun getItemCount() =quizResults.size

    override fun onBindViewHolder(holder: ResultsAdapter.ResultViewHolder, position: Int) {
        holder.binding.textViewTitle.text = quizResults[position].question
        holder.binding.textViewCorrectAnswer.text = quizResults[position].correctAnswer
        holder.binding.textViewUserAnswer.text = quizResults[position].userAnswer
    }
    inner class ResultViewHolder(val binding: ResultLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}