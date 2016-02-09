/**
  * Handler class containing the logic for DNS lookup requests
  * 
  * @author Jeremy Dormitzer
  */

import java.io.*;
import java.net.*;

public class DNSHandler {
    public static final int BUFFER_SIZE = 256;

    public void process(Socket client) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedReader fromClient = null;
        PrintWriter toClient = null;

        try {
            fromClient = new BufferedReader (new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream());

            String bufString;
            InetAddress hostAddress;

            bufString = fromClient.readLine();
            System.out.printf("Client request read: %s\n", bufString);

             try {
                hostAddress = InetAddress.getByName(bufString);
                System.out.printf("Host address is %s\n", hostAddress.getHostAddress());
                String address = hostAddress.getHostAddress();
                toClient.println(address);
                toClient.flush();
             }
             catch (UnknownHostException uhe) {
                String message = "Unknown Host: " + bufString;
                toClient.println(message);
                toClient.flush();
             }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            if (fromClient != null) fromClient.close();
            if (toClient != null) toClient.close();
        }
    }
}
