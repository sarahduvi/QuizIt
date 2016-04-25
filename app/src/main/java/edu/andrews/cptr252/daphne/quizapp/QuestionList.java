package edu.andrews.cptr252.daphne.quizapp;

/**
 * Created by Daphne on 2/8/2016.
 */

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class QuestionList {
    private static QuestionList sOurInstance;
    private ArrayList<Question> mQuestions;
    private Context mAppContext;

    public static QuestionList getInstance(Context c) {
        if (sOurInstance == null){
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }

    private static final String TAG = "QuestionList";
    private static final String FILENAME = "quiz.json";
    private QuizJSONSerializer mSerializer;

    public boolean saveQuestions() {
        try{
            mSerializer.saveQuestions(mQuestions);
            Log.d(TAG, "Question saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving question: " + e);
            return false;
        }
    }

    public void addQuestion(Question question) {
        mQuestions.add(question);
        saveQuestions();
    }

    public void deleteQuestion(Question question) {
        mQuestions.remove(question);
        saveQuestions();
    }

    private QuestionList(Context appContext) {
        mAppContext = appContext;
        mSerializer = new QuizJSONSerializer(mAppContext, FILENAME);

        try{
            mQuestions = mSerializer.loadQuestions();
        } catch (Exception e) {
            mQuestions = new ArrayList<Question>();
            Log.e(TAG, "Error loading question: ", e);
        }
    }

    public ArrayList<Question> getQuestions(){
        return mQuestions;
    }

    public Question getQuestion(UUID questionId) {
        for (Question question : mQuestions) {
            if (question.getmId().equals(questionId))
                return question;
        }
        return null;
    }

}
