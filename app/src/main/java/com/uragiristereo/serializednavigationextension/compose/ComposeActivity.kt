package com.uragiristereo.serializednavigationextension.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.uragiristereo.serializednavigationextension.SneRoute
import com.uragiristereo.serializednavigationextension.compose.theme.SneTheme
import com.uragiristereo.serializednavigationextension.navigation.compose.NavHost
import com.uragiristereo.serializednavigationextension.navigation.compose.composable
import com.uragiristereo.serializednavigationextension.runtime.navigate

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SneTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SneRoute.Home,
        enterTransition = {
            fadeIn()
        },
        exitTransition = {
            fadeOut()
        },
    ) {
        composable(defaultValue = SneRoute.Home) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = "Home")

                Button(
                    onClick = {
                        navController.navigate(SneRoute.Notification(message = "hello? & goodbye"))
                    },
                    content = {
                        Text(text = "Go to Notification")
                    },
                )
            }
        }

        composable<SneRoute.Notification> {
            val navArgs = rememberNavArgsOf()

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = "Posts")

                Text(text = "$navArgs")
            }
        }
    }
}
