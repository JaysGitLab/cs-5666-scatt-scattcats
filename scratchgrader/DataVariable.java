/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchgrader;

/**
 *
 * @author erick
 */
public class DataVariable {
    
    private String name;
    private int uses;
    private Boolean global;
    
    public DataVariable(String name, int uses, Boolean global ) 
    {
        this.name = name;
        this.uses = uses;
        this.global = global;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public String getName() {
        return name;
    }

    public int getUses() {
        return uses;
    }

    public Boolean getGlobal() {
        return global;
    }
    
}
