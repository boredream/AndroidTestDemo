package com.boredream.androidespressotest;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    // ---------------------------    登录测试用例   ---------------------------
    //
    //        编号      输入/动作                            期望的输出/相应
    //        1         使用合法用户名和密码登陆              登陆成功,进入主页
    //        2         使用错误的用户名或密码登陆            显示用户名或密码错误提示信息
    //        3         用户名为空登陆                       显示请输入用户名提示信息
    //        4         密码为空进行登陆                      显示请输入密码提示信息
    //        5         用户名和密码都为空进行登陆            显示请输入用户名提示信息(由上到下以依次判断)

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class, true, false);

    @Test
    public void test1() {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_username)).perform(typeText("boredream"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(longClick());

        onView(withId(R.id.tv_title))
                 .check(matches(withText("主页")));
    }

    @Test
    public void test2() throws InterruptedException {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_username)).perform(typeText("boredream"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("111111"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        onView(withId(android.R.id.message))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("用户")));

//        onView(withText("用户名或密码错误"))
//                 .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
//                 .check(matches(isDisplayed()));
    }

    @Test
    public void test3() throws InterruptedException {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_username)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(1000);

        onView(withText("请输入用户名"))
                 .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                 .check(matches(isDisplayed()));
    }

    @Test
    public void test4() throws InterruptedException {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_username)).perform(typeText("boredream"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(1000);

        onView(withText("请输入密码"))
                 .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                 .check(matches(isDisplayed()));
    }

    @Test
    public void test5() throws InterruptedException {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.et_username)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(1000);

        onView(withText("请输入用户名"))
                 .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                 .check(matches(isDisplayed()));
    }

}


