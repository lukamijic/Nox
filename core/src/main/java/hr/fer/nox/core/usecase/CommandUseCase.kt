package hr.fer.nox.core.usecase

import io.reactivex.Completable

interface CommandUseCase {

    operator fun invoke(): Completable
}