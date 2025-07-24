package com.lifeofgorakh.gfocus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.lifeofgorakh.gfocus.ui.theme.utils.AppListHelper

class MainActivity : AppCompatActivity() {

    private lateinit var selectFocusAppsButton: Button
    private lateinit var startFocusButton: Button
    private lateinit var exitFocusButton: Button
    private lateinit var statusText: TextView
    private lateinit var allowedAppsContainer: LinearLayout

    private val PREFS_NAME = "GFocusPrefs"
    private val KEY_FOCUS_ACTIVE = "focus_active"
    private val KEY_ALLOWED_APPS = "allowed_apps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectFocusAppsButton = findViewById(R.id.selectFocusAppsButton)
        startFocusButton = findViewById(R.id.startFocusButton)
        exitFocusButton = findViewById(R.id.exitFocusButton)
        statusText = findViewById(R.id.statusText)
        allowedAppsContainer = findViewById(R.id.allowedAppsContainer)

        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFocusModeActive = sharedPrefs.getBoolean(KEY_FOCUS_ACTIVE, false)

        updateUI(isFocusModeActive)

        selectFocusAppsButton.setOnClickListener {
            val intent = Intent(this, FocusAppSelectionActivity::class.java)
            startActivity(intent)
        }

        startFocusButton.setOnClickListener {
            requestDefaultLauncher()
        }

        exitFocusButton.setOnClickListener {
            sharedPrefs.edit {
                putBoolean(KEY_FOCUS_ACTIVE, false)
            }
            showDefaultLauncherChooser()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // No-op to block back gesture
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFocusModeActive = sharedPrefs.getBoolean(KEY_FOCUS_ACTIVE, false)
        updateUI(isFocusModeActive)
    }

    private fun updateUI(isFocusModeActive: Boolean) {
        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val allowedApps = sharedPrefs.getStringSet(KEY_ALLOWED_APPS, emptySet()) ?: emptySet()

        if (isFocusModeActive) {
            selectFocusAppsButton.visibility = View.GONE
            startFocusButton.visibility = View.GONE
            exitFocusButton.visibility = View.VISIBLE
            statusText.text = "Focus Mode is ACTIVE"
        } else {
            selectFocusAppsButton.visibility = View.VISIBLE
            startFocusButton.visibility = View.VISIBLE
            exitFocusButton.visibility = View.GONE
            statusText.text = "Select your focus apps and start Focus Mode"
        }

        allowedAppsContainer.removeAllViews()
        if (allowedApps.isNotEmpty()) {
            for (pkg in allowedApps) {
                val appName = AppListHelper.getAppName(this, pkg)
                val textView = TextView(this)
                textView.text = appName
                allowedAppsContainer.addView(textView)
            }
        }
    }

    private fun requestDefaultLauncher() {
        val intent = Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS)
        startActivity(intent)

        // After user selects GFocus as launcher
        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit {
            putBoolean(KEY_FOCUS_ACTIVE, true)
        }
    }

    private fun showDefaultLauncherChooser() {
        val intent = Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS)
        startActivity(intent)
    }
}
