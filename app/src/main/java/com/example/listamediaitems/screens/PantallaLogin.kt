package com.example.listamediaitems.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.listamediaitems.PantallaPrincipal
import com.example.listamediaitems.User
import com.example.listamediaitems.addUser
import com.example.listamediaitems.leerUsuarios
import java.io.*


@Composable
fun Milogin(context: Context, navController: NavHostController) {
    var usuario by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var password by remember {
        mutableStateOf("")
    }
    var showMensaje by remember { mutableStateOf(false)}
    var mensajeAMostrar by remember { mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = usuario, onValueChange = { usuario = it },
            label = { Text(text = "Usuario") },
        )
        OutlinedTextField(value = password, onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Row {
            Button(onClick = {
                var users = leerUsuarios(context)
                var user = User(usuario, password)
                if (users.contains(user)){
                    navController.navigate(misScreens.PantallaListaItems.ruta)
                }
                else{
                    showMensaje = true
                    mensajeAMostrar = "No existe el usuario"
                }
            }) {
                Text(text = "Aceptar")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {
                if (addUser(usuario, password, context)){
                    mensajeAMostrar = "Usuario creado"
                    usuario = ""
                    password = ""
                    }
                else{
                    mensajeAMostrar = "Ese usuario ya existe"
                    }
                showMensaje = true
            }) {
                Text(text = "Nuevo usuario")
            }
        }
        if(showMensaje){
            Box (
                contentAlignment = Alignment.BottomCenter){
                Surface(
                    shape = MaterialTheme.shapes.medium, 
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                    ) {
                    Row (horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.clickable { showMensaje=false }
                    ){
                        Text(text = mensajeAMostrar,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


