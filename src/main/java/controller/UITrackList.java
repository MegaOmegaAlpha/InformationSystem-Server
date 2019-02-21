package controller;

import java.util.List;

public interface UITrackList {
    int size();
    List<UITrack> getTracks();
    void delete(UITrack track);
    void markAsChanged(UITrack track);
    void markAsNew(UITrack track);
    UITrack newTrack();
    void synchronize();
    List<UITrack> find(String artists, String album, String name, String duration, String genre);
//  GenreDAO getGenreFile();
}
