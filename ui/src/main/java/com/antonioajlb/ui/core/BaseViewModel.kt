package com.antonioajlb.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(initialState: T) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<T> = _uiState.asStateFlow()

    protected fun updateState(reducer: T.() -> T) {
        _uiState.update(reducer)
    }

    protected fun launchInViewModelScope(
        onStart: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            try {
                onStart()
                block()
            } catch (e: Exception) {
                onError(e)
            } finally {
                onComplete()
            }
        }
    }
}