package sunghyeon.manse.kanjiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button mBtnSelectWords;
    private Button mBtnStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setLayout();
        setButtons();
    }

    private void setLayout() {
        mBtnSelectWords = findViewById(R.id.select_words_btn);
        mBtnStartQuiz = findViewById(R.id.start_quiz_btn);
    }

    private void setButtons() {
        mBtnSelectWords.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SelectWordsActivity.class);
            startActivity(intent);
        });

        mBtnStartQuiz.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), QuizActivity.class);
            startActivity(intent);
        });
    }
}