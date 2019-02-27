package controller;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    private UITrackList trackList;

    public Message handle(Message m){
        Message response = new Message();

        switch (m.actionType){
            case INIT:
                UITrackListFactory factory = new UITrackListFactory();
                trackList = factory.getUITrackList(m.trackfile, m.genrefile);
                response.actionType = Message.ActionType.INIT;
                if (trackList == null) {
                    response.success = false;
                } else {
                    response.success = true;
                }
                return response;

            case NEW:
                response.track = trackList.newTrack();
                response.actionType = Message.ActionType.NEW;
                response.success = true;
                return response;

            case FIX_NEW:
                trackList.markAsNew(m.track);
                response.actionType = Message.ActionType.FIX_NEW;
                response.success = true;
                return response;

            case DELETE:
                trackList.delete(m.track);
                response.actionType = Message.ActionType.DELETE;
                response.success = true;
                return response;

            case EDIT:
                trackList.markAsChanged(m.track);
                response.actionType = Message.ActionType.EDIT;
                response.success = true;
                return response;

            case SAVE:
                trackList.synchronize();
                response.actionType = Message.ActionType.SAVE;
                response.success = true;
                return response;
            case SIZE:
                response.size = trackList.size();
                response.actionType = Message.ActionType.SIZE;
                response.success = true;
                return response;
            case GET:
                List<UITrack> list = trackList.getTracks();
                int page = m.page;
                response.actionType = Message.ActionType.GET;
                int pages = list.size()/8 + ((list.size()%8 == 0)? 0 : 1);
                if ( pages < page || page < 0)
                    response.success = false;
                else {
                    List<UITrack> shortList = new ArrayList<>();
//                    if ( pages == page )
//                        response.end = true;
                    for ( int i = (page - 1)*8; i < page*8; i++ ) {
                        try {
                            shortList.add(list.get(i));
                        } catch (IndexOutOfBoundsException e) {
                        }
                    }
                    response.list = shortList;
                    response.success = true;
                }
                return response;
        }

        return null;
    }
}
