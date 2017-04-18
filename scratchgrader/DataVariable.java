/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchgrader;
/**
 * DataVariable.java
 * Object Data Variable 
 * @author Chris Campell
 * @author Eric Cambel
 * @author EriK Lares 
 * @version 3/22/2017
 */
public class DataVariable {
    
    private String name;
    private int uses;
    private Boolean global;


    /**
     * DataVariable - Constructor for objects of type DataVariable.
     * @param name - Name of the DataVariable object
     * @param uses - Numbers of time the DataVariable is being used.
     * @param global - True if the DataVariable can be used by multiple sprites.
     */
    public DataVariable(String name, int uses, Boolean global ) 
    {
        this.name = name;
        this.uses = uses;
        this.global = global;
    }


    /**
     * setName - Set the name of the DataVariable.
     *
     * @return String - name of the DataVariable
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setUses - Set the uses of the DataVariable.
     *
     * @return int - uses of the DataVariable
     */
    public void setUses(int uses) {
        this.uses = uses;
    }

    /**
     * setGlobal - Set the global status 
     * of the DataVariable.
     *
     * @return Boolean - status of 
     * the DataVariable
     */
    public void setGlobal(Boolean global) {
        this.global = global;
    }

    /**
     *  getName - Get the name 
     *  of the  dataVariables.
     * @return String - A string wih the name
     */
    public String getName() {
        return name;
    }

    /**
     *  getUses - Get the uses
     *  of the  dataVariables.
     * @return int - An int wih the uses
     */
    public int getUses() {
        return uses;
    }

    /**
     *  getName - Get the status 
     *  of the  dataVariables.
     * @return boolean - A boolean wih the status
     */
    public Boolean getGlobal() {
        return global;
    }
    
}

