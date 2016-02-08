/** This is an extension of Connection.java.
  * It uses a DNSHandler instead of a handler.
  *
  * @author Jeremy Dormitzer
  */

import java.net.*;
import java.io.*;

public class DNSConnection extends Connection {
    public DNSConnection(Socket client) {
        super(client);
        this.handler = new DNSHandler();
    }
}
