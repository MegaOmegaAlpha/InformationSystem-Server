package controller;

import model.Track;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {

    public enum ActionID {ID_INIT, ID_GET, ID_DELETE, ID_EDIT, ID_NEW, ID_SAVE}

    ActionID actionID;

    int clientID;

    int amount;
    int offset;

    UITrack track;

    //UUID id;

    String trackfile;
    String genrefile;
}
