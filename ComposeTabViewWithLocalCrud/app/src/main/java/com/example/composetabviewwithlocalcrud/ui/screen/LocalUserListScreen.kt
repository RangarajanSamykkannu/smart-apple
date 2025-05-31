package com.example.composetabviewwithlocalcrud.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composetabviewwithlocalcrud.data.LocalUser
import com.example.composetabviewwithlocalcrud.ui.navigation.Screen
import com.example.composetabviewwithlocalcrud.ui.viewmodel.LocalUserViewModel

@Composable
fun LocalUserListScreen(
    navController: NavController,
    localUserViewModel: LocalUserViewModel // Passed from AppNavigation
) {
    val localUsers by localUserViewModel.localUsers.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (localUsers.isEmpty()) {
            Text(text = "No local users saved yet.", modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(localUsers, key = { it.id }) { user ->
                    LocalUserRow(
                        user = user,
                        onRowClick = {
                            // Navigate to EditUserPage for this local user
                            navController.navigate(Screen.EditUser.createRoute(userId = null, localUserId = user.id))
                        },
                        onDetailsClick = {
                            // Navigate to DisplayDetailsPage for this local user
                            navController.navigate(Screen.DisplayDetails.createRoute(localUserId = user.id))
                        },
                        onDeleteClick = {
                            localUserViewModel.deleteUser(user.id)
                        }
                    )
                    Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalUserRow(
    user: LocalUser,
    onRowClick: () -> Unit,
    onDetailsClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onRowClick), // Row click for editing
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${user.firstName} ${user.lastName}", style = MaterialTheme.typography.titleMedium)
                Text(text = user.email, style = MaterialTheme.typography.bodySmall)
                if (user.address.isNotBlank()) {
                    Text(text = "Address: ${user.address}", style = MaterialTheme.typography.bodySmall)
                }
            }
            Row {
                IconButton(onClick = onDetailsClick) {
                    Icon(Icons.Filled.Info, contentDescription = "View Details")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete User")
                }
            }
        }
    }
}
