package com.example.listamediaitems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.listamediaitems.screens.misScreens

@Composable
fun ItemSeleccinado(navController: NavHostController, mediaItem: MediaItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.height(200.dp)
            ) {
                AsyncImage(model = mediaItem.thumb,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondaryVariant),
            contentAlignment = Alignment.Center
                ) {
            Text(text = mediaItem.titulo,
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.h5
                )
        }
        Box (modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
                ){
            Text(text = mediaItem.descipcion,
                modifier = Modifier.padding(8.dp)
            )
        }
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = {
                    navController.navigate(misScreens.PantallaListaItems.ruta)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
            ) {
                Text(text = "Volver")
            }
        }
    }
}