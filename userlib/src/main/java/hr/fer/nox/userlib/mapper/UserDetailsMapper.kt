package hr.fer.nox.userlib.mapper

import hr.fer.nox.userlib.model.api.ApiUserDetails
import hr.fer.nox.userlib.model.UserDetails

interface UserDetailsMapper {

    fun map(apiUserDetails: ApiUserDetails): UserDetails
}