package mazerunner;

import java.io.IOException;
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
            System.out.println("Press Enter to exit");
            System.in.read();
        } catch (HTTP400StatusException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
