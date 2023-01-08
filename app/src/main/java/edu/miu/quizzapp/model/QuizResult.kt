package edu.miu.quizzapp.model

import android.os.Parcelable
import edu.miu.quizzapp.db.Answer
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizResult(
    val question: String,
    val correctAnswer: String,
    val userAnswer: String
): java.io.Serializable, Parcelable
