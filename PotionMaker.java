import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class PotionMaker implements Contract {

    private ArrayList<String> inventory;
    private ArrayList<String> table;
    private String[] cauldronContents;
    private Hashtable<String, String> items;
    private int mp;
    private int energy;
    private int potions;


    /** Constructor */
    public PotionMaker() {
        this.inventory = new ArrayList<>();
        this.table = new ArrayList<>();
        this.cauldronContents = new String[] { "empty","empty","empty","empty"};
        this.potions = 0;

        this.items = new Hashtable<>();
        this.items.put("mushroom", "A mushroom from the forest.");
        this.items.put("seashell", "A seashell from the ocean.");
        this.items.put("flower", "A flower you picked from your garden.");
        this.items.put("rock", "A small rock from the mountain trail.");

        this.mp = 50;
        this.energy = 50;

    }


    @Override
    /**
     * @param item - item you want to take from the table
     * if item is on the table, add it to your inventory and remove from table
     * if table is empty, throw runtimeexception "item is not on the table"
     */
    public void grab(String item) {

        item = item.toLowerCase();

        if (table.contains(item)) {
            this.inventory.add(item); // add item from table to inventory
            this.table.remove(this.table.indexOf(item)); // remove item from table
        } 
        else {
            throw new RuntimeException(item.substring(0, 1).toUpperCase() + item.substring(1, item.length()) + " is not on the table.");

        }
    }

    @Override
    /**
     * if item is valid, add it to the table. otherwise, throw runtimeexception
     * @param item
     * @return - item has been added to the table
     */
    public String drop(String item) {

        item = item.toLowerCase();

        if (this.inventory.contains(item)) { // remove item from inventory (if it is in inventory) and put on table

            this.table.add(item);
            this.inventory.remove(item);

            return item.substring(0, 1).toUpperCase() + item.substring(1, item.length()) + " added to the table.";
        } 
        else { // if inventory is empty throw runtime exception
            throw new RuntimeException(item + " is not in your inventory.");
        }

    }

    @Override
    /**
     * provides a set description of the item if it is valid and you have it. if not, throw exception
     * @param item
     */
    public void examine(String item) {

        item = item.toLowerCase();

        if (this.inventory.contains(item) || this.table.contains(item)) {

            System.out.println(this.items.get(item) + " You can use this to make a potion.");

        } 
        else {
            throw new RuntimeException("You do not have " + item + ".");
        }

    }

    @Override
    /**
     * if item is in inventory, then remove it and increase mp and energy. otherwise, throw runtimeexception
     * @param item
     */
    public void use(String item) {

        item = item.toLowerCase();


        if (this.inventory.contains(item)) {

            this.inventory.remove(item);
            System.out.println("You ate the " + item + ".");
            this.mp += 5;
            this.energy += 5;
        } 
        else {
            throw new RuntimeException(item.substring(0, 1).toUpperCase() + item.substring(1, item.length()) + " is not in your inventory.");
        }
    }

    @Override
    /**
     * if energy is low return false, otherwise you move in a direction and get an item, and your energy decreases
     * @param direction north, east, south, or west
     * @return returns whether or not you were able to walk/if you had enough energy
     */
    public boolean walk(String direction) {

        if (this.energy <= 0) {
            System.out.println("You need to rest.");
            return false;
        } 
        else {
            if (direction.equalsIgnoreCase("north")) {
                System.out.print("You went north to the nearby forest and found a mushroom on your walk.");
                this.inventory.add("mushroom");

            } 
            else if (direction.equalsIgnoreCase("east")) {
                System.out.print("You went east up the mountain trail and found a rock on your walk.");
                this.inventory.add("rock");

            } 
            else if (direction.equalsIgnoreCase("south")) {
                System.out.print("You went south to the ocean shore and found a seashell in the sand.");
                this.inventory.add("seashell");

            } 
            else if (direction.equalsIgnoreCase("west")) {
                System.out.print("You went west to your garden and found a flower.");
                this.inventory.add("flower");

            } 
            else {
                throw new RuntimeException("You can only go north, east, south, or west.");
            }

            this.energy -= 10;
            System.out.println(" Then you went home. [Energy -10]");
            return true;
        }

    }

    @Override
    /**
     * if you have MP, you can fly (destination isn't important here)
     * @param x coordinate of where you want to go
     * @param y 
     * @return returns whether or not you were able to complete the action
     */
    public boolean fly(int x, int y) {


        if (this.mp >= 10) {
            this.mp -= 10;
            System.out.println("You flew around on your broom. [MP -10]");
            return true;
        } 
        else {
            System.out.println("You need to recover MP.");
            return false;
        }

    }

    @Override
    /** this is how you remove things from a full cauldron and get a potion (the specific potion isn't important)
     * @return returns the number of potions you have made so far
     */
    public Number shrink() {

        for (int i = 0; i <= 3; i++) {
            if (this.cauldronContents[i].equals("empty")) {
                throw new RuntimeException("You need more ingredients to complete this action.");
            } 
            else {
                this.cauldronContents[i] = "empty";
            }
        }

        this.potions += 1;

        return this.potions;

    }

    @Override
    /**
     * add as many items from inventory as possible to cauldron at once. if cauldron is completely full throw exception, otherwise add as many things as possible
     * @return
     */
    public Number grow() {
        // if cauldron full, throw exception
        if (this.inventory.size() < 1) {
            throw new RuntimeException("Your inventory is empty.");
        } 
        else if (!Arrays.asList(this.cauldronContents).contains("empty")) {
            throw new RuntimeException("The cauldron is full.");
        } 
        else {
            for (int i = 0, count = 0; i <= 3; i++) {
                if (this.cauldronContents[i].equals("empty")) {
                    this.cauldronContents[i] = inventory.get(count);
                    inventory.remove(count);

                }
            }

            return this.potions;
        }



    }

    @Override
    /** reset to max energy and mp */
    public void rest() {

        this.energy = 50;
        this.mp = 50;
        System.out.println("You rested and feel energized!");
    }

    @Override
    /** raise exception "The ingredients have already been mixed together" if things are in the cauldron, otherwise throw a different exception */
    public void undo() {
        int count = 0;

        for (int i = 0; i <= 3; i++) {

            if (this.cauldronContents[i].equals("empty")) {
                count++;
            }
        }

        if (count > 0) {
            throw new RuntimeException("Your ingredients have already dissolved!");
        } 
        else {
            throw new RuntimeException("You haven't found a way to time travel, yet.");
        }

    }

    /**
     * getter for inventory
     * @return
     */
    public String getInv() {
        return "INVENTORY: " + this.inventory.toString();
    }

    /**
     * getter for table contents
     * @return
     */
    public String getTable() {
        return "TABLE: " + this.table.toString();
    }

    /**
     * getter for cauldronContents
     * @return
     */
    public String checkCauldron() {
        return "CAULDRON: " + Arrays.toString(this.cauldronContents);
    }

    public static void main(String[] args) {

        PotionMaker p = new PotionMaker();
        // System.out.println(p.getInv());
        // System.out.println(p.checkCauldron());

        p.walk("north");
        p.walk("east");
        p.walk("south");
        p.walk("west");
        p.walk("north");
        p.walk("north");


        p.fly(5, 10);
        p.fly(19, 50);
        p.fly(297, 29);
        p.fly(82, 48);
        p.fly(372, 293);
        p.fly(372, 93);

        p.rest();

        System.out.println(p.getInv());

        System.out.println(p.grow());
        System.out.println(p.getInv());
        System.out.println(p.checkCauldron());
        System.out.println(p.shrink());
        System.out.println(p.checkCauldron());

    }

}