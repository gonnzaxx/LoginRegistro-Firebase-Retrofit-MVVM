package com.example.mvvm.ui.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.ui.viewmodel.LoginViewModel
import com.example.mvvm.ui.viewmodel.RegistroViewModel

@Composable
fun ScreenRegistro(viewModel : RegistroViewModel, navigateToLogin: () -> Unit){

    val email by viewModel.email.observeAsState("")
    val pass by viewModel.password.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val isLoading by viewModel.isLoading.observeAsState(false)
    val isRegisterOk by viewModel.isRegisterOk.observeAsState(false)

    if (isRegisterOk == true) {
        navigateToLogin()
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        if(isLoading == true){
            CircularProgressIndicator()
        }else {
            Text(
                text = "Registro",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(red = 181, green = 36, blue = 106),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )


            OutlinedTextField(
                value = email,
                onValueChange = {x-> viewModel.onEmailChange(x)},
                label = {Text(text = "E-mail")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = {x-> viewModel.onPasswordChange(x)},
                label = {Text(text = "Contraseña")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation('*')
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                viewModel.onRegister()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 181, green = 36, blue = 106),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(55.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                )
            ){
                Text(text = "Registrarse")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¿Ya tienes cuenta?"
            )
            Text(
                text = "Iniciar Sesión",
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navigateToLogin()
                }
            )

            if (isRegisterOk != true && !errorMessage.isNullOrBlank()) {
                Text(text = errorMessage!!)
            }
        }


    }
}


