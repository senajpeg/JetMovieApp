package com.senaaksoy.jetmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.senaaksoy.jetmovieapp.movieNavigation.Navigation
import com.senaaksoy.jetmovieapp.ui.theme.JetMovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetMovieAppTheme {
                Navigation()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun JetMovieAppPreview() {
    JetMovieAppTheme {
        Navigation()
    }
}