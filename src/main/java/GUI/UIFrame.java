package GUI;

import GUI.Windows.*;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static GUI.Nodes.stopServer;

public class UIFrame extends JFrame implements ActionListener {
    private static String NODE1name;
    private static String NODE2name;
    private static String NODE3name;
    private static String NODE4name;

    private static String NODE1time;
    private static String NODE2time;
    private static String NODE3time;
    private static String NODE4time;

    private static int NODE1currentID;
    private static int NODE1nextID;
    private static int NODE1prevID;
    private static String NODE1nextIP;
    private static String NODE1prevIP;

    private static int NODE2currentID;
    private static int NODE2nextID;
    private static int NODE2prevID;
    private static String NODE2nextIP;
    private static String NODE2prevIP;

    private static int NODE3currentID;
    private static int NODE3nextID;
    private static int NODE3prevID;
    private static String NODE3nextIP;
    private static String NODE3prevIP;

    private static int NODE4currentID;
    private static int NODE4nextID;
    private static int NODE4prevID;
    private static String NODE4nextIP;
    private static String NODE4prevIP;

    private Nodes Node1;
    private Nodes Node2;
    private Nodes Node3;
    private Nodes Node4;
    private Nodes Server;


    JTextArea StatusserverON = new JTextArea("SERVER RUNNING");
    JTextArea Statusnode1ON = new JTextArea("ON");
    JTextArea Statusnode2ON = new JTextArea("ON");
    JTextArea Statusnode3ON = new JTextArea("ON");
    JTextArea Statusnode4ON = new JTextArea("ON");

    JTextArea StatusserverOFF = new JTextArea("SERVER NOT RUNNING");
    JTextArea Statusnode1OFF = new JTextArea("OFF");
    JTextArea Statusnode2OFF = new JTextArea("OFF");
    JTextArea Statusnode3OFF = new JTextArea("OFF");
    JTextArea Statusnode4OFF = new JTextArea("OFF");

    JTextField node1name = new JTextField(10);
    JTextField node2name = new JTextField(10);
    JTextField node3name = new JTextField(10);
    JTextField node4name = new JTextField(10);

    JTextField node1time = new JTextField(10);
    JTextField node2time = new JTextField(10);
    JTextField node3time = new JTextField(10);
    JTextField node4time = new JTextField(10);

    JButton STARTserver = new JButton("START SERVER");
    JButton STARTnode1 = new JButton("START N1");
    JButton STARTnode2 = new JButton("START N2");
    JButton STARTnode3 = new JButton("START N3");
    JButton STARTnode4 = new JButton("START N4");

    JButton SHUTDOWNserver = new JButton("KILL SERVER");
    JButton SHUTDOWNnode1 = new JButton("KILL N1");
    JButton SHUTDOWNnode2 = new JButton("KILL N2");
    JButton SHUTDOWNnode3 = new JButton("KILL N3");
    JButton SHUTDOWNnode4 = new JButton("KILL N4");

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

        return null;
    }
    public static String getTime(int number) {
        if(number == 1){
            return NODE1time;
        }
        else if(number == 2){
            return NODE2time;
        }
        else if(number == 3){
            return NODE3time;
        }
        else if(number == 4){
            return NODE4time;
        }
        return null;
    }
    public static int getCurrentID(int number){
        if(number == 1){
            return NODE1currentID;
        }
        else if(number == 2){
            return NODE2currentID;
        }
        else if(number == 3){
            return NODE3currentID;
        }
        else if(number == 4){
            return NODE4currentID;
        }
        return 0;
    }
    public static int getNextID(int number){
        if(number == 1){
            return NODE1nextID;
        }
        else if(number == 2){
            return NODE2nextID;
        }
        else if(number == 3){
            return NODE3nextID;
        }
        else if(number == 4){
            return NODE4nextID;
        }
        return 0;
    }
    public static String getNextIP(int number){
        if(number == 1){
            return NODE1nextIP;
        }
        else if(number == 2){
            return NODE2nextIP;
        }
        else if(number == 3){
            return NODE3nextIP;
        }
        else if(number == 4){
            return NODE4nextIP;
        }
        return null;
    }
    public static int getPreviousID(int number){
        if(number == 1){
            return NODE1prevID;
        }
        else if(number == 2){
            return NODE2prevID;
        }
        else if(number == 3){
            return NODE3prevID;
        }
        else if(number == 4){
            return NODE4prevID;
        }
        return 0;
    }
    public static String getPreviousIP(int number){
        if(number == 1){
            return NODE1prevIP;
        }
        else if(number == 2){
            return NODE2prevIP;
        }
        else if(number == 3){
            return NODE3prevIP;
        }
        else if(number == 4){
            return NODE4prevIP;
        }
        return null;
    }

    public UIFrame() {
        super("Distributed project");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialise();
        this.setVisible(true);
    }

    private void initialise() {
        this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());
        JLabel server = new JLabel("SERVER:");
        AddComponent(server, new Insets(-400,-600,10,10));

        JButton node1 = new JButton("NODE1:");
        AddComponent(node1, new Insets(-300,-600,10,10));
        node1.addActionListener(this);

        JButton node2 = new JButton("NODE2:");
        AddComponent(node2, new Insets(-200,-600,10,10));
        node2.addActionListener(this);

        JButton node3 = new JButton("NODE3:");
        AddComponent(node3, new Insets(-100,-600,10,10));
        node3.addActionListener(this);

        JButton node4 = new JButton("NODE4:");
        AddComponent(node4, new Insets(0,-600,10,10));
        node4.addActionListener(this);

        JLabel name = new JLabel("Node Name: ");
        AddComponent(name, new Insets(-400, -300, 10, 10));
        JLabel time = new JLabel("Time (ms): ");
        AddComponent(time, new Insets(-400, 0, 10, 10));


        AddComponent(node1name, new Insets(-300,-300,10,10));
        AddComponent(node2name, new Insets(-200,-300,10,10));
        AddComponent(node3name, new Insets(-100,-300,10,10));
        AddComponent(node4name, new Insets(0,-300,10,10));

        AddComponent(node1time, new Insets(-300,0,10,10));
        AddComponent(node2time, new Insets(-200,0,10,10));
        AddComponent(node3time, new Insets(-100,0,10,10));
        AddComponent(node4time, new Insets(0,0,10,10));

        AddComponent(STARTserver, new Insets(-400,300,10,10));
        STARTserver.addActionListener(this);
        AddComponent(STARTnode1, new Insets(-300,300,10,10));
        STARTnode1.addActionListener(this);
        AddComponent(STARTnode2, new Insets(-200,300,10,10));
        STARTnode2.addActionListener(this);
        AddComponent(STARTnode3, new Insets(-100,300,10,10));
        STARTnode3.addActionListener(this);
        AddComponent(STARTnode4, new Insets(0,300,10,10));
        STARTnode4.addActionListener(this);


        AddComponent(SHUTDOWNserver, new Insets(-400,650,10,10));
        SHUTDOWNserver.addActionListener(this);
        SHUTDOWNserver.setEnabled(false);
        AddComponent(SHUTDOWNnode1, new Insets(-300,650,10,10));
        SHUTDOWNnode1.addActionListener(this);
        SHUTDOWNnode1.setEnabled(false);
        AddComponent(SHUTDOWNnode2, new Insets(-200,650,10,10));
        SHUTDOWNnode2.addActionListener(this);
        SHUTDOWNnode2.setEnabled(false);
        AddComponent(SHUTDOWNnode3, new Insets(-100,650,10,10));
        SHUTDOWNnode3.addActionListener(this);
        SHUTDOWNnode3.setEnabled(false);
        AddComponent(SHUTDOWNnode4, new Insets(0,650,10,10));
        SHUTDOWNnode4.addActionListener(this);
        SHUTDOWNnode4.setEnabled(false);

        AddComponent(Statusnode1ON, new Insets(-250,-600,10,10));
        this.Statusnode1ON.setVisible(false);
        AddComponent(Statusnode2ON, new Insets(-150,-600,10,10));
        this.Statusnode2ON.setVisible(false);
        AddComponent(Statusnode3ON, new Insets(-50,-600,10,10));
        this.Statusnode3ON.setVisible(false);
        AddComponent(Statusnode4ON, new Insets(50,-600,10,10));
        this.Statusnode4ON.setVisible(false);
        AddComponent(StatusserverON, new Insets(-365,-600,10,10));
        this.StatusserverON.setVisible(false);

        AddComponent(Statusnode1OFF, new Insets(-250,-600,10,10));
        AddComponent(Statusnode2OFF, new Insets(-150,-600,10,10));
        AddComponent(Statusnode3OFF, new Insets(-50,-600,10,10));
        AddComponent(Statusnode4OFF, new Insets(50,-600,10,10));
        AddComponent(StatusserverOFF, new Insets(-365,-600,10,10));
    }

    private void AddComponent(Component component, Insets insets){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = insets;
        c.fill = GridBagConstraints.NONE;
        this.add(component, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "NODE1:":
                try {
                    NODE1currentID = Node1.getCurrentID();
                    NODE1nextID = Node1.getNextID();
                    NODE1nextIP = Node1.getNextIP();
                    NODE1prevID = Node1.getPrevID();
                    NODE1prevIP = Node1.getPrevIP();
                    Node1Window n1W = new Node1Window();

                } catch (IOException | ParseException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
                break;
            case "NODE2:":
                try {
                    NODE2currentID = Node2.getCurrentID();
                    NODE2nextID = Node2.getNextID();
                    NODE2nextIP = Node2.getNextIP();
                    NODE2prevID = Node2.getPrevID();
                    NODE2prevIP = Node2.getPrevIP();
                    Node2Window n2W = new Node2Window();
                } catch (IOException | ParseException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
                break;
            case "NODE3:":
                NODE3currentID = Node3.getCurrentID();
                NODE3nextID = Node3.getNextID();
                NODE3nextIP = Node3.getNextIP();
                NODE3prevID = Node3.getPrevID();
                NODE3prevIP = Node3.getPrevIP();
                try {
                    Node3Window n3W = new Node3Window();
                } catch (IOException | ParseException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
                break;
            case "NODE4:":
                NODE4currentID = Node4.getCurrentID();
                NODE4nextID = Node4.getNextID();
                NODE4nextIP = Node4.getNextIP();
                NODE4prevID = Node4.getPrevID();
                NODE4prevIP = Node4.getPrevIP();
                try {
                    Node4Window n4W = new Node4Window();
                } catch (IOException | ParseException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
                break;
            case "START N1":
                this.Statusnode1OFF.setVisible(false);
                this.Statusnode1ON.setVisible(true);
                NODE1name = this.node1name.getText();
                NODE1time = this.node1time.getText();
                System.out.println(NODE1name);
                System.out.println(NODE1time);
                Node1 = new Nodes("startNode","0", NODE1name, NODE1time);
                Node1.start();
                this.node1time.setEnabled(false);
                this.node1name.setEnabled(false);
                this.STARTnode1.setEnabled(false);
                this.SHUTDOWNnode1.setEnabled(true);
                break;
            case "START N2":
                this.Statusnode2OFF.setVisible(false);
                this.Statusnode2ON.setVisible(true);
                NODE2name = this.node2name.getText();
                NODE2time = this.node2time.getText();
                Node2 = new Nodes("startNode","2", NODE2name, NODE2time);
                Node2.start();
                this.node2time.setEnabled(false);
                this.node2name.setEnabled(false);
                this.STARTnode2.setEnabled(false);
                this.SHUTDOWNnode2.setEnabled(true);
                break;
            case "START N3":
                this.Statusnode3OFF.setVisible(false);
                this.Statusnode3ON.setVisible(true);
                NODE3name = this.node3name.getText();
                NODE3time = this.node3time.getText();
                Node3 = new Nodes("startNode","3", NODE3name, NODE3time);
                Node3.start();
                this.node3time.setEnabled(false);
                this.node3name.setEnabled(false);
                this.STARTnode3.setEnabled(false);
                this.SHUTDOWNnode3.setEnabled(true);
                break;
            case "START N4":
                this.Statusnode4OFF.setVisible(false);
                this.Statusnode4ON.setVisible(true);
                NODE4name = this.node4name.getText();
                NODE4time = this.node4time.getText();
                Node4 = new Nodes("startNode","4", NODE4name, NODE4time);
                Node4.start();
                this.node4time.setEnabled(false);
                this.node4name.setEnabled(false);
                this.STARTnode4.setEnabled(false);
                this.SHUTDOWNnode4.setEnabled(true);
                break;
            case "KILL N1":
                this.Statusnode1ON.setVisible(false);
                this.Statusnode1OFF.setVisible(true);
                this.node1name.setEnabled(true);
                this.node1time.setEnabled(true);
                this.STARTnode1.setEnabled(true);
                this.SHUTDOWNnode1.setEnabled(false);
                new Nodes("stopNode","0", " ", " ").start();
                break;
            case "KILL N2":
                this.Statusnode2ON.setVisible(false);
                this.Statusnode2OFF.setVisible(true);
                this.node2name.setEnabled(true);
                this.node2time.setEnabled(true);
                this.STARTnode2.setEnabled(true);
                this.SHUTDOWNnode2.setEnabled(false);
                new Nodes("stopNode","2", " ", " ").start();
                break;
            case "KILL N3":
                this.Statusnode3ON.setVisible(false);
                this.Statusnode3OFF.setVisible(true);
                this.node3name.setEnabled(true);
                this.node3time.setEnabled(true);
                this.STARTnode3.setEnabled(true);
                this.SHUTDOWNnode3.setEnabled(false);
                new Nodes("stopNode","3", " ", " ").start();
                break;
            case "KILL N4":
                this.Statusnode4ON.setVisible(false);
                this.Statusnode4OFF.setVisible(true);
                this.node4name.setEnabled(true);
                this.node4time.setEnabled(true);
                this.STARTnode4.setEnabled(true);
                this.SHUTDOWNnode4.setEnabled(false);
                new Nodes("stopNode","4", " ", " ").start();
                break;
            case "START SERVER":
                this.StatusserverOFF.setVisible(false);
                this.StatusserverON.setVisible(true);
                this.STARTserver.setEnabled(false);
                this.SHUTDOWNserver.setEnabled(true);
                new Nodes("startServer", " ", " ", " ").start();
                break;
            case "KILL SERVER":
                this.StatusserverON.setVisible(false);
                this.StatusserverOFF.setVisible(true);
                this.STARTserver.setEnabled(true);
                this.SHUTDOWNserver.setEnabled(false);
                try {
                    stopServer();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                //new Nodes("stopNode","1", " ", " ").start();
                break;
        }
    }
}