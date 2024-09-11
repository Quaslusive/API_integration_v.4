package mobilt_java23.carl_sundberg.apiintegrationv4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import mobilt_java23.carl_sundberg.apiintegrationv4.fragments.AsteroidListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kontrollera om det redan finns ett fragment (vid t.ex. rotation)
        if (savedInstanceState == null) {
            // Ladda första fragmentet (t.ex. AsteroidListFragment)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AsteroidListFragment.newInstance(1)) // Sätter första fragmentet
                .commit()
        }

        // Om du använder Navigation Component
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Setup ActionBar for back navigation with NavController
        setupActionBarWithNavController(navController)
    }

    // Hantera navigationens "bakåt"-knapp
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}
