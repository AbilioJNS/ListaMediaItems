package com.example.listamediaitems.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.listamediaitems.ItemSeleccinado
import com.example.listamediaitems.MediaItem
import com.example.listamediaitems.PantallaPrincipal
import com.example.listamediaitems.screens.misScreens.*
import com.google.gson.Gson

@Composable
fun navHost(context: Context, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Pantallalogin.ruta){
        composable(
            route = PantallaItem.ruta + "/{jsonItem}",
            arguments = listOf(
                navArgument("jsonItem"){
                    type = NavType.StringType
                })
            ){ navBackStackEntry ->
            val itemJson = navBackStackEntry.arguments?.getString("jsonItem")
            val item = Gson().fromJson(itemJson,MediaItem::class.java)
            ItemSeleccinado(navController, item)
        }
        composable(route = PantallaListaItems.ruta){
            PantallaPrincipal(navController)
        }
        composable(route = Pantallalogin.ruta){
            Milogin(context, navController)
        }
    }

}