package com.happylrd.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by lenovo on 2016/8/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
