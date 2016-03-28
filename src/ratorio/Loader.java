/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratorio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Dutch Jer
 */
public class Loader {

    //TODO add selection for loading from different place(other then default %appdata%)
    //TODO load recipes&items from other mods.
    public static String modlocation = System.getProperty("user.home") + "\\Appdata\\Roaming\\Factorio";
    public static String baselocation = System.getenv("ProgramFiles") + "\\Factorio\\data\\base\\";
    public static HashMap<String, Item> items = new HashMap<String, Item>();
    public static HashMap<String, Recipe> recipes = new HashMap<String, Recipe>();

    public Loader() {
        loadItems(baselocation);
    }

    private void loadItems(String path) {
        try {//items
            Files.walk(Paths.get(path + "prototypes\\item\\")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println(filePath);
                    if (!filePath.toString().contains("groups")) {
                        try {
                            List<String> lines = Files.readAllLines(filePath);
                            Item it = null;
                            for (String l : lines) {
                                l = l.trim();

                                if (l.startsWith("name")) {
                                    it = new Item(l);
                                    //recipes.add(new Recipe(l));
                                }
                                if (l.startsWith("icon")) {
                                    it.addIcon(l);
                                    items.put(it.name, it);

                                }

                            }

                        } catch (IOException ex) {
                            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        } catch (FileNotFoundException ef) {
            ef.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(items.size());

        try {//recipes
            Files.walk(Paths.get(path + "prototypes\\recipe\\")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println(filePath);
                    if (!filePath.toString().contains("groups")) {
                        try {
                            List<String> lines = Files.readAllLines(filePath);
                            Recipe r = null;
                            for (int i = 0; i < lines.size(); i++) {
                                if (r != null) {
                                    recipes.put(r.name, r);
                                }
                                lines.set(i, lines.get(i).trim());
                                if (lines.get(i).startsWith("name")) {
                                    r = new Recipe(lines.get(i));
                                    //System.out.println(lines.get(i));
                                }
                                if (lines.get(i).startsWith("ingredients")) {
                                    String ingredients = lines.get(i);
                                    i++;
                                    while (!lines.get(i).contains("=")) {
                                        ingredients += lines.get(i);
                                        i++;
                                    }
                                    r.addIngredients(ingredients);
                                }
                                if(lines.get(i).startsWith("result =")){
                                    String[] outputs = lines.get(i).split("\"");
                                    r.addOutput(items.get(outputs[1]));
                                }
                                if(lines.get(i).startsWith("result_count")){
                                    int resultcount = Integer.parseInt(lines.get(i).split("= ")[1]);
                                    r.outputs = new Pair(items.get(lines.get(i-1).split("\"")[1]),resultcount);
                                }

                            }

                        } catch (IOException ex) {
                            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        } catch (FileNotFoundException ef) {
            ef.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(recipes.size());

    }
}
