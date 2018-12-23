package connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {

    private static final Logger logger = Logger.getLogger(MainServer.class);

    public static void main(String[] args) {
        logger.info("Server started");
        try {
            ServerSocket serverSocket = new ServerSocket(0066);
            while (true) {
                new Thread(new Server(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
