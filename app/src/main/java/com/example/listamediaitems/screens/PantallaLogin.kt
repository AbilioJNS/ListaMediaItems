package com.example.listamediaitems.screens

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.*

@Composable
fun Milogin(){
    var usuario by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var password by remember {
        mutableStateOf("")
    }
    Column( modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = usuario, onValueChange = {usuario = it} )
            OutlinedTextField(value = password, onValueChange = {password = it},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
        Row {
            Button(onClick = {
                    leerUsuarios()
            }) {
                Text(text = "Aceptar")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Nuevo usuario")
            }
        }

    }
}

fun leerUsuarios() {

    val inputStream :InputStream   = File("resources/txt/archivo").inputStream()
    try {
        BufferedReader(inputStream.bufferedReader()).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                println(line)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

@Preview (
    showBackground = true
        )
@Composable
fun PreviewLogin(
){
    Milogin()
}