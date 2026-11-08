package com.griffith.smoothdrive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val store = remember { SettingsStore(context) }
    var settings by remember { mutableStateOf(store.load()) }
    var saved by remember { mutableStateOf(false) }
    val emailValid = settings.ownerEmail.isBlank() ||
            android.util.Patterns.EMAIL_ADDRESS.matcher(settings.ownerEmail).matches()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = settings.businessName,
            onValueChange = {
                if (it.length <= 40) {
                    settings = settings.copy(businessName = it)
                    saved = false
                }
            },
            label = { Text("Business name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = settings.ownerEmail,
            onValueChange = {
                settings = settings.copy(ownerEmail = it)
                saved = false
            },
            label = { Text("Owner email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Use miles instead of kilometres")
            Switch(
                checked = settings.useMiles,
                onCheckedChange = {
                    settings = settings.copy(useMiles = it)
                    saved = false
                }
            )
        }

        Column {
            Text("Harsh event threshold: ${"%.1f".format(settings.sensitivity)} m/s²")
            Slider(
                value = settings.sensitivity,
                onValueChange = {
                    settings = settings.copy(sensitivity = it)
                    saved = false
                },
                valueRange = 1.0f..2.5f
            )
            Text(
                "Lower values detect more events",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(enabled = emailValid, onClick = {
            store.save(settings)
            saved = true
        }) { Text("Save") }

        if (saved) {
            Text("Settings saved")
        }
    }
}