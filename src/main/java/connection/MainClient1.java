package connection;

import model.Track;
import org.apache.log4j.Logger;
import protocol.Command;
import protocol.SimpleMessageForTraining;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class MainClient1 {

    private static final Logger logger = Logger.getLogger(MainClient1.class);
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 0066);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream;
            while (true) {
                System.out.println("Print command:");
                Scanner scanner = new Scanner(System.in);
                SimpleMessageForTraining message;
                switch (scanner.next()) {
                    case "GetAll":
                        message = new SimpleMessageForTraining(Command.GetAll, null);
                        objectOutputStream.writeObject(message);
                        objectInputStream = new ObjectInputStream(socket.getInputStream());
                        List<Track> trackList = (List<Track>) objectInputStream.readObject();
                        for (Track track : trackList) {
                            System.out.println(track.getName());
                        }
                        break;
                }
            }
        } catch (ConnectException e) {
            logger.error("Connection is not set", e);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
