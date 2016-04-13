package com.bwanamkaya.blogfollower.ui;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by bwana.mkaya on 30/12/15.
 */
public class UIManager {
    Context context;

    public static void ReplaceFragment(FragmentManager manager, @IdRes int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerViewId, fragment).addToBackStack(null).commit();
    }
}
