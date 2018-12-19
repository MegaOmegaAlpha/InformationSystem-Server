package model;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrackXML implements TrackDAO{

    private File file;
    private XStream xStream;

    public TrackXML(File file) {
        this.file = file;
        xStream = new XStream();
        if (!file.exists()) {
            List<Track> trackList = new ArrayList<>();
            try {
                xStream.toXML(trackList, new FileWriter(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Track newTrack) {
        List<Track> tracks = getAll();
        tracks.add(newTrack);
        try {
            xStream.toXML(tracks, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UUID id, Track newTrack) {
        List<Track> tracks = getAll();
        for (Track track : tracks) {
            if (track.getId().equals(id)) {
                track.setName(newTrack.getName());
                track.setArtists(newTrack.getArtists());
                track.setAlbum(newTrack.getAlbum());
                track.setDuration(newTrack.getDuration());
                track.setGenresId(newTrack.getGenresId());
            }
        }
        try {
            xStream.toXML(tracks, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UUID id) {
        List<Track> tracks = getAll();
        for (Track track : tracks) {
            if (track.getId().equals(id)) {
                tracks.remove(track);
                break;
            }
        }
        try {
            xStream.toXML(tracks, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Track getTrack(UUID id) {
        List<Track> tracks = getAll();
        for (Track track : tracks) {
            if (track.getId().equals(id)) {
                return track;
            }
        }
        return null;
    }

    @Override
    public List<Track> getAll() {
        try {
            return (List<Track>) xStream.fromXML(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
