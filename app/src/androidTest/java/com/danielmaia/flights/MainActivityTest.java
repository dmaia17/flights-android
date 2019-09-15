package com.danielmaia.flights;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.danielmaia.flights.views.FilterActivity;
import com.danielmaia.flights.views.MainActivity;
import com.danielmaia.flights.views.SortActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void checkInitialStateMainActivity() {
        onView(withId(R.id.txtTitle1)).check(matches(isDisplayed()));
        onView(withId(R.id.txtTitle2)).check(matches(isDisplayed()));
        onView(withId(R.id.txtFilter)).check(matches(isDisplayed()));
        onView(withId(R.id.txtSort)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfFilterActivityIsCalled() {
        Intents.init();
        Matcher<Intent> matcher = hasComponent(FilterActivity.class.getName());

        onView(withId(R.id.rlFilter)).perform(click());
        intended(matcher);
        Intents.release();
    }

    @Test
    public void checkIfSortActivityIsCalled() {
        Intents.init();
        Matcher<Intent> matcher = hasComponent(SortActivity.class.getName());

        onView(withId(R.id.rlSort)).perform(click());
        intended(matcher);
        Intents.release();
    }


}
