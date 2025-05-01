package com.collection.ysseries.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collection.ysseries.R
import com.collection.ysseries.ui.theme.YsSeriesTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.self_photo),
                contentDescription = "Self Photo",
                modifier = Modifier
                    .size(240.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Muhammad Rizky Ramadhani",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Email: muhrizkyram@gmail.com"
            )
        }
    }
}

@Preview
@Composable
private fun AboutScreenPreview() {
    YsSeriesTheme {
        AboutScreen()
    }
}