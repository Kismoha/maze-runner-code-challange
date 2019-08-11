/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner.algorithm;

import mazerunner.HTTP400StatusException;
import mazerunner.restapiacces.RestRequestsImpl;
import mazerunner.restapiresponse.Cell;

/**
 *
 * @author kismoha
 */
public class StepBackAlgorithm {

    private RestRequestsImpl apiAccess;
    private Cell currentCell;
    private Cell startingCell;
    private Storage storage;
    private boolean shouldTerminate;
    private String lastDir;

    public StepBackAlgorithm() throws HTTP400StatusException {
        shouldTerminate = false;
        apiAccess = new RestRequestsImpl();
        storage = new Storage();
    }

    public void run()
            throws HTTP400StatusException, CloneNotSupportedException {
        setStartingCell();
        while (!shouldTerminate) {
            System.out.println("Choosing Actions");
            System.out.println(currentCell.toString());
            chooseNextAction();
        }
    }

    private void setStartingCell()
            throws HTTP400StatusException, CloneNotSupportedException {
        //API call
        currentCell = apiAccess.initMaze().getCurrentCell();
        System.out.println("Set SC: " + currentCell.toString());
        //Storing the starting cell
        storage.store(currentCell);

    }

    private void stepBack() throws HTTP400StatusException {
        //Stepping back in the path
        currentCell = storage.popUntilValidCell();
        System.out.println("Step Back To:" + currentCell.getX()+" "+currentCell.getY());
        currentCell = apiAccess.jumpTo(currentCell.getMazeGuid(), currentCell.getX(), currentCell.getY()).getCurrentCell();
        
        
    }

    private void stepForward() throws HTTP400StatusException {
        //API call
        currentCell = apiAccess.moveTo(currentCell.getMazeGuid(),
                currentCell.nextCell()).getCurrentCell();
        System.out.println("Step Forward To:" + currentCell.getX()+" "+currentCell.getY());
        //Storing the current cell
        storage.store(currentCell);
    }

    private void terminate() {
        System.out.println("Terminate");
        shouldTerminate = true;
    }

    private void chooseNextAction() throws HTTP400StatusException {
        if (isSolved()) {
            terminate();
        } else if (isCurrentCellDeadEnd()) {
            stepBack();
        } else {
            stepForward();
        }
    }

    private boolean isCurrentCellDeadEnd() {
        return currentCell.isDeadEnd();
    }

    private boolean isSolved() {
        return currentCell.isAtEnd();
    }
    
    private String directionInverse(String direction){
        switch(direction){
            case "NORTH":
                return "SOUTH";
            case "EAST":
                return "WEST";
            case "WEST":
                return "EAST";
            case "SOUTH":
                return "NORTH";
        }
        return null;
    }

}
