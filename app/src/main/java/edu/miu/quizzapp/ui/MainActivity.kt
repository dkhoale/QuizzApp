package edu.miu.quizzapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import edu.miu.quizzapp.R
import edu.miu.quizzapp.model.QuizViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mnavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    as NavHostFragment
        mnavController = navHostFragment.navController
        // Code to link the navigation controller to the app bar
        NavigationUI.setupActionBarWithNavController(this,mnavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mnavController.navigateUp()
    }
}