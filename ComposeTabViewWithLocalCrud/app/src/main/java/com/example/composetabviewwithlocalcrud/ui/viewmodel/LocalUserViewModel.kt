package com.example.composetabviewwithlocalcrud.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composetabviewwithlocalcrud.data.LocalUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocalUserViewModel : ViewModel() {

    private val _localUsers = MutableStateFlow<List<LocalUser>>(emptyList())
    val localUsers: StateFlow<List<LocalUser>> = _localUsers.asStateFlow()

    fun addUser(user: LocalUser) {
        _localUsers.update { currentList ->
            // Simple add. For update, check if user with same id exists.
            val existingUser = currentList.find { it.id == user.id }
            if (existingUser != null) {
                // Update existing user
                currentList.map { if (it.id == user.id) user else it }
            } else {
                // Add new user
                currentList + user
            }
        }
    }

    // In a real app, you'd likely want updateUser to be more explicit
    // and possibly handle ID generation for brand new users differently.
    // This addUser function serves as add/update for simplicity here.

    fun deleteUser(userId: Int) {
        _localUsers.update { currentList ->
            currentList.filterNot { it.id == userId }
        }
    }

    fun getUserById(userId: Int): LocalUser? {
        return _localUsers.value.find { it.id == userId }
    }
}
