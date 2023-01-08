package edu.miu.quizzapp.db

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithAnswers(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "quesId",
        entityColumn = "quesOwnerId"
    )
    val answers: List<Answer>
)
