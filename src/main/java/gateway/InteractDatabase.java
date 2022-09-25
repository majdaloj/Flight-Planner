package gateway;

import java.io.*;
import java.util.*;

/**
 * Class is a Gateway
 * Provides static methods to serialize and deserialize project data
 */
public class InteractDatabase {
    private static final String databasePath = "src/main/java/database";

    /**
     * Generates file location of database file
     * @param objectType Class being stored
     * @param <T> objectType is serializable
     * @return File path corresponding to objectType
     */
    private static <T extends Serializable> String filePath(Class<T> objectType) {
        return databasePath + "/" + objectType.getSimpleName().toLowerCase() + ".ser";
    }

    /**
     * Fetches database information
     * @param objectType Class being retrieved
     * @param <T> objectType is serializable
     * @return ArrayList containing corresponding objectType
     */
    public static <T extends Serializable> ArrayList<T> fetchList(Class<T> objectType) {
        try {
            // Every database file name is the same as the Object type it stores
            FileInputStream fis = new FileInputStream(filePath(objectType));
            ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            // Only putList can write to the files, and putList type-checks ArrayList<T>
            ArrayList<T> outputList = (ArrayList<T>) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write database information
     * This is idempotent
     * @param saveList ArrayList to store
     * @param objectType Class being written
     * @param <T> objectType is serializable
     */
    private static <T extends Serializable> void putList(ArrayList<T> saveList, Class<T> objectType) {
        // Serialize an ArrayList
        try {
            FileOutputStream fos = new FileOutputStream(filePath(objectType));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(saveList);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Append new data <toStore>
     * @param toStore Data to add
     * @param objectType Class of data being added
     * @param <T> objectType is serializable
     */
    public static <T extends Serializable> void post(T toStore, Class<T> objectType) {
        // Serializes <toStore>
        ArrayList<T> databaseList = fetchList(objectType);
        assert databaseList != null;
        databaseList.add(toStore);
        putList(databaseList, objectType);
    }

    /**
     * Replace existing data
     * This is idempotent
     * @param toStore Data to replace
     * @param index Place in database array to change
     * @param objectType Class of data being added
     * @param <T> objectType is serializable
     */
    public static <T extends Serializable> void put(T toStore, int index, Class<T> objectType) {
        // Modify or Replace some data at <index> with <toStore>
        ArrayList<T> databaseList = fetchList(objectType);
        assert databaseList != null;
        databaseList.set(index, toStore);
        putList(databaseList, objectType);
    }

    /**
     * Reset database to blank ArrayList
     * This is idempotent
     * @param objectType Class to reset
     * @param <T> objectType is serializable
     */
    public static <T extends Serializable> void reset(Class <T> objectType) {
        // Initialize empty database
        ArrayList<T> databaseList = new ArrayList<>();
        putList(databaseList, objectType);
    }
}