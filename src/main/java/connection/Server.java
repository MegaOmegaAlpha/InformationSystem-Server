package connection;

import controller.Message;
import controller.MessageHandler;
import model.Genre;
import model.Track;
import model.TrackXML;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class Server implements Runnable {

    private Socket socket;
    private static final Logger logger = Logger.getLogger(Server.class);
    private static final int TIMEOUT_VALUE = 300000;

    public Server(Socket socket) {
        this.socket = socket;
        System.out.println("Client connected");
        logger.info("Client connected");
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (!Thread.currentThread().isInterrupted()) {
                socket.setSoTimeout(TIMEOUT_VALUE);
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("message received");
                Message message = (Message) objectInputStream.readObject();
                MessageHandler messageHandler = new MessageHandler();
                message = messageHandler.handle(message);
                objectOutputStream.writeObject(message);
            }
        } catch (SocketException e) {
            logger.error("SocketException in 'run'");
        } catch (IOException e) {
            logger.error("IOException in 'run'");
            System.out.println("There is timeout on server");
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException in 'run'");
        } finally {
            try {
                socket.close();
            } catch (IOException e1) {
                logger.error("IOException while socket closing");
            }
        }
    }
}
