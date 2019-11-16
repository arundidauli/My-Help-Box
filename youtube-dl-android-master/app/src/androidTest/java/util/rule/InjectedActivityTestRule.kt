package util.rule

import android.app.Activity
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import dagger.android.AndroidInjector
import util.HasTestInjectors

/**
 * Created by cuongpm on 1/29/19.
 */


class InjectedActivityTestRule<T : Activity>(
    activityClass: Class<T>,
    private val activityInjector: (T) -> Unit
) : IntentsTestRule<T>(
    activityClass,
    false,
    false
) {

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()

        setActivityInjector()
    }

    private fun setActivityInjector() {
        val testApp = InstrumentationRegistry.getTargetContext().applicationContext as HasTestInjectors
        testApp.activityInjector = AndroidInjector {
            @Suppress("UNCHECKED_CAST")
            activityInjector(it as T)
        }
    }
}
