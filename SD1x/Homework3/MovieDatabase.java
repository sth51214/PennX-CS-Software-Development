import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nahua on 2017-09-10.
 * PennX SD1x
 * Homework Assignment 3: Movie Database
 * MovieDatabase Class does not implement data structures like set or map since the topics are not in SD1x Week 3.
 */

public class MovieDatabase {
    // instance variable
    ArrayList<Movie> movieList;
    ArrayList<Actor> actorList;

    // constructor
    public MovieDatabase() {
        movieList = new ArrayList<Movie>();
        actorList = new ArrayList<Actor>();
    } // with empty movieList and actorList

    // getters
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    // methods
    boolean newActor(String actorName) {
        for (Actor actor : this.actorList) {
            if (actor.getName() != null && actor.getName().equals(actorName)) return false;
        }
        return true;
    }

    boolean newMovie(String movieName) {
        for (Movie movie : this.movieList) {
            if (movie.getName() != null && movie.getName().equals(movieName)) return false;
        }
        return true;
    }

    void crossReference(Actor actor, Movie movie) {
        actor.getMovies().add(movie);
        movie.getActors().add(actor);
    }

    void addMovie(String name, String[] actors) {
        if (newMovie(name)) {
            // if the movie is a new movie, a movie object is created and added to the movieList
            Movie newMovie = new Movie(name);
            movieList.add(newMovie);

            for (String actorName : actors) {
                // if actorName is new
                if (newActor(actorName)) {
                    // create Actor object
                    Actor newActor = new Actor(actorName);
                    // add to actorList
                    actorList.add(newActor);
                    crossReference(newActor, newMovie);
                } else {
                    // if actorName exists already
                    for (Actor actor: actorList) {
                        // find the actor from actorList
                        if (actor.getName().equals(actorName)) {
                            // point new Movie to existing Actor
                            crossReference(actor, newMovie);
                        }
                    }
                }
            }
        }
    }

    void addRating(String name, double rating) {
        for (Movie movie: movieList) {
            if (movie.getName().equals(name)) movie.setRating(rating);
        }
    }

    void updateRating(String name, double newRating) {
        for (Movie movie: movieList) {
            if (movie.getName().equals(name)) movie.setRating(newRating);
        }
    }

    String getBestActor() {
        double bestScore = 0;
        String bestActor = "DEFAULT_ACTOR";
        for (Actor actor: actorList) {
            double score = 0;
            for (Movie movie : actor.getMovies()) {
                score = score + movie.getRating();
            }
            score = score / actor.getMovies().size();
            if (score > bestScore) {
                bestScore = score;
                bestActor = actor.getName();
            }
        }
        return bestActor;
    }

    String getBestMovie() {
        double bestScore = 0;
        String bestMovie = "DEFAULT_MOVIE";
        for (Movie movie: movieList) {
            if (movie.getRating() > bestScore) {
                bestScore = movie.getRating();
                bestMovie = movie.getName();
            }
        }
        return bestMovie;
    }


    public static void main(String[] args) throws FileNotFoundException {
        // create a new instance of a MovieDatabase
        MovieDatabase mdb = new MovieDatabase();

        // set up variables to manipulate movies.txt
        List<String> actorSet = new ArrayList<String>(); // store set of actors & actresses
        List<String> movieSet = new ArrayList<String>(); // store set of movies
        List<String[]> movieRecord = new ArrayList<String[]>(); // store all movies from each line w/o actor

        // READ FILE movie.txt and prepare the sets and record of movies and actors
        File moviesFile = new File("movies.txt");
        try {
            Scanner scan = new Scanner(moviesFile);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] temp = line.split("\\s*,\\s*"); // trim initial and after-comma spaces (\\s*, 0 or more)

                // check if actor is already in actorSet; if not, then add
                // some actors repeat (Di Caprio), and so do some movies of his
                actorSet.add(temp[0]); //if (!actorSet.contains(temp[0])) actorSet.add(temp[0]);

                // check if movie is already added to movieSet; if not, then add
                for (int i = 1; i < temp.length; i++) {
                    if (!movieSet.contains(temp[i])) movieSet.add(temp[i]);
                }

                // store movies in temp to each
                movieRecord.add(Arrays.copyOfRange(temp, 1, temp.length));
            }

            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // process the record of movies for MovieDatabase.addMovie() method; ADD TO DATABASE
        for (String movie: movieSet) {
            List<String> actorFeedList = new ArrayList<String>();
            for (int i = 0; i < actorSet.size(); i++) {
                // SINCE SOME ACTORS ARE REPEATED IN movie.txt
                // AVOID ADDING THE SAME ACTOR TWICE IN ACTOR FEED to addMovie()
                if (Arrays.asList(movieRecord.get(i)).contains(movie) && !actorFeedList.contains(actorSet.get(i))) {
                    actorFeedList.add(actorSet.get(i));
                }
            }
            // convert actorFeedList to String array and then add to database
            String[] actorFeed = actorFeedList.toArray(new String[actorFeedList.size()]); // passed x1
            mdb.addMovie(movie, actorFeed); // NEED TO TEST, MAYBE CHANGE TOSTRING OF Actor and Movie
        }


        // READ FILE ratings.txt and use MovieDatabase.addRating() to populate the movies
        File ratingsFile = new File("ratings.txt");
        try {
            Scanner scanRatings = new Scanner(ratingsFile);
            scanRatings.nextLine(); // skip the header of ratings.txt
            while (scanRatings.hasNextLine()) {
                String line = scanRatings.nextLine();
                String[] temp = line.split("\t"); // split by 1 or more spaces \\s{2,}; TABS here
                String movieName = temp[0];
                double movieRating = Double.parseDouble(temp[1]);

                mdb.addRating(movieName, movieRating);
            }
            scanRatings.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(mdb.getBestActor());
        System.out.println(mdb.getBestMovie());
    }

}
