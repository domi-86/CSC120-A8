Use this file to record your reflection on this assignment.

- Tell me about your class! What does it represent?
    My class represents a person gathering items and using them to make potions. It was inspired by the thought of me doing this when I was a kid (making "potions), and seeing the fly method, why not make it about a witch?

    void grab(String item); - If the item is on the table, add it to your inventory and remove it from table. If the table is empty, throw RuntimException
    String drop(String item); - If the item is in your inventory, add it to the table. Otherwise, throw RuntimeException
    void examine(String item); - Provides a set description (which comes from the Hashtable) of the item if it is valid and you have it either in your inventory or on the table. If not, throw a RuntimeException
    void use(String item); - If item is in inventory, remove it and increase MP and energy. Otherwise, throw runtimeexception
    boolean walk(String direction); - If energy is too low, returns false, otherwise an item is added to your inventory, your energy decreases, and returns true
    boolean fly(int x, int y); - If you have enough MP, you can fly (destination isn't important) and returns true. otherwise, returns false
    Number shrink(); - This is how you remove things from a full cauldron and get a potion (the specific potion isn't important). Empties cauldron and increases the potions count
    Number grow(); - Adds as many items from inventory as possible to cauldron at once. If the cauldron is completely full, throw RuntimeException, otherwise add as many things as possible and remove them from inventory
    void rest(); - resets energy and mp to max values (50)
    void undo(); - Raise RuntimeException "The ingredients have already been mixed together" if things are in the cauldron, otherwise throw a different RuntimeException because the program doesn't keep track of commands.
    String getInv(); - returns inventory as a String
    String getTable(); - returns table as a String
    String checkCauldron(); - returns cauldronContents as a String

- What additional methods (if any) did you implement alongside those listed in the interface?
    I implemented a method to show your inventory, what is on the table, and what's in the cauldron.

- What worked, what didn't, what advice would you give someone taking this course in the future?
    My concept started as something simple (you make simple potions) but as I started thinking how to fit all of the methods and make it make sense I also had to include a lot of smaller steps - so it turned out a lot bigger than I wanted it to be at first. My advice is to not underestimate the size of your project.
    I also had a lot of problems with iterating through cauldronContents and making accurate changes, but I found out my problem was always the boundaries I set.




