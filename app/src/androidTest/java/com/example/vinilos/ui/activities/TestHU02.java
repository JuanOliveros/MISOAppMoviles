package com.example.vinilos.ui.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anyOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.rule.ActivityTestRule;

import com.example.vinilos.EntranceActivity;
import com.example.vinilos.R;

import org.junit.Rule;
import org.junit.Test;

public class TestHU02 {

    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);



    @Test
    public void guestClickDetailAlbumTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(2000);

        onView(withText("Buscando América")).perform(click());
        Thread.sleep(2000);


    }
    @Test
    public void guestSeesDetailAlbumTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(2000);

        onView(withText("Buscando América")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.albumDetailReleaseDate)).check(matches(withText("1984-08-01")));


    }
}
