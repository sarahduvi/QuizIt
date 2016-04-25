package edu.andrews.cptr252.daphne.quizapp;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daphne on 2/1/2016.
 */
public class Question {
    private UUID mId;
    /** Text of question */
    private String mQuestion;

    /** Description of question */
    //private String mmAnswer;

    /**Answer to question (true/false)*/
    private boolean mAnswer;

    public Question(){
        //Generate unique identifier for the new question.
        mId = UUID.randomUUID();
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public UUID getmId(){
        return mId;
    }

    public String getmTitle() {
        return mQuestion;
    }

    public void setmTitle (String title) {
        mQuestion = title;
    }

    //public String getmAnswer() {return mmAnswer; }
    //public void setmDescription(String description) {
    //    mmAnswer = description; } //comment
    public boolean ismSolved() {return mAnswer; }
    public void setmSolved(boolean solved) {
        mAnswer = solved; }

    private static final String JSON_ID = "id";
    private static final String JSON_TEXT = "title";
    private static final String sdfg = "description";
    private static final String JSON_ANSWER = "solved";

    public Question(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mQuestion = json.getString(JSON_TEXT);
        mAnswer = json.getBoolean(JSON_ANSWER);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(JSON_ID, mId.toString());
        json.put(JSON_TEXT, mQuestion);
        json.put(JSON_ANSWER, mAnswer);

        return json;
    }

    public void setAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }

    public boolean isTrue() {
        mAnswer = true;
        return mAnswer;
    }


}
