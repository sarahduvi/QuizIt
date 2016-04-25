package edu.andrews.cptr252.daphne.quizapp;

import android.support.v4.app.Fragment;

/**
 * Created by Daphne on 4/12/2016.
 */
public class QuizMode extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {return new QuizModeFragment();}
}
