package sunghyeon.manse.kanjiquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class KanjiAdapter extends BaseAdapter {
    private Context mContext;
    private int mCellLayout;
    private ArrayList<KanjiData> mKanjiList;

    private LayoutInflater mLayoutInflater;

    public KanjiAdapter(Context context, int cellLayout, ArrayList<KanjiData> kanjiList) {
        mContext = context;
        mCellLayout = cellLayout;
        mKanjiList = kanjiList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mKanjiList.size();
    }

    @Override
    public Object getItem(int i) {
        return mKanjiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = mLayoutInflater.inflate(mCellLayout, viewGroup, false);
            CellViewHolder holder = new CellViewHolder();

            holder.setKanji(view.findViewById(R.id.kanji));
            holder.setKorean(view.findViewById(R.id.korean));
            holder.setOnDoku(view.findViewById(R.id.ondoku));
            holder.setKunDoku(view.findViewById(R.id.kundoku));
            holder.setCheckBox(view.findViewById(R.id.kanji_checkbox));

            view.setTag(holder);
        }

        CellViewHolder holder = (CellViewHolder) view.getTag();

        KanjiData kanjiData = mKanjiList.get(i);
        holder.getKanji().setText(kanjiData.getKanji());
        holder.getKorean().setText(kanjiData.getKorean());
        holder.getOnDoku().setText(kanjiData.getOnDoku());
        holder.getKunDoku().setText(kanjiData.getKunDoku());
        holder.getCheckBox().setChecked(kanjiData.getIsChecked());

        return view;
    }
}
