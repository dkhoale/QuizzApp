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
    private var checkedAnswer: String = ""
    var checkedId: Int? = null
    private var MAX_QUESTIONS_NUMBER: Int = 15

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
            rgQuizAnswerGrp.setOnCheckedChangeListener { group, id ->
                checkedId = id
                val radioBtn = group.findViewById<RadioButton>(id)
                if(radioBtn?.text != null){
                    checkedAnswer = radioBtn.text.toString()
                }
            }

            btnQuizNext.setOnClickListener {
                updateQuizResult()
                rgQuizAnswerGrp.clearCheck()
                if(mainViewModel.currentQuestionIndex.value!! < MAX_QUESTIONS_NUMBER - 1){
                    mainViewModel.updateQuestionIndex()
                }else {
                    val action = QuizFragmentDirections.actionQuizFragmentToSummaryFragment()
                    action.setQuizResults(mainViewModel.quizResults.toTypedArray())
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
        val checkedAnswer = checkedId?.let { binding.rgQuizAnswerGrp.findViewById<RadioButton>(it)?.text?.toString() }
        val quizResult = QuizResult(questions[currentIndex].question.content,
        questions[currentIndex].answers.findLast {
            it.isCorrectAnswer
        }!!.title,
        checkedAnswer ?: "")
        mainViewModel.addQuizResult(quizResult)
    }

    private fun loadQuiz(){
        if(questions.isNotEmpty()){
            val currentIndex = mainViewModel.currentQuestionIndex.value?.toInt()!!
            questions[currentIndex].question.content.also { binding.tvQuizQuestion.text = "${currentIndex + 1}. $it" }
            questions[currentIndex].answers[0].title.also { binding.radioButton.text = it }
            questions[currentIndex].answers[1].title.also { binding.radioButton2.text = it }
            questions[currentIndex].answers[2].title.also { binding.radioButton3.text = it }
        }
    }

}