package GUI;

import GUI.Windows.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UIFrame extends JFrame implements ActionListener{
    private static String NODE1name;
    private static String NODE2name;
    private static String NODE3name;
    private static String NODE4name;
    private static String NODE5name;

    private Node1Window N1W;
    private Node2Window N2W;
    private Node3Window N3W;
    private Node4Window N4W;
    private Node5Window N5W;

    JTextArea StatusserverON = new JTextArea("SERVER RUNNING");
    JTextArea Statusnode1ON = new JTextArea("ON");
    JTextArea Statusnode2ON = new JTextArea("ON");
    JTextArea Statusnode3ON = new JTextArea("ON");
    JTextArea Statusnode4ON = new JTextArea("ON");
    JTextArea Statusnode5ON = new JTextArea("ON");

    JTextArea StatusserverOFF = new JTextArea("SERVER NOT RUNNING");
    JTextArea Statusnode1OFF = new JTextArea("OFF");
    JTextArea Statusnode2OFF = new JTextArea("OFF");
    JTextArea Statusnode3OFF = new JTextArea("OFF");
    JTextArea Statusnode4OFF = new JTextArea("OFF");
    JTextArea Statusnode5OFF = new JTextArea("OFF");

    JTextField node1name = new JTextField(10);
    JTextField node2name = new JTextField(10);
    JTextField node3name = new JTextField(10);
    JTextField node4name = new JTextField(10);
    JTextField node5name = new JTextField(10);

    JTextField node1time = new JTextField(10);
    JTextField node2time = new JTextField(10);
    JTextField node3time = new JTextField(10);
    JTextField node4time = new JTextField(10);
    JTextField node5time = new JTextField(10);

    JButton STARTserver = new JButton("START SERVER");
    JButton STARTnode1 = new JButton("START N1");
    JButton STARTnode2 = new JButton("START N2");
    JButton STARTnode3 = new JButton("START N3");
    JButton STARTnode4 = new JButton("START N4");
    JButton STARTnode5 = new JButton("START N5");

    JButton SHUTDOWNserver = new JButton("KILL SERVER");
    JButton SHUTDOWNnode1 = new JButton("KILL N1");
    JButton SHUTDOWNnode2 = new JButton("KILL N2");
    JButton SHUTDOWNnode3 = new JButton("KILL N3");
    JButton SHUTDOWNnode4 = new JButton("KILL N4");
    JButton SHUTDOWNnode5 = new JButton("KILL N5");

    public static String getNames(int number) {
        if(number == 1){
            return NODE1name;
        }
        else if(number == 2){
            return NODE2name;
        }
        else if(number == 3){
            return NODE3name;
        }
        else if(number == 4){
            return NODE4name;
        }
        else if(number == 5){
            return NODE5name;
        }
        return null;
    }

    public UIFrame() throws IOException {
        super("Distributed project");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialise();
        this.setVisible(true);
    }

    private void initialise() throws IOException {
        this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());
        JLabel server = new JLabel("SERVER:");
        AddComponent(server, 0,0,1,1, new Insets(-400,-600,10,10), false);

        JButton node1 = new JButton("NODE1:");
        AddComponent(node1, 0,0,1,1, new Insets(-300,-600,10,10), false);
        node1.addActionListener(this);

        JButton node2 = new JButton("NODE2:");
        AddComponent(node2, 0,0,1,1, new Insets(-200,-600,10,10), false);
        node2.addActionListener(this);

        JButton node3 = new JButton("NODE3:");
        AddComponent(node3, 0,0,1,1, new Insets(-100,-600,10,10), false);
        node3.addActionListener(this);

        JButton node4 = new JButton("NODE4:");
        AddComponent(node4, 0,0,1,1, new Insets(0,-600,10,10), false);
        node4.addActionListener(this);

        JButton node5 = new JButton("NODE5:");
        AddComponent(node5, 0,0,1,1, new Insets(100,-600,10,10), false);
        node5.addActionListener(this);

        AddComponent(node1name, 0,0,1,1, new Insets(-300,-300,10,10), false);
        AddComponent(node2name, 0,0,1,1, new Insets(-200,-300,10,10), false);
        AddComponent(node3name, 0,0,1,1, new Insets(-100,-300,10,10), false);
        AddComponent(node4name, 0,0,1,1, new Insets(0,-300,10,10), false);
        AddComponent(node5name, 0,0,1,1, new Insets(100,-300,10,10), false);

        AddComponent(node1time, 0,0,1,1, new Insets(-300,0,10,10), false);
        AddComponent(node2time, 0,0,1,1, new Insets(-200,0,10,10), false);
        AddComponent(node3time, 0,0,1,1, new Insets(-100,0,10,10), false);
        AddComponent(node4time, 0,0,1,1, new Insets(0,0,10,10), false);
        AddComponent(node5time, 0,0,1,1, new Insets(100,0,10,10), false);

        AddComponent(STARTserver, 0,0,1,1, new Insets(-400,300,10,10), false);
        STARTserver.addActionListener(this);
        AddComponent(STARTnode1, 0,0,1,1, new Insets(-300,300,10,10), false);
        STARTnode1.addActionListener(this);
        AddComponent(STARTnode2, 0,0,1,1, new Insets(-200,300,10,10), false);
        STARTnode2.addActionListener(this);
        AddComponent(STARTnode3, 0,0,1,1, new Insets(-100,300,10,10), false);
        STARTnode3.addActionListener(this);
        AddComponent(STARTnode4, 0,0,1,1, new Insets(0,300,10,10), false);
        STARTnode4.addActionListener(this);
        AddComponent(STARTnode5, 0,0,1,1, new Insets(100,300,10,10), false);
        STARTnode5.addActionListener(this);

        AddComponent(SHUTDOWNserver, 0,0,1,1, new Insets(-400,650,10,10), false);
        SHUTDOWNserver.addActionListener(this);
        SHUTDOWNserver.setEnabled(false);
        AddComponent(SHUTDOWNnode1, 0,0,1,1, new Insets(-300,650,10,10), false);
        SHUTDOWNnode1.addActionListener(this);
        SHUTDOWNnode1.setEnabled(false);
        AddComponent(SHUTDOWNnode2, 0,0,1,1, new Insets(-200,650,10,10), false);
        SHUTDOWNnode2.addActionListener(this);
        SHUTDOWNnode2.setEnabled(false);
        AddComponent(SHUTDOWNnode3, 0,0,1,1, new Insets(-100,650,10,10), false);
        SHUTDOWNnode3.addActionListener(this);
        SHUTDOWNnode3.setEnabled(false);
        AddComponent(SHUTDOWNnode4, 0,0,1,1, new Insets(0,650,10,10), false);
        SHUTDOWNnode4.addActionListener(this);
        SHUTDOWNnode4.setEnabled(false);
        AddComponent(SHUTDOWNnode5, 0,0,1,1, new Insets(100,650,10,10), false);
        SHUTDOWNnode5.addActionListener(this);
        SHUTDOWNnode5.setEnabled(false);

        AddComponent(Statusnode1ON, 0,0,1,1, new Insets(-250,-600,10,10), false);
        this.Statusnode1ON.setVisible(false);
        AddComponent(Statusnode2ON, 0,0,1,1, new Insets(-150,-600,10,10), false);
        this.Statusnode2ON.setVisible(false);
        AddComponent(Statusnode3ON, 0,0,1,1, new Insets(-50,-600,10,10), false);
        this.Statusnode3ON.setVisible(false);
        AddComponent(Statusnode4ON, 0,0,1,1, new Insets(50,-600,10,10), false);
        this.Statusnode4ON.setVisible(false);
        AddComponent(Statusnode5ON, 0,0,1,1, new Insets(150,-600,10,10), false);
        this.Statusnode5ON.setVisible(false);
        AddComponent(StatusserverON, 0,0,1,1, new Insets(-365,-600,10,10), false);
        this.StatusserverON.setVisible(false);

        AddComponent(Statusnode1OFF, 0,0,1,1, new Insets(-250,-600,10,10), false);
        AddComponent(Statusnode2OFF, 0,0,1,1, new Insets(-150,-600,10,10), false);
        AddComponent(Statusnode3OFF, 0,0,1,1, new Insets(-50,-600,10,10), false);
        AddComponent(Statusnode4OFF, 0,0,1,1, new Insets(50,-600,10,10), false);
        AddComponent(Statusnode5OFF, 0,0,1,1, new Insets(150,-600,10,10), false);
        AddComponent(StatusserverOFF, 0,0,1,1, new Insets(-365,-600,10,10), false);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "NODE1:":
                N1W = new Node1Window();
                break;
            case "NODE2:":
                N2W = new Node2Window();
                break;
            case "NODE3:":
                N3W = new Node3Window();
                break;
            case "NODE4:":
                N4W = new Node4Window();
                break;
            case "NODE5:":
                N5W = new Node5Window();
                break;
            case "START N1":
                this.Statusnode1OFF.setVisible(false);
                this.Statusnode1ON.setVisible(true);
                NODE1name = this.node1name.getText();
                this.node1name.setEnabled(false);
                this.STARTnode1.setEnabled(false);
                this.SHUTDOWNnode1.setEnabled(true);
                //Nodes.startNode(1);
                break;
            case "START N2":
                this.Statusnode2OFF.setVisible(false);
                this.Statusnode2ON.setVisible(true);
                NODE2name = this.node2name.getText();
                this.node2name.setEnabled(false);
                this.STARTnode2.setEnabled(false);
                this.SHUTDOWNnode2.setEnabled(true);
                break;
            case "START N3":
                this.Statusnode3OFF.setVisible(false);
                this.Statusnode3ON.setVisible(true);
                NODE3name = this.node3name.getText();
                this.node3name.setEnabled(false);
                this.STARTnode3.setEnabled(false);
                this.SHUTDOWNnode3.setEnabled(true);
                break;
            case "START N4":
                this.Statusnode4OFF.setVisible(false);
                this.Statusnode4ON.setVisible(true);
                NODE4name = this.node4name.getText();
                this.node4name.setEnabled(false);
                this.STARTnode4.setEnabled(false);
                this.SHUTDOWNnode4.setEnabled(true);
                break;
            case "START N5":
                this.Statusnode5OFF.setVisible(false);
                this.Statusnode5ON.setVisible(true);
                NODE5name = this.node5name.getText();
                this.node5name.setEnabled(false);
                this.STARTnode5.setEnabled(false);
                this.SHUTDOWNnode5.setEnabled(true);
                break;
            case "KILL N1":
                this.Statusnode1ON.setVisible(false);
                this.Statusnode1OFF.setVisible(true);
                this.node1name.setEnabled(true);
                this.STARTnode1.setEnabled(true);
                this.SHUTDOWNnode1.setEnabled(false);
                break;
            case "KILL N2":
                this.Statusnode2ON.setVisible(false);
                this.Statusnode2OFF.setVisible(true);
                this.node2name.setEnabled(true);
                this.STARTnode2.setEnabled(true);
                this.SHUTDOWNnode2.setEnabled(false);
                break;
            case "KILL N3":
                this.Statusnode3ON.setVisible(false);
                this.Statusnode3OFF.setVisible(true);
                this.node3name.setEnabled(true);
                this.STARTnode3.setEnabled(true);
                this.SHUTDOWNnode3.setEnabled(false);
                break;
            case "KILL N4":
                this.Statusnode4ON.setVisible(false);
                this.Statusnode4OFF.setVisible(true);
                this.node4name.setEnabled(true);
                this.STARTnode4.setEnabled(true);
                this.SHUTDOWNnode4.setEnabled(false);
                break;
            case "KILL N5":
                this.Statusnode5ON.setVisible(false);
                this.Statusnode5OFF.setVisible(true);
                this.node5name.setEnabled(true);
                this.STARTnode5.setEnabled(true);
                this.SHUTDOWNnode5.setEnabled(false);
                break;
            case "START SERVER":
                this.StatusserverOFF.setVisible(false);
                this.StatusserverON.setVisible(true);
                this.STARTserver.setEnabled(false);
                this.SHUTDOWNserver.setEnabled(true);
                break;
            case "KILL SERVER":
                this.StatusserverON.setVisible(false);
                this.StatusserverOFF.setVisible(true);
                this.STARTserver.setEnabled(true);
                this.SHUTDOWNserver.setEnabled(false);
                break;
        }
    }
}

