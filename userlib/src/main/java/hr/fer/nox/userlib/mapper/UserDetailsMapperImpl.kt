package hr.fer.nox.userlib.mapper

import hr.fer.nox.userlib.model.api.ApiUserDetails
import hr.fer.nox.userlib.model.UserDetails

class UserDetailsMapperImpl(
): UserDetailsMapper {



    override fun map(apiUserDetails: ApiUserDetails): UserDetails {

        return with(apiUserDetails) {
            UserDetails(
                id,
                name,
                surname,
                email,
                imageUrl
            )
        }
    }
}