package sunghyeon.manse.kanjiquiz;

import android.widget.CheckBox;
import android.widget.TextView;

public class CellViewHolder {
    private TextView mKanji;
    private TextView mKorean;
    private TextView mOnDoku;
    private TextView mKunDoku;
    private CheckBox mCheckBox;

    public void setKanji(TextView kanji) {
        mKanji = kanji;
    }

    public void setKorean(TextView korean) {
        mKorean = korean;
    }

    public void setOnDoku(TextView onDoku) {
        mOnDoku = onDoku;
    }

    public void setKunDoku(TextView kunDoku) {
        mKunDoku = kunDoku;
    }

    public void setCheckBox(CheckBox checkBox) {
        mCheckBox = checkBox;
    }

    public TextView getKanji() {
        return mKanji;
    }

    public TextView getKorean() {
        return mKorean;
    }

    public TextView getOnDoku() {
        return mOnDoku;
    }

    public TextView getKunDoku() {
        return mKunDoku;
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }
}
