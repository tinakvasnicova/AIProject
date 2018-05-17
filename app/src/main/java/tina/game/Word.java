package tina.game;

import java.util.ArrayList;

public class Word {
    ArrayList<Cell> wordCells;
    int wordLength;
    String currentSetWord = "";
    ArrayList<String> matchedWords = new ArrayList<>();

    Word(ArrayList<Cell> wordCells) {
        this.wordCells = wordCells;
        wordLength = wordCells.size();

    }

    public ArrayList<Cell> getWordCells() {
        return wordCells;
    }

    public void setWordCells(ArrayList<Cell> wordCells) {
        this.wordCells = wordCells;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public ArrayList<Character> matchLetter() {
        ArrayList<Character> returnArray = new ArrayList<>();

        for (Cell cell : wordCells) {
            if (Character.isLetter(cell.getLetter())) {
                char cellLetter = cell.getLetter();
                returnArray.add(cellLetter);
            } else {

                returnArray.add('#');
            }

        }
        return returnArray;

    }

    public void setMatchedWord(String string) {

        currentSetWord = string;
        char[] letterArray = string.toCharArray();
        int indexCount = 0;
        for (char letter : letterArray) {

            Cell cell = wordCells.get(indexCount);
            cell.setLetter(letter);

            indexCount = indexCount + 1;
        }
    }

    public ArrayList<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(ArrayList<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

    public void setNextMatchedWord() {

            matchedWords.remove(0);
            String string = matchedWords.get(0);

        setMatchedWord(string);
    }

    public boolean isLastInTheList() {
        if (matchedWords.size() < 2) {
            return true;
        } else {
            return false;
        }
    }
     public boolean isMatchedWordsEmpty() {
        if (matchedWords.isEmpty()){
            return true;

        }
        else {
            return false;
        }

     }
     public void setFirstMatchedWordToCells (){
        String matchedWord = matchedWords.get(0);
        setMatchedWord(matchedWord);
     }
     public void resetMatchedWordsList(){
        matchedWords.clear();
     }

    public String getCurrentSetWord() {
        return currentSetWord;
    }

    public int getHeuristicScore() {
        int count = 0;

        for (Cell cell : wordCells){
            if (Character.isLetter(cell.getLetter())) {
                char cellLetter = cell.getLetter();
                count = count + 1;
            }

        }

        if (count == wordLength) {
            return 10000;
        }
        return count;
    }

}



