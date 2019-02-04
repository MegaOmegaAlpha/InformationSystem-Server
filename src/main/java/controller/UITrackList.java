package controller;

import javafx.collections.ObservableList;
import model.*;

import java.util.List;

public interface UITrackList {
    int size();
    List<UITrack> getTracks();
    void delete(UITrack track);
    void markAsChanged(UITrack track);
    UITrack newTrack();
    void synchronize();
//  GenreDAO getGenreFile();
}
