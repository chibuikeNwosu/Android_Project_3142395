package com.griffith.smoothdrive

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController




    object Routes {
        const val DASHBOARD = "dashboard"
        const val LIVE_TRIP = "live_trip"
        const val HISTORY = "history"
        const val VEHICLES = "vehicles"
        const val REPORT = "report"
        const val SETTINGS = "settings"
    }

    data class BottomItem(val route: String, val label: String, val icon: ImageVector)

    @Composable
    fun SmoothDriveApp() {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val items = listOf(
            BottomItem(Routes.DASHBOARD, "Dashboard", Icons.Default.Home),
            BottomItem(Routes.HISTORY, "History", Icons.AutoMirrored.Filled.List),
            BottomItem(Routes.VEHICLES, "Vehicles", Icons.Default.Build),
            BottomItem(Routes.SETTINGS, "Settings", Icons.Default.Settings)
        )

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.DASHBOARD,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Routes.DASHBOARD) {
                    DashboardScreen(
                        onStartTrip = { navController.navigate(Routes.LIVE_TRIP) },
                        onOpenReport = { navController.navigate(Routes.REPORT) }
                    )
                }
                composable(Routes.LIVE_TRIP) { PlaceholderScreen("Live Trip") }
                composable(Routes.HISTORY) { PlaceholderScreen("Trip History") }
                composable(Routes.VEHICLES) { PlaceholderScreen("Vehicles") }
                composable(Routes.REPORT) { PlaceholderScreen("Weekly Report") }
                composable(Routes.SETTINGS) { SettingsScreen() }
            }
        }
    }
