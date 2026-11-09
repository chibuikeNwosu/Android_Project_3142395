package com.griffith.smoothdrive

import android.content.Context


/*
These are the four settings the user can change, with their default values
*/
data class AppSettings(
    val businessName: String = "",
    val ownerEmail: String = "",
    val useMiles: Boolean = false,
    val sensitivity: Float = 1.5f
)

/*
This reads and writes the settings using sharedPreferences, a small key-value file
private to this app that survives restarts, because The screens do not need to know how the data is stored
*/
class SettingsStore(context: Context) {
    private val prefs =
        context.getSharedPreferences("smoothdrive_settings", Context.MODE_PRIVATE)

    /*
This reads each value, falling back to the default if nothing has been saved yet
*/
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