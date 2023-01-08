package edu.miu.quizzapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Question::class, Answer::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun getQuestionDao() : QuestionDao // Need this function to get the Dao for the Entity

    // Build RoomDB
    companion object {
        @Volatile private var instance : QuizDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuizDatabase::class.java,
            "quizdatabase"
        ).createFromAsset("database/quiz.db").fallbackToDestructiveMigration().build()
    }
}