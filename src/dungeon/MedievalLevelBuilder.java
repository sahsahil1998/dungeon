package dungeon;
import java.util.ArrayList;
import java.util.List;

public class MedievalLevelBuilder {

    private int levelNumber;
    private int numOfMonsters;
    private int numOfTreasure;
    private int numOfRoom;
    private int totalMonster;
    private int totalTreasure;
    private List<Room> rooms;

    public MedievalLevelBuilder(int l, int r, int m, int t) {
        if (l < 0 || r < 0 || m < 0 || t < 0) {
            throw new IllegalArgumentException
                    ("Levels, rooms, monsters or treasures cannot be negative.");
        }

        this.levelNumber = l;
        this.numOfMonsters = m;
        this.numOfTreasure = t;
        this.numOfRoom = r;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(String description) {
        if (rooms.size() == numOfRoom) {
            throw new IllegalStateException("Maximum capacity reached. No more rooms can be added");
        }

        rooms.add(new Room(description));

    }

    public void addPotion(int roomNumber) {
        if (totalTreasure == numOfTreasure) {
            throw new IllegalStateException("Cannot add any more treasure now");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        Treasure p = new Treasure("a healing potion", 1);
        rooms.get(roomNumber).addTreasure(p);
        totalTreasure++;
    }

    public void addWeapon(int roomNumber, String weapon) {
        if (totalTreasure == numOfTreasure) {
            throw new IllegalStateException("Cannot add this piece of weapon to treasure now!");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        Treasure w = new Treasure(weapon, 10);
        rooms.get(roomNumber).addTreasure(w);
        totalTreasure++;

    }

    public void addGold(int roomNumber, int value) {
        if (totalTreasure == numOfTreasure) {
            throw new IllegalStateException("Cannot add gold to treasure now!");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }


        Treasure g = new Treasure("pieces of gold", value);
        rooms.get(roomNumber).addTreasure(g);
        totalTreasure++;

    }

    public void addSpecial(int roomNumber, String specialDescription, int value) {
        if (totalTreasure == numOfTreasure) {
            throw new IllegalStateException("Cannot add any treasure now");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        rooms.get(roomNumber).addTreasure(new Treasure(specialDescription, value));
        totalTreasure++;
    }

    public void addOgre(int roomNumber) {
        if (totalMonster == numOfMonsters) {
            throw new IllegalStateException("Cannot add an ogre");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        String ogreDescription = "large, hideous man-like being that likes to eat humans for lunch";
        Monster og = new Monster(TypeOfMonsters.ogre.toString(), ogreDescription, 50);
        rooms.get(roomNumber).addMonster(og);
        totalMonster++;

    }

    public void addGoblins(int roomNumber, int numberOfGoblins) {
        if (totalMonster + numberOfGoblins > numOfMonsters) {
            throw new IllegalStateException("Number of goblins is too large!");
        }

        if (rooms == null || roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        String goblinDescription = "mischievous and very unpleasant, vengeful, and greedy creature whose primary purpose is to cause trouble to humankind";
        for (int i = 0; i < numberOfGoblins; i++) {
            Monster m = new Monster(TypeOfMonsters.goblin.toString(), goblinDescription, 7);
            rooms.get(roomNumber).addMonster(m);
        }

        totalMonster += numberOfGoblins;

    }

    public void addHuman(int roomNumber, String name, String description, int value) {
        if (totalMonster == numOfMonsters) {
            throw new IllegalStateException("Cannot add a human!");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        Monster human = new Monster(name, description, value);
        rooms.get(roomNumber).addMonster(human);
        totalMonster++;

    }

    public void addOrc(int roomNumber) {
        if (totalMonster == numOfMonsters) {
            throw new IllegalStateException("Cannot add an orc");
        }

        if (roomNumber >= rooms.size()) {
            throw new IllegalArgumentException("The target room has not been created!");
        }

        String orcDescription = "brutish, aggressive, malevolent being serving evil";
        Monster o = new Monster(TypeOfMonsters.orc.toString(), orcDescription, 20);
        rooms.get(roomNumber).addMonster(o);
        totalMonster++;

    }

    public Level build() {
        if (rooms == null || rooms.size() < numOfRoom || totalTreasure < numOfTreasure || totalMonster < numOfMonsters) {
            throw new IllegalStateException("Level must be completed before it is initialized.");
        }


        return new Level(levelNumber, rooms);
    }

}