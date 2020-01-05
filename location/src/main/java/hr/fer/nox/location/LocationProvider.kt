package hr.fer.nox.location

import hr.fer.nox.location.model.Location

interface LocationProvider {

    fun getLocation(): Location
}