package hr.fer.nox.core.usecase

import io.reactivex.Completable

interface CommandUseCaseWithParameter<Parameter> {

    operator fun invoke(parameter: Parameter): Completable
}