package com.lifeofgorakh.gfocus

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FocusAppSelectionActivity : AppCompatActivity() {

    private val PREFS_NAME = "GFocusPrefs"
    private val KEY_ALLOWED_APPS = "allowed_apps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Select Apps for Focus Mode"
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
        }

        val scrollView = ScrollView(this)
        val appContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val allowedApps = sharedPrefs.getStringSet(KEY_ALLOWED_APPS, mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        val pm = packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
            .sortedBy { it.loadLabel(pm).toString() }

        for (app in apps) {
            val checkBox = CheckBox(this)
            checkBox.text = app.loadLabel(pm).toString()
            checkBox.isChecked = allowedApps.contains(app.packageName)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    allowedApps.add(app.packageName)
                } else {
                    allowedApps.remove(app.packageName)
                }
                sharedPrefs.edit().putStringSet(KEY_ALLOWED_APPS, allowedApps).apply()
            }
            appContainer.addView(checkBox)
        }

        val infoText = TextView(this)
        infoText.text = "✅ Select only the apps you want to allow during focus mode"
        layout.addView(infoText)

        scrollView.addView(appContainer)
        layout.addView(scrollView)
        setContentView(layout)
    }
}
