/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author nguyn
 */
public class MyOwnRuntimeException extends RuntimeException {
    public MyOwnRuntimeException() {
    }

    public MyOwnRuntimeException(String message) {
        super(message);
    }
}
