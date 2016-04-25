package edu.andrews.cptr252.daphne.quizapp;

/**
 * Created by Daphne on 2/8/2016.
 */

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class QuestionListActivity extends SingleFragmentActivity
        implements QuestionListFragment.Callbacks, QuestionDetailsFragment.Callbacks {

    public void onQuestionSelected(Question question) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            Intent i = new Intent(this, QuizModeDetailsActivity.class);
            //i.putExtra(QuestionDetailsFragment.EXTRA_BUG_ID, question.getmId());
            startActivityForResult(i, 0);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = QuestionDetailsFragment.newInstance(question.getmId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onBugUpdated(Question question) {
        FragmentManager fm = getSupportFragmentManager();
        QuestionListFragment listFragment = (QuestionListFragment)
                fm.findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    @Override
    protected Fragment createFragment() {

        return new QuestionListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onQuestionUpdated(Question question) {

    }
}
