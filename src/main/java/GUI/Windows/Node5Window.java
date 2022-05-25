package GUI.Windows;

import GUI.UIFrame;

import javax.swing.*;
import java.awt.*;

public class Node5Window extends JFrame {
    private String NODE5name;

    public Node5Window(){
        super("Project distributed");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Initialise();
        this.setVisible(true);
    }

    private void Initialise(){
        this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());
        this.NODE5name = UIFrame.getNames(5);
        JLabel node1 = new JLabel("NODE5: " + NODE5name);
        AddComponent(node1, 0,0,1,1, new Insets(-400,-600,10,10), false);
        //hier een update functie voor de prevID en nextID
        JLabel files = new JLabel("FILES:");
        AddComponent(files, 0,0,1,1, new Insets(-400,200,10,10), false);
        JLabel RepFiles = new JLabel("replicated:");
        AddComponent(RepFiles, 0,0,1,1, new Insets(-350,180,10,10), false);
        JLabel LocalFiles = new JLabel("local:");
        AddComponent(LocalFiles, 0,0,1,1, new Insets(100,200,10,10), false);
        JTextField AddRepFile = new JTextField(10);
        AddComponent(AddRepFile, 0,0,1,1, new Insets(-345,400,10,10), false);
        JButton AddRepFileButton = new JButton("ADD replicated");
        AddComponent(AddRepFileButton, 0,0,1,1, new Insets(-345,700,10,10), false);
        JButton RemoveRepFileButton = new JButton("REMOVE replicated");
        AddComponent(RemoveRepFileButton, 0,0,1,1, new Insets(-280,700,10,10), false);
        JTextField AddLocalFile = new JTextField(10);
        AddComponent(AddLocalFile, 0,0,1,1, new Insets(100,400,10,10), false);
        JButton AddLocalFileButton = new JButton("ADD local");
        AddComponent(AddLocalFileButton, 0,0,1,1, new Insets(95,700,10,10), false);
        JButton RemoveLocalFileButton = new JButton("REMOVE local");
        AddComponent(RemoveLocalFileButton, 0,0,1,1, new Insets(160,700,10,10), false);
    }

    private void AddComponent(Component component, int row, int column, int width, int height, Insets insets, boolean fill){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = width;
        c.gridheight = height;
        c.insets = insets;
        if(fill)
            c.fill = GridBagConstraints.HORIZONTAL;
        else
            c.fill = GridBagConstraints.NONE;
        this.add(component, c);
    }
}
