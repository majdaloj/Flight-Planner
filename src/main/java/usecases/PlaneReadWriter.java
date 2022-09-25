package usecases;

import entities.Plane;
import gateway.InteractDatabase;

import java.util.ArrayList;

public class PlaneReadWriter {
    public static ArrayList<Plane> getPlaneList() {
        // Returns list of Planes
        return InteractDatabase.fetchList(Plane.class);
    }

    public static void postPlane(Plane toStore) {
        // Serializes <toStore>
        InteractDatabase.post(toStore, Plane.class);
    }
}
