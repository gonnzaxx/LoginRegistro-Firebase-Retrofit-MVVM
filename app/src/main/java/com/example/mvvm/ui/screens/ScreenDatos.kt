package com.example.mvvm.ui.screens

// ScreenDatos.kt
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.mvvm.data.repository.SharedRepository
import com.example.mvvm.ui.viewmodel.DatosViewModel
import com.example.mvvm.ui.viewmodel.DatosViewModelFactory

@Composable
fun ScreenDatos(navigateToVista: () -> Unit) {

    val context = LocalContext.current
    val sharedRepo = remember { SharedRepository(context) }
    val vm: DatosViewModel = viewModel(factory = DatosViewModelFactory(sharedRepo))

    val email by vm.email.observeAsState("")
    val password by vm.password.observeAsState("")
    val isLoading by vm.isLoading.observeAsState(false)
    val message by vm.message.observeAsState(null)
    val goVista by vm.navigateToVista.observeAsState(false)


    LaunchedEffect(goVista) {
        if (goVista) {
            vm.onNavigated()
            navigateToVista()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Datos personales",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(red = 181, green = 36, blue = 106),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(20.dp))

        // EMAIL BLOQUEADO (no editable)
        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("E-mail") },
            enabled = false,             // <- bloquea la edición
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // CONTRASEÑA EDITABLE
        OutlinedTextField(
            value = password,
            onValueChange = { vm.onPasswordChange(it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation('*'),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { vm.onSaveChanges() },
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 181, green = 36, blue = 106),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 10.dp
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            } else {
                Text("Guardar cambios")
            }
        }

        Spacer(Modifier.height(12.dp))

        message?.let {
            Text(it)
        }
    }
}


