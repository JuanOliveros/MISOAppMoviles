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
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.vinilos.EntranceActivity;
import com.example.vinilos.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestHU07 {

    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);

    @Test
    public void accessAsCollectorTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.collector_button));
        guestBtn.check(matches(withText("Coleccionista")));
        guestBtn.perform(click());
    }

    @Test
    public void collectorSeesAlbumsOptionInMenuTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.collector_button));
        guestBtn.check(matches(withText("Coleccionista")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());
    }

    @Test
    public void collectorCanExitFromMainScreenTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.collector_button));
        guestBtn.check(matches(withText("Coleccionista")));
        guestBtn.perform(click());

        Thread.sleep(1000);

        onView(anyOf(withId(R.id.exit_collector_button)))
                .perform(click());

        onView(withId(R.id.collector_button))
                .check(matches(withText("Coleccionista")));

    }

    @Test
    public void collectorCanExitFromMenuTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.collector_button));
        guestBtn.check(matches(withText("Coleccionista")));
        guestBtn.perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        Thread.sleep(1000);

        onView(anyOf(withId(R.id.logout_button)))
                .perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.collector_button))
                .check(matches(withText("Coleccionista")));

    }

    @Test
    public void collectorCanAccessAlbumCreationFormTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.collector_button));
        guestBtn.check(matches(withText("Coleccionista")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(1000);

        onView(anyOf(withId(R.id.createAlbumButton)))
                .perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.createAlbumLabel))
                .check(matches(withText("Crear álbum")));

    }
}