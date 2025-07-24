package com.lifeofgorakh.gfocus.ui.theme.utils

import android.content.Context
import android.content.pm.PackageManager

object AppListHelper {
    fun getAppName(context: Context, packageName: String): String {
        return try {
            val packageManager = context.packageManager
            val appInfo = packageManager.getApplicationInfo(packageName, 0)
            packageManager.getApplicationLabel(appInfo).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            packageName
        }
    }
}
