package mazerunner.restapiacces;

import mazerunner.exceptions.HTTP400StatusException;
import mazerunner.restapiresponse.APIResponse;

/**
 * The interface specifies methods which are used to 
 * communicate with the REST API.
 * 
 * @author kismoha
 */
public interface RestRequests {

    /** The base URL.
     *
     */
    public final String BASE_URL = "https://www.epdeveloperchallenge.com";

    /** This method handles the initial message which is used to create
     * the maze and get the starting cell and the maze ID.
     * The path is BASE_URL/api/init.
     * POST method.
     *
     * @return returns an APIResponse object parsed from the response json.
     * @throws HTTP400StatusException
     */
    public APIResponse initMaze() throws HTTP400StatusException;

    /** This method handles a movement in the maze to a neighbouring cell.
     * The path is BASE_URL/api/move.
     * POST method.
     *
     * @param mazeGuid The ID of the maze which we want to move in.
     * @param direction The direction of the movement.
     * @return returns an APIResponse object parsed from the response json.
     * @throws HTTP400StatusException
     */
    public APIResponse moveTo(String mazeGuid, String direction) throws HTTP400StatusException;

    /** This method handles a jump in the maze to a specified cell which we
     * already visited.
     * The path is BASE_URL/api/jump.
     * POST method.
     * 
     * @param mazeGuid The ID of the maze which we want to jump in.
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return returns an APIResponse object parsed from the response json.
     * @throws HTTP400StatusException
     */
    public APIResponse jumpTo(String mazeGuid, int x, int y) throws HTTP400StatusException;

    /** This method handles querying of the current cell.
     * The path is BASE_URL/api/currentCell.
     * GET method.
     * 
     * @param mazeGuid The id of the maze where we want to get the current cell.
     * @return returns an APIResponse object parsed from the response json.
     */
    public APIResponse currentCell(String mazeGuid);

}
