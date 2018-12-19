package model;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GenreXML implements GenreDAO {

    private File file;
    private XStream xStream;
    //private List<Genre> genreList;

    public GenreXML(File file) {
        this.file = file;
        xStream = new XStream();
        xStream.alias("Genres", List.class);
        if (!file.exists()) {
            List<Genre> list = new ArrayList<>();
            try {
                xStream.toXML(list, new FileWriter(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Genre newGenre) {
        List<Genre> genres = getAll();
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file));) {
            genres.add(newGenre);
            xStream.toXML(genres, printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UUID id, String newName) {
        List<Genre> genres = getAll();
        for (Genre genre : genres) {
            if (genre.getId().equals(id)) {
                genre.setName(newName);
            }
        }
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
            xStream.toXML(genres, printWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UUID id, TrackFile toDelete) {
    }

    @Override
    public Genre getGenre(UUID id) {
        List<Genre> genres = getAll();
        for (Genre genre : genres) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }
        return null;
    }

    @Override
    public List<Genre> getAll() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return (List<Genre>) xStream.fromXML(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
