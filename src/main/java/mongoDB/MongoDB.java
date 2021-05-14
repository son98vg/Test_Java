package mongoDB;


import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {
    public static void main(String[] args) {
        // Connect to MongoDB

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        // Access to database
        MongoDatabase database = mongoClient.getDatabase("MongoDB");

        // Access to Collection
       MongoCollection collection = database.getCollection("test");
        List<Document> seedData = new ArrayList<Document>();

        seedData.add(new Document("decade", "1970s")
                .append("artist", "Debby Boone")
                .append("song", "You Light Up My Life")
                .append("weeksAtOne", 10)
        );

        seedData.add(new Document("decade", "1980s")
                .append("artist", "Olivia Newton-John")
                .append("song", "Physical")
                .append("weeksAtOne", 10)
        );

        seedData.add(new Document("decade", "1990s")
                .append("artist", "Mariah Carey")
                .append("song", "One Sweet Day")
                .append("weeksAtOne", 16)
        );
       /// Add record to DB
//       Document document = new Document("name", "Son");
////       document.append("Age", "22");
//       document.append("city", "Ha Noi");
//       collection.insertMany(seedData);
       Document findQuery = new Document("weeksAtOne", new Document("$gte",10));
       Document orderBy = new Document("decade", 1);
        MongoCursor <Document> cursor = collection.find(findQuery).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
