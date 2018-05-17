package tina.game;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    int dimension;
    Crossword myCrossword;
    ArrayList<TextView> allTextViews = new ArrayList<>();

    LinkedList<Game> gamesList = new LinkedList<>();
    Iterator iterator;

    Game myGame;
    Dictionary myDictionary;

    ArrayList<Word> allWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        myDictionary = new Dictionary();

        Button button = findViewById(R.id.button1);
//        called views created in layout res

//        creating listener for buttons(text)

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                findWords();


                System.out.println("all words size = " + allWords.size());

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {


//

                        // back tracking


                        int index = 0;

                        while (index < allWords.size() && index >= 0) {
                            updateDisplay();
                            heuristicWordListSort();


                            System.out.println("current index = " + Integer.toString(index));


                            while (true) {

                                Word enteredWord = allWords.get(index);

                                System.out.println("current word = " + enteredWord.getCurrentSetWord());

                                if (enteredWord.isMatchedWordsEmpty()) {
                                    ArrayList<String> matchingWords = myDictionary.findMatchingWords(enteredWord.matchLetter());
                                    enteredWord.setMatchedWords(matchingWords);
                                    if (enteredWord.isMatchedWordsEmpty() == false) {
                                        enteredWord.setFirstMatchedWordToCells();
                                        index = index + 1;
                                        break;
                                    } else {
                                        index = index - 1;
                                        break;
                                    }
                                } else {
                                    if (enteredWord.isLastInTheList() == false) {
//                            another matched word can be tried
                                        enteredWord.setNextMatchedWord();
                                        index = index + 1;
                                        break;
                                    } else {
                                        enteredWord.resetMatchedWordsList();
                                        index = index - 1;
                                        break;
                                    }

                                }

                            }
                        }
                        updateDisplay();

                        if (index == -1) {
                            toast("No Solution Found");
                        } else {
                            toast("Crossword Solved");
                        }
                    }


                });

                thread.start();


//
            }
        });

        myCrossword = new

                Crossword();

        Button gameButton = findViewById(R.id.gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (iterator.hasNext()) {

                    myGame = (Game) iterator.next();
                } else {
                    iterator = gamesList.iterator();
                    myGame = (Game) iterator.next();
                }

                setMyGame(myGame);
                updateDisplay();


//                    button action for choosing the next game

            }
        });


        initialiseGame();
        iterator = gamesList.iterator();

//        at this point in the programme the game's skeleton is set,
// and recognize where the words are created list of words for the game
//        Next step is to find the letters that will fit into word object


    }

    public void initialiseGame() {
        String name1 = "Game1";
        int dimension1 = 7;
        boolean[] skeleton1 = {

                true, true, true, false, true, true, false,
                true, true, true, false, true, true, false,
                false, false, false, false, false, false, false,
                false, true, true, false, true, true, false,
                false, true, true, false, true, true, false,
                false, true, false, false, false, false, true,
                true, true, true, true, true, true, true


        };
        Game myGame1 = new Game(skeleton1, name1, dimension1);


        String name2 = "Game2";
        int dimension2 = 16;
        boolean[] skeleton2 = {
                false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false,
                false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false,
                false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false,
                true, false, false, false, false, true, true, true, false, false, false, true, false, false, false, false,
                true, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false,
                false, false, false, true, false, false, false, false, true, false, false, false, false, true, true, true,
                false, false, false, false, true, false, false, false, true, true, true, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, true, true, true, false, false, false, true, false, false, false, false,
                true, true, true, false, false, false, false, true, false, false, false, false, true, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true,
                false, false, false, false, true, false, false, false, true, true, true, false, false, false, false, true,
                false, false, false, false, true, false, false, false, true, true, true, false, false, false, false, true,
                false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false,
                false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false,
                false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false


        };
        Game myGame2 = new Game(skeleton2, name2, dimension2);

        String name3 = "Game3";
        int dimension3 = 16;
        boolean[] skeleton3 = {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false,
                false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false,
                false, false, false, true, true, false, false, false, false, false, true, true, false, false, false, false,
                true, true, true, false, false, false, false, true, true, false, false, false, false, true, true, true,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false,
                true, true, true, false, false, false, false, true, true, false, false, false, false, true, true, true,
                false, false, false, false, true, true, false, false, false, false, false, true, true, false, false, false,
                false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false,
                false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false,
                false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false


        };
        Game myGame3 = new Game(skeleton3, name3, dimension3);

        String name4 = "Game4";
        int dimension4 = 12;
        boolean[] skeleton4 = {

                false, false, false, false, false, false, false, false, false, true, true, true,
                true, true, false, true, true, true, true, true, true, true, true, true,
                true, true, false, false, false, false, true, true, false, false, false, true,
                true, true, false, true, true, true, true, true, true, true, true, true,
                true, true, false, true, true, true, false, false, false, false, false, true,
                true, false, false, false, false, false, true, true, true, true, true, true,
                true, true, false, true, true, true, true, true, true, true, true, true,
                false, false, false, false, false, false, true, false, false, false, false, false,
                true, false, true, true, true, true, true, true, true, true, true, true,
                false, false, false, false, false, false, false, false, true, true, true, true,
                true, false, true, true, true, true, true, true, true, true, true, true,
                true, false, true, true, true, false, false, false, false, false, true, true


        };
        Game myGame4 = new Game(skeleton4, name4, dimension4);

        gamesList.add(myGame1);
        gamesList.add(myGame2);
        gamesList.add(myGame3);
        gamesList.add(myGame4);


    }

    public void setMyGame(Game game) {

        dimension = game.getDimension();


        myCrossword.clearCrossword();
        allTextViews = new ArrayList<>();

        GridLayout frame = findViewById(R.id.frame);
        frame.removeAllViews();
        frame.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        frame.setColumnCount(dimension);
        frame.setRowCount(dimension);
        frame.setColumnOrderPreserved(false);
        frame.setRowOrderPreserved(false);


        for (int i = 0; i < dimension * dimension; i++) {

            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.cell_border);
            textView.setTag(i);

            allTextViews.add(textView);

            Cell cell = new Cell(i, dimension);
            myCrossword.addCellToCrossword(cell);


            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1.0f), GridLayout.spec(GridLayout.UNDEFINED, 1.0f));


            textView.setLayoutParams(params);

            frame.addView(textView);

        }


        ArrayList<Cell> allCells = myCrossword.getAllCells();
        for (Cell cell : allCells) {
            int cellNumber = cell.getCellNumber();
            cell.setBlack(myGame.getSkeleton()[cellNumber]);
            TextView textView = allTextViews.get(cellNumber);
            if (cell.isBlack() == true) {
                textView.setBackgroundResource(R.drawable.cell_border_black);
            }


        }

    }

    public void findWords() {

        allWords = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
//            searching rows first
            ArrayList<Cell> cellsInWord = new ArrayList<>();
            ArrayList<Cell> cellsInRow = myCrossword.getRowOfCells(i);
            for (Cell cell : cellsInRow) {


                if (cell.isBlack() == true) {
//if cell is black we need to finish the word
                    if (cellsInWord.size() > 1) {

                        Word word = new Word(cellsInWord);
                        allWords.add(word);
                        cellsInWord = new ArrayList<>();
                    } else {
                        cellsInWord = new ArrayList<>();
                    }


                } else {
//                    cell is white
                    cellsInWord.add(cell);

                    if (cellsInWord.size() > 1) {
                        if (cell.getColumnNumber() == dimension - 1) {

                            Word word = new Word(cellsInWord);
                            allWords.add(word);
                            cellsInWord = new ArrayList<>();
                        }
                    }
                }

            }


        }


        for (int i = 0; i < dimension; i++) {
//            searching column
            ArrayList<Cell> cellsInWord = new ArrayList<>();
            ArrayList<Cell> cellsInColumn = myCrossword.getColumnOfCells(i);
            for (Cell cell : cellsInColumn) {


                if (cell.isBlack() == true) {
//if cell is black we need to finish the word
                    if (cellsInWord.size() > 1) {

                        Word word = new Word(cellsInWord);
                        allWords.add(word);
                        cellsInWord = new ArrayList<>();
                    } else {
                        cellsInWord = new ArrayList<>();
                    }


                } else {
//                    cell is white
                    cellsInWord.add(cell);

                    if (cellsInWord.size() > 1) {
                        if (cell.getRowNumber() == dimension - 1) {

                            Word word = new Word(cellsInWord);
                            allWords.add(word);
                            cellsInWord = new ArrayList<>();
                        }
                    }
                }

            }
        }
        Collections.sort(allWords, new Comparator<Word>() {
            public int compare(Word word1, Word word2) {
                return Integer.valueOf(word2.getWordLength()).compareTo(word1.getWordLength());
            }
        });
    }


    public void updateDisplay() {

        this.runOnUiThread(new Runnable() {
            public void run() {

                for (TextView textView : allTextViews) {

                    int tag = (int) textView.getTag();

                    Cell cell = myCrossword.getCell(tag);
                    String letter = Character.toString(cell.getLetter());

                    if (!letter.equals("#")) {
                        textView.setText(letter);
                    }
                }
            }
        });

    }

    public void heuristicWordListSort() {
        Collections.sort(allWords, new Comparator<Word>() {
            public int compare(Word word1, Word word2) {
                return Integer.valueOf(word2.getHeuristicScore()).compareTo(word1.getHeuristicScore());
            }
        });

    }

    public void toast(final String toastMessage) {

        this.runOnUiThread(new Runnable() {
            public void run() {
                Spannable centredText = new SpannableString(toastMessage);


                centredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                        0, toastMessage.length() - 1,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                Toast.makeText(getApplicationContext(), centredText, Toast.LENGTH_LONG).show();

            }
        });

    }


}
