import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Restaurant requiredRest =null;

       // try{

            for (Restaurant restaurant : restaurants) {
                if (restaurant.getName().equals(restaurantName)) {
                    requiredRest = restaurant;
                }
            }

        /*}catch(Exception e){
            throw new restaurantNotFoundException(restaurantName);
        }*/
        return requiredRest;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null)
            throw new restaurantNotFoundException(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
