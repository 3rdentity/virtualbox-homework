/** This is a variation of Connection.java.
  * It uses a DNSHandler instead of a handler.
  *
  * @author Jeremy Dormitzer
  */

import java.net.*;
import java.io.*;

public class DNSConnection implements Runnable {
    private Socket client;
    private static DNSHandler handler = new DNSHandler();
        
    public DNSConnection(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            handler.process(client);
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
