package tina.game;

import java.util.ArrayList;

public class Crossword {

    ArrayList<Cell> allCells = new ArrayList<>();
//    the array list has to be ordered by cell number, it has to be in order which then matches the index

    Crossword() {
//        this is a constructor for Crossword and its blank because we dont need to set anything, it is just a collection of cells which is
//        done above
    }


    public boolean isComplete() {
        for (Cell cell : allCells) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
//        this method (iscomplete) checks the game goal by going through every cell
    }
public void addCellToCrossword (Cell cell) {
        allCells.add(cell);
//        this methods adds cells to our crossword - collections of cells
}


public Cell getCell(int cellNumber){
        return allCells.get(cellNumber);
//        if I want one specific sell I'll call the index of the array list which matches and return the cell number
}

    public ArrayList<Cell> getAllCells() {
        return allCells;

    }

    public ArrayList<Cell> getRowOfCells(int rowNumber){

        ArrayList<Cell> rowOfCells = new ArrayList<>();
        for (Cell cell : allCells) {
            if (cell.getRowNumber() == rowNumber) {
                rowOfCells.add(cell);
            }
        }


        return rowOfCells;
    }

    public ArrayList<Cell> getColumnOfCells(int columnNumber){
        ArrayList<Cell> columnOfCells = new ArrayList<>();
        for (Cell cell : allCells) {
            if (cell.getColumnNumber() == columnNumber) {
                columnOfCells.add(cell);
            }
        }
        return columnOfCells;
    }

    public void clearCrossword() {
        allCells = new ArrayList<>();

    }

}



