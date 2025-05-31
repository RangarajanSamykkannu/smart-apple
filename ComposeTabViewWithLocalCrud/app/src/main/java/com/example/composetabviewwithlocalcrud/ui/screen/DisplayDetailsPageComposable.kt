package com.example.composetabviewwithlocalcrud.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composetabviewwithlocalcrud.data.LocalUser
import com.example.composetabviewwithlocalcrud.ui.viewmodel.LocalUserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayDetailsPageComposable(
    navController: NavController,
    localUserViewModel: LocalUserViewModel,
    localUserId: Int
) {
    var userToShow by remember { mutableStateOf<LocalUser?>(null) }

    LaunchedEffect(key1 = localUserId) {
        userToShow = localUserViewModel.getUserById(localUserId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
            if (userToShow == null) {
                Text("User not found or loading...", modifier = Modifier.align(Alignment.Center))
            } else {
                userToShow?.let { user ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(user.avatar)
                                .crossfade(true)
                                .error(android.R.drawable.sym_def_app_icon) // Placeholder for error
                                .placeholder(android.R.drawable.sym_def_app_icon) // Placeholder for loading
                                .build(),
                            contentDescription = "${user.firstName} ${user.lastName} avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DetailItem(label = "ID:", value = user.id.toString())
                        DetailItem(label = "First Name:", value = user.firstName)
                        DetailItem(label = "Last Name:", value = user.lastName)
                        DetailItem(label = "Email:", value = user.email)
                        DetailItem(label = "Address:", value = user.address)
                        DetailItem(label = "Country:", value = user.country)
                        DetailItem(label = "Phone:", value = user.phone)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.width(100.dp) // Fixed width for labels
        )
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
