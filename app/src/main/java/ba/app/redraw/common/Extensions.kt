package ba.app.redraw.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.getCurrentVisibleFragment(): Fragment = try {
    supportFragmentManager.fragments[0].childFragmentManager.fragments[0]
} catch (e: Exception) {
    supportFragmentManager.fragments[0]
}

fun Context.getStringByName(resourceName: String): String {
    return try {
        resources.getIdentifier(resourceName, "string", packageName).let {
            getString(it)
        }
    } catch (ex: java.lang.Exception) {
        ""
    }
}

@Suppress("DEPRECATION")
fun Context.resolveColor(@ColorRes color: Int, theme: Resources.Theme? = null): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, theme)
    } else {
        resources.getColor(color)
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
@Suppress("DEPRECATION")
fun Context.resolveDrawable(@DrawableRes drawable: Int, theme: Resources.Theme? = null): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getDrawable(drawable, theme)
    } else {
        resources.getDrawable(drawable)
    }
}
