public class User {
    //unique user ID from login
    private string UID;

    User() {

    }

    //Feed class or List of recipes for display purposes
    ArrayList<Recipes> recipesArrayList;
    //List of favorites, used to obtain information for feed
    ArrayList<Favorites> favoritesArrayList;
    //Feed object, used for displaying the feed
    Object userObject;
    //List of users favorited, also used for feed
    ArrayList<User> favoriteUsers;

}
