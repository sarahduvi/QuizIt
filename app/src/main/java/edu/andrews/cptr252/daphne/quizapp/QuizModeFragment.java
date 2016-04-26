package edu.andrews.cptr252.daphne.quizapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by daphne on 4/20/2016.
 */
public class QuizModeFragment extends Fragment {

    public static final String KEY_QUESTION_INDEX = "questionIndex";
    private TextView mQuestionTextView;
    private Button mPreviousButton;
    private Button mNextButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private int mCurrentIndex = 0;

    private ArrayList<Question> mQuestionArrayList = new ArrayList<Question>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.question_list_label);
        mQuestionArrayList = QuestionList.getInstance(getActivity()).getQuestions();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_QUESTION_INDEX, mCurrentIndex);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_activity, container, false);
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX);
        }

        //a question is selected from list and set question as the current on quiz
        mQuestionTextView = (TextView) v.findViewById(R.id.question_text_textView);
        Question question = mQuestionArrayList.get(mCurrentIndex);
        mQuestionTextView.setText(question.getQuestion());


        //update question to previous question unless the question is the first one
        mPreviousButton = (Button) v.findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex != 0){
                    mCurrentIndex--;
                }
                updateQuestion();
            }
        });

        //set the next button to update questions on click
        mNextButton = (Button) v.findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex++;
                if (mCurrentIndex == mQuestionArrayList.size()) {
                    mCurrentIndex = 0;
                }
                updateQuestion();
            }
        });


        /**
         * @mTrueButton returns toast upon click
         */
        mTrueButton = (Button) v.findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = mQuestionArrayList.get(mCurrentIndex);
                if(question.isTrue()) {
                    Toast.makeText(getActivity(), R.string.correct_toast,
                            Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getActivity(), R.string.wrong_toast,
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        /**
         * @mFalseButton returns toast upon click
         */

        mFalseButton = (Button) v.findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Question question = mQuestionArrayList.get(mCurrentIndex);
                if(!question.isTrue()) {
                    Toast.makeText(getActivity(), R.string.correct_toast,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), R.string.wrong_toast,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    //updates question
    private void updateQuestion(){
        Question question = mQuestionArrayList.get(mCurrentIndex);

        mQuestionTextView.setText(question.getQuestion());


    }

}
