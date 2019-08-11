package mazerunner;

import mazerunner.exceptions.HTTP400StatusException;
import mazerunner.algorithm.StepBackAlgorithm;

/** The main entry point of the program
 *
 * @author kismoha
 */
public class MazeRunnerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            new StepBackAlgorithm().run();
        } catch (HTTP400StatusException ex) {
            System.out.println("Error while communicating with the REST API");
        }
    }
    
}
