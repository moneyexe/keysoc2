package com.example.Model

import androidx.lifecycle.LiveData
import java.util.*

data class AlbumsListModel(
    var resultCount: Int,
    var results: List<Result>
)

data class Result(
    val collectionId: Int,
    val amgArtistId: Int,
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl60: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionType: String,
    val collectionViewUrl: String,
    val contentAdvisoryRating: String,
    val copyright: String,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCount: Int,
    val wrapperType: String,
    var isbookMark: Boolean = false
)