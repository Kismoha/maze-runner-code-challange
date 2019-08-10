/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import mazerunner.restapiacces.RestRequestsImpl;

/**
 *
 * @author kismoha
 */
public class MazeRunnerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            new RestRequestsImpl().initMaze();
        }catch(HTTP400StatusException e){
            e.printStackTrace();
        }
        
    }
    
}
