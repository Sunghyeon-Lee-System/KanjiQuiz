package sunghyeon.manse.kanjiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class SelectWordsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private GridView mKanjiGridView;

    private Button mSelectOkButton;
    private Button mSelectCancelButton;

    private final QuizManager quizManager = QuizManager.getInstance();

    private KanjiAdapter kanjiAdapter;

    private KanjiDataContainer kanjiDataContainer=KanjiDataContainer.getInstance();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        KanjiAdapter adapter = (KanjiAdapter) parent.getAdapter();
        KanjiData rowData = (KanjiData) adapter.getItem(position);
        boolean checkedState = rowData.getIsChecked();

        checkedState = !checkedState;
        rowData.setIsChecked(checkedState);

        if (checkedState) {
            quizManager.addKanjiIndex(position);
        } else {
            quizManager.removeKanjiIndex(position);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_cell, null);

        KanjiData kanjiData = kanjiDataContainer.getKanjiData(position);

        TextView dialogKanji = dialogView.findViewById(R.id.dialog_kanji);
        TextView dialogKorean = dialogView.findViewById(R.id.dialog_korean);
        TextView dialogOnDoku = dialogView.findViewById(R.id.dialog_onDoku);
        TextView dialogKunDoku = dialogView.findViewById(R.id.dialog_kunDoku);
        Button dialogOkButton = dialogView.findViewById(R.id.dialog_ok_button);

        String onDoku = kanjiData.getOnDoku();
        String kunDoku = kanjiData.getKunDoku();

        if (Objects.equals(onDoku, "")) {
            dialogOnDoku.append("없음");
        }

        if (Objects.equals(kunDoku, "")) {
            dialogKunDoku.append("없음");
        }

        dialogKanji.setText(kanjiData.getKanji());
        dialogKorean.setText(kanjiData.getKorean());

        dialogOnDoku.append(onDoku);
        dialogKunDoku.append(kunDoku);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialogOkButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_words);

        kanjiDataContainer.parseKanjiData(this);

        setLayout();
        setListeners();
        updateUi();
    }

    private void setLayout() {
        mKanjiGridView = findViewById(R.id.kanji_list);

        mSelectOkButton = findViewById(R.id.select_ok_btn);
        mSelectCancelButton = findViewById(R.id.select_cancel_btn);
    }

    private void setListeners() {
        mKanjiGridView.setOnItemClickListener(this);
        mKanjiGridView.setOnItemLongClickListener(this);

        mSelectOkButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
            startActivity(intent);
            finish();
        });

        mSelectCancelButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(SelectWordsActivity.this);
            builder.setTitle("教えて上げる");
            builder.setMessage("本当にキャンセルするつもりですか");

            builder.setPositiveButton("예아!", (dialog, which) -> {
                quizManager.resetKanjiIndices();
                finish();
            });
            builder.setNegativeButton("안된다!", (dialog, which) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    private void updateUi() {
        kanjiAdapter = new KanjiAdapter(this, R.layout.gridview_item, kanjiDataContainer.getAllKanjiData());
        mKanjiGridView.setAdapter(kanjiAdapter);
    }
}