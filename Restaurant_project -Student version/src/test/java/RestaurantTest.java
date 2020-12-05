import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    public static final LocalTime OPENING_TIME = LocalTime.parse("10:30:00");
    public static final LocalTime CLOSING_TIME = LocalTime.parse("22:00:00");
    private Restaurant getRestaurantAdded() {
        restaurant=new Restaurant("Amelie's cafe","Chennai",OPENING_TIME,CLOSING_TIME);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime time= LocalTime.parse("13:00:00");
        restaurant=getRestaurantAdded();
        Restaurant rest= Mockito.spy(restaurant);
       // Mockito.when(LocalTime.now()).thenReturn(time);
        Mockito.when(rest.getCurrentTime()).thenReturn(time);

        boolean result=rest.isRestaurantOpen();
        assertEquals(true,result);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime time= LocalTime.parse("09:00:00");
        restaurant=getRestaurantAdded();
        Restaurant rest= Mockito.spy(restaurant);
        // Mockito.when(LocalTime.now()).thenReturn(time);
        Mockito.when(rest.getCurrentTime()).thenReturn(time);

        boolean result=rest.isRestaurantOpen();
        assertEquals(false,result);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant=getRestaurantAdded();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant=getRestaurantAdded();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant=getRestaurantAdded();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}