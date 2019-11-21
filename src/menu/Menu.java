package menu;

public class Menu {
    public static final int APPETIZERS = 1;
    public static final int MAIN_DISH = 2;
    public static final int DESSERT = 3;

    public static final int MILD = 1;
    public static final int SPICY = 2;
    public static final int VERY_SPICY = 3;

    MenuItem[] menu;

    public Menu() {
        menu = new MenuItem[0];
    }

    public Menu(MenuItem[] menu) {
        this.menu = menu;
    }

    public Menu(Menu menu) {
        this.menu = menu.menu;
    }

    /**
     * Adds a MenuItem to the menu array list
     * @param item MenuItem to add
     */
    public void add(MenuItem item) {
        // Create new menu array with previous length plus one
        MenuItem[] menu = new MenuItem[this.menu.length + 1];

        // Copy old contents to new array and put new item at last place
        for (int i = 0; i < this.menu.length; i++) {
            menu[i] = this.menu[i];
        }
        menu[this.menu.length] = item;

        // Set the menu to the menu created
        this.menu = menu;
    }

    /**
     * Removes a menu item from the menu array list
     * @deprecated Uses method of removing index entirely, ruining the iterator current index
     * @param item the item to delete
     */
    public void deleteItem(MenuItem item) {
        MenuItem[] menu = new MenuItem[this.menu.length - 1];
        int current = 0;

        for (int i = 0; i < this.menu.length; i++) {
            if (!item.equals(this.menu[i])) {
                menu[current] = this.menu[i];
            } else {
                current--;
            }
            current++;
        }

        this.menu = menu;
    }

    /**
     * Removes a menu item from the menu array list
     * Does not mess with the length of the menu array, preserving the iterator current index
     * @param item the item to delete
     */
    public void remove(MenuItem item) {
        MenuItem[] menu = new MenuItem[this.menu.length];

        for (int i = 0; i < this.menu.length; i++) {
            if (!item.equals(this.menu[i])) {
                menu[i] = this.menu[i];
            } else {
                menu[i] = new MenuItem();
            }
        }

        this.menu = menu;
    }

    public MenuItem[] getMenu() {
        return menu;
    }

    // Iterator factory methods

    public MenuIterator getAllItemsIterator() {
        return new AllItemsIterator(this, 0);
    }

    public MenuIterator getItemIterator(int category) {
        return new ItemIterator(this, 0, category);
    }

    public MenuIterator getSpicinessIterator(int spiciness) {
        return new SpicinessIterator(this, 0, spiciness);
    }

    public MenuIterator getPriceIterator(String price) {
        return new PriceIterator(this, 0, price);
    }

    // Iterator private inner classes

    private static class AllItemsIterator implements MenuIterator {
        private Menu menu;
        private int current_index;

        AllItemsIterator(Menu menu, int start_loc) {
            this.menu = menu;
            this.current_index = start_loc;
        }

        @Override
        public MenuItem next() {
            return menu.menu[current_index++];
        }

        @Override
        public boolean hasNext() {
            return current_index < menu.menu.length;
        }
    }

    private static class ItemIterator implements MenuIterator {
        private Menu menu;
        private int current_index;
        private final int CATEGORY;

        ItemIterator(Menu menu, int start_loc, int category) {
            this.menu = menu;
            this.current_index = start_loc;
            this.CATEGORY = category;
        }

        @Override
        public MenuItem next() {
            MenuItem item = new MenuItem();
            for (int i = current_index; i < menu.menu.length; i++) {
                if (menu.menu[i].getCategory() == CATEGORY) {
                    item = menu.menu[i];
                    current_index = i + 1;
                    break;
                }
            }
            return item;
        }

        @Override
        public boolean hasNext() {
            boolean flag = false;
            for (int i = current_index; i < menu.menu.length; i++) {
                if (menu.menu[i].getCategory() == CATEGORY) {
                    flag = true;
                }
            }
            return flag;
        }
    }

    private static class SpicinessIterator implements MenuIterator {
        private Menu menu;
        private int current_index;
        private final int SPICINESS;

        SpicinessIterator(Menu menu, int start_loc, int spiciness) {
            this.menu = menu;
            this.current_index = start_loc;
            this.SPICINESS = spiciness;
        }

        @Override
        public MenuItem next() {
            MenuItem item = new MenuItem();
            for (int i = current_index; i < menu.menu.length; i++) {
                if (menu.menu[i].getSpiciness() == SPICINESS) {
                    item = menu.menu[i];
                    current_index = i + 1;
                    break;
                }
            }
            return item;
        }

        @Override
        public boolean hasNext() {
            boolean flag = false;
            for (int i = current_index; i < menu.menu.length; i++) {
                if (menu.menu[i].getSpiciness() == SPICINESS) {
                    flag = true;
                }
            }
            return flag;
        }
    }

    private static class PriceIterator implements MenuIterator {
        private Menu menu;
        private int current_index;
        private final String PRICE;

        PriceIterator(Menu menu, int start_loc, String price) {
            this.menu = menu;
            this.current_index = start_loc;
            this.PRICE = price;
        }

        @Override
        public MenuItem next() {
            MenuItem item = new MenuItem();
            for (int i = current_index; i < menu.menu.length; i++) {
                if (Double.parseDouble(menu.menu[i].getPrice()) < Double.parseDouble(PRICE)) {
                    item = menu.menu[i];
                    current_index = i + 1;
                    break;
                }
            }
            return item;
        }

        @Override
        public boolean hasNext() {
            boolean flag = false;
            for (int i = current_index; i < menu.menu.length; i++) {
                if (Double.parseDouble(menu.menu[i].getPrice()) < Double.parseDouble(PRICE)) {
                    flag = true;
                }
            }
            return flag;
        }
    }
}
