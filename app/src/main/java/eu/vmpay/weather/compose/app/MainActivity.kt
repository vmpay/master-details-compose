package eu.vmpay.weather.compose.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.vmpay.weather.compose.app.ui.ItemDetailsScreen
import eu.vmpay.weather.compose.app.ui.ItemListScreen
import eu.vmpay.weather.compose.app.ui.theme.WeatherComposeAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MaterialTheme {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "plp") {
                            composable("plp") { ItemListScreen(navController) }
                            composable("pdp/{id}") {
                                ItemDetailsScreen(navController, it.arguments?.getString("id"))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    ItemListScreen(rememberNavController())
}
