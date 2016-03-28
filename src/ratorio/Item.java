/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratorio;

/**
 *
 * @author Dutch Jer
 */
public class Item {
    public Recipe recipe;
    public String name;
    public String icon;
    
    public Item(String s){
        String[] vals = s.split("\"");
        //System.out.println(vals[1]);
        name = vals[1];
    }
    
    public void addIcon(String s){
        String[] vals = s.split("\"");
        if(vals[1].startsWith("__base__")){
            String location = vals[1].replace("__base__/", "");
            location = location.replace("/", "\\");
            icon = Loader.baselocation + location;
        }
    }
}
