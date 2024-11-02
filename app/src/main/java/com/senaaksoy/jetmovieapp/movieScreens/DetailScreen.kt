package com.senaaksoy.jetmovieapp.movieScreens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.senaaksoy.jetmovieapp.R
import com.senaaksoy.jetmovieapp.model.Movie
import com.senaaksoy.jetmovieapp.model.movies


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movieId:Int?){
    val newMovieList= movies.filter{movie->
        movie.id==movieId
    }

    Scaffold (topBar = { EditTopAppBar(showBackIcon = true) {
        navController.popBackStack()} }
    ){paddingValues->
        Surface(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {
                EditMovieCard(movie = newMovieList.first())
                Divider()
                Text(text = stringResource(id = R.string.movie_images))
                EditLazyRow(movieList = newMovieList)

            }
        }

    }

}

@Composable
fun EditLazyRow(movieList:List<Movie>, modifier: Modifier=Modifier){
    val context= LocalContext.current
    movieList.forEach {movie->
        LazyRow {
            items(context.resources.getStringArray(movie.images)){imagesUrl->
                Card(
                    modifier
                        .size(160.dp)
                        .padding(4.dp)) {
                    AsyncImage(model = imagesUrl,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds)
                }

            }
        }  }




}