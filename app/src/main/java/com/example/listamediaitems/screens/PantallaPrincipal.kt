package com.example.listamediaitems

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.listamediaitems.screens.misScreens
import com.google.gson.Gson

@Composable
fun PantallaPrincipal(navController: NavHostController) {
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        items(getMediaItem()) { item ->
            MediaListItem(item,navController)
        }
    }
}

@Composable
fun MediaListItem(item: MediaItem, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 5.dp)
            .clickable {
                val mediajon = Gson().toJson(item)
                navController.navigate(misScreens.PantallaItem.ruta +"/"+ Uri.encode(mediajon))
            }
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = item.thumb,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if(item.type==MediaItem.Type.VIDEO) {
                Icon(
                    imageVector = Icons.Default.PlayCircleOutline,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "${item.titulo}")
        }
    }

}




