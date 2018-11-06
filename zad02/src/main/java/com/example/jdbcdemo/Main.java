import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        MovieService movieService = new MovieService();
        Movie filmJeden = new Movie("Star Wars","2017-10-12","science-fiction");
        movieService.addMovie(filmJeden);



    }


}