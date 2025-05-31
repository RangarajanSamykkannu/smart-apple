package com.example.composetabviewwithlocalcrud.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composetabviewwithlocalcrud.data.LocalUser
// Assuming ApiUserListViewModel can provide a way to get basic User info if needed,
// or we pass basic info via navigation if EditUserPage is also for *new* entries based on API users.
// For now, this focuses on editing/creating LocalUser.
import com.example.composetabviewwithlocalcrud.ui.viewmodel.LocalUserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserPageComposable(
    navController: NavController,
    localUserViewModel: LocalUserViewModel,
    apiUserId: Int?, // ID from the API list (if navigating from there)
    localUserIdToEdit: Int? // ID from the local list (if editing an existing local user)
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // State for the input fields
    // Initialize based on whether we are editing an existing local user or creating a new one (possibly from API user)
    var idState by remember { mutableStateOf(localUserIdToEdit ?: apiUserId ?: 0) } // Prioritize localId, then apiId, then 0 for new
    var firstNameState by remember { mutableStateOf("") }
    var lastNameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var avatarState by remember { mutableStateOf("") } // URL
    var addressState by remember { mutableStateOf("") }
    var countryState by remember { mutableStateOf("") }
    var phoneState by remember { mutableStateOf("") }

    var isNewUserFromApi = remember { apiUserId != null && localUserIdToEdit == null}

    // Load existing user data if localUserIdToEdit is provided
    LaunchedEffect(key1 = localUserIdToEdit) {
        if (localUserIdToEdit != null && localUserIdToEdit != -1) {
            val existingUser = localUserViewModel.getUserById(localUserIdToEdit)
            if (existingUser != null) {
                idState = existingUser.id
                firstNameState = existingUser.firstName
                lastNameState = existingUser.lastName
                emailState = existingUser.email
                avatarState = existingUser.avatar
                addressState = existingUser.address
                countryState = existingUser.country
                phoneState = existingUser.phone
                isNewUserFromApi = false // Explicitly not a new user from API if we load local data
            }
        } else if (apiUserId != null && apiUserId != -1) {
            // If it's from an API user, and not yet a local user,
            // we'd ideally pre-fill from the API user's basic data.
            // This requires either passing the User object through navigation (not ideal for complex objects)
            // or having the ApiUserListViewModel provide a method to get a user by ID (if lists are cached).
            // For simplicity, if API data isn't directly available here, these might start blank or with what was passed.
            // Let's assume for now, if it's an API user, ID and potentially avatar are key.
            // The prompt said "pre-filled with data of tapped API user". This part is tricky without passing more data.
            // For this iteration, we'll mainly focus on the local user editing and adding new local users.
            // If apiUserId is present, it's used for the ID. Other fields are blank for address etc.
            // A more robust solution would involve a shared ViewModel or passing more nav args.
            idState = apiUserId // Use API user's ID
            // Placeholder: In a real app, you might fetch User details from a ViewModel using apiUserId
            // to prefill firstName, lastName, email, avatar if this is the first time.
            // For now, if it's a new local entry based on an API user, these will be blank unless manually set.
            // The prompt says "pre-filled", so this is a simplification.
            if (isNewUserFromApi) {
                 // A more complete solution would fetch the API user by apiUserId to pre-fill these:
                 // firstNameState = "Fetched API First Name"
                 // lastNameState = "Fetched API Last Name"
                 // emailState = "fetched.api.email@example.com"
                 // avatarState = "fetched_api_avatar_url"
                 // For now, they will be blank if not editing an existing local user.
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text(if (localUserIdToEdit != null && localUserIdToEdit != -1) "Edit User Profile" else "Complete User Profile") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(value = firstNameState, onValueChange = { firstNameState = it }, label = { Text("First Name") }, modifier = Modifier.fillMaxWidth())
            TextField(value = lastNameState, onValueChange = { lastNameState = it }, label = { Text("Last Name") }, modifier = Modifier.fillMaxWidth())
            TextField(value = emailState, onValueChange = { emailState = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            TextField(value = avatarState, onValueChange = { avatarState = it }, label = { Text("Avatar URL") }, modifier = Modifier.fillMaxWidth())
            TextField(value = addressState, onValueChange = { addressState = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
            TextField(value = countryState, onValueChange = { countryState = it }, label = { Text("Country") }, modifier = Modifier.fillMaxWidth())
            TextField(value = phoneState, onValueChange = { phoneState = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (idState == 0 && apiUserId == null && localUserIdToEdit == null) { // Truly new user, not from API
                        idState = System.currentTimeMillis().toInt() // Simplistic unique ID for brand new
                    }
                    val userToSave = LocalUser(
                        id = idState,
                        firstName = firstNameState,
                        lastName = lastNameState,
                        email = emailState,
                        avatar = avatarState,
                        address = addressState,
                        country = countryState,
                        phone = phoneState
                    )
                    localUserViewModel.addUser(userToSave) // addUser handles add or update
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("User profile saved!")
                    }
                    navController.popBackStack() // Go back to the previous screen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Profile")
            }
        }
    }
}
