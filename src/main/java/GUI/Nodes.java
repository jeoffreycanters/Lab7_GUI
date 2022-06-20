package GUI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Nodes extends Thread {
    private final String name;
    private final String action;
    private final String number;
    private final String time;
    private int nextID;
    private String nextIP;
    private int prevID;
    private String prevIP;
    private int currentID;
    //1. StartNode - 2. Read local files - 3. Read replication files - 4. Kill pidoff java kill process

    public Nodes(String action, String number, String name, String time){
        this.name = name;
        this.action = action;
        this.number = number;
        this.time = time;

    }
    public void run(){
        switch(action){
            case "startNode":
                try {
                    startNode(this.number,this.name,this.time);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "stopNode":
                try {
                    stopNode(this.number);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            case "startServer":
                try {
                        startServer();
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

        }
    }
    public void startNode(String number, String name, String time) throws IOException, ParseException {
        String command = "cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number + " cd distributed/Distr-Lab5; mvn exec:java -Dexec.mainClass=\"Node.NamingNode\" -Dexec.args=\"'" + name + " " + time + "'\"";
        System.out.println(command);
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if(line.contains("received data: {\"status\":\"OK\",\"sender\":\"NamingServer\"")){
                String line2 = line.substring(18);
                //System.out.println(line2);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line2);
                currentID = (int) (long) ((JSONObject) obj).get("node ID");
                prevID = (int) (long) ((JSONObject) obj).get("previousID");
                nextID = (int) (long) ((JSONObject) obj).get("nextID");
                prevIP = (String) ((JSONObject) obj).get("previousIP");
                nextIP = (String) ((JSONObject) obj).get("nextIP");
            }
            System.out.println(line);
        }
    }

    public static String[] readLocalFiles(String number) throws IOException {
        Process process1 = Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number +" cd distributed/Distr-Lab5/src/main/resources/LocalFiles; ls");
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
        String line;
        int i = 0;
        String[] localFiles = new String[20];
        while((line = reader1.readLine()) != null){
            localFiles[i] = line;
            i++;
        }
        //System.out.println(Arrays.toString(localFiles));
        return localFiles;

    }
    public static String[] readReplicationFiles(String number) throws IOException {
        Process process2 = Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number + " cd distributed/Distr-Lab5/src/main/resources/ReplicatedFiles; ls");
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
        String line;
        int i = 0;
        String[] replicationFiles = new String[20];
        while((line = reader2.readLine()) != null){
            replicationFiles[i] = line;
            i++;
        }
        //System.out.println(replicationFiles);
        return replicationFiles;
    }
    public static void stopServer() throws IOException {
        Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 10031 killall -9 java");
    }
    public static void stopNode(String number) throws IOException, ParseException {
        Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number + " kill $(pidof java)");
    }
    public static Process startServer() throws IOException, ParseException {
        return Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 10031 cd distributed/Distr-Lab5; mvn spring-boot:run");
    }
    public static void deleteFile(String number, String name) throws IOException, ParseException {
        Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number +" cd distributed/Distr-Lab5/src/main/resources/LocalFiles; rm " + name);
    }
    public static void  createFile(String number, String name) throws IOException, ParseException {
        Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number + " cd distributed/Distr-Lab5/src/main/resources/LocalFiles; touch " + name);
    }

    public int getNextID() {
        return nextID;
    }

    public String getNextIP() {
        return nextIP;
    }

    public int getPrevID() {
        return prevID;
    }

    public String getPrevIP() {
        return prevIP;
    }

    public int getCurrentID() {
        return currentID;
    }
}