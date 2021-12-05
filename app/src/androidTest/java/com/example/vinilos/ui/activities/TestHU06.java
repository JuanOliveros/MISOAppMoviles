package com.example.vinilos.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.StringContains.containsString;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.rule.ActivityTestRule;

import com.example.vinilos.EntranceActivity;
import com.example.vinilos.R;

import org.junit.Rule;
import org.junit.Test;

public class TestHU06 {

    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);



    @Test
    public void guestSeeCollectorMenuTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Coleccionistas")).perform(click());

        Thread.sleep(2000);

    }

    @Test
    public void guestListCollectorTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Coleccionistas")).perform(click());

        Thread.sleep(2000);

        onView(anyOf(withId(R.id.fragment_collector)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void guestClickCollectorDetailTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Coleccionistas")).perform(click());

        Thread.sleep(2000);

        onView(anyOf(withId(R.id.fragment_collector)))
                .check(matches(isDisplayed()));

        Thread.sleep(2000);
        onView(withText("Manolo Bellon")).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.collectorDetailEmail)).check(matches(withText(containsString("@"))));


    }
}
