package com.lifeofgorakh.gfocus.ui.theme.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("GFocusPrefs", Context.MODE_PRIVATE)

    fun saveWhitelistedApps(apps: Set<String>) {
        prefs.edit().putStringSet("whitelisted_apps", apps).apply()
    }

    fun getWhitelistedApps(): Set<String> {
        return prefs.getStringSet("whitelisted_apps", emptySet()) ?: emptySet()
    }
}
