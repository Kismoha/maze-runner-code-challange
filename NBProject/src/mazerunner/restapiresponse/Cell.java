/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner.restapiresponse;

/**
 *
 * @author kismoha
 */
public class Cell implements Cloneable {

    private boolean previouslyVisited;
    private boolean atEnd;
    private CellAvailability east;
    private CellAvailability south;
    private CellAvailability west;
    private CellAvailability north;
    private String mazeGuid;
    private String note;
    private int y;
    private int x;

    @Override
    public String toString() {
        return "Cell{" + "previouslyVisited=" + previouslyVisited
                + ", atEnd=" + atEnd + ", east=" + east + ", south="
                + south + ", west=" + west + ", north=" + north + ", mazeGuid="
                + mazeGuid + ", note=" + note + ", y=" + y + ", x=" + x + '}';
    }

    public boolean isDeadEnd() {
        return !east.equals(CellAvailability.UNEXPLORED)
                && !west.equals(CellAvailability.UNEXPLORED)
                && !north.equals(CellAvailability.UNEXPLORED)
                && !south.equals(CellAvailability.UNEXPLORED);
    }

    public String nextCell() {
        return south.equals(CellAvailability.UNEXPLORED) ? "SOUTH"
                : east.equals(CellAvailability.UNEXPLORED) ? "EAST"
                : west.equals(CellAvailability.UNEXPLORED) ? "WEST"
                : "NORTH";

    }
    
    public boolean shouldStore(){
        int counter = 0;
        if(south.equals(CellAvailability.UNEXPLORED)){counter++;}
        if(north.equals(CellAvailability.UNEXPLORED)){counter++;}
        if(west.equals(CellAvailability.UNEXPLORED)){counter++;}
        if(east.equals(CellAvailability.UNEXPLORED)){counter++;}
        return counter > 1 ? true : false;
    }

    public boolean equals(Cell obj) {
        return obj.getX() == x && obj.getY() == y;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getMazeGuid() {
        return mazeGuid;
    }

    public boolean isAtEnd() {
        return atEnd;
    }

}
