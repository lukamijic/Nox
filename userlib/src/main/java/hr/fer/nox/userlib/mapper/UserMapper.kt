package hr.fer.nox.userlib.mapper

import hr.fer.nox.userlib.model.api.ApiUserShort
import hr.fer.nox.userlib.model.User

interface UserMapper {

    fun map(apiUserShort: ApiUserShort): User

    fun map(apiUsersShort: List<ApiUserShort>): List<User>
}