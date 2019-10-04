package hr.fer.nox.coreui.base

import androidx.lifecycle.ViewModel
import hr.fer.nox.coreui.lifecycle.Destroyable
import hr.fer.nox.coreui.lifecycle.Destroyables
import org.koin.core.KoinContext

class ScopeRetainer(
    private val koin: KoinContext
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
        koin.getOrCreateScope(definedScope)
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