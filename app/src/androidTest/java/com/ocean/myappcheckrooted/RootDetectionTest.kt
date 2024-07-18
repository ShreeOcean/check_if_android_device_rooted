package com.ocean.myappcheckrooted

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RootDetectionTest {

    @Rule
    @JvmField
    val  activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRootedDevice(){
        if (DeviceRooted.isDeviceRooted){
            Espresso.onView(ViewMatchers.withText("Device Rooted"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }else{
            Espresso.onView(ViewMatchers.withId(R.id.btn_check_rooted))
                .perform(ViewActions.click())
        }
    }
}