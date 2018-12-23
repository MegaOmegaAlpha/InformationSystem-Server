package connection;

import model.Genre;
import model.Track;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {

    Socket socket;
    private static final Logger logger = Logger.getLogger(Server.class);

    public Server(Socket socket) {
        this.socket = socket;
        logger.info("Client connected");
    }

    @Override
    public void run() {
        while(true) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject("Hello, client!");
            } catch (SocketException e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                logger.error("IOException in 'run'");
            }
        }
    }
}
