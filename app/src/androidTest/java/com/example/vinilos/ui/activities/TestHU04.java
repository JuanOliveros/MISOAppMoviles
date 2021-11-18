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
public class TestHU04 {


    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);



    @Test
    public void guestListArtistsTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Artistas")).perform(click());

        Thread.sleep(2000);


    }
    @Test
    public void guestClickDetailArtistTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Artistas")).perform(click());

        Thread.sleep(2000);

        onView(withText("Queen")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.artist_detail_description)).check(matches(withText(containsString("Londres"))));


    }
}
