/**
  * Client for DNS lookup
  * @author Jeremy Dormitzer
  */

import java.net.*;
import java.io.*;

public class DNSLookUpClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java DNSLookUpClient <server ip> <desired host name>");
        System.exit(1);
        }
        try {
            Socket sock = new Socket(args[0], 6052);
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader (new InputStreamReader(in));
            OutputStream out = sock.getOutputStream();
            PrintWriter bout = new PrintWriter(out);
            System.out.printf("Writing %s to the server\n", args[1]);
            bout.println(args[1]);
            bout.flush();
            System.out.println("Written");
            String line = bin.readLine();
            System.out.println(line);
            sock.close();
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
