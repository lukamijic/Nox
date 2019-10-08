package hr.fer.nox.coreui.base

import androidx.lifecycle.ViewModel
import hr.fer.nox.coreui.lifecycle.Destroyable
import hr.fer.nox.coreui.lifecycle.Destroyables
import org.koin.core.Koin
import org.koin.core.qualifier.named

class ScopeRetainer(
    val koin: Koin
): ViewModel() {

    private lateinit var definedScope: String

    private val destroyables = Destroyables()

    fun setScope(scope: String) {
        if (this::definedScope.isInitialized) {
            if (definedScope != scope) {
                throw IllegalStateException("Invalid new scope $scope for scoped VM with scope $definedScope")
            }
            return
        }

        definedScope = scope
        koin.getOrCreateScope(definedScope, named(definedScope))
    }

    fun addDestroyable(destroyable: Destroyable) {
        destroyables.addDestroyable(destroyable)
    }

    override fun onCleared() {
        try {
            koin.getScope(definedScope).close()
            destroyables.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}