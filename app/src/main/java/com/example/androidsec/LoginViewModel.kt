package com.example.androidsec

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel (application: Application) :
    AndroidViewModel(application) {
    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseRespond<LoginResponse>> =
        MutableLiveData()

    fun loginUser(email: String, pwd: String) {
        loginResult.value = BaseRespond.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseRespond.Success(response.body())
                } else {
                    loginResult.value = BaseRespond.Error(response?.message())
                }
            } catch (ex: Exception) {
                loginResult.value = BaseRespond.Error(ex.message)
            }
        }
    }
}
