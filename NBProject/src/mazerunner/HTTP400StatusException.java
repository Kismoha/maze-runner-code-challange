/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

/**
 *
 * @author kismoha
 */
public class HTTP400StatusException extends Exception{
    public HTTP400StatusException(String errorMessage, Throwable err ){
        super(errorMessage,err);
    }
}
