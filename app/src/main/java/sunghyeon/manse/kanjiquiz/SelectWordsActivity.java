package sunghyeon.manse.kanjiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        KanjiAdapter adapter = (KanjiAdapter) parent.getAdapter();
        KanjiData rowData = (KanjiData) adapter.getItem(position);
        boolean checkedState = rowData.getIsChecked();

        checkedState = !checkedState;
        rowData.setIsChecked(checkedState);

        if (checkedState) {
            mSelectedKanjiList.add(rowData);
        } else {
            mSelectedKanjiList.remove(rowData);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_cell, null);

        KanjiData kanjiData = mKanjiList.get(position);

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

    private GridView mKanjiGridView;

    private Button mSelectOkButton;
    private Button mSelectCancelButton;

    private ArrayList<KanjiData> mKanjiList = new ArrayList<>();
    private ArrayList<KanjiData> mSelectedKanjiList = new ArrayList<>();

    private KanjiAdapter kanjiAdapter;

    private KanjiDataContainer kanjiDataContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_words);

        setLayout();
        setListeners();
        findKanjiList();
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
    }

    private void findKanjiList() {
        kanjiDataContainer = new KanjiDataContainer(this);
        mKanjiList = kanjiDataContainer.getKanjiData();
    }

    private void updateUi() {
        kanjiAdapter = new KanjiAdapter(this, R.layout.gridview_item, mKanjiList);
        mKanjiGridView.setAdapter(kanjiAdapter);
    }
}