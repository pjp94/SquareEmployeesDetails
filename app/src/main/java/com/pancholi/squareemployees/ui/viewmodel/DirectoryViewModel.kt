package com.pancholi.squareemployees.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pancholi.squareemployees.model.Directory
import com.pancholi.squareemployees.model.Employee
import com.pancholi.squareemployees.network.ApiService
import com.pancholi.squareemployees.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DirectoryViewModel(
  private val apiService: ApiService,
  private val coroutineScope: CoroutineScope?,
  private val dispatcher: CoroutineDispatcher?
) : ViewModel() {

  private val directory = MutableLiveData<NetworkResponse<Directory>>(
    NetworkResponse.loading(null)
  )

  fun getDirectory(): LiveData<NetworkResponse<Directory>> {
    return directory
  }

  fun fetchDirectory() {
    if (directory.value!!.data != null) {
      return
    }

    getCoroutineScope().launch(getCoroutineDispatcher()) {
      try {
        val response = apiService.getEmployees()
        handleResponse(response)
      } catch (exception: Exception) {
        postError("Failed to get employees: ${exception.message}")
      }
    }
  }

  private fun getCoroutineScope(): CoroutineScope {
    return coroutineScope ?: viewModelScope
  }

  private fun getCoroutineDispatcher(): CoroutineDispatcher {
    return dispatcher ?: Dispatchers.IO
  }

  private fun handleResponse(response: Response<Directory>) {
    if (response.isSuccessful) {
      val body = response.body()
      sortEmployeesByName(body?.employees)
      postSuccess(body)
    } else {
      postError("Getting employees was unsuccessful, code ${response.code()}")
    }
  }

  private fun sortEmployeesByName(employees: MutableList<Employee>?) {
    employees?.sortWith { o1, o2 -> o1.fullName.compareTo(o2.fullName) }
  }

  private fun postSuccess(data: Directory?) {
    directory.postValue(NetworkResponse.success(data ?: Directory(mutableListOf())))
  }

  private fun postError(message: String) {
    directory.postValue(NetworkResponse.error(null, message))
  }
}