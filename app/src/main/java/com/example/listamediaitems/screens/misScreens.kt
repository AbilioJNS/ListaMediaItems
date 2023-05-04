package com.example.listamediaitems.screens

sealed class misScreens(
    val ruta:String){
    object PantallaListaItems:misScreens("pantalla_principal")
    object PantallaItem:misScreens("pantalla_item")
    object Pantallalogin:misScreens("pantalla_login")
}
