package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test 1: Check whether the activity correctly switched
     * This test verifies that clicking on a city item launches ShowActivity
     */
    @Test
    public void testActivitySwitch() {
        // Add a city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Toronto"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify that ShowActivity is displayed by checking if the city name TextView is visible
        onView(withId(R.id.textView_cityName)).check(matches(isDisplayed()));
    }

    /**
     * Test 2: Test whether the city name is consistent
     * This test verifies that the city name clicked is correctly displayed in ShowActivity
     */
    @Test
    public void testCityNameConsistency() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Montreal"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify that the city name displayed in ShowActivity matches what was clicked
        onView(withId(R.id.textView_cityName)).check(matches(withText("Montreal")));
    }

    /**
     * Test 3: Test the "back" button
     * This test verifies that clicking the back button returns to MainActivity
     */
    @Test
    public void testBackButton() {
        // Add a city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Ottawa"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city to open ShowActivity
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify we're in ShowActivity
        onView(withId(R.id.textView_cityName)).check(matches(isDisplayed()));

        // Click the back button
        onView(withId(R.id.button_back)).perform(click());

        // Verify we're back in MainActivity by checking if the Add button is visible
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}
