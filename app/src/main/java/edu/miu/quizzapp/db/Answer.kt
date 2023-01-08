package edu.miu.quizzapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer")
data class Answer(
    val title: String,
    val isCorrectAnswer: Boolean,
    val quesOwnerId: Int
): java.io.Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
