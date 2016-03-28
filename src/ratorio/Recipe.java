/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratorio;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;
import ratorio.Item;

/**
 *
 * @author Dutch Jer
 */
public class Recipe {

    public HashMap<Item, Integer> inputs;
    public Pair<Item, Integer> outputs;
    public double craftTime = 0.5;
    public String name;

    public Recipe(String s) {
        inputs = new HashMap<Item, Integer>();
        String[] vals = s.split("\"");
        //System.out.println(vals[1]);
        name = vals[1];
    }

    public void addIngredients(String i) {
        //System.out.println(i);
        i = i.replace("ingredients =", "");
        i = i.trim();
        //System.out.println(i);
        String[] ingredients = i.split("\\}\\w?,");
        int x = 0;
        while (x < ingredients.length) {
            Pattern p = Pattern.compile(".*?\"([^\\\"]*?)\\\",\\s?(\\d+).*?");
            Matcher m = p.matcher(ingredients[x]);
            if (m.matches()) {
                String ingname = m.group(1);
                int amount = Integer.parseInt(m.group(2));
                inputs.put(Loader.items.get(ingname), amount);
            }
            x++;
        }
    }
    
    public void addOutput(Item s){
        outputs = new Pair(s,1);
    }

}
