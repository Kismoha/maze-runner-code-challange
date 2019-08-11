package mazerunner.restapiresponse;

/** The representation of a maze cell.
 *
 * @author kismoha
 */
public class Cell {

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

    /** Check if the cell is a dead end
     *
     * @return returns true if it is a dead end, false otherwise.
     */
    public boolean isDeadEnd() {
        return !east.equals(CellAvailability.UNEXPLORED)
                && !west.equals(CellAvailability.UNEXPLORED)
                && !north.equals(CellAvailability.UNEXPLORED)
                && !south.equals(CellAvailability.UNEXPLORED);
    }

    /** Checks in what direction should be the next cell.
     *  Priority: SOUTH > EAST > WEST > NORTH.
     * 
     * @return returns a direction.
     */
    public String nextCell() {
        return south.equals(CellAvailability.UNEXPLORED) ? "SOUTH"
                : east.equals(CellAvailability.UNEXPLORED) ? "EAST"
                : west.equals(CellAvailability.UNEXPLORED) ? "WEST"
                : "NORTH";

    }
    
    /** Checks if the cell will be used later in the algorithm.
     * A cell is useful if there are more than one unexplored cells
     * neighbouring it.
     * 
     * @return returns true if it should be stored, false otherwise.
     */
    public boolean shouldStore(){
        int counter = 0;
        if(south.equals(CellAvailability.UNEXPLORED)){counter++;}
        if(north.equals(CellAvailability.UNEXPLORED)){counter++;}
        if(west.equals(CellAvailability.UNEXPLORED)){counter++;}
        if(east.equals(CellAvailability.UNEXPLORED)){counter++;}
        return counter > 1;
    }

    /** Check if the cells are identical.
     * Cells are identical if their coordinates match.
     *
     * @param obj The cell which it should be compared with.
     * @return returns true if they identical, false otherwise.
     */
    public boolean equals(Cell obj) {
        return obj.getX() == x && obj.getY() == y;
    }
    
    /** Formats the coordinates of the cell.
     *
     * @return returns a string of the cell's coordinates formatted.
     */
    public String toStringCoords(){
        return " x: "+x+" y: "+y;
    }

    /** Getter method for y field.
     *
     * @return returns the y field.
     */
    public int getY() {
        return y;
    }

    /** Getter method for x field.
     *
     * @return returns the x field.
     */
    public int getX() {
        return x;
    }

    /** Getter method for mazeGuid field.
     *
     * @return returns the mazeGuid field.
     */
    public String getMazeGuid() {
        return mazeGuid;
    }

    /** Getter method for atEnd field.
     *
     * @return returns the atEnd field.
     */
    public boolean isAtEnd() {
        return atEnd;
    }

}
