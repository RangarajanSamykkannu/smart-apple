package com.example.composetabviewwithlocalcrud.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetabviewwithlocalcrud.data.RetrofitInstance
import com.example.composetabviewwithlocalcrud.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Sealed interface for UI state is good practice, but for brevity, using simple states here.
data class ApiUsersUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ApiUserListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ApiUsersUiState())
    val uiState: StateFlow<ApiUsersUiState> = _uiState.asStateFlow()

    // Keep track of the current page if this ViewModel instance handles only one page.
    // If it were to handle multiple, this would need to be managed differently or per-request.
    private var currentPage: Int = 1

    fun loadUsers(page: Int) {
        if (_uiState.value.isLoading) return // Prevent multiple loads
        currentPage = page
        viewModelScope.launch {
            _uiState.value = ApiUsersUiState(isLoading = true)
            try {
                val response = RetrofitInstance.api.getUsers(page)
                _uiState.value = ApiUsersUiState(users = response.data)
            } catch (e: Exception) {
                _uiState.value = ApiUsersUiState(error = e.message ?: "An unknown error occurred")
            }
        }
    }
}
