package com.boredream.androidespressotest.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.boredream.androidespressotest.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class TestActivityTest {

	@Rule
	public ActivityTestRule<TestActivity> mActivityRule = new ActivityTestRule<>(TestActivity.class, true, false);

	@Test
	public void test() {
		Intent intent = new Intent();
		mActivityRule.launchActivity(intent);

		// actions
		onView(withId(R.id.et_username)).perform(typeText("type some text here"), closeSoftKeyboard());
		onView(withId(R.id.et_password)).perform(typeText("type some text here"), closeSoftKeyboard());
		onView(withId(R.id.btn_login)).perform(click());
		onView(withId(R.id.tv_regist)).perform(click());

		// assertions
	}
}
