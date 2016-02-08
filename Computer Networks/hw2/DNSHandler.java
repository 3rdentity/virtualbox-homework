/**
  * Handler class containing the logic for DNS lookup requests
  * 
  * @author Jeremy Dormitzer
  */

import java.io.*;
import java.net.*;

public class DNSHandler extends Handler {
    public static final int BUFFER_SIZE = 256;

    public void process(Socket client) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        InputStream fromClient = null;
        OutputStream toClient = null;

        try {
            fromClient = new BufferedInputStream(client.getInputStream());
            toClient = new BufferedOutputStream(client.getOutputStream());

            int numBytes;

            while ((numBytes = fromClient.read(buffer)) != -1) {
                InetAddress hostAddress;

                String bufString = new String(buffer);

                try {
                    hostAddress = InetAddress.getByName(bufString);
                    toClient.write(hostAddress.getHostAddress().getBytes(), 0, hostAddress.getHostAddress().length());
                    toClient.flush();
                }
                catch (UnknownHostException uhe) {
                    String message = "Unknown Host: " + bufString;
                    toClient.write(message.getBytes(), 0, message.length());
                    toClient.flush();
                }
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
