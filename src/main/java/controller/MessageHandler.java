package controller;

public class MessageHandler {

    private UITrackList trackList;

    public Message handle(Message m){
        Message response;

        switch (m.actionID){
            case ID_INIT:
                UITrackListFactory factory = new UITrackListFactory();
                trackList = factory.getUITrackList(m.trackfile, m.genrefile);
                response = new Message();
                response.actionID = Message.ActionID.ID_NEW;
                return response;
            case ID_NEW:
                response = new Message();
                response.track = trackList.newTrack();
                response.actionID = Message.ActionID.ID_NEW;
                return response;
            case ID_DELETE:
                trackList.delete(m.track);
                return null;
            case ID_EDIT:
                trackList.markAsChanged(m.track);
                return null;
            case ID_SAVE:
                trackList.synchronize();
                return null;
            case ID_GET:
                // TODO:
                return null;
        }

        return null;
    }
}
