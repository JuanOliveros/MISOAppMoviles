package com.example.vinilos.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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

    @Test
    public void collectorFillsFormToCreateNewAlbumTest() throws InterruptedException {
        ViewInteraction guestBtn = onView(withId(R.id.collector_button));
        guestBtn.check(matches(withText("Coleccionista")));
        guestBtn.perform(click());

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer

        onView(withText("Álbumes")).perform(click());

        Thread.sleep(3000);

        onView(anyOf(withId(R.id.createAlbumButton)))
                .perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.createAlbumLabel))
                .check(matches(withText("Crear álbum")));

        ViewInteraction nameField = onView(withId(R.id.albumNameField));
        nameField.perform(scrollTo(), replaceText("Album de prueba Equipo 12"), closeSoftKeyboard());

        ViewInteraction coverField = onView(withId(R.id.albumCoverField));
        coverField.perform(scrollTo(), replaceText("Url for new cover"), closeSoftKeyboard());

        ViewInteraction dateField = onView(withId(R.id.albumReleaseDateField));
        dateField.perform(scrollTo(), replaceText("12-20-1993"), closeSoftKeyboard());

        ViewInteraction descriptionField = onView(withId(R.id.albumDescriptionField));
        descriptionField.perform(scrollTo(), replaceText("Descripcion del album de prueba"), closeSoftKeyboard());

        onView(anyOf(withId(R.id.submitAlbumCreateButton)))
                .perform(click());

        Thread.sleep(1500);

        onView(withId(R.id.createAlbumButton))
                .check(matches(withText("Crear álbum")));

        // se valida que el formulario se llena exitosamente y vuelve al listado de albumes
    }
}