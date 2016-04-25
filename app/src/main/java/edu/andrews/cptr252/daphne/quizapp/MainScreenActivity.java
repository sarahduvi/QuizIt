package edu.andrews.cptr252.daphne.quizapp;

import android.support.v4.app.Fragment;

/**
 * Created by Daphne on 4/25/2016.
 */
public class MainScreenActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {return new MainScreenFragment();}
}
