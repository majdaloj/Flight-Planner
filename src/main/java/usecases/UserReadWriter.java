package usecases;

import gateway.InteractDatabase;

/**
 * Class a part of Application Business Rules
 * As name implies, reads and writes users into server and file allowing for continuing state of program
 */

public class UserReadWriter {
    /**
     * Writes the users to file at filePath.
     *
     * @param allUsers contains list of user managers to be serialized
     */
    public static void saveToFile(UserList allUsers) {
        InteractDatabase.put(allUsers, 0, UserList.class);
    }

    /**
     * Reads users from file at filePath.
     *
     * @return list of user managers
     */
    public static UserList readFromFile() {
        return InteractDatabase.fetchList(UserList.class).get(0);
    }
}
