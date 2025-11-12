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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm.R


@Composable
fun ScreenInicio(navigateToLogin: () -> Unit, navigateToRegistro: () -> Unit) {

    val imagen1 = R.drawable.logoluisvives

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {

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

        Spacer(modifier = Modifier.height(60.dp))


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navigateToLogin() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 181, green = 36, blue = 106),
                contentColor = Color.White
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(55.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 10.dp
            )) {

            Text(text = "Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {navigateToRegistro()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 181, green = 36, blue = 106),
                contentColor = Color.White
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(55.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 10.dp
            )) {

            Text(text = "Registrarse")
        }
    }
}

