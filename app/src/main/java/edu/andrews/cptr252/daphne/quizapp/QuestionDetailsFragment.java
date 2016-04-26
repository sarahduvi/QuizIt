package edu.andrews.cptr252.daphne.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Shows details for a question and allows user to edit them
 */
public class QuestionDetailsFragment extends Fragment {
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onQuestionUpdated(Question question);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }


    /**Question that is being viewed or edited.*/
    private Question mQuestion;
    /**Reference to text field for question.*/
    private EditText mQuestionField;

    private static final String TAG = "QuestionDetailsFragment";

    /**Reference to answer checkbox*/
    private CheckBox mAnswerCheckBox;

    /**Key used to pass the id of a question*/
    public static final String EXTRA_QUESTION_ID =
            "edu.andrews.cptr252.daphne.quizapp.question_id";

    public static QuestionDetailsFragment newInstance(UUID questionId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION_ID, questionId);

        QuestionDetailsFragment fragment = new QuestionDetailsFragment();

        fragment.setArguments(args);
        return fragment;
    }

    public QuestionDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID questionId = (UUID)getArguments().getSerializable(EXTRA_QUESTION_ID);

        //gets the correct question to display
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(questionId);


    }

    //Creates view for layout which will be adding a question to the list
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_question, container, false);
        mQuestionField = (EditText) v.findViewById(R.id.edit_question_text);
        mQuestionField.setText(mQuestion.getQuestion());
        mQuestionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mQuestion.setQuestion(s.toString());
                Log.d(TAG, "Title change to " + mQuestion.getQuestion());
                mCallbacks.onQuestionUpdated(mQuestion);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //left intentionally blank
            }
        });
        mAnswerCheckBox = (CheckBox) v.findViewById(R.id.answer_checkbox);
        mAnswerCheckBox.setChecked(mQuestion.isTrue());
        mAnswerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mQuestion.setAnswer(isChecked);
                Log.d(TAG, "Set answer to " + isChecked);
                mCallbacks.onQuestionUpdated(mQuestion);
            }
        });
        return v;
    }

    /**
     * saves Question when user exits the fragment_add_question layout
     */
    @Override
    public void onPause() {
        super.onPause();
        QuestionList.getInstance(getActivity()).saveQuestions();
    }

}