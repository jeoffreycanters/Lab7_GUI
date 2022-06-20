package GUI.Windows;

import GUI.Nodes;
import GUI.UIFrame;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Node4Window extends JFrame implements ActionListener {
    JTextField AddLocalFile = new JTextField(10);
    JButton AddLocalFileButton = new JButton("ADD local");
    DefaultListModel LocalModel = new DefaultListModel();
    DefaultListModel ReplicatedModel = new DefaultListModel();
    static JList local;
    static JList replicated;


    public Node4Window() throws IOException, ParseException, InterruptedException {
        super("Project distributed");
        this.setSize(900,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Initialise();
        this.setVisible(true);
    }

    private void Initialise() throws IOException, ParseException, InterruptedException {
        this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());
        String NODE4name = UIFrame.getNames(4);
        String node4time = UIFrame.getTime(4);

        int currID = UIFrame.getCurrentID(4);
        int nextiousID = UIFrame.getNextID(4);
        String nextiousIP = UIFrame.getNextIP(4);
        int previousID = UIFrame.getPreviousID(4);
        String previousIP = UIFrame.getPreviousIP(4);



        JLabel node1 = new JLabel("NODE4: " + NODE4name + ", time running:" + node4time);
        AddComponent(node1, new Insets(-400,-600,10,10));
        //hier een update functie voor de prevID en nextID
        JLabel files = new JLabel("replicated:");
        AddComponent(files, new Insets(-400,200,10,10));
        JLabel LocalFiles = new JLabel("local:");
        AddComponent(LocalFiles, new Insets(0,200,10,10));

        AddComponent(AddLocalFile, new Insets(0,400,10,10));
        AddLocalFileButton.addActionListener(this);

        AddComponent(AddLocalFileButton, new Insets(0,700,10,10));
        JButton RemoveLocalFileButton = new JButton("REMOVE local");
        AddComponent(RemoveLocalFileButton, new Insets(80,700,10,10));
        RemoveLocalFileButton.addActionListener(this);


        updateFiles();
        local = new JList(LocalModel);
        replicated = new JList(ReplicatedModel);
        AddComponent(local, new Insets(200,400,0,0));
        AddComponent(replicated, new Insets(-300,400,0,0));
        JLabel currentID = new JLabel("CurrentID: " + currID);
        JLabel nextID = new JLabel("NextID: " + nextiousID);
        JLabel prevID = new JLabel("PreviousID: " + previousID);
        JLabel nextIP = new JLabel("NextIP: " + nextiousIP);
        JLabel prevIP = new JLabel("PreviousIP: " + previousIP);


        AddComponent(currentID, new Insets(-300,-400,0,0));
        AddComponent(nextID, new Insets(-200,-400,0,0));
        AddComponent(prevID, new Insets(-100,-400,0,0));
        AddComponent(nextIP, new Insets(0,-400,0,0));
        AddComponent(prevIP, new Insets(100,-400,0,0));

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
        switch (e.getActionCommand()) {
            case "ADD local":
                try {
                    Nodes.createFile("4", AddLocalFile.getText());
                    updateFiles();
                } catch (IOException | ParseException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
                break;
            case "REMOVE local":
                try {
                    Nodes.deleteFile("4", AddLocalFile.getText());
                    updateFiles();
                } catch (IOException | ParseException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
        }
    }
    public void updateFiles() throws IOException, ParseException, InterruptedException {
        Thread.sleep(250);
        LocalModel.removeAllElements();
        ReplicatedModel.removeAllElements();
        String[] localFiles = Nodes.readLocalFiles("4");
        int i = 0;
        while(i < localFiles.length){
            LocalModel.addElement(localFiles[i]);
            i++;
        }
        String[] replicatedFiles = Nodes.readReplicationFiles("4");
        int j = 0;
        while(j < replicatedFiles.length){
            ReplicatedModel.addElement(replicatedFiles[j]);
            j++;
        }

    }

}
