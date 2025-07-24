package com.lifeofgorakh.gfocus.utils

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.lifeofgorakh.gfocus.R

data class AppInfo(val name: String, val packageName: String, val icon: Drawable)

class WhitelistedAppAdapter(
    private val apps: List<AppInfo>,
    private val selectedApps: MutableSet<String>
) : RecyclerView.Adapter<WhitelistedAppAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.app_icon)
        val name: TextView = view.findViewById(R.id.app_name)
        val checkbox: CheckBox = view.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = apps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.name.text = app.name
        holder.icon.setImageDrawable(app.icon)
        holder.checkbox.isChecked = selectedApps.contains(app.packageName)

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedApps.add(app.packageName)
            else selectedApps.remove(app.packageName)
        }
    }

    fun getSelectedPackageNames(): Set<String> = selectedApps
}
