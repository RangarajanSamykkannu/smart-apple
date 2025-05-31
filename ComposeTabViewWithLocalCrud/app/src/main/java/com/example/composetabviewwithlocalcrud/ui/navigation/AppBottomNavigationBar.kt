package com.example.composetabviewwithlocalcrud.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home // Example icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star // Example icon for local data
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavScreens.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(getIconForScreen(index), contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

// Helper to assign icons - can be improved
@Composable
private fun getIconForScreen(index: Int): ImageVector {
    return when (index) {
        0 -> Icons.Filled.Home
        1 -> Icons.Filled.List
        2 -> Icons.Filled.Settings // Placeholder
        3 -> Icons.Filled.Person  // Placeholder
        4 -> Icons.Filled.Star    // For Local Data Tab
        else -> Icons.Filled.Home
    }
}
