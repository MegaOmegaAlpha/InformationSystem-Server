package controller;

import model.GenreDAO;
import model.Track;
import model.TrackDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class UITrackListImpl implements UITrackList {

    private TrackDAO source;
    private GenreDAO genreFile;
    private List<UITrack> tracks;

    private List<UITrack> changedTracks;
    private List<UITrack> newTracks;
    private List<UITrack> deleteTracks;

    public UITrackListImpl(TrackDAO source, GenreDAO genreFile) {
        this.source = source;
        this.genreFile = genreFile;
        changedTracks = new ArrayList<>();
        newTracks = new ArrayList<>();
        deleteTracks = new ArrayList<>();
        synchronize();
    }

    private List<UITrack> getAll(){
        List<UITrack> newList;
        List<Track> sourceList = source.getAll();

        newList = sourceList.stream().map(x -> new UITrackImpl(x, genreFile)).collect(Collectors.toList());

        return newList;
    }

    @Override
    public void synchronize() {
        // Updating the trackfile
        for (UITrack item : changedTracks) {
            String album;
            if (item.getAlbum().contentEquals("no_album")) album = null;
            else album = item.getAlbum();

            List<UUID> genres = new ArrayList<>();
            //List<String> genreSource = item.getGenres();
            //TODO:
            //genres = genreSource.stream().map(x -> genreFile.getGenreIdByName(x)).collect(Collectors.toList());

            Track track = new Track(item.getName(), item.getArtists(), Optional.ofNullable(album), UITrackUtils.stringTimeToInt(item.getDuration()), genres);
            source.update(item.getId(), track);
        }
        for (UITrack item : deleteTracks) {
            source.delete(item.getId());
        }
        for (UITrack item : newTracks) {
            String album;
            if (item.getAlbum().contentEquals("no_album")) album = null;
            else album = item.getAlbum();

            List<UUID> genres = new ArrayList<>();
            //List<String> genreSource = item.getGenres();
            //TODO:
            //genres = genreSource.stream().map(x -> genreFile.getGenreIdByName(x)).collect(Collectors.toList());

            Track track = new Track(item.getName(), item.getArtists(), Optional.ofNullable(album), UITrackUtils.stringTimeToInt(item.getDuration()), genres);
            source.add(track);
        }
        changedTracks.clear();
        deleteTracks.clear();
        newTracks.clear();

        // Get new track list to make sure it matches the trackfile even if something went wrong
        tracks = getAll();
    }

    @Override
    public int size(){
        return tracks.size();
    }

    private List<UITrack> find(String artists, String album, String name, String duration, String genre) {
        return  tracks.stream().filter(x -> x.getArtists().contains(artists)).filter(x -> x.getAlbum().contains(album))
                .filter(x -> x.getName().contains(name)).filter(x -> x.getDuration().contentEquals(duration))
                .filter(x -> x.getGenres().contains(genre)).collect(Collectors.toList());
    }

    @Override
    public List<UITrack> getTracks() {
        return tracks;
    }

    @Override
    public void delete(UITrack track) {
        deleteTracks.add(track);
        tracks.remove(track);
    }

    @Override
    public void markAsChanged(UITrack track) {
        changedTracks.add(track);
    }

    @Override
    public UITrack newTrack() {
        UITrackImpl track = new UITrackImpl(genreFile);
        newTracks.add(track);
        tracks.add(track);
        return track;
    }
}
