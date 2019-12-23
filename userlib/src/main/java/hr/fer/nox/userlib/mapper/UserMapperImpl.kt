package hr.fer.nox.userlib.mapper

import hr.fer.nox.userlib.model.ApiUserShort
import hr.fer.nox.userlib.model.User


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
}