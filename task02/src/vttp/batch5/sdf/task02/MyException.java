package vttp.batch5.sdf.task02;

public class MyException extends Exception{

    public MyException(){

    }

    public MyException(String msg){
        // call the super class in the constructor
        //this the first line
        super(msg); // this line calls the Exception(String) constructor in Exception parent class



    }

    public MyException(String msg, Throwable t){
        super(msg, t);

    }


    
}
