package com.example.vinilos.ui.activities;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.StringContains.containsString;

import static java.util.EnumSet.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.vinilos.EntranceActivity;
import com.example.vinilos.R;

import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.Matchers.anything;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestHU08 {
    @Rule
    public ActivityTestRule<EntranceActivity> mActivityTestRule = new ActivityTestRule<>(EntranceActivity.class);


   @Test
    public void collector1SeeBtnAsociateTest() throws InterruptedException {
        ViewInteraction collectorBtn = onView(withId(R.id.collector_button));
        collectorBtn.check(matches(withText("Coleccionista")));
        collectorBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(4000);
        onView(withText("Buscando América")).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.linkTrackButton)).check(matches(withText(containsString("ASOCIAR TRACKS"))));
       Thread.sleep(3000);

    }

    // Definir el nombre de la canción -- funciona para el escenario 2 y 3.
    String trackName = "Salsita3";


    @Test
    public void collector2CreateAsociateTrackTest() throws InterruptedException {
        ViewInteraction collectorBtn = onView(withId(R.id.collector_button));
        collectorBtn.check(matches(withText("Coleccionista")));
        collectorBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(4000);
        onView(withText("Buscando América")).perform(click());
        Thread.sleep(3000);
        onView(withText("ASOCIAR TRACKS")).perform(click());
        onView(withId(R.id.trackNameField)).perform(typeText(trackName));
        onView(withId(R.id.trackDurationField)).perform(typeText("10"));
        onView(withText("ASOCIAR")).perform(click());
        Thread.sleep(2000);

    }

   @Test
    public void collector3ListTrackCreatedTest() throws InterruptedException {
        ViewInteraction collectorBtn = onView(withId(R.id.collector_button));
        collectorBtn.check(matches(withText("Coleccionista")));
        collectorBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());
        Thread.sleep(4000);

        onView(withText("Buscando América")).perform(click());
        Thread.sleep(2000);


        onView(withId(R.id.albumTracksList)).check(matches(isDisplayed()));
        Thread.sleep(4000);


        onView(withId(R.id.albumTracksList)).perform(actionOnItemAtPosition(66, scrollTo()));
        Thread.sleep(5000);
        onView(withId(R.id.albumTracksList)).check(matches(isDisplayed()));
        onView(withText(trackName)).perform(click());
        Thread.sleep(5000);

    }
}
