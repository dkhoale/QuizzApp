package edu.miu.quizzapp.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface QuestionDao {

    @Transaction
    @Query("SELECT * FROM QUESTION")
    suspend fun getAllQuestionsWithAnswers(): List<QuestionWithAnswers>
}