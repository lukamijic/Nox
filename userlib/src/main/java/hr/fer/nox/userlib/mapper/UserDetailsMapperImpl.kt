package hr.fer.nox.userlib.mapper

import hr.fer.nox.userlib.model.ApiUserDetails
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
                gender,
                age,
                iq
            )
        }
    }
}