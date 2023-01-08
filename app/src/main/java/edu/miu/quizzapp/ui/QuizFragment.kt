package edu.miu.quizzapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.miu.quizzapp.R
import edu.miu.quizzapp.databinding.FragmentQuizBinding
import edu.miu.quizzapp.db.QuestionWithAnswers
import edu.miu.quizzapp.db.QuizDatabase
import edu.miu.quizzapp.model.QuizResult
import edu.miu.quizzapp.model.QuizViewModel
import kotlinx.coroutines.launch


class QuizFragment : BaseFragment() {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var mainViewModel: QuizViewModel
    private var questions: List<QuestionWithAnswers> = ArrayList<QuestionWithAnswers>()
    private var quizResults: ArrayList<QuizResult> = ArrayList()
    private var checkedAnswer: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater,container,false)
        mainViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentQuestionIndex.observe(viewLifecycleOwner){
            loadQuiz()
        }

        binding.apply {
            rgQuizAnswerGrp.setOnCheckedChangeListener { group, checkedId ->
                checkedAnswer = group.findViewById<RadioButton>(checkedId)?.text?.toString() ?: ""
            }

            btnQuizNext.setOnClickListener {
                updateQuizResult()
                rgQuizAnswerGrp.clearCheck()
                if(mainViewModel.currentQuestionIndex.value!! < 1){
                    mainViewModel.updateQuestionIndex()
                }else {
                    val action = QuizFragmentDirections.actionQuizFragmentToSummaryFragment()
                    action.setQuizResults(quizResults.toTypedArray())
                   findNavController().navigate(action)
                }
            }

            btnQuizHome.setOnClickListener {
                findNavController().navigate(R.id.action_quizFragment_to_homeFragment)
            }
        }


        launch {
            context?.let {
                questions = QuizDatabase(it).getQuestionDao().getAllQuestionsWithAnswers()
                loadQuiz()
            }
        }

    }

    private fun updateQuizResult() {
        val currentIndex = mainViewModel.currentQuestionIndex.value?.toInt()!!
        val quizResult = QuizResult(questions[currentIndex].question.content,
        questions[currentIndex].answers.findLast {
            println(it)
            it.isCorrectAnswer
        }!!.title,
        checkedAnswer ?: "")
        quizResults.add(quizResult)
    }

    private fun loadQuiz(){
        if(questions.isNotEmpty()){
            questions[mainViewModel.currentQuestionIndex.value?.toInt()!!].question.content.also { binding.tvQuizQuestion.text = it }
            questions[mainViewModel.currentQuestionIndex.value?.toInt()!!].answers[0].title.also { binding.radioButton.text = it }
            questions[mainViewModel.currentQuestionIndex.value?.toInt()!!].answers[1].title.also { binding.radioButton2.text = it }
            questions[mainViewModel.currentQuestionIndex.value?.toInt()!!].answers[2].title.also { binding.radioButton3.text = it }
        }
    }

}