/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner.algorithm;

import java.util.Stack;
import mazerunner.restapiresponse.Cell;

/**
 *
 * @author kismoha
 */
public class Storage {

    private Stack<Cell> currentPath;

    public Storage() {
        currentPath = new Stack<>();
    }

    public void store(Cell currentCell) {
        push(currentCell);
    }

    //TO DO : implement no more moves
    public Cell popUntilValidCell() {
        do{
            pop();
        }while(peek().isDeadEnd());
        return peek();
    }
    
    private Cell pop(){
        return currentPath.pop();
    }
    
    private Cell peek(){
        return currentPath.peek();
    }

    private void push(Cell item){
        currentPath.push(item);
    }
}
