import java.util.*;

/**
 * Created by nahua on 2017-09-10.
 * PennX SD1x
 * Homework Assignment 3: Movie Database
 */

public class Movie {
    // instance variables
    String name; // name of the movie
    ArrayList<Actor> actors; // actors in the movie
    double rating; // rating from rotten tomatoes

    // constructors
    public Movie() {
        this.name = "DEFAULT_NAME";
        actors = new ArrayList<Actor>();
        this.rating = 0;
    }

    public Movie(String name) {
        this();
        this.name = name;
    }

    public Movie(String name, ArrayList<Actor> actors, double rating) {
        this.name = name;
        this.actors = actors;
        this.rating = rating;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object obj) {
        Movie movieObj = (Movie) obj; // cast Movie to Object obj
        return this.name == movieObj.name;
    }

}
