import java.util.*;

/**
 * Created by nahua on 2017-09-10.
 * PennX SD1x
 * Homework Assignment 3: Movie Database
 */

public class Actor {
    // instance variables
    String name;
    ArrayList<Movie> movies;

    // constructors
    public Actor() {
        this.name = "DEFAULT_NAME";
        movies = new ArrayList<Movie>();
    }

    public Actor(String name) {
        this();
        this.name = name;
    }

    public Actor(String name, ArrayList<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object obj) {
        Actor actorObj = (Actor) obj; // cast Movie to Object obj
        return this.name == actorObj.name;
    }
}
