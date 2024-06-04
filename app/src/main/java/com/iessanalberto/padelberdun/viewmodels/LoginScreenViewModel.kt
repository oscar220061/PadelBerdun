package com.iessanalberto.padelberdun.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel {
    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    fun onChanged(emailUi: String, passwordUi: String){
        _uiState.update{
            currentState -> currentState.copy(user = emailUi, password = passwordUi)
        }
    }

    fun logear(): Boolean{
        return !(_uiState.value.user.isEmpty() || _uiState.value.password.isEmpty())
    }
}