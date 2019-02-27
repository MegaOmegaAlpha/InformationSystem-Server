package controller;

import model.Genre;
import model.GenreXML;
import model.TrackXML;

import java.io.File;

public class UITrackListFactory {
    /**
     * Returns a new instance of a tracklist.
     * Creates a new genrefile if it doesn't exist.
     * @param trackPath path to the track file
     * @param genrePath path to the genre file
     * @return new tracklist
     */
    public UITrackList getUITrackList(String trackPath, String genrePath) {
        if (trackPath == null || genrePath == null) {
            //return new UITrackListImpl(new TestTrackFile(), new TestGenreFile());
            return null;
        } else {
            GenreXML genreFile;
            File file = new File(genrePath);
            if(!file.exists()) {
                genreFile = new GenreXML(file);
                genreFile.add(new Genre("Blues", "1b7514c5-e9d2-4377-b228-5f23daa432cb"));
                genreFile.add(new Genre("Rock", "baaac02d-d548-4a17-80a9-b7f4ce8a9763"));
                genreFile.add(new Genre("Rap", "917d3a79-a83b-4664-bb72-123e0acbf618"));
                genreFile.add(new Genre("Jazz", "474d1b2e-eddf-4ac8-97b8-9ac4fe0ffd48"));
                genreFile.add(new Genre("Classic", "bc3773d8-887c-44e5-8499-c57682d832cc"));
                genreFile.add(new Genre("Country", "a1328ca1-9147-45c2-86a3-6e3071d40296"));
            } else {
                genreFile = new GenreXML(file);
            }
            return new UITrackListImpl(new TrackXML(new File(trackPath)), genreFile);
        }
    }
}
