package hr.fer.nox.core.usecase

import io.reactivex.Flowable

interface QueryUseCaseWithParameter<Parameter, Result> {

    operator fun invoke(param: Parameter): Flowable<Result>
}