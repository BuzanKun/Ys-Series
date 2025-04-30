package com.collection.ysseries.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.collection.ysseries.MainActivityViewModel
import com.collection.ysseries.R
import com.collection.ysseries.ViewModelFactory
import com.collection.ysseries.data.YsSeriesRepository
import com.collection.ysseries.ui.components.ScrollToTopButton
import com.collection.ysseries.ui.components.Search
import com.collection.ysseries.ui.components.SectionText
import com.collection.ysseries.ui.components.SeriesItem
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Banner()
        SectionText("Ys Series")
        SeriesColumn()
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel = viewModel(factory = ViewModelFactory(YsSeriesRepository()))
) {
    val query by viewModel.query
    Box(modifier = Modifier) {
        Image(
            painter = painterResource(R.drawable.ys_series_logo_wide),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier.height(160.dp)
        )
        Search(
            query = query,
            onQueryChange = viewModel::search,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SeriesColumn(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel = viewModel(factory = ViewModelFactory(YsSeriesRepository()))
) {
    Box(
        modifier = modifier
    ) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState,
            contentPadding = PaddingValues(bottom = 8.dp),
            modifier = modifier
        ) {
            items(viewModel.series.value, key = { it.id }) { series ->
                SeriesItem(
                    title = series.title,
                    image = series.image,
                    releaseYear = series.releaseYear,
                    modifier = Modifier
                        .animateItem(placementSpec = tween(durationMillis = 100))
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 32.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}