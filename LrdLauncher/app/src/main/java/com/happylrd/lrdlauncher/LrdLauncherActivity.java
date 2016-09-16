package com.happylrd.lrdlauncher;

import android.support.v4.app.Fragment;

public class LrdLauncherActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return LrdLauncherFragment.newInstance();
    }
}
