package com.lifeofgorakh.gfocus

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lifeofgorakh.gfocus.utils.AppInfo
import com.lifeofgorakh.gfocus.ui.theme.utils.PreferenceHelper
import com.lifeofgorakh.gfocus.utils.WhitelistedAppAdapter

class AppSelectionActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var saveButton: Button
    private lateinit var prefHelper: PreferenceHelper
    private lateinit var adapter: WhitelistedAppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_selection)

        prefHelper = PreferenceHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        saveButton = findViewById(R.id.saveButton)

        val pm = packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
            .map {
                AppInfo(
                    it.loadLabel(pm).toString(),
                    it.packageName,
                    it.loadIcon(pm)
                )
            }

        val savedPackages = prefHelper.getWhitelistedApps().toMutableSet()
        adapter = WhitelistedAppAdapter(apps, savedPackages)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        saveButton.setOnClickListener {
            val selectedPackages = adapter.getSelectedPackageNames()
            prefHelper.saveWhitelistedApps(selectedPackages)
            Toast.makeText(this, "Apps saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
