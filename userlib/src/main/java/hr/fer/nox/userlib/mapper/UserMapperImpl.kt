package hr.fer.nox.userlib.mapper

import hr.fer.nox.userlib.model.api.ApiUserShort
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.model.api.ApiUserDetails


class UserMapperImpl(): UserMapper {

    override fun map(apiUserShort: ApiUserShort): User =
        User(
            apiUserShort.id,
            apiUserShort.name,
            apiUserShort.surname,
            apiUserShort.email
        )

    override fun map(apiUsersShort: List<ApiUserShort>): List<User> =
        apiUsersShort.map(this::map)


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

    override fun mapUserDetails(apiUsersDetails: List<ApiUserDetails>): List<UserDetails> =
        apiUsersDetails.map(this::map)
}
