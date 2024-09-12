package mobilt_java23.carl_sundberg.apiintegrationv4

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import mobilt_java23.carl_sundberg.apiintegrationv4.fragments.AsteroidListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Hämta NavHostFragment och NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // Sätt upp ActionBar med NavController för att hantera bakåtknappen korrekt
        setupActionBarWithNavController(navController)


        // Sätt Toolbar som ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        // Hämta knapparna och sätt OnClickListener
        val showAsteroidsButton: Button = findViewById(R.id.button_show_asteroids)
        val searchByIdButton: Button = findViewById(R.id.button_search_by_id)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AsteroidListFragment())
                .commit()
        }
        // Lägg till OnClickListener för knappar
        showAsteroidsButton.setOnClickListener {
            navController.navigate(R.id.action_to_asteroidDetailFragment)
        }
//
//        searchByIdButton.setOnClickListener {
//            navController.navigate(R.id.action_to_searchByIdFragment)
//        }

        searchByIdButton.setOnClickListener {
            // Navigera till dagens asteroider
            findNavController(R.id.fragment_container).navigate(R.id.action_to_asteroidTodayFragment)
        }


        // Setup ActionBar for back navigation with NavController
        setupActionBarWithNavController(navController)
    }

    // Om du använder Navigation Component och hanterar "bakåt"-knappen med ActionBar
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}
