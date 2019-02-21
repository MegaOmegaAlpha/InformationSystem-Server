package controller;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainClient1 {

    private static final Logger logger = Logger.getLogger(MainClient1.class);
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 0066)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream;
            Message message = new Message();
            message.actionID = Message.ActionID.ID_INIT;
            message.genrefile = "genreFile";
            message.trackfile = "trackFile";
            objectOutputStream.writeObject(message);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
            System.out.println(message.success);
            System.out.println();

            Thread.sleep(100);
            message = new Message();
            message.actionID = Message.ActionID.ID_NEW;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
            UITrack uiTrack = message.track;
            System.out.println(uiTrack.getName() + " " + uiTrack.getAlbum());

            message.actionID = Message.ActionID.ID_SAVE;
            uiTrack.setName("SongName");
            uiTrack.setAlbum("SongAlbum");
            uiTrack.setDuration(12);
            List<String> genreList = new ArrayList<>();
            genreList.add("Blues");
            genreList.add("Rock");
            uiTrack.setGenre(genreList);
            message.track = uiTrack;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
            System.out.println(message.success);

            /*message = new Message();
            message.actionID = Message.ActionID.ID_EDIT;
            uiTrack.setName("SongName");
            uiTrack.setAlbum("SongAlbum");
            uiTrack.setDuration(12);
            List<String> genreList = new ArrayList<>();
            genreList.add("Blues");
            genreList.add("Rock");
            uiTrack.setGenre(genreList);
            message.track = uiTrack;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();


            message.actionID = Message.ActionID.ID_SAVE;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
            System.out.println(message.success);*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
