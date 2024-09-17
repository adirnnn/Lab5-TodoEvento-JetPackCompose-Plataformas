package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import coil.compose.AsyncImage
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.Person
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "concertsScreen") {
        // Pantalla 1: Lista de Conciertos
        composable("concertsScreen") {
            ConcertsScreen(navController)
        }

        // Pantalla 2: Detalles del Concierto
        composable("concertDetails/{concertId}") { backStackEntry ->
            val concertId = backStackEntry.arguments?.getString("concertId")?.toIntOrNull()
            if (concertId != null) {
                ConcertDetailsScreen(concertId) // Pasa el ID del concierto
            }
        }

        // Pantalla 3: Lista de Lugares
        composable("placeList") {
            LocationListScreen() // Implement this screen accordingly
        }

        // Pantalla 4: Información del Perfil
        composable("profileInfo") {
            ProfileScreen() // Implement this screen accordingly
        }
    }
}

data class Concert(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val date: String,
    val time: String,
    val description: String
)


val favoriteConcerts = listOf(
    Concert(1, "Arctic Monkeys", "Lyons, France", "https://garajedelrock.com/wp-content/uploads/2020/12/monkeys.jpg", "2024-09-25", "20:00", "An amazing night with Arctic Monkeys live in France."),
    Concert(2, "Twenty One Pilots", "NY, USA", "https://i.ytimg.com/vi/HmV31ci4Faw/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDPjP3Wl0YG5YIXMEkvn4dcEMmtwA", "2024-09-12", "20:00", "A part of the Clancy World Tour, here in NY."),
)

val allConcerts = listOf(
    Concert(
        id = 1,
        title = "Arctic Monkeys",
        subtitle = "Lyons, France",
        imageUrl = "https://garajedelrock.com/wp-content/uploads/2020/12/monkeys.jpg",
        date = "2024-09-25",         // Example date
        time = "20:00",              // Example time
        description = "An amazing night with Arctic Monkeys live in France."  // Example description
    ),
    Concert(
        id = 2,
        title = "Twenty One Pilots",
        subtitle = "NY, USA",
        imageUrl = "https://i.ytimg.com/vi/HmV31ci4Faw/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDPjP3Wl0YG5YIXMEkvn4dcEMmtwA",
        date = "2024-09-12",
        time = "20:00",
        description = "A part of the Clancy World Tour, here in NY."
    ),
    Concert(
        id = 3,
        title = "Kendrick Lamar",
        subtitle = "LA, California, USA",
        imageUrl = "https://ahmedmusaad.com/content/images/2022/10/kendrick_lamar_live--6-.JPG",
        date = "2024-10-10",
        time = "21:00",
        description = "Kendrick Lamar live in concert, a show you don't want to miss!"
    ),
    Concert(
        id = 4,
        title = "Tyler, the Creator",
        subtitle = "Barcelona, Spain",
        imageUrl = "https://images.seattletimes.com/wp-content/uploads/2022/04/040922_TylerTheCreator_tzr.jpg?d=1020x655",
        date = "2024-11-05",
        time = "19:00",
        description = "Tyler, the Creator brings his unique style to Barcelona."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcertsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TopAppBar(
            title = { Text("Concerts") },
            actions = {
                IconButton(
                    onClick = {
                        // Navegar a la pantalla de perfil cuando se hace clic en el ícono
                        navController.navigate("profileInfo")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon"
                    )
                }
                IconButton(
                    onClick = {
                        // Navegar a la pantalla de perfil cuando se hace clic en el ícono
                        navController.navigate("placeList")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "View Locations"
                    )
                }
            }
        )

        Text(
            text = "Your favorites",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(220.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favoriteConcerts) { concert ->
                ConcertCard(concert, navController)
            }
        }

        Text(
            text = "All concerts",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(440.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allConcerts) { concert ->
                ConcertCard(concert, navController)
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concert, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable {
                // Navegar a la pantalla de detalles del concierto
                navController.navigate("concertDetails/${concert.id}")
            },
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
