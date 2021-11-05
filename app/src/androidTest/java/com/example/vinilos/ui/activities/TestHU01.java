package com.example.vinilos.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.vinilos.EntranceActivity;

import com.example.vinilos.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestHU01 {

    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);

    @Test
    public void mainActivityTest2() throws InterruptedException {
        ViewInteraction skipBtn = onView(withId(R.id.guest_button));
        skipBtn.check(matches(withText("Invitado")));
        skipBtn.perform(click());
    }
}