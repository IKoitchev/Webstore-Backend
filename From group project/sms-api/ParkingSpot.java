package twiliosms.api.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ParkingSpot")
public class ParkingSpot {

    @Id
    @BsonProperty(value = "_id")
    private int _id;
    @BsonProperty(value = "available")
    private boolean available;

    public ParkingSpot(int _id, boolean available) {
        this._id = _id;
        this.available = available;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
