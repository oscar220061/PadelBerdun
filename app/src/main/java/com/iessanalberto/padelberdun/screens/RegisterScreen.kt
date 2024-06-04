package com.iessanalberto.padelberdun.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.padelberdun.R
import com.iessanalberto.padelberdun.navigation.AppScreens
import com.iessanalberto.padelberdun.viewmodels.RegisterScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen (navController: NavController){
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Registro") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            ),

            //Botón para voolver hacia atrás
            navigationIcon = {
                IconButton(onClick = {navController.navigate(AppScreens.LoginScreen.route)}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) { paddingValues ->
        RegisterScreenBodyContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController, registerScreenViewModel = RegisterScreenViewModel()
        )

    }
}
@Composable
fun RegisterScreenBodyContent(modifier: Modifier, navController: NavController, registerScreenViewModel: RegisterScreenViewModel) {
    val registerUiState by registerScreenViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_padel_berdun), // Reemplaza "your_image" con el ID de tu imagen
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f) // Puedes ajustar la transparencia si lo deseas
        )
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)

            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(value = registerUiState.registroCorreo,
                onValueChange = {
                    registerScreenViewModel.onChanged(
                        registroCorreo = it,
                        registroPassword = registerUiState.registroPassword,
                        registroConfPasw = registerUiState.registroConfPasw)

                },
                label = { Text(text = "User")}

            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = registerUiState.registroPassword,
                onValueChange = {
                    registerScreenViewModel.onChanged(
                        registroPassword = it,
                        registroCorreo = registerUiState.registroCorreo,
                        registroConfPasw = registerUiState.registroConfPasw)

                },
                label = { Text(text = "Password")}

            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = registerUiState.registroConfPasw,
                onValueChange = {
                    registerScreenViewModel.onChanged(
                        registroConfPasw = it,
                        registroPassword = registerUiState.registroPassword,
                        registroCorreo = registerUiState.registroCorreo)

                },
                label = { Text(text = "Confirm Password")}

            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { /*TODO*/ }) {

                Text(text = "Registrar")
            }

        }





    }
}