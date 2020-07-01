package com.example.androidtestlearning

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlinx.android.synthetic.main.activity_main.*
import org.robolectric.Robolectric
import org.robolectric.Shadows
/**
 * Created by Wenxi on 2020/6/30 16:23
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @Test
    @Throws(Exception::class)
    fun clickText() {
        val scenario = ActivityScenario.launch(
            MainActivity::class.java
        )

        scenario.onActivity { activity ->
            activity.tv_turn_new_activity.performClick()
            val expectedIntent = Intent(activity, SecondActivity::class.java)
            val shadowActivity = Shadows.shadowOf(activity)
            val actualIntent = shadowActivity.nextStartedActivity
            assertEquals(expectedIntent, actualIntent)
        }
    }
}