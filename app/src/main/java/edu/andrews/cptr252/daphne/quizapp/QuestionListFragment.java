package edu.andrews.cptr252.daphne.quizapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.ListFragment;
import java.util.ArrayList;

import android.view.ActionMode;
import android.view.ContextMenu;
import android.widget.AbsListView;
import android.app.Activity;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends ListFragment {

    private Callbacks mCallbacks;

    public interface Callbacks {
        void onQuestionSelected(Question question);
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

    public void updateUI() {
        ((QuestionAdapter)getListAdapter()).notifyDataSetChanged();
    }


    public static final String TAG = "QuestionListFragment";
    private ArrayList<Question> mQuestions;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    private class QuestionAdapter extends ArrayAdapter<Question>
    {
        public QuestionAdapter(ArrayList<Question> questions) {
            super(getActivity(), 0, questions);
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_question, null);
            }

            Question question = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.question_list_item_titleTextView);
            titleTextView.setText(question.getmTitle());

            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.question_answered_checkbox);
            solvedCheckBox.setChecked(question.ismSolved());

            return convertView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.question_list_label);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        QuestionAdapter adapter = new QuestionAdapter(mQuestions);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Question question = (Question)(getListAdapter()).getItem(position);
        Log.d(TAG, question.getmTitle() + "was clicked");

        mCallbacks.onQuestionSelected(question);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((QuestionAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private void addQuestion() {
        Question question = new Question();
        QuestionList.getInstance(getActivity()).addQuestion(question);

        mCallbacks.onQuestionSelected(question);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_list, container, false);

        View addButton = v.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestion();
            }
        });

        ListView listView = (ListView) v.findViewById(android.R.id.list);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.question_list_item_context, menu);
                return true;
            }

            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                //nothing to do
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_delete_bug:
                        QuestionAdapter adapter = (QuestionAdapter) getListAdapter();
                        QuestionList questionList = QuestionList.getInstance(getActivity());

                        for (int i = adapter.getCount() - 1; i >= 0; i--) {
                            if (getListView().isItemChecked(i)) {
                                questionList.deleteQuestion(adapter.getItem(i));
                            }
                        }
                        mode.finish();

                        adapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; //nothing to do
            }

            public void onDestroyActionMode(ActionMode mode) {
                //nothing to do
            }

        });

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.question_list_item_context, menu);
    }
}
