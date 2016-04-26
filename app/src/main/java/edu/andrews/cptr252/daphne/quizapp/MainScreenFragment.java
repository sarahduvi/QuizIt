package edu.andrews.cptr252.daphne.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Daphne on 4/25/2016.
 */
public class MainScreenFragment extends Fragment {
    private Button mQuestionListButton;
    private Button mQuizModeButton;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mainscreen_activity, container, false);
        super.onCreate(savedInstanceState);

        mQuestionListButton = (Button) v.findViewById(R.id.question_list_button);
        mQuestionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //setTargetFragment(QuestionListFragment).show();
                //QuestionList.getInstance(getActivity());
                //view = inflater.inflate(R.layout.fragment_add_question, container);
                Intent intent = new Intent(getActivity().getApplicationContext(), QuestionListActivity.class);
                startActivity(intent);
            }
        });

        mQuizModeButton = (Button) v.findViewById(R.id.quiz_mode_button);
        mQuizModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                //QuizModeActivity.getInstance(getActivity()).getQuestions();
                //view = inflater.inflate(R.layout.quiz_mode_fragment, container);
                Intent intent = new Intent(getActivity().getApplicationContext(), QuizModeActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
