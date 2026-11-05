package com.griffith.smoothdrive

import android.content.Context

data class AppSettings(
    val businessName: String = "",
    val ownerEmail: String = "",
    val useMiles: Boolean = false,
    val sensitivity: Float = 1.5f
)

class SettingsStore(context: Context) {
    private val prefs =
        context.getSharedPreferences("smoothdrive_settings", Context.MODE_PRIVATE)

    fun load(): AppSettings = AppSettings(
        businessName = prefs.getString("business_name", "") ?: "",
        ownerEmail = prefs.getString("owner_email", "") ?: "",
        useMiles = prefs.getBoolean("use_miles", false),
        sensitivity = prefs.getFloat("sensitivity", 1.5f)
    )

    fun save(settings: AppSettings) {
        prefs.edit()
            .putString("business_name", settings.businessName)
            .putString("owner_email", settings.ownerEmail)
            .putBoolean("use_miles", settings.useMiles)
            .putFloat("sensitivity", settings.sensitivity)
            .apply()
    }
}