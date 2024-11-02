package com.senaaksoy.jetmovieapp.movieScreens


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.senaaksoy.jetmovieapp.R
import com.senaaksoy.jetmovieapp.model.Movie
import com.senaaksoy.jetmovieapp.model.movies
import com.senaaksoy.jetmovieapp.movieNavigation.Screen


@Composable
fun MainScreen(navController: NavController){

    Scaffold(topBar ={ EditTopAppBar()}) { paddingValues->
        LazyColumn(contentPadding = paddingValues) {
            items(movies){ navigate->
                EditMovieCard(movie = navigate){
                    navController.navigate("${Screen.DetailScreen.route}/${navigate.id}")

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTopAppBar(showBackIcon:Boolean=false,onBackClick: ()->Unit={}){

    TopAppBar(title =
    {
        Row {
            if(showBackIcon){
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription =null,
                    modifier = Modifier.clickable { onBackClick() },)
                Spacer(modifier = Modifier.width(12.dp))}


            Text(
                text = stringResource(id = R.string.movies)
            )
        }

    },
        colors = TopAppBarDefaults.topAppBarColors(Color.LightGray)
    )

}

@Composable
fun EditMovieCard(movie: Movie, onItemClick:(Movie)->Unit={}){
    var expanded by remember{ mutableStateOf(false) }
    val context = LocalContext.current
    val images = context.resources.getStringArray(movie.images)[0]
    Row(modifier= Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(movie)
        }) {

        Surface(modifier = Modifier
            .padding(4.dp)
            .size(120.dp)
            .shadow(elevation = 8.dp),
            shape = RectangleShape,
            border = BorderStroke(width = 1.dp, color = Color.LightGray)

        ) {
            AsyncImage(modifier=Modifier.clip(RectangleShape),
                model = images,
                contentDescription = null,
                contentScale = ContentScale.Crop)
        }
        Column(modifier=Modifier.animateContentSize(animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        )
        )) {
            Text(text = stringResource(id =movie.title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Director: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(stringResource(id = movie.director))
                    }
                },
                modifier = Modifier.padding(4.dp)
            )
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight =FontWeight.Bold )){
                    append("Released: ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)){
                    append(stringResource(id = movie.year))
                }
            },
                modifier = Modifier.padding(4.dp)
            )
            Icon(imageVector =if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                , contentDescription =null
                ,modifier=Modifier.clickable { expanded=!expanded })
            Divider()

            if(expanded){
                Column {
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight =FontWeight.SemiBold )){
                            append("Plot: ")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)){
                            append(stringResource(id = movie.plot))
                        }
                    },
                        modifier = Modifier.padding(4.dp)
                    )
                    Divider()

                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight =FontWeight.SemiBold )){
                            append("Actors: ")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)){
                            append(stringResource(id = movie.actors))
                        }
                    },
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight =FontWeight.SemiBold )){
                            append("Rating: ")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)){
                            append(stringResource(id = movie.rating))
                        }
                    },
                        modifier = Modifier.padding(4.dp)
                    )
                    Divider()
                }
            }


        }
    }



}