package com.collection.ysseries.model

import com.collection.ysseries.R


data class Series(
    val image: Int,
    val title: String,
    val releaseYear: Int
)

val ysSeriesData = listOf(
    Series(
        R.drawable.ys1_2_logo,
        "Ys I: Ancient Ys Vanished",
        1987
    ),
    Series(
        R.drawable.ys1_2_logo,
        "Ys II: Ancient Ys Vanished - The Final Chapter",
        1987
    ),
    Series(
        R.drawable.ys3_logo,
        "Ys III: The Oath in Felghana",
        2005
    ),
    Series(
        R.drawable.ys4_logo,
        "Ys IV: Memories of Celceta",
        2012
    ),
    Series(
        R.drawable.ys5_logo,
        "Ys V: Lost Kefin, Kingdom of Sand",
        1995
    ),
    Series(
        R.drawable.ys6_logo,
        "Ys VI: The Ark of Naphishtim",
        2003
    ),
    Series(
        R.drawable.ys7_logo,
        "Ys VII: Five Dragons of Altago",
        2009
    ),
    Series(
        R.drawable.ys8_logo,
        "Ys VIII: Lacrimosa of Dana",
        2016
    ),
    Series(
        R.drawable.ys9_logo,
        "Ys IX: Monstrum Nox",
        2019
    ),
    Series(
        R.drawable.ys10_logo,
        "Ys X: Nordics", 2023
    )
)