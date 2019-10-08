package hr.fer.nox.app.di

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import hr.fer.nox.app.router.RouterImpl
import hr.fer.nox.coreui.util.*
import hr.fer.nox.navigation.router.Router
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule = module {

    factory<Router> { (activity: AppCompatActivity) -> RouterImpl(activity, activity.supportFragmentManager) }

    factory<ActionRouter> { ActionRouterImpl() }

    single<ResourceUtils> { ResourceUtilsImpl(androidApplication().resources) }

    single<ImageUtils> { ImageUtilsImpl(androidContext()) }

    single { androidApplication().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    factory {
        val activity: Activity = it[0]
        val watcher = KeyboardWatcherImpl(activity, get())
        watcher as KeyboardWatcher
    }

    factory<KeyboardUtils> {
        KeyboardUtilsImpl(get())
    }
}