package hr.fer.nox.core

sealed class Either<out Left, out Right> {

    data class Left<out Left>(val value: Left) : Either<Left, Nothing>()
    data class Right<out Right>(val value: Right) : Either<Nothing, Right>()

    /**
     * Applies `ifLeft` if this is a [Left] or `ifRight` if this is a [Right].
     *
     * Example:
     * ```
     * val result: Either<Exception, Value> = possiblyFailingOperation()
     * result.fold(
     *      { log("operation failed with $it") },
     *      { log("operation succeeded with $it") }
     * )
     * ```
     *
     * @param ifLeft the function to apply if this is a [Left]
     * @param ifRight the function to apply if this is a [Right]
     * @return the results of applying the function
     */
    inline fun <Result> fold(ifLeft: (Left) -> Result, ifRight: (Right) -> Result): Result =
        when (this) {
            is Either.Left -> ifLeft(value)
            is Either.Right -> ifRight(value)
        }
}