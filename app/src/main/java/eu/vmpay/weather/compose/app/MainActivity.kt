package eu.vmpay.weather.compose.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.vmpay.weather.compose.app.ui.ItemDetailsScreen
import eu.vmpay.weather.compose.app.ui.ItemListScreen
import eu.vmpay.weather.compose.app.ui.theme.WeatherComposeAppTheme
import eu.vmpay.weather.compose.app.viewmodels.DetailsViewModel
import eu.vmpay.weather.compose.app.viewmodels.ListViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val listViewModel: ListViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MaterialTheme {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "plp") {
                            composable("plp") { ItemListScreen(navController, listViewModel) }
                            composable("pdp/{id}") {
                                val id: String? = it.arguments?.getString("id")
                                detailsViewModel.getDetails(id)
                                ItemDetailsScreen(detailsViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

