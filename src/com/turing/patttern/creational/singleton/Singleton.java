package com.turing.patttern.creational.singleton;

public class Singleton {

    //volatile keyword signals the JVM/Threads not to cache the singleton and always invoke this from main memory
    private static volatile Singleton instance = null;


    /**
     * This is a private singleton constructor so that any other class may not be able to
     * directly initialize this class.
     *
     */
    private Singleton(){
        if(instance != null){
            throw new RuntimeException("Use the getInstance() method to create object");
        }
    }

    public static Singleton getInstance() {
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
