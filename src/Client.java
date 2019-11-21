import menu.Menu;
import menu.MenuItem;
import menu.MenuIterator;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        // Initialization
        Scanner input = new Scanner(System.in);
        Menu menu = initializeMenu();
        MenuIterator itr = menu.getAllItemsIterator();

        // Gets the user input for the type of iterator they want
        System.out.println("\n\nSelect the type of iterator you want:\n1  -  All Items\n2  -  Item Type\n" +
                "3  -  Item Spiciness\n4  -  Item Price");
        String userInput = input.nextLine();
        switch (userInput) {
            case "1":
                break;
            case "2":
                System.out.println("\nSelect menu item type:\n1  -  APPETIZERS\n2  -  MAIN_DISH\n3  -  DESSERT");
                int type = input.nextInt();
                if (type == 1)
                    itr = menu.getItemIterator(Menu.APPETIZERS);
                else if (type == 2)
                    itr = menu.getItemIterator(Menu.MAIN_DISH);
                else if (type == 3)
                    itr = menu.getItemIterator(Menu.DESSERT);
                break;
            case "3":
                System.out.println("\nSelect menu item spiciness:\n1  -  MILD\n2  -  SPICY\n3  -  VERY_SPICY");
                type = input.nextInt();
                if (type == 1) {
                    itr = menu.getSpicinessIterator(Menu.MILD);
                } else if (type == 2) {
                    itr = menu.getSpicinessIterator(Menu.SPICY);
                } else if (type == 3) {
                    itr = menu.getSpicinessIterator(Menu.VERY_SPICY);
                }
                break;
            case "4":
                System.out.println("\nSelect a price cap for menu items (in ######.## form):");
                String price = input.nextLine();
                itr = menu.getPriceIterator(price);
        }

        // Iterates through the iterator, allowing the user to add or delete items as it goes
        System.out.println("\n\nType STOP to stop the iteratorType ADD to add a menu item to the array\nType DELETE to " +
                "delete the current menu item in the array\nPress enter to continue through the entries");
        MenuItem currItem = new MenuItem();
        userInput = input.nextLine();
        while (!userInput.equals("STOP")) {
            switch (userInput) {
                case "ADD":
                    System.out.println("Type in the name of the menu item:");
                    String name = input.nextLine();
                    System.out.println("Select menu item type:\n1  -  APPETIZERS\n2  -  MAIN_DISH\n3  -  DESSERT");
                    int type = input.nextInt();
                    System.out.println("Select menu item spiciness:\n1  -  MILD\n2  -  SPICY\n3  -  VERY_SPICY");
                    int spiciness = input.nextInt();
                    System.out.println("Set the menu item price (in ######.## format):");
                    String price = input.next();
                    menu.add(new MenuItem(name, type, spiciness, price));
                    break;
                case "DELETE":
                    System.out.println("Deleting " + currItem);
                    menu.remove(currItem);
                default:
                    if (itr.hasNext()) {
                        currItem = itr.next();
                        System.out.println(currItem);
                    } else {
                        System.out.println("No more menu items");
                    }
                    break;
            }
            userInput = input.nextLine();
        }

        // Prints out final value of menu so that the user can see adds and deletes worked
        System.out.println("\n\n------- FINAL MENU OUTCOME -------");
        for (MenuItem item: menu.getMenu()) {
            System.out.println(item);
        }
    }

    /**
     * Initializes a new menu with default menu items
     * @return a new menu object
     */
    private static Menu initializeMenu() {
        Menu menu = new Menu();

        menu.add(new MenuItem("Jalapeno Poppers", Menu.APPETIZERS, Menu.VERY_SPICY, "2.00"));
        menu.add(new MenuItem("Fried Chicken", Menu.MAIN_DISH, Menu.SPICY, "10.00"));
        menu.add(new MenuItem("Brownies", Menu.DESSERT, Menu.MILD, "5.00"));
        menu.add(new MenuItem("Cajun Steak", Menu.MAIN_DISH, Menu.VERY_SPICY, "15.00"));
        menu.add(new MenuItem("Shrimp Cocktails", Menu.APPETIZERS, Menu.SPICY, "8.00"));
        menu.add(new MenuItem("Chocolate Lava Cake", Menu.DESSERT, Menu.MILD, "7.00"));

        return menu;
    }
}
