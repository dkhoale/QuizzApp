package edu.miu.quizzapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.miu.quizzapp.db.QuestionWithAnswers

class QuizViewModel : ViewModel(){
    var currentQuestionIndex = MutableLiveData<Int>()

    init {
        currentQuestionIndex.value = 0
    }

    fun updateQuestionIndex(){
        currentQuestionIndex.value = (currentQuestionIndex.value)?.plus(1)
    }

}