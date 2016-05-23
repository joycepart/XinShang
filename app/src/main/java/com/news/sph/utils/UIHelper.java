package com.news.sph.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.news.sph.common.base.SimpleActivity;
import com.news.sph.common.base.SimplePage;

public class UIHelper {

    public static void showFragment(Context context, SimplePage page) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showFragment(Context context, SimplePage page,
                                    Bundle args) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        context.startActivity(intent);
    }

}
