/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratorio;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Dutch Jer
 */
public class Frame extends JFrame {

    public Frame() {
        JPanel panel = new JPanel();
        String[] columnNames = {"icon", "name"};
        Object[][] data = new Object[Loader.items.size()][2];
        int i = 0;
        Iterator it = Loader.items.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            //icon
            Item item = (Item)pair.getValue();
            data[i][0] = new ImageIcon(((Item)pair.getValue()).icon);
            //name
            data[i][1] = ((Item)pair.getValue()).name;
            i++;
        }
        JTable table = new JTable(data,columnNames);
        
        JScrollPane tableContainer = new JScrollPane(table);

        panel.add(tableContainer, BorderLayout.CENTER);
        this.getContentPane().add(panel);
        this.pack();
        this.setVisible(true);
    }
}
