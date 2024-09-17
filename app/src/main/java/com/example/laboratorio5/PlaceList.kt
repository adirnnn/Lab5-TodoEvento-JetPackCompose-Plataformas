package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import coil.compose.AsyncImage


class PlaceList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LocationListScreen()
                }
            }
        }
    }
}

data class Location(
    val name: String,
    val address: String
)

val locations = listOf(
    Location("France", "Lyons"),
    Location("USA", "NY"),
    Location("USA", "LA, Cali"),
    Location("Spain", "Barcelona")
)

@Composable
fun LocationListScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(locations) { location ->
            LocationItem(location)
        }
    }
}

@Composable
fun LocationItem(location: Location) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(
                text = location.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = location.address,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

