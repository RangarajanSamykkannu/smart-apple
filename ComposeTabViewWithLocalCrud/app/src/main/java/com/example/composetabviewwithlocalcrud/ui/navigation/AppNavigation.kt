package com.example.composetabviewwithlocalcrud.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// Import the actual screen
import com.example.composetabviewwithlocalcrud.ui.screen.ApiUserListScreen
import com.example.composetabviewwithlocalcrud.ui.screen.LocalUserListScreen // Import LocalUserListScreen
import com.example.composetabviewwithlocalcrud.ui.screen.EditUserPageComposable // Import EditUserPageComposable
import com.example.composetabviewwithlocalcrud.ui.screen.DisplayDetailsPageComposable // Import DisplayDetailsPageComposable
import com.example.composetabviewwithlocalcrud.ui.viewmodel.ApiUserListViewModel
import com.example.composetabviewwithlocalcrud.ui.viewmodel.LocalUserViewModel

// Placeholder Composables for screens - will be replaced by actual screen implementations later
@Composable fun PlaceholderScreen(text: String, modifier: Modifier = Modifier) { Text(text = text, modifier = modifier) }

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // ViewModels that might be shared or passed down.
    // For simplicity, LocalUserViewModel is created here, it might be better scoped in real app.
    val localUserViewModel: LocalUserViewModel = viewModel()

    Scaffold(
        bottomBar = { AppBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.ApiTab1.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Main Tab Screens (Tabs 1-4 for API users)
            composable(Screen.ApiTab1.route) {
                val apiViewModel: ApiUserListViewModel = viewModel(key = "apiTab1VM")
                ApiUserListScreen(
                    navController = navController,
                    pageToLoad = 1,
                    apiUserListViewModel = apiViewModel
                )
            }
            composable(Screen.ApiTab2.route) {
                val apiViewModel: ApiUserListViewModel = viewModel(key = "apiTab2VM")
                ApiUserListScreen(
                    navController = navController,
                    pageToLoad = 2,
                    apiUserListViewModel = apiViewModel
                )
            }
            composable(Screen.ApiTab3.route) {
                val apiViewModel: ApiUserListViewModel = viewModel(key = "apiTab3VM")
                ApiUserListScreen(
                    navController = navController,
                    pageToLoad = 3,
                    apiUserListViewModel = apiViewModel
                )
            }
            composable(Screen.ApiTab4.route) {
                val apiViewModel: ApiUserListViewModel = viewModel(key = "apiTab4VM")
                ApiUserListScreen(
                    navController = navController,
                    pageToLoad = 4,
                    apiUserListViewModel = apiViewModel
                )
            }

            // Tab 5 for Local Users (remains placeholder for now)
            composable(Screen.LocalTab5.route) {
                // LocalUserListScreen(navController, localUserViewModel) // Actual screen later
                PlaceholderScreen(text = "Local Users List (Tab 5)")
            }

            // Edit User Page (Details/Edit Page) - (remains placeholder for now)
            composable(
                route = Screen.EditUser.route,
                arguments = listOf(
                    navArgument("userId") { type = NavType.IntType; defaultValue = -1 },
                    navArgument("localUserId") { type = NavType.IntType; defaultValue = -1 }
                )
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId")
                val localUserId = backStackEntry.arguments?.getInt("localUserId")
                // EditUserPageComposable(navController, localUserViewModel, userId.takeIf { it != -1 }, localUserId.takeIf { it != -1 })
                PlaceholderScreen(text = "Edit User Page (User: $userId, LocalUser: $localUserId)")
            }

            // Display Details Page (Details Page 2) - (remains placeholder for now)
            composable(
                route = Screen.DisplayDetails.route,
                arguments = listOf(navArgument("localUserId") { type = NavType.IntType })
            ) { backStackEntry ->
                val localUserId = backStackEntry.arguments?.getInt("localUserId") ?: -1
                // DisplayDetailsPageComposable(navController, localUserViewModel, localUserId)
                PlaceholderScreen(text = "Display Details Page (LocalUser: $localUserId)")
            }
        }
    }
}
