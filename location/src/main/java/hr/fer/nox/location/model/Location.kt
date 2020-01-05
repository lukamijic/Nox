package hr.fer.nox.location.model

data class Location(
    val lat: Float,
    val long: Float
) {

    companion object {

        val EMPTY = Location(Float.MAX_VALUE, Float.MAX_VALUE)
    }
}