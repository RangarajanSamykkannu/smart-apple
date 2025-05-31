package com.example.composetabviewwithlocalcrud.ui.navigation

sealed class Screen(val route: String, val title: String) {
    object ApiTab1 : Screen("api_tab1", "API Tab 1")
    object ApiTab2 : Screen("api_tab2", "API Tab 2")
    object ApiTab3 : Screen("api_tab3", "API Tab 3")
    object ApiTab4 : Screen("api_tab4", "API Tab 4")
    object LocalTab5 : Screen("local_tab5", "Local Data")

    object EditUser : Screen("edit_user/{userId}?localUserId={localUserId}", "Edit User") {
        fun createRoute(userId: Int?, localUserId: Int?): String {
            return "edit_user/${userId ?: -1}?localUserId=${localUserId ?: -1}" // -1 or other sentinel for null
        }
    }
    object DisplayDetails : Screen("display_details/{localUserId}", "User Details") {
        fun createRoute(localUserId: Int): String = "display_details/$localUserId"
    }
}

val bottomNavScreens = listOf(
    Screen.ApiTab1,
    Screen.ApiTab2,
    Screen.ApiTab3,
    Screen.ApiTab4,
    Screen.LocalTab5,
)
