package com.example.areum.quizassignmentareum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.example.areum.quizassignmentareum.R.*;


public class MainActivity2_quiz extends Fragment {

    // questions
    String[] question = {
            "Which city is the capital of Australia? ",
            "Which city is the capital of Canada? ",
            "Which city is the capital of China? ",
            "Which city is the capital of Czech Republic? ",
            "Which city is the capital of France? ",
            "Which city is the capital of Germany? ",
            "Which city is the capital of South Korea? ",
            "Which city is the capital of Brazil? ",
            "Which city is the capital of Sweden? ",
            "Which city is the capital of Monaco? "
    };

    // correct answer
    String[] correctAnswer = {
            "Canberra",
            "Ottawa",
            "Beijing",
            "Prague",
            "Paris",
            "Berlin",
            "Seoul",
            "Brasilia",
            "Stockholm",
            "Monaco"
    };

    // incorrect answer
    String[] incorrectAnswer = {
            "Accra","Bangkok","Areum",
            "Toronto","Amsterdam","Peru",
            "Manila","Marc","Moscow",
            "Russia","Slovakia","Bratislava",
            "London","Washington","Hanoi",
            "Andrea","Joanna","Mexico",
            "Toronto","Dundas West","Ontario",
            "Marcel","Kingstown","Quito",
            "Asia","Rome","Caracas",
            "Tokyo","Hanoi","Beirut"
    };


    List<Quiz> questions = new ArrayList<Quiz>();
    List<String> otherAnswerList;

    int myAnswerCheck = 0;      // How many answer I selected
    int myAnswerCorrect = 0;    // How many answer is correct
    String myAnswer;            // the city name(answer) that I selected

    TextView lblquestion;       // show quiz
    TextView lblquestionNumber; // show quiz's number
    RadioGroup radioGroup;
    RadioButton checkedRadioNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        otherAnswerList = Arrays.asList(incorrectAnswer);

        View view = inflater.inflate(layout.activity_main2_quiz, container, false);

        lblquestionNumber = (TextView) view.findViewById(id.tvQuizNumShow);
        lblquestion = (TextView) view.findViewById(R.id.tvQuizShow);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        // Make Quiz 1
        makeFirstQuiz();

        // Click the NEXT button
        view.findViewById(id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2_quiz.super.getActivity());
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();

                // click NEXT button without checking the radio button
                if(selectedRadioId == -1){
                    alertDialog.setMessage("Check your answer!!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.show();

                } else {
                    checkedRadioNum = (RadioButton) radioGroup.findViewById(selectedRadioId);
                    myAnswer = checkedRadioNum.getText().toString();

                    // checked fifth quiz's answer
                    if(myAnswerCheck >= 4){
                        // if I selected correct answer, myAnswerCorrect + 1
                        if (Arrays.asList(correctAnswer).contains(myAnswer)) {
                            myAnswerCorrect++;
                        }

                        // pass myScore value to MainActivity3_result.
                        String myScore = String.valueOf(myAnswerCorrect);

                        Intent intent = new Intent(MainActivity2_quiz.super.getActivity(), MainActivity3_result.class);
                        intent.putExtra("MY_RESULT", myScore);
                        startActivity(intent);

                    } else {
                        // checked 1~4 quiz's answer
                        myAnswerCheck ++;

                        // if I selected correct answer, myAnswerCorrect + 1
                        if (Arrays.asList(correctAnswer).contains(myAnswer)){
                            myAnswerCorrect ++;
                        }

                        // Make next quiz2 ~ 5.
                        makeQuiz();

                    }
                }
            }
        });

        return view;
    }

    // make quiz1 (with make questions array)
    public void makeFirstQuiz() {
        // make questions array and shuffle
        for (int i = 0; i < question.length; i++) {
            questions.add(new Quiz(question[i], correctAnswer[i], otherAnswerList.subList(i * 3, (i + 1) * 3)));
        }
        Collections.shuffle(questions);

        // show
        makeQuiz();

    }

    // make quiz and answers statements
    public void makeQuiz() {

        Quiz quiz = questions.get(0);
        // remove used questions eliment that was used for preventing duplication
        questions.remove(0);

        // set number of quiz and question.
        lblquestion.setText(quiz.question);
        lblquestionNumber.setText("Quiz " + (myAnswerCheck + 1));

        // set all of the answers in the RadioButtons text
        int count = radioGroup.getChildCount();
        for(int i = 0; i < count; i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(quiz.answers.get(i));
        }

        radioGroup.clearCheck();

    }



}
