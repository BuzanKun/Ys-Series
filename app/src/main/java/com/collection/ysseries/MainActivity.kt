package com.collection.ysseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collection.ysseries.model.BottomBarItem
import com.collection.ysseries.model.Series
import com.collection.ysseries.model.ysSeriesData
import com.collection.ysseries.ui.components.Search
import com.collection.ysseries.ui.components.SectionText
import com.collection.ysseries.ui.components.SeriesItem
import com.collection.ysseries.ui.theme.YsSeriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YsSeriesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    YsSeriesApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun YsSeriesApp(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Banner()
            SectionText("Ys Series")
            SeriesColumn(ysSeriesData)
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = Modifier) {
        Image(
            painter = painterResource(R.drawable.ys_series_logo_wide),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier.height(160.dp)
        )
        Search()
    }
}

@Composable
fun SeriesColumn(
    listSeries: List<Series>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(listSeries, key = { it.title }) { series ->
            SeriesItem(series)
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = "Home",
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = "Favorite",
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = "Profile",
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItems.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.title == navigationItems[0].title,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun YsSeriesAppPreview() {
    YsSeriesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            YsSeriesApp(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}