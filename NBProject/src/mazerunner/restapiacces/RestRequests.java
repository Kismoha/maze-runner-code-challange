/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner.restapiacces;

import mazerunner.HTTP400StatusException;
import mazerunner.restapiresponse.APIResponse;


/**
 *
 * @author kismoha
 */
public interface RestRequests {
    
    public final String BASE_URL = "https://www.epdeveloperchallenge.com";
    
    public APIResponse initMaze() throws HTTP400StatusException;
    
    public APIResponse moveTo(String mazeGuid, String direction) throws HTTP400StatusException;
    
    public APIResponse jumpTo(String mazeGuid, int x ,int y) throws HTTP400StatusException;
    
    public APIResponse currentCell(String mazeGuid);
    
}
