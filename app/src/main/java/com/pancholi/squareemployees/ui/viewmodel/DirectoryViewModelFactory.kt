package com.pancholi.squareemployees.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pancholi.squareemployees.network.ApiService

@Suppress("UNCHECKED_CAST")
class DirectoryViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(DirectoryViewModel::class.java)) {
      return DirectoryViewModel(apiService, null, null) as T
    }

    throw IllegalArgumentException("Invalid class for DirectoryViewModel.")
  }
}