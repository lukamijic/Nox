package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import hr.fer.nox.movieslib.api.models.old.OldApiCredits
import hr.fer.nox.movieslib.api.models.old.OldApiVideo

@JsonClass(generateAdapter = true)
data class ApiMovieDetails(
    @Json(name = "movieDBid") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val synopsis: String,
    @Json(name = "releaseDate") val releaseDate: String,
    @Json(name = "genres") val genres: List<String>?, /** Dovoljna samo lista imena zanrova*/
    @Json(name = "runtime") val runtimeInMinutes: Int?, /** Runtime u minutama*/
    @Json(name = "imageURL") val posterPath: String?,
    @Json(name = "score") val score: ApiScore,
    @Json(name = "videos") val videos: List<OldApiVideo>?, /** Pogledaj ovaj OldApiVideo, mozes samo dodati orginalni json, ja radim vec interno filtriranje tih videa da izvucem s youtubea i da je pravi trailer */
    @Json(name = "credits") val credits: OldApiCredits? /** Treba mi i crew i lista glumaca, ako ti zelis mozes ti isfiltrirati directora iz crewa jer mi samo on treba*/
)