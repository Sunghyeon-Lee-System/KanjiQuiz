package sunghyeon.manse.kanjiquiz;

import java.util.ArrayList;
import java.util.Collections;

public class QuizManager {
    private static final QuizManager instance = new QuizManager();

    private final ArrayList<Integer> selectedKanjiIndices = new ArrayList<>();

    private int currentNumber = 0;
    private final KanjiDataContainer kanjiDataContainer = KanjiDataContainer.getInstance();

    private QuizManager() {
    }

    public static QuizManager getInstance() {
        return instance;
    }

    public void incCurrentNumber() {
        if (currentNumber < selectedKanjiIndices.size() - 1) {
            currentNumber++;
        }
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void addKanjiIndex(int index) {
        selectedKanjiIndices.add(index);
    }

    public void removeKanjiIndex(int index) {
        selectedKanjiIndices.remove(index);
    }

    public void resetKanjiIndices() {
        selectedKanjiIndices.clear();
    }

    public void shuffleQuestions() {
        Collections.shuffle(selectedKanjiIndices);
    }

    public String getQuestion() {
        return kanjiDataContainer.getKanjiData(selectedKanjiIndices.get(currentNumber)).getKorean();
    }

    public String getAnswer() {
        return kanjiDataContainer.getKanjiData(selectedKanjiIndices.get(currentNumber)).getKanji();
    }

    public ArrayList<String> getChoices() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add(getAnswer());

        ArrayList<Integer> choiceIndices = (ArrayList<Integer>) selectedKanjiIndices.clone();
        choiceIndices.remove(currentNumber);
        Collections.shuffle(choiceIndices);

        for (int i = 0; i < 5; i++) {
            choices.add(kanjiDataContainer.getKanjiData(choiceIndices.get(i)).getKanji());
        }

        Collections.shuffle(choices);

        return choices;
    }
}
