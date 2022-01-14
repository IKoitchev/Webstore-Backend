package twiliosms.api.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("Visitor")
public class Visitor {


    @Id()
    @BsonProperty(value = "_id")
    private UUID id;
    @BsonProperty(value = "Name")
    private String name;
    @BsonProperty(value = "LicensePlate")
    private String licencePlate;
    @BsonProperty(value = "PhoneNumber")
    private String phoneNumber;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Visitor(UUID id, String name, String licencePlate, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.licencePlate = licencePlate;
        this.phoneNumber = phoneNumber;
    }

    public Visitor() {
    }
}
