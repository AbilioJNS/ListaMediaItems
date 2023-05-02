package com.example.listamediaitems.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listamediaitems.ItemSeleccinado
import com.example.listamediaitems.MediaItem
import com.example.listamediaitems.PantallaPrincipal
import com.example.listamediaitems.screens.misScreens.PantallaItem
import com.example.listamediaitems.screens.misScreens.PantallaListaItems
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun navHost(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PantallaListaItems.ruta){
        composable(
            route = PantallaItem.ruta + "/{jsonItem}",
            arguments = listOf(
                navArgument("jsonItem"){
                    type = NavType.StringType
                }
            )
            ){ navBackStackEntry ->
            val itemJson = navBackStackEntry.arguments?.getString("jsonItem")
            val item = Gson().fromJson(itemJson,MediaItem::class.java)
            ItemSeleccinado(navController, item)

        }

        composable(route = PantallaListaItems.ruta){
            PantallaPrincipal(navController)
        }
    }

}