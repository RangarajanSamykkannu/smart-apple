package com.example.composetabviewwithlocalcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.composetabviewwithlocalcrud.ui.theme.ComposeTabViewWithLocalCrudTheme // Assume this will be created or use a default
import com.example.composetabviewwithlocalcrud.ui.navigation.AppNavigation // To be created

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Assuming a theme file will be created, e.g., ui/theme/Theme.kt
            ComposeTabViewWithLocalCrudTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation() // Entry point for navigation
                }
            }
        }
    }
}
