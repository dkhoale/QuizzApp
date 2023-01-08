package edu.miu.quizzapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    val content: String
): java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    var quesId:Int = 0
}
