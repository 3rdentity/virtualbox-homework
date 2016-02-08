/**
  * DNS look up server listening to port 6052.
  *
  *@author Jeremy Dormitzer
  *
  */

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class DNSLookUpServer {
    public static final int DEFAULT_PORT = 6052;
    
    private static final Executor exec = Executors.newCachedThreadPool();
    
    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;

        try {
            sock = new ServerSocket(DEFAULT_PORT);

            while(true) {
                Runnable task = new DNSConnection(sock.accept());
                exec.execute(task);
            }    
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            if (sock != null) {
                sock.close();
            }
        }
    }
}
