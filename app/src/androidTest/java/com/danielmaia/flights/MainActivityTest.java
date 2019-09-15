package com.danielmaia.flights;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.danielmaia.flights.views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void checkInitialStateMainActivity() {
        onView(withId(R.id.txtTitle1)).check(matches(isDisplayed()));
        onView(withId(R.id.txtTitle2)).check(matches(isDisplayed()));
        onView(withId(R.id.txtFilter)).check(matches(isDisplayed()));
        onView(withId(R.id.txtSort)).check(matches(isDisplayed()));
    }


}
