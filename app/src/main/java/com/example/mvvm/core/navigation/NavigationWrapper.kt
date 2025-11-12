package com.example.mvvm.core.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mvvm.ui.screens.ScreenDatos
import com.example.mvvm.ui.screens.ScreenInicio
import com.example.mvvm.ui.screens.ScreenRegistro
import com.example.mvvm.ui.screens.ScreenLogin
import com.example.mvvm.ui.screens.ScreenVista
import com.example.mvvm.ui.viewmodel.LoginViewModel
import com.example.mvvm.ui.viewmodel.RegistroViewModel
import com.example.mvvm.ui.viewmodel.VistaViewModel

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Inicio){


        composable<Inicio>{
            ScreenInicio({navController.navigate(Login)},
                {navController.navigate(Registro)})
        }

        composable<Login> {
            val viewModel : LoginViewModel = viewModel()

            ScreenLogin(viewModel, {navController.navigate(Datos)})
        }

        composable<Registro> {
            val viewModel : RegistroViewModel = viewModel()

            ScreenRegistro(viewModel, {navController.navigate(Login)})
        }

        composable<Datos>{
            ScreenDatos({ navController.navigate(Vista) })
        }
        composable<Vista> {
            val vm: VistaViewModel = viewModel()
            ScreenVista(
                navigateToInicio = {
                    navController.navigate(Inicio) {
                        popUpTo(Inicio) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                vistaViewModel = vm
            )
        }
    }

}