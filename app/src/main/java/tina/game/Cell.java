package tina.game;

public class Cell {

    boolean isBlack = false;
    boolean isEmpty = true;
    char letter = Character.MIN_VALUE;
    int cellNumber;
    int rowNumber;
    int columnNumber;
//    attributes of cell class

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
        isEmpty = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public char getLetter() {

        char letter = this.letter;

        if (letter == Character.MIN_VALUE){
            letter = '#';
        }

        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    // create a constructor for cell object
    Cell (int cellNumber, int dimension) {
        this.cellNumber = cellNumber;
        columnNumber = cellNumber % dimension;
        rowNumber = (cellNumber - columnNumber)/dimension;





    }


}
