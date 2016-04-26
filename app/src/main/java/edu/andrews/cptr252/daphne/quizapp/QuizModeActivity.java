package edu.andrews.cptr252.daphne.quizapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Daphne on 4/12/2016.
 */
public class QuizModeActivity extends SingleFragmentActivity /*implements QuestionDetailsFragment.Callbacks */{
    @Override
    protected Fragment createFragment() {return new QuizModeFragment();}
    /*public void onQuestionUpdated(Question question) {
        //do nothing
    }

    private ViewPager mViewPager;

    private ArrayList<Question> mQuestions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        //gets question list
        mQuestions = QuestionList.getInstance(this).getQuestions();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Question question = mQuestions.get(i);
                return QuestionDetailsFragment.newInstance(question.getmId());
            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });

        UUID questionId = (UUID)getIntent().getSerializableExtra(QuestionDetailsFragment.EXTRA_QUESTION_ID);

        for (int i = 0; i < mQuestions.size(); i++) {
            if(mQuestions.get(i).getmId().equals(questionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                Question question = mQuestions.get(i);
                if (question.getQuestion() != null) {
                    setQuestion(question.getQuestion());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    .setAction("Action", null).show();
    }
    });
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.menu_question_list, menu);
     return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
     // Handle action bar item clicks here. The action bar will
     // automatically handle clicks on the Home/Up button, so long
     // as you specify a parent activity in AndroidManifest.xml.
     int id = item.getItemId();

     //noinspection SimplifiableIfStatement
     if (id == R.id.action_settings) {
     return true;
     }

     return super.onOptionsItemSelected(item);
     } */
    }