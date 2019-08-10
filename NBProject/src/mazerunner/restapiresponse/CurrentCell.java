/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner.restapiresponse;

/**
 *
 * @author kismoha
 */
public class CurrentCell {
    
    private boolean previouslyVisited;
    private CellAvailability east;
    private CellAvailability south;
    private CellAvailability west;
    private CellAvailability north;
    private String mazeGuid;
    private String note;
    private int y;
    private int x;

    @Override
    public String toString() {
        return "CurrentCell{" + "previouslyVisited=" + previouslyVisited + ", east=" + east + ", south=" + south + ", west=" + west + ", north=" + north + ", mazeGuid=" + mazeGuid + ", note=" + note + ", y=" + y + ", x=" + x + '}';
    }
    
    
    
}
