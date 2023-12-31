package sunghyeon.manse.kanjiquiz;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

import android.content.Context;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class KanjiDataContainer {
    private final ArrayList<KanjiData> mKanjiData = new ArrayList<>();

    private static final KanjiDataContainer instance = new KanjiDataContainer();

    private KanjiDataContainer() {
    }

    public static KanjiDataContainer getInstance() {
        return instance;
    }

    public ArrayList<KanjiData> getAllKanjiData() {
        return mKanjiData;
    }

    public KanjiData getKanjiData(int index) {
        return mKanjiData.get(index);
    }

    public void parseKanjiData(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.kanji);

        try {
            OPCPackage pkg = OPCPackage.open(inputStream);
            XSSFWorkbook workbook = new XSSFWorkbook(pkg);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                KanjiData kanjiData = new KanjiData();

                kanjiData.setKorean(row.getCell(0, CREATE_NULL_AS_BLANK).getStringCellValue());
                kanjiData.setKanji(row.getCell(1, CREATE_NULL_AS_BLANK).getStringCellValue());
                kanjiData.setOnDoku(row.getCell(2, CREATE_NULL_AS_BLANK).getStringCellValue());
                kanjiData.setKunDoku(row.getCell(3, CREATE_NULL_AS_BLANK).getStringCellValue());

                mKanjiData.add(kanjiData);
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
