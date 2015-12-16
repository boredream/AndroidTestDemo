package com.boredream.androidespressotest;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test1() {
        // rule中的构造函数没有第三个参数false时,即默认为true会加载页面,那么这里就不用再lauch了
//        Intent intent = new Intent();
//        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_citypinyin)).perform(typeText("aisaiebiya"), closeSoftKeyboard());
        onView(withId(R.id.btn_get_weather)).perform(click());
        onView(withId(R.id.tv_weather)).check(matches(withText(startsWith("获取失败"))));

    }

    @Test
    public void test2() {
        onView(withId(R.id.et_citypinyin)).perform(typeText("nanjing"), closeSoftKeyboard());
        onView(withId(R.id.btn_get_weather)).perform(click());
        // 这个测试其实和test3是一个意思,只不过是取反再加not,相当于双重否定,只是示范下api的多种组合用法
        onView(withId(R.id.tv_weather)).check(matches(withText(not("获取失败"))));
    }

    @Test
    public void test3() {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_citypinyin)).perform(typeText("nanjing"), closeSoftKeyboard());
        onView(withId(R.id.btn_get_weather)).perform(click());
        onView(withId(R.id.tv_weather)).check(matches(withText(startsWith("温度"))));
    }
}




