package mobilt_java23.carl_sundberg.apiintegrationv4.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import mobilt_java23.carl_sundberg.apiintegrationv4.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

//val showAsteroidsButton: Button = findViewById(R.id.button_show_asteroids)
  //      val searchByIdButton: Button = findViewById(R.id.button_search_by_id)
        val todayAsteroidButton: Button = findViewById(R.id.todayAsteroidButton)


        todayAsteroidButton.setOnClickListener {

            Log.d("carl", "TodayAstro Klicked why so slow?")
            navController.navigate(R.id.asteroidTodayFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
