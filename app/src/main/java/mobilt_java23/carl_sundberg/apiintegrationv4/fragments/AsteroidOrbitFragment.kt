package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import mobilt_java23.carl_sundberg.apiintegrationv4.R

class AsteroidOrbitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_asteroid_orbit, container, false)

        // Hämta asteroidens JPL-länk från argumenten
        val asteroidJplUrl = arguments?.getString("nasa_jpl_url")

        // Hämta WebView och ladda JPL URL
        val webView: WebView = view.findViewById(R.id.orbitWebView)
        webView.webViewClient = WebViewClient()
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.javaScriptEnabled = true
        asteroidJplUrl?.let {
            webView.loadUrl(it)
        }
        return view
    }
}
