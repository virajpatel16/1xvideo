package app.myecomms.a1xvideo.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

import app.myecomms.a1xvideo.R;
import app.myecomms.a1xvideo.modals.Quiz;


public class QuizFragment extends Fragment {

    private View view;
    private TextView txtQuestions;
    private Button btnOptionA, btnOptionB, btnOptionC;

    private final ArrayList<Quiz> quizList = new ArrayList<>();
    private int currentQuestionIndex;
    private int userScore;
    private boolean quizScoreDialogShown = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        initView();
        return view;
    }

    private void initView() {
        txtQuestions = view.findViewById(R.id.txt_quiz_question);
        btnOptionA = view.findViewById(R.id.btn_quiz_option_a);
        btnOptionB = view.findViewById(R.id.btn_quiz_option_b);
        btnOptionC = view.findViewById(R.id.btn_quiz_option_c);

        initializeQuestions();
        userScore = 0;
        showNextQuestion();

        btnOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnOptionA.getText().toString());
            }
        });
        btnOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnOptionB.getText().toString());
            }
        });
        btnOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnOptionC.getText().toString());
            }
        });
    }

    private void checkAnswer(String selectedAnswer) {
        Quiz currentQuestion = quizList.get(currentQuestionIndex - 1);
        String correctAnswer = currentQuestion.quizQuestionAnswer;

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(requireContext(), "Correct !", Toast.LENGTH_SHORT).show();
            userScore++;
        } else {
            Toast.makeText(requireContext(), "Incorrect. Try again !", Toast.LENGTH_SHORT).show();
        }
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < quizList.size()) {
            Quiz currentQuestion = quizList.get(currentQuestionIndex);

            txtQuestions.setText(currentQuestion.quizQuestion);
            btnOptionA.setText(currentQuestion.quizOptionA);
            btnOptionB.setText(currentQuestion.quizOptionB);
            btnOptionC.setText(currentQuestion.quizOptionC);

            currentQuestionIndex++;

        } else {
            showQuizScore();
        }
    }

    private void showQuizScore() {
        if (quizScoreDialogShown || !isAdded()) return;
        quizScoreDialogShown = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        if (userScore >= 10) {
            builder.setTitle("Congratulations!");
            builder.setMessage("You answered more than 10 questions correctly!");
        } else {
            builder.setTitle("Quiz Complete");
            builder.setMessage("Your score: " + userScore + "/" + quizList.size());
        }

        builder.setCancelable(false);
        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();




    }

    private void initializeQuestions() {
        quizList.add(new Quiz("What is the largest internal organ in the human body?", "Lungs", "Heart", "Liver", "Liver"));
        quizList.add(new Quiz("What is the percentage of the Earth covered by water?", "71%", "60%", "45%", "71%"));
        quizList.add(new Quiz("What was the name of Drake’s 2023 album?", "Take Care", "Scorpion", "For All the Dogs", "For All the Dogs"));
        quizList.add(new Quiz("Which of the following British presenters never presented ‘Strictly Comes Dancing’?", "Claudia Winkleman", "Stacey Dooley", "Tess Daly", "Stacey Dooley"));
        quizList.add(new Quiz("Which country is the band AC/DC from?", "Australia", "New Zealand", "USA", "Australia"));
        quizList.add(new Quiz("When was the Cuban Missile Crisis?", "1982", "1992", "1962", "1962"));
        quizList.add(new Quiz("Which of the following is not a Japanese dish?", "Ramen", "Babi guling", "Sushi", "Babi guling"));
        quizList.add(new Quiz("Which sports is Steve Redgrave known for?", "Rowing", "Football", "Swimming", "Rowing"));
        quizList.add(new Quiz("Which member of the Spice Girls was known as ‘Sporty Spice’?", "Melanie Chisholm", "Emma Bunton", "Geri Halliwell", "Melanie Chisholm"));
        quizList.add(new Quiz("What is the atomic number of Hydrogen?", "3", "4", "1", "1"));
        quizList.add(new Quiz("What is the equivalent of 100 Celsius in Fahrenheit?", "212", "185", "155", "212"));
        quizList.add(new Quiz("What is the main ingredient of gnocchi?", "Rice", "Chocolate", "Potato", "Potato"));


        Collections.shuffle(quizList);
        currentQuestionIndex = 0;
    }


}