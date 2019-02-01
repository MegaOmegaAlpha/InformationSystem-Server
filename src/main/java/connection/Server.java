package connection;

import model.Genre;
import model.Track;
import model.TrackXML;
import org.apache.log4j.Logger;
import protocol.SimpleMessageForTraining;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class Server implements Runnable {

    private Socket socket;
    private static final Logger logger = Logger.getLogger(Server.class);

    public Server(Socket socket) {
        this.socket = socket;
        System.out.println("Client connected");
        logger.info("Client connected");
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream;
            SimpleMessageForTraining message;
            TrackXML trackXML = new TrackXML(new File("target/tracksToSend"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (!Thread.currentThread().isInterrupted()) {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                message = (SimpleMessageForTraining) objectInputStream.readObject();
                switch (message.getCommand()) {
                    case Add:
                        break;
                    case Get:
                        /*
                        controller methods have to be called in cases?
                         */
                        objectOutputStream.writeObject(trackXML.getTrack(message.getTrackId()));
                        break;
                    case Delete:
                        break;
                    case Update:
                        break;
                    case GetAll:
                        objectOutputStream.writeObject(trackXML.getAll());
                        break;
                }
            }
        } catch (SocketException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                logger.error("IOException while socket closing");
            }
        } catch (IOException e) {
            logger.error("IOException in 'run'");
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException in 'run'");
        }
    }
}
