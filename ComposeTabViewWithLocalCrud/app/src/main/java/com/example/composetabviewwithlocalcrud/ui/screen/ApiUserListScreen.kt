package com.example.composetabviewwithlocalcrud.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composetabviewwithlocalcrud.data.User
import com.example.composetabviewwithlocalcrud.ui.navigation.Screen
import com.example.composetabviewwithlocalcrud.ui.viewmodel.ApiUserListViewModel
// ApiUsersUiState is in the same package as ApiUserListViewModel, so not explicitly imported here if not used directly by this file.
// However, it is good practice to import it if it were in a different file or for clarity.
// import com.example.composetabviewwithlocalcrud.ui.viewmodel.ApiUsersUiState // Assuming it's used by the ViewModel

@Composable
fun ApiUserListScreen(
    navController: NavController,
    pageToLoad: Int,
    apiUserListViewModel: ApiUserListViewModel = viewModel()
) {
    val uiState by apiUserListViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = pageToLoad) {
        apiUserListViewModel.loadUsers(pageToLoad)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.error != null -> {
                Text(
                    text = "Error: ${uiState.error}",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
            uiState.users.isNotEmpty() -> {
                UserList(
                    users = uiState.users,
                    onUserClick = { userId ->
                        navController.navigate(Screen.EditUser.createRoute(userId = userId, localUserId = null))
                    }
                )
            }
            else -> {
                 Text(text = "No users found.", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun UserList(users: List<User>, onUserClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users, key = { it.id }) { user ->
            UserRow(user = user, onClick = { onUserClick(user.id) })
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRow(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar)
                    .crossfade(true)
                    .build(),
                contentDescription = "${user.firstName} ${user.lastName} avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "${user.firstName} ${user.lastName}", style = MaterialTheme.typography.titleMedium)
                Text(text = user.email, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
