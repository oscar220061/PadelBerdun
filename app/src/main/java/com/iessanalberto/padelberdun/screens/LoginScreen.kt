package com.iessanalberto.padelberdun.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iessanalberto.padelberdun.R
import com.iessanalberto.padelberdun.navigation.AppScreens
import com.iessanalberto.padelberdun.viewmodels.LoginScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, loginScreenViewModel: LoginScreenViewModel) {
    val context = LocalContext.current
    val auth: FirebaseAuth = Firebase.auth
    var passwordVisible by remember { mutableStateOf(false) }
    val annotatedText = buildAnnotatedString {
        pushStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        )
        append("Registrarse")
        pop()
    }
    val loginScreenUiState by loginScreenViewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_padel_berdun), // Reemplaza "your_image" con el ID de tu imagen
            contentDescription = null,
            contentScale = ContentScale.Crop, // Ajusta la imagen para llenar todo el fondo
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f) // Puedes ajustar la transparencia si lo deseas
        )

        // Columna de contenido
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Image(
                painter = painterResource(id = R.drawable.logo_app_removebg_preview), // Reemplaza "your_image" con el ID de tu imagen
                contentDescription = null,
                modifier = Modifier.size(300.dp)


            )

            OutlinedTextField(value = loginScreenUiState.user,
                onValueChange = {
                    loginScreenViewModel.onChanged(
                        emailUi = it,
                        passwordUi = loginScreenUiState.password)

                },
                label = { Text(text = "Email")}

                )

            OutlinedTextField(value = loginScreenUiState.password,
                onValueChange = {
                    loginScreenViewModel.onChanged(
                        emailUi = loginScreenUiState.user,
                        passwordUi = it)
                },
                label = { Text(text = "Password")},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                //Este es el botón que sale al lado del campo de entrada para poder cambiar el modo de vista de la contraseña
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(R.drawable.ojocontrasena),
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }

            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if(loginScreenViewModel.logear()){
                    auth.signInWithEmailAndPassword(loginScreenUiState.user, loginScreenUiState.password).addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            navController.navigate(AppScreens.ReservasScreen.route)
                        }else{
                            Toast.makeText(context, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(context, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
                }



            }) {
                Text(text = "Acceder")
            }
            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = annotatedText,
                onClick = {
                    navController.navigate(AppScreens.RegisterScreen.route)
                }
            )
        }
    }

}

