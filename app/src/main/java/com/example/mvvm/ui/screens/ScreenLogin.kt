package com.example.mvvm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.ui.viewmodel.LoginViewModel
import com.example.mvvm.R
import com.example.mvvm.data.repository.SharedRepository


@Composable
fun ScreenLogin(viewModel : LoginViewModel, navigateToDatos: () -> Unit){


    val email by viewModel.email.observeAsState("")
    val pass by viewModel.password.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val isLoading by viewModel.isLoading.observeAsState(false)
    val isLoginOk by viewModel.isLoginOk.observeAsState(false)


    val imagen1 = R.drawable.logoluisvives

    val contexto = LocalContext.current
    val sharedRepo = remember { SharedRepository(contexto) }

    if (isLoginOk == true) {
        sharedRepo.saveLogin(email, pass)
        navigateToDatos()
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(isLoading == true){
            CircularProgressIndicator()
        }else{
            Text(
                text = "RESERVIVES",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(red = 181, green = 36, blue = 106)
            )
            Text(
                text = "Gestiona. Reserva. Disfruta.",
                fontSize = 14.sp,
                color = Color(red = 181, green = 36, blue = 106),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = imagen1),
                contentDescription = "logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = email,
                onValueChange = {x -> viewModel.onEmailChange(x)},
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = pass,
                onValueChange = {x -> viewModel.onPasswordChange(x)},
                label = { Text(text = "Contraseña") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation('*')
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                viewModel.onLogin()
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 181, green = 36, blue = 106),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(55.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                )
            ) {
                Text(text = "Iniciar Sesión - Navegar a Datos Personales")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (isLoginOk != true && !errorMessage.isNullOrBlank()) {
                Text(text = errorMessage!!)
            }
        }
    }
}


