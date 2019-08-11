package mazerunner.algorithm;

import mazerunner.exceptions.HTTP400StatusException;
import mazerunner.restapiacces.RestRequestsImpl;
import mazerunner.restapiresponse.Cell;

/**
 * Represents the algorithm which is used to solve the problem.
 *
 * @author kismoha
 */
public class StepBackAlgorithm {

    private final RestRequestsImpl apiAccess;
    private Cell currentCell;
    private final Storage storage;
    private boolean shouldTerminate;

    /**
     * Sets up the necessary fields to run the algorithm.
     *
     * @throws HTTP400StatusException
     */
    public StepBackAlgorithm() throws HTTP400StatusException {
        shouldTerminate = false;
        apiAccess = new RestRequestsImpl();
        storage = new Storage();
    }

    /**
     * Starts the algorithm.
     *
     * @throws HTTP400StatusException when there is an error communicating with
     * the API.
     */
    public void run() throws HTTP400StatusException {
        setStartingCell();
        while (!shouldTerminate) {
            chooseNextAction();
        }
    }

    /*
    Initiaties the maze via the API and sets the starting cell
    then logs the action and stores the cell if necessary.
     */
    private void setStartingCell()
            throws HTTP400StatusException {
        currentCell = apiAccess.initMaze().getCurrentCell();
        System.out.println("Starting From:" + currentCell.toStringCoords());
        if (currentCell.shouldStore()) {
            storage.store(currentCell);
        }

    }

    /*
    Simulates the action when the algorithm needs to step back:
    - removing from top of the stack
    - logging the action
    - setting current cell via API call
    - storing if necessary
     */
    private void stepBack() throws HTTP400StatusException {
        currentCell = storage.remove();
        System.out.println("Step Back To:" + currentCell.toStringCoords());
        currentCell = apiAccess.jumpTo(currentCell.getMazeGuid(),
                currentCell.getX(), currentCell.getY()).getCurrentCell();
        if (currentCell.shouldStore()) {
            storage.store(currentCell);
        }

    }

    /*
    Simulates the action when the algorithm needs to step forward.
    - getting the next cell via API call
    - logging action
    - storing if necessary
     */
    private void stepForward() throws HTTP400StatusException {
        String dir = currentCell.nextCell();
        currentCell = apiAccess.moveTo(currentCell.getMazeGuid(),
                dir).getCurrentCell();
        System.out.println("Step " + dir + " To:"
                + currentCell.toStringCoords());
        if (currentCell.shouldStore()) {
            storage.store(currentCell);
        }
    }

    // Sets the var which determines if the algorithm should stop to true.
    private void terminate() {
        System.out.println("Terminated");
        shouldTerminate = true;
    }

    // Chooses the next action for the algorithm based on the current cell.
    private void chooseNextAction() throws HTTP400StatusException {
        if (isSolved()) {
            terminate();
        } else if (isCurrentCellDeadEnd()) {
            stepBack();
        } else {
            stepForward();
        }
    }

    // Checks if the current cell is a dead end.
    private boolean isCurrentCellDeadEnd() {
        return currentCell.isDeadEnd();
    }

    // Checks if the current cell is the end of the maze.
    private boolean isSolved() {
        return currentCell.isAtEnd();
    }
}
