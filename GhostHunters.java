/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ghosthunters;

/**
 *
 * @author AEscudero2026
 */
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class GhostHunters {

    // these are all the lists I need for the game to work
    static ArrayList<GhostData> ghosts = new ArrayList<>();
    static ArrayList<EvidenceType> evidenceTypes = new ArrayList<>();
    static ArrayList<EvidenceData> evidenceLog = new ArrayList<>();

    // the 4 ghosts the game can pick from
    static ArrayList<String> ghostNames = new ArrayList<>(Arrays.asList(
        "Demon",
        "Jinn",
        "Poltergeist",
        "Banshee"
    ));

    // all the rooms in the haunted house
    static ArrayList<String> roomNames = new ArrayList<>(Arrays.asList(
        "Kitchen",
        "Living Room",
        "Kids Bedroom",
        "Master Bedroom",
        "Bathroom",
        "Master Bathroom",
        "Garage"
    ));

    // every item and what evidence it finds
    static ArrayList<ItemData> allItems = new ArrayList<>(Arrays.asList(
        new ItemData("Thermometer",        "Freezing Temperatures"),
        new ItemData("EMF",                "High EMF Levels"),
        new ItemData("Notebook",           "Ghost Writing"),
        new ItemData("Night Vision Light", "Ghost Orbs"),
        new ItemData("Blue Light",         "Handprints"),
        new ItemData("Sensors",            "Ghost Movement"),
        new ItemData("Music Box",          "Ghost Activity")
    ));

    // matching evidence types to the item that detects them
    static ArrayList<EvidenceType> allEvidenceTypes = new ArrayList<>(Arrays.asList(
        new EvidenceType("Ghost Orbs",           "Night Vision Light"),
        new EvidenceType("Freezing Temperatures","Thermometer"),
        new EvidenceType("High EMF Levels",      "EMF"),
        new EvidenceType("Ghost Writing",        "Notebook"),
        new EvidenceType("Handprints",           "Blue Light"),
        new EvidenceType("Ghost Movement",       "Sensors"),
        new EvidenceType("Ghost Activity",       "Music Box")
    ));

    // this maps each ghost to the evidence it gives off
    static HashMap<String, ArrayList<String>> ghostEvidenceMap = new HashMap<>();
    static {
        ghostEvidenceMap.put("Demon",       new ArrayList<>(Arrays.asList(
            "Ghost Orbs", "Freezing Temperatures", "Ghost Writing")));
        ghostEvidenceMap.put("Jinn",        new ArrayList<>(Arrays.asList(
            "High EMF Levels", "Ghost Orbs", "Freezing Temperatures")));
        ghostEvidenceMap.put("Poltergeist", new ArrayList<>(Arrays.asList(
            "Ghost Writing", "Handprints", "Ghost Activity")));
        ghostEvidenceMap.put("Banshee",     new ArrayList<>(Arrays.asList(
            "Ghost Orbs", "Freezing Temperatures", "Handprints")));
    }

    // file paths for all the text files
    static String ghostFilePath    = "ghosts.txt";
    static String itemFilePath     = "items.txt";
    static String evidenceFilePath = "evidence.txt";
    static String evidenceLogPath  = "evidence_log.txt";
    static String resultFilePath   = "results.txt";
    static String instructionsPath = "instructions.txt";

    // all the sound files
    static final String SOUND_INTRO       = "GHIntrosound.wav";
    static final String SOUND_DOOR        = "Dooropeningsound.wav";
    static final String SOUND_GHOST       = "Ghostsound.wav";
    static final String SOUND_FLASHLIGHT  = "Flashlightsound.wav";
    static final String SOUND_SENSORS     = "sensorsound.wav";
    static final String SOUND_EMF         = "EMFsound.wav";
    static final String SOUND_THERMOMETER = "thermometersound.wav";
    static final String SOUND_NOTEBOOK    = "notebooksound.wav";
    static final String SOUND_NIGHTVISION = "nightvisionGoggles.wav";
    static final String SOUND_MUSICBOX    = "musicboxsound.wav";

    // variables that keep track of the game while its running
    static GhostData activeGhost = null;
    static ArrayList<ItemData> inventory = new ArrayList<>();
    static ArrayList<EvidenceData> collectedEvidence = new ArrayList<>();
    static ArrayList<RoomData> rooms = new ArrayList<>();
    static RoomData currentRoom = null;
    static RoomData ghostRoom = null;
    static boolean freezingTempsDetected = false;
    static int guessesLeft = 2;

    // holds a reference to the intro clip so we can stop it when the player acts
    static Clip introClip = null;

    // plays a sound without stopping the game
    static void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            if (soundFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Sound file not found: " + soundFileName);
            }
        } catch (Exception e) {
            System.out.println("Sound error: " + e.getMessage());
        }
    }

    // plays the intro sound in the background and saves the clip so it can be stopped later
    static void playIntroSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            if (soundFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                introClip = AudioSystem.getClip();
                introClip.open(audioInput);
                introClip.start();
            } else {
                System.out.println("Sound file not found: " + soundFileName);
            }
        } catch (Exception e) {
            System.out.println("Sound error: " + e.getMessage());
        }
    }

    // stops the intro sound if it is still playing
    static void stopIntroSound() {
        if (introClip != null && introClip.isRunning()) {
            introClip.stop();
            introClip.close();
        }
        introClip = null;
    }

    // reads and prints the instructions from instructions.txt
    static void loadInstructions() {
        try {
            File myFile = new File(instructionsPath);
            if (!myFile.exists()) {
                System.out.println("instructions.txt not found. Place it in the project root folder.");
                return;
            }
            Scanner reader = new Scanner(myFile);
            System.out.println();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
                // pause briefly on the door line so the sound syncs up with the text
                if (line.contains("front door")) {
                    try { Thread.sleep(300); } catch (InterruptedException ignored) {}
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading instructions: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        // load everything from the text files first
        loadGhostsFromFile();
        loadItemDescriptionsFromFile();
        loadEvidenceTypesFromFile();

        buildRooms();

        Scanner scan = new Scanner(System.in);

        System.out.println("\n          WELCOME TO GHOST HUNTERS");

        // randomly pick which ghost is haunting the house this game
        String chosenName = ghostNames.get(new Random().nextInt(ghostNames.size()));
        activeGhost = findGhostByName(chosenName);

        assignEvidenceToRooms(activeGhost);
        setGhostRoom();
        distributeItems();

        currentRoom = rooms.get(0);

        // load and display the instructions from the text file, play door sound during it
        loadInstructions();
        playSound(SOUND_DOOR);

        // start the intro music playing in the background before showing the action menu
        playIntroSound(SOUND_INTRO);

        boolean valid = true;
        boolean firstAction = true;

        while (valid) {

            // check if the player is out of guesses
            if (guessesLeft <= 0) {
                System.out.println("\nYou have no guesses left!");
                handleLoss();
                valid = false;
                break;
            }

            System.out.println("\n  Location: " + currentRoom.name
                    + " | Guesses left: " + guessesLeft);
            System.out.println("\nWhat would you like to do?");
            System.out.println("V - visit a room");
            System.out.println("P - pick up an item");
            System.out.println("U - use an item");
            System.out.println("M - check menu");
            System.out.println("Q - quit");

            String action = scan.nextLine();

            // stop the intro music the moment the player types anything
            if (firstAction) {
                stopIntroSound();
                firstAction = false;
            }

            if (action.equalsIgnoreCase("V")) {
                visitRoom(scan);
            } else if (action.equalsIgnoreCase("P")) {
                pickUpItem(scan);
            } else if (action.equalsIgnoreCase("U")) {
                useItem(scan);
            } else if (action.equalsIgnoreCase("M")) {
                checkMenu(scan);
            } else if (action.equalsIgnoreCase("Q")) {
                System.out.println("Quitting investigation");
                valid = false;
            } else {
                System.out.println("Not a valid option, try again.");
            }
        }
    }

    // builds the room objects in the same fixed order every game
    static void buildRooms() {
        for (String name : roomNames) {
            rooms.add(new RoomData(name));
        }
        System.out.println("Loaded " + rooms.size() + " rooms.");
    }

    // puts the ghost's evidence into random rooms
    static void assignEvidenceToRooms(GhostData ghost) {
        ArrayList<String> possibleEvidence = ghostEvidenceMap.get(ghost.name);
        if (possibleEvidence == null) return;

        ArrayList<RoomData> shuffledRooms = new ArrayList<>(rooms);
        Collections.shuffle(shuffledRooms);

        for (int i = 0; i < possibleEvidence.size() && i < shuffledRooms.size(); i++) {
            shuffledRooms.get(i).evidenceType = possibleEvidence.get(i);
            shuffledRooms.get(i).hasEvidence = true;
        }
    }

    // finds which room has freezing temps and marks it as the ghost room
    static void setGhostRoom() {
        ghostRoom = null;
        for (RoomData room : rooms) {
            if (room.evidenceType.equalsIgnoreCase("Freezing Temperatures")) {
                ghostRoom = room;
                ghostRoom.isGhostRoom = true;
                break;
            }
        }
    }

    // searches the ghosts list for a matching name and returns it
    static GhostData findGhostByName(String name) {
        for (GhostData g : ghosts) {
            if (g.name.equalsIgnoreCase(name)) {
                return g;
            }
        }
        return new GhostData(name, "No description loaded.", "See ghosts.txt");
    }

    // lets the player move to a different room
    static void visitRoom(Scanner scan) {
        System.out.println("\nAvailable rooms:");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + " - " + rooms.get(i).name);
        }
        System.out.print("Enter room number: ");
        try {
            int choice = Integer.parseInt(scan.nextLine().trim());
            if (choice >= 1 && choice <= rooms.size()) {
                currentRoom = rooms.get(choice - 1);

                // door sound every time you walk into a room
                playSound(SOUND_DOOR);

                System.out.println("\n  Room: " + currentRoom.name);

                if (currentRoom.isGhostRoom && freezingTempsDetected) {
                    System.out.println("  The air in here is ice cold. This feels like the center of the haunting.");
                }

                // ghost sound plays if the room has evidence that hasnt been found yet
                if (currentRoom.hasEvidence && !currentRoom.evidenceCollected) {
                    playSound(SOUND_GHOST);
                    System.out.println("Something feels off, there may be evidence here");
                }

                if (!currentRoom.items.isEmpty()) {
                    System.out.print("Items here: ");
                    for (int i = 0; i < currentRoom.items.size(); i++) {
                        System.out.print(currentRoom.items.get(i).name);
                        if (i < currentRoom.items.size() - 1) System.out.print(", ");
                    }
                    System.out.println();
                } else {
                    System.out.println("No items visible here");
                }

            } else {
                System.out.println("Invalid room number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    // lets the player pick up an item from the current room
    static void pickUpItem(Scanner scan) {
        if (currentRoom.items.isEmpty()) {
            System.out.println("No items to pick up here.");
            return;
        }
        System.out.println("\nItems in room:");
        for (int i = 0; i < currentRoom.items.size(); i++) {
            System.out.println((i + 1) + " - " + currentRoom.items.get(i).name);
        }
        System.out.print("Pick up which item: ");
        try {
            int choice = Integer.parseInt(scan.nextLine().trim());
            if (choice >= 1 && choice <= currentRoom.items.size()) {
                ItemData chosen = currentRoom.items.get(choice - 1);
                currentRoom.items.remove(chosen);
                inventory.add(chosen);
                System.out.println("Picked up: " + chosen.name);
            } else {
                System.out.println("Invalid item number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    // lets the player use an item from their inventory
    // each item has its own sound that plays when you use it
    static void useItem(Scanner scan) {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }
        System.out.println("\nYour inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + " - " + inventory.get(i).name);
        }
        System.out.print("Use which item: ");
        try {
            int choice = Integer.parseInt(scan.nextLine().trim());
            if (choice >= 1 && choice <= inventory.size()) {
                ItemData chosen = inventory.get(choice - 1);

                // play the right sound depending on which item the player picked
                if (chosen.name.equalsIgnoreCase("Blue Light")) {
                    playSound(SOUND_FLASHLIGHT);
                } else if (chosen.name.equalsIgnoreCase("Sensors")) {
                    playSound(SOUND_SENSORS);
                } else if (chosen.name.equalsIgnoreCase("EMF")) {
                    playSound(SOUND_EMF);
                } else if (chosen.name.equalsIgnoreCase("Thermometer")) {
                    playSound(SOUND_THERMOMETER);
                } else if (chosen.name.equalsIgnoreCase("Notebook")) {
                    playSound(SOUND_NOTEBOOK);
                } else if (chosen.name.equalsIgnoreCase("Night Vision Light")) {
                    playSound(SOUND_NIGHTVISION);
                } else if (chosen.name.equalsIgnoreCase("Music Box")) {
                    playSound(SOUND_MUSICBOX);
                }

                System.out.println("You use the " + chosen.name + ". " + chosen.use);
                if (currentRoom.hasEvidence && !currentRoom.evidenceCollected) {
                    if (chosen.detectsEvidence.equalsIgnoreCase(currentRoom.evidenceType)) {
                        System.out.println("The " + chosen.name + " picks something up!");
                        collectEvidenceWithItem(chosen);
                    } else {
                        System.out.println("Nothing detected in this room with the " + chosen.name);
                    }
                } else {
                    System.out.println("Nothing to detect here.");
                }
            } else {
                System.out.println("Invalid item number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    // saves the evidence to the collected list and logs it
    static void collectEvidenceWithItem(ItemData item) {
        currentRoom.evidenceCollected = true;
        EvidenceData ev = new EvidenceData(currentRoom.evidenceType, currentRoom.name);
        collectedEvidence.add(ev);
        evidenceLog.add(ev);
        System.out.println("Evidence collected: " + ev.type + " (found in " + ev.room + ")");
        // remember if freezing temps were found so the ghost room message can appear
        if (ev.type.equalsIgnoreCase("Freezing Temperatures")) {
            freezingTempsDetected = true;
        }
        saveEvidenceToFile();
    }

    // shows all the evidence the player has collected so far
    static void analyzeEvidence() {
        if (collectedEvidence.isEmpty()) {
            System.out.println("No evidence to analyze.");
            return;
        }
        System.out.println("\nAnalyzing collected evidence");
        for (EvidenceData ev : collectedEvidence) {
            System.out.println("  [" + ev.type + "] found in " + ev.room
                    + " - This clue points toward a specific ghost type.");
        }
    }

    // the in game menu with all the options
    static void checkMenu(Scanner scan) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n               GHOST HUNTERS MENU");
            System.out.println("1 - Ghost Types and Descriptions");
            System.out.println("2 - Analyze Evidence");
            System.out.println("3 - Show Inventory");
            System.out.println("4 - Guess Ghost Type");
            System.out.println("5 - Return to Investigation");
            System.out.print("\nChoose option (1-5): ");
            String choice = scan.nextLine();

            if (choice.equals("1")) {
                // show all ghost info loaded from the file
                System.out.println("\n--- Ghost Types and Descriptions ---");
                if (ghosts.isEmpty()) {
                    System.out.println("  (No ghost data loaded - check ghosts.txt is in the project root)");
                    for (String name : ghostNames) {
                        System.out.println("  - " + name);
                    }
                } else {
                    for (GhostData g : ghosts) {
                        System.out.println("\n  [" + g.name + "]");
                        System.out.println("  Description: " + g.description);
                        System.out.println("  Evidence:    " + g.evidenceClues);
                    }
                }

            } else if (choice.equals("2")) {
                analyzeEvidence();

            } else if (choice.equals("3")) {
                showInventory();

            } else if (choice.equals("4")) {
                // player takes a guess at what ghost it is
                System.out.print("Enter your ghost guess: ");
                String guess = scan.nextLine();
                if (guess.equalsIgnoreCase(activeGhost.name)) {
                    handleCorrectGuess();
                    inMenu = false;
                } else {
                    boolean stillPlaying = handleIncorrectGuess();
                    if (!stillPlaying) inMenu = false;
                }

            } else if (choice.equals("5")) {
                System.out.println("Returning to investigation");
                inMenu = false;

            } else {
                System.out.println("Not a valid option, try again.");
            }
        }
    }

    // prints out everything currently in the players inventory
    static void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("  Inventory: (empty)");
        } else {
            System.out.print("  Inventory: ");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.print(inventory.get(i).name);
                if (i < inventory.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    // player guessed right, they win
    static void handleCorrectGuess() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  CORRECT! You identified the ghost!");
        System.out.println("  The " + activeGhost.name + " is banished. The house is safe.");
        System.out.println("=".repeat(50));
        saveResultToFile("WIN", activeGhost.name);
        System.out.println("\nThank you for playing Ghost Hunters Investigator! You saved the day!");
    }

    // player guessed wrong, lose a guess
    static boolean handleIncorrectGuess() {
        guessesLeft--;
        System.out.println("\nWrong guess! Guesses remaining: " + guessesLeft);
        if (guessesLeft <= 0) {
            handleLoss();
            return false;
        }
        return true;
    }

    // player ran out of guesses, game over
    static void handleLoss() {
        System.out.println("\nThe ghost was a " + activeGhost.name + ".");
        System.out.println(activeGhost.description);
        saveResultToFile("LOSS", activeGhost.name);
        System.out.println("\nGame over. The ghost claims another victim");
    }

    // gives 4 random items to the player at the start, scatters the other 3 across rooms
    static void distributeItems() {
        ArrayList<ItemData> itemCopies = new ArrayList<>(allItems);
        Collections.shuffle(itemCopies);

        // first 4 go straight into the player's inventory
        for (int i = 0; i < 4; i++) {
            inventory.add(itemCopies.get(i));
        }
        System.out.println("You start with: ");
        for (ItemData item : inventory) {
            System.out.println("  - " + item.name);
        }

        // remaining 3 get placed into random rooms, one per room
        ArrayList<RoomData> shuffledRooms = new ArrayList<>(rooms);
        Collections.shuffle(shuffledRooms);
        for (int i = 4; i < itemCopies.size(); i++) {
            shuffledRooms.get(i - 4).items.add(itemCopies.get(i));
        }
    }

    // reads ghost data from ghosts.txt
    // format is: Name - Evidence1, Evidence2, Evidence3
    static void loadGhostsFromFile() {
        try {
            File myFile = new File(ghostFilePath);
            if (!myFile.exists()) {
                System.out.println("ghosts.txt not found. Place it in the project root folder.");
                return;
            }
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" - ", 2);
                if (parts.length == 2) {
                    String name        = parts[0].trim();
                    String evidenceStr = parts[1].trim();
                    GhostData g = new GhostData(name, "A paranormal entity.", evidenceStr);
                    ghosts.add(g);
                }
            }
            reader.close();
            System.out.println("Loaded " + ghosts.size() + " ghosts from file.");
        } catch (IOException e) {
            System.out.println("FileSystem Error: " + e.getMessage());
        }
    }

    // reads item descriptions from items.txt
    // format is: name,description
    static void loadItemDescriptionsFromFile() {
        try {
            File myFile = new File(itemFilePath);
            if (!myFile.exists()) {
                System.out.println("items.txt not found. Item descriptions will be generic.");
                return;
            }
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] data = line.split(",", 2);
                if (data.length >= 2) {
                    for (ItemData item : allItems) {
                        if (item.name.equalsIgnoreCase(data[0].trim())) {
                            item.use = data[1].trim();
                        }
                    }
                }
            }
            reader.close();
            System.out.println("Loaded item descriptions from file.");
        } catch (IOException e) {
            System.out.println("FileSystem Error: " + e.getMessage());
        }
    }

    // reads evidence descriptions from evidence.txt
    // format is: name,description
    static void loadEvidenceTypesFromFile() {
        try {
            File myFile = new File(evidenceFilePath);
            if (!myFile.exists()) {
                System.out.println("evidence.txt not found. Evidence descriptions will be generic.");
                return;
            }
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] data = line.split(",", 2);
                if (data.length >= 2) {
                    for (EvidenceType et : allEvidenceTypes) {
                        if (et.name.equalsIgnoreCase(data[0].trim())) {
                            et.description = data[1].trim();
                        }
                    }
                }
            }
            reader.close();
            System.out.println("Loaded evidence descriptions from file.");
        } catch (IOException e) {
            System.out.println("FileSystem Error: " + e.getMessage());
        }
    }

    // saves the latest piece of evidence to the log file
    static void saveEvidenceToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(evidenceLogPath, true))) {
            EvidenceData latest = evidenceLog.get(evidenceLog.size() - 1);
            writer.write(latest.toString());
            writer.newLine();
            System.out.println("Evidence log saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // saves whether the player won or lost to the results file
    static void saveResultToFile(String result, String ghostType) {
        try {
            File myFile = new File(resultFilePath);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFilePath, true))) {
                writer.write(result + "," + ghostType);
                writer.newLine();
                System.out.println("Result saved to file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

// all the data classes that hold info for the game objects

class GhostData {
    String name;
    String description;
    String evidenceClues;

    public GhostData(String name, String description, String evidenceClues) {
        this.name = name;
        this.description = description;
        this.evidenceClues = evidenceClues;
    }

    public String toString() {
        return name + "," + description + "," + evidenceClues;
    }
}

class ItemData {
    String name;
    String use;
    String detectsEvidence;

    public ItemData(String name, String detectsEvidence) {
        this.name = name;
        this.detectsEvidence = detectsEvidence;
        this.use = "No description loaded yet.";
    }

    public String toString() {
        return name + "," + use;
    }
}

class EvidenceType {
    String name;
    String detectedBy;
    String description;

    public EvidenceType(String name, String detectedBy) {
        this.name = name;
        this.detectedBy = detectedBy;
        this.description = "No description loaded yet.";
    }

    public String toString() {
        return name + "," + description;
    }
}

class RoomData {
    String name;
    String evidenceType;
    boolean hasEvidence;
    boolean evidenceCollected = false;
    boolean isGhostRoom = false;
    ArrayList<ItemData> items = new ArrayList<>();

    public RoomData(String name) {
        this.name = name;
        this.evidenceType = "";
        this.hasEvidence = false;
    }

    public String toString() {
        return name + "," + evidenceType;
    }
}

class EvidenceData {
    String type;
    String room;

    public EvidenceData(String type, String room) {
        this.type = type;
        this.room = room;
    }

    public String toString() {
        return room + "," + type;
    }
}