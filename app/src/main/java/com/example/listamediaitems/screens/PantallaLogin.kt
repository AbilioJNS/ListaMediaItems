package com.example.listamediaitems.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.example.listamediaitems.User
import com.google.gson.Gson
import java.io.*


@Composable
fun Milogin(context: Context) {
    var usuario by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var password by remember {
        mutableStateOf("")
    }
    var showMensaje by remember { mutableStateOf(true)}
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
                if (users.contains(User(usuario, password))){
                    //TODO REALIZAR NAVEGACION
                }
                else{
                    showMensaje = true
                }
            }) {
                Text(text = "Aceptar")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { addUser(usuario, password, context) }) {
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
                    Row {
                        Text(text = "El usuario no esta registrado",
                        Modifier.clickable { showMensaje=false })
                    }
                }
            }
        }
    }
}

fun addUser(usuario: String, password: String, context: Context) {
    var user = User(usuario, password)
    val userJson = Gson().toJson(user)
    val file = File(context.getExternalFilesDir(null), "users.txt")
    if (file == null) {
        file.createNewFile()
    }
    val fileWriter = FileWriter(file, true)
    fileWriter.append(userJson + "\n")
    fileWriter.close()
}

fun leerUsuarios(context: Context): MutableList<User> {
    val usuarios = mutableListOf<User>()
    val file = File(context.getExternalFilesDir(null), "users.txt")
    try {
        val buffer: BufferedReader =
            File(context.getExternalFilesDir(null), "users.txt").bufferedReader()

        buffer.useLines {
                lines -> lines.forEach { v ->
                val user = Gson().fromJson(v, User::class.java)
                    usuarios.add(user)
                }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return usuarios
}
