package com.example.listamediaitems

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.IOException

data class User(
    val nombre:String,
    val pass:String
){}
@SuppressLint("SuspiciousIndentation")
fun leerUsuarios(context: Context): MutableList<User> {
    val usuarios = mutableListOf<User>()
    val file = File(context.getExternalFilesDir(null), "users.txt")
    Log.d("Direccion","${context.getExternalFilesDir(null)}")
    try {
        val buffer: BufferedReader =
            File(context.getExternalFilesDir(null), "users.txt").bufferedReader()
        buffer.useLines { lines ->
            lines.forEach { v ->
                val user = Gson().fromJson(v, User::class.java)
                usuarios.add(user)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return usuarios
}

fun addUser(usuario: String, password: String, context: Context): Boolean {
    var user = User(usuario, password)
    var users = leerUsuarios(context)
    var isCreate = false
    if (!users.contains(user)){
        try {
            val userJson = Gson().toJson(user)
            val file = File(context.getExternalFilesDir(null), "users.txt")
            if (file == null) {
                file.createNewFile()
            }
            val fileWriter = FileWriter(file, true)
            fileWriter.append(userJson + "\n")
            fileWriter.close()
            isCreate=true
        }catch (e: IOException){
            Log.i("Error añadir usuario", "Algo ha ido mal al añadir usuario nuevo")
        }
    }
    return isCreate
}
