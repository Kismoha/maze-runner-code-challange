package mazerunner.algorithm;

import java.util.Stack;
import mazerunner.restapiresponse.Cell;

/** Represents the storage for the algorithm
 *
 * @author kismoha
 */
public class Storage {

    private final Stack<Cell> currentPath;

    /**  Creates an empty stack for the data.
     *
     */
    public Storage() {
        currentPath = new Stack<>();
    }

    /** Stores a cell on top of the stack.
     *
     * @param currentCell The cell which should be stored.
     */
    public void store(Cell currentCell) {
        push(currentCell);
    }

    /** Removes the top cell from the stack.
     *
     * @return Returns the removed cell.
     */
    public Cell remove(){
        return pop();
    }
    
    // Pops from the stack.
    private Cell pop(){
        return currentPath.pop();
    }

    // Pushes onto the stack.
    private void push(Cell item){
        currentPath.push(item);
    }
}
