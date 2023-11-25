package sunghyeon.manse.kanjiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {


    private TextView tvQuiz;
    private TextView tvResult;

    private final Button[] btnAnswers = new Button[6];

    private final QuizManager quizManager = QuizManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizManager.shuffleQuestions();

        setLayout();
        setListeners();
        updateUi();
    }

    private void setLayout() {
        tvQuiz = findViewById(R.id.tv_question);
        tvResult = findViewById(R.id.tv_result);

        int[] ids = {R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6};

        for (int i = 0; i < 6; i++) {
            btnAnswers[i] = findViewById(ids[i]);
        }
    }

    private void setListeners() {
        for (int i = 0; i < 6; i++) {
            btnAnswers[i].setOnClickListener(v -> {
                Button btn = (Button) v;
                if (btn.getText() == quizManager.getAnswer()) {
                    Toast.makeText(getApplicationContext(), "よし！", Toast.LENGTH_SHORT).show();
                    tvResult.setText("よし！");

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        quizManager.incCurrentNumber();
                        updateUi();
                        tvResult.setText(null);
                    }, 500);
                } else {
                    Toast.makeText(getApplicationContext(), "違う！", Toast.LENGTH_SHORT).show();
                    tvResult.setText("違う！");
                }
            });
        }
    }

    private void updateUi() {
        setQuestion();
        setChoices();
    }

    private void setQuestion() {
        tvQuiz.setText((quizManager.getCurrentNumber() + 1) + ". " + quizManager.getQuestion());
    }

    private void setChoices() {
        ArrayList<String> choices = quizManager.getChoices();

        for (int i = 0; i < 6; i++) {
            btnAnswers[i].setText(choices.get(i));
        }
    }
}