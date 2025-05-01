package com.collection.ysseries.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.collection.ysseries.R
import com.collection.ysseries.ViewModelFactory
import com.collection.ysseries.di.Injection
import com.collection.ysseries.ui.common.UiState
import com.collection.ysseries.ui.theme.YsSeriesTheme

@Composable
fun DetaiLScreen(
    seriesId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getSeriesById(seriesId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    title = data.title,
                    image = data.image,
                    releaseYear = data.releaseYear,
                    description = data.description,
                    onBackClick = navigateBack
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    image: Int,
    releaseYear: Int,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "Series Image",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
            )
            InfoDetail(
                title = title,
                releaseYear = releaseYear,
            )
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Button(
            onClick = {
                onBackClick()
            },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.End)
        ) {
            Text(
                text = "Go Back"
            )
        }
    }
}

@Composable
fun InfoDetail(
    title: String,
    releaseYear: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .shadow(2.dp)
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = "Title: "
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Text(
                text = "Release Year :"
            )
            Text(
                text = releaseYear.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    YsSeriesTheme {
        DetailContent(
            title = "The Oath in Felghana",
            image = R.drawable.ys6_logo,
            releaseYear = 2003,
            description = "The adventure of the red-haired swordsman Adol Christin begins in the land of Esteria. Washed ashore with amnesia, Adol is tasked by a fortune-teller to seek out the six Books of Ys. These ancient texts hold the secrets to the vanished civilization of Ys and are crucial to understanding and defeating the evil that plagues Esteria. Gameplay is characterized by the unique \"bump attack\" system, where Adol damages enemies by running into them.",
            onBackClick = TODO()
        )
    }
}

@Preview
@Composable
private fun InfoDetailPreview() {
    YsSeriesTheme {
        InfoDetail(
            title = "The Oath in Felghana",
            releaseYear = 2003
        )
    }
}