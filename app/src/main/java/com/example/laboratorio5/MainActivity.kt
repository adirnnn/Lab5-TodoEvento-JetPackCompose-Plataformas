package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConcertsScreen()
                }
            }
        }
    }
}

data class Concert(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String
)

val favoriteConcerts = listOf(
    Concert(1, "Arctic Monkeys", "Lyons, France", "https://garajedelrock.com/wp-content/uploads/2020/12/monkeys.jpg"),
    Concert(2, "Twenty One Pilots", "NY, USA", "https://i.ytimg.com/vi/HmV31ci4Faw/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDPjP3Wl0YG5YIXMEkvn4dcEMmtwA"),
)

val allConcerts = listOf(
    Concert(1, "Arctic Monkeys", "Lyons, France", "https://garajedelrock.com/wp-content/uploads/2020/12/monkeys.jpg"),
    Concert(2, "Twenty One Pilots", "NY, USA", "https://i.ytimg.com/vi/HmV31ci4Faw/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDPjP3Wl0YG5YIXMEkvn4dcEMmtwA"),
    Concert(3, "Kendrick Lamar", "LA, California, USA", "https://ahmedmusaad.com/content/images/2022/10/kendrick_lamar_live--6-.JPG"),
    Concert(4, "Tyler, the Creator", "Barcelona, Spain", "https://images.seattletimes.com/wp-content/uploads/2022/04/040922_TylerTheCreator_tzr.jpg?d=1020x655")
)

@Composable
fun ConcertsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Your favorites",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(220.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favoriteConcerts) { concert ->
                ConcertCard(concert)
            }
        }

        Text(
            text = "All concerts",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(440.dp), // Adjust this height as needed
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allConcerts) { concert ->
                ConcertCard(concert)
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concert) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = concert.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = concert.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
            Text(
                text = concert.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
        }
    }
}
