package mazerunner.restapiresponse;

/** This is an enumerator class which specifies what are the neighbouring
 * cell's status can be.
 *
 * @author kismoha
 */
public enum CellAvailability {

    /** The BLOCKED state means that we cannot move to that cell from our
     * current position. There is a wall in the way.
     *
     */
    BLOCKED,

    /** The VISITED state means that we already visited that cell.
     * It is possible to move to that cell from this position.
     *
     */
    VISITED,

    /** The UNEXPLORED state means that we have not yet been to that cell.
     * It is possible to move to that cell from this position.
     *
     */
    UNEXPLORED;
}
