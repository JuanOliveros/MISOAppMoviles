package com.example.vinilos.ui.activities;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import com.example.vinilos.EntranceActivity;

import com.example.vinilos.R;
import com.example.vinilos.ui.fragments.AlbumsFragment;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestHU01 {

    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);

    @Test
    public void accessAsGuestTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());
    }

    @Test
    public void guestSeesAlbumsOptionInMenuTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());
    }

    @Test
    public void albumsAreListedInAlbumsSectionTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(2000);

        onView(anyOf(withId(R.id.fragment_album)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void guestCanExitFromMainScreenTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        Thread.sleep(1000);

        onView(anyOf(withId(R.id.exit_guest_button)))
                .perform(click());

        onView(withId(R.id.guest_button))
                .check(matches(withText("Invitado")));

    }

    @Test
    public void guestCanExitFromMenuTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.guest_button));
        guestBtn.check(matches(withText("Invitado")));
        guestBtn.perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        Thread.sleep(1000);

        onView(anyOf(withId(R.id.logout_button)))
                .perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.guest_button))
                .check(matches(withText("Invitado")));

    }
}