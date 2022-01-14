package twiliosms.api;

import twiliosms.api.database.Database;
import twiliosms.api.models.ParkingSpot;

public class ParkingSpotService {

    Database db;
    public ParkingSpotService() {
        db = new Database();
    }
    public void setParkingSpotState(ParkingSpot newParkingSpot) {

        String message = db.changeParkingSpotState(newParkingSpot.getId(), newParkingSpot.isAvailable());

        System.out.println(newParkingSpot.getId() + " " + newParkingSpot.isAvailable());

    }
}
