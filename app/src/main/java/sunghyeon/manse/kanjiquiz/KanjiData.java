package sunghyeon.manse.kanjiquiz;

public class KanjiData {
    private String mKanji;
    private String mKorean;
    private String mOnDoku;
    private String mKunDoku;
    private boolean mIsChecked = false;

    /*public KanjiData(String kanji, String korean, String onDoku, String kunDoku, boolean isChecked) {
        mKanji = kanji;
        mKorean = korean;
        mOnDoku = onDoku;
        mKunDoku = kunDoku;
        mIsChecked=isChecked;
    }*/

    public KanjiData() {

    }

    public String getKanji() {
        return mKanji;
    }

    public String getKorean() {
        return mKorean;
    }

    public String getOnDoku() {
        return mOnDoku;
    }

    public String getKunDoku() {
        return mKunDoku;
    }

    public boolean getIsChecked() {
        return mIsChecked;
    }

    public void setKanji(String kanji) {
        mKanji = kanji;
    }

    public void setKorean(String korean) {
        mKorean = korean;
    }

    public void setOnDoku(String onDoku) {
        mOnDoku = onDoku;
    }

    public void setKunDoku(String kunDoku) {
        mKunDoku = kunDoku;
    }

    public void setIsChecked(boolean isChecked) {
        mIsChecked = isChecked;
    }
}
