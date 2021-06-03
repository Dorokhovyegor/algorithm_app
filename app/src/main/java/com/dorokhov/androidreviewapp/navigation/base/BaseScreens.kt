package com.dorokhov.androidreviewapp.navigation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.pure.AppScreen
import ru.terrakok.cicerone.android.support.SupportAppScreen

const val EXTRA_SCREEN_DATA = "EXTRA_SCREEN_DATA"
const val EXTRA_SCREEN_DEEPLINK = "EXTRA_SCREEN_DEEPLINK"

/**
 * Класс для создания навигационного screen в виде activity,
 * @param args будут переданы как intent extra data соответствующей активити
 */
abstract class ActivityScreen(
    private val args: Parcelable? = null
) : SupportAppScreen() {
    override fun getActivityIntent(context: Context): Intent {
        val intent = Intent(context, clazz)
        args?.let {
            intent.putExtra(EXTRA_SCREEN_DATA, args)
        }
        flags?.let { intent.addFlags(it) }
        return intent
    }

    //TODO think about moving flags to params
    open val flags: Int? = null

    abstract val clazz: Class<*>
}

/**
 * Класс для создания навигационного screen в виде фрагмента,
 * @param data будут присвоены в качестве arguments соответствующему фрагменту
 */
abstract class FragmentScreen(
    private val data: Parcelable? = null
) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        val fragment = fragmentScreen

        data?.let {
            fragment.arguments = Bundle().setDto(it)
        }
        return fragment
    }

    abstract val fragmentScreen: Fragment
}

/**
 * Получение Dto, переданного при вызове activity
 */
fun <T : Parcelable> Intent?.getDto(): T? {
    return this?.getParcelableExtra(EXTRA_SCREEN_DATA)
}

/**
 * Получение Dto, переданного fragment
 */
fun <T : Parcelable> Bundle?.getDto(): T? {
    return this?.getParcelable(EXTRA_SCREEN_DATA)
}

/**
 * Установить Dto в bundle
 */
fun <T : Parcelable> Bundle.setDto(dto: T?): Bundle {
    this.putParcelable(EXTRA_SCREEN_DATA, dto)
    return this
}

/**
 * Получение Dto, переданного при вызове activity
 */
fun <T : Parcelable> Intent?.getDeeplinkDto(): T? {
    return this?.getParcelableExtra(EXTRA_SCREEN_DEEPLINK)
}

fun <T : Parcelable> Intent?.consumeDeeplinkDto(): T? {
    return this?.getParcelableExtra<T>(EXTRA_SCREEN_DEEPLINK)?.also {
        this.removeExtra(
            EXTRA_SCREEN_DEEPLINK
        )
    }
}