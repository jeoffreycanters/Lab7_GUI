package GUI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Nodes {
    private String NODEname;
    //1. StartNode - 2. Read local files - 3. Read replication files - 4. Kill pidoff java kill process

    public static void startNode(String number, String name, String time) throws IOException, ParseException {
        Process process = Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003"+ number +"\"cd distributed/Distr-Lab5; mvn exec:java -Dexec.mainClass=\"Node.NamingNode\" -Dexec.args=\"-a oliver -b 30000");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            if(line.contains("received data: {\"status\":\"OK\",\"sender\":\"NamingServer\"")){
                String line2 = line.substring(15);
                //System.out.println(line2);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line2);
                String status = ((JSONObject) obj).get("status").toString();
                String sender = ((JSONObject) obj).get("sender").toString();
                int currentID = (int) (long) ((JSONObject) obj).get("node ID");
                int amount = (int) (long) ((JSONObject) obj).get("node amount");
                int previousID = (int) (long) ((JSONObject) obj).get("previousID");
                int nextID = (int) (long) ((JSONObject) obj).get("nextID");
                String previousIP = (String) ((JSONObject) obj).get("previousIP");
                String nextIP = (String) ((JSONObject) obj).get("nextIP");
            }
            System.out.println(line);
        }
    }

    public static void readLocalFiles(int number) throws IOException, ParseException {
        Process process1 = Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003"+ number +"\"cd distributed/Distr-Lab5/src/main/resources/LocalFiles; ls");
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
        String line = "";
        List<String> localFiles = new ArrayList<>();
        while((line = reader1.readLine()) != null){
            localFiles.add(line);
        }
        System.out.println(localFiles);

    }

    public static void readReplicationFiles(int number) throws IOException, ParseException {
        Process process2 = Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003"+ number + "\"cd distributed/Distr-Lab5/src/main/resources/ReplicatedFiles; ls");
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
        String line = "";
        List<String> replicationFiles = new ArrayList<>();
        while((line = reader2.readLine()) != null){
            replicationFiles.add(line);
        }
        System.out.println(replicationFiles);
    }

    public static void stopNode(int number) throws IOException, ParseException {
        Process process3 = Runtime.getRuntime().exec("cmd /c ssh root@dist-computing.idlab.uantwerpen.be -p 1003" + number + "\"cd distributed/Distr-Lab5; kill pidoff java");
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(process3.getInputStream()));
        String line = "";
        System.out.println(reader3);
    }
}
