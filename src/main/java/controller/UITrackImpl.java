package controller;

import model.GenreDAO;
import model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class UITrackImpl implements UITrack {

    private UUID id;
    private String name;
    private List<String> artists;
    private String album;
    private String duration;
    private List<String> genres;

    public UITrackImpl(Track source, GenreDAO genreFile) {
        id = source.getId();
        name = source.getName();
        artists = source.getArtists();
        album = source.getAlbum().orElse("no_album");
        duration = UITrackUtils.intTimeToString(source.getDuration());

        List<String> newList;
        List<UUID> genreSource = source.getGenresId();
        newList = genreSource.stream().map(x -> genreFile.getGenre(x).getName()).collect(Collectors.toList());
        genres = newList;
    }


    public UITrackImpl(GenreDAO genreFile){
        //source = new Track("empty_track", new ArrayList<String>(), null, 0, null);
        this(new Track("empty_track", new ArrayList<String>(), Optional.empty(), 0, new ArrayList<UUID>()), genreFile);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getArtists() {
        return artists;
    }

    @Override
    public String getAlbum() {
        return album;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAlbum(String album) {
        this.album = album;
    }


    @Override
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = UITrackUtils.intTimeToString(duration);
    }

    @Override
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    @Override
    public void setGenre(List<String> genres) {
        this.genres = genres;
    }
}
