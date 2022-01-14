package twiliosms.api.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.conversions.Bson;
import twiliosms.api.models.ParkingSpot;
import twiliosms.api.models.Visitor;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {

    String conString = "mongodb+srv://admin:admin@cluster0.8zpfw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    String dbName = "VisitorApp";

    public MongoDatabase database;
    public Database() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        //CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        MongoClientSettings clientSettings = MongoClientSettings.builder().build();
        //CodecRegistry codecRegistry = MongoClients.create(clientSettings).getDatabase("VisitorApp").getCodecRegistry();
        CodecRegistry codecRegistry = fromRegistries(clientSettings.getCodecRegistry(), pojoCodecRegistry);

        ConnectionString connectionString = new ConnectionString(conString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .uuidRepresentation(UuidRepresentation.JAVA_LEGACY).build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(dbName);

        MongoCollection<Document> visitors = database.getCollection("Visitor");


        //Testing if properly connected
        //System.out.println("visitor count: " + visitors.countDocuments());

    }

    public Visitor getVisitorByLicencePlate(String licencePlate){

        MongoCollection<Visitor> visitors = database.getCollection("Visitor", Visitor.class);

        Visitor visitor = visitors.find(new Document("LicensePlate", licencePlate)).first();

        return visitor;
    }
    public String changeParkingSpotState(int id, boolean newState){
        MongoCollection<ParkingSpot> parkingSpots = database.getCollection("ParkingSpot", ParkingSpot.class);

        Bson filter = eq("_id", id);
        Bson updateOperation = set("available", newState);

        UpdateResult updateResult = parkingSpots.updateOne(filter, updateOperation);

        return updateResult.toString();
    }
    public long freeParkingSpotsCount(){

        MongoCollection<ParkingSpot> parkingSpots = database.getCollection("ParkingSpot", ParkingSpot.class);
        Bson filter = eq("available", true);

        var count = parkingSpots.countDocuments(filter);

        return count;

    }


}
