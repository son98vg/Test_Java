package mongoDB;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MongoDB {
    public static void main(String[] args) {
        // Connect to MongoDB

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        // Access to database
        MongoDatabase database = mongoClient.getDatabase("MongoDB");

        // Access to Collection
       MongoCollection collection = database.getCollection("test");
       // Insert one document
//        Document doc = new Document("name", "MongoDB")
//                .append("type", "database")
//                .append("count", 1)
//                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//                .append("info", new Document("x", 203).append("y", 102));
//        collection.insertOne(doc);
        // Insert many
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 10; i++) {
            documents.add(new Document("i", i));
        }
        collection.insertMany(documents);

        // Query all
//        MongoCursor<Document> cursor = collection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }

        // Query Filter

        Document findQuery = new Document("i", new Document("$gt",""));
        MongoCursor<Document> cursor1 = collection.find(findQuery).iterator();
//        collection.deleteMany(findQuery);
        try {
            while (cursor1.hasNext()) {
                System.out.println(cursor1.next().toJson());
            }
        } finally {
            cursor1.close();
        }




//        List<Document> seedData = new ArrayList<Document>();
//
//        seedData.add(new Document("decade", "1970s")
//                .append("artist", "Debby Boone")
//                .append("song", "You Light Up My Life")
//                .append("weeksAtOne", 10)
//        );
//
//        seedData.add(new Document("decade", "1980s")
//                .append("artist", "Olivia Newton-John")
//                .append("song", "Physical")
//                .append("weeksAtOne", 10)
//        );
//
//        seedData.add(new Document("decade", "1990s")
//                .append("artist", "Mariah Carey")
//                .append("song", "One Sweet Day")
//                .append("weeksAtOne", 16)
//        );
//       /// Add record to DB
////       Document document = new Document("name", "Son");
//////       document.append("Age", "22");
////       document.append("city", "Ha Noi");
////       collection.insertMany(seedData);
////        String test = "Son";
//        Document name = new Document("Son",  new Document("$gte",10));
//        Document findQuery = new Document("weeksAtOne", new Document("$gte",10));
//        Document orderBy = new Document("decade", 1);
//        MongoCursor <Document> cursor = collection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                Document doc = cursor.next();
//                System.out.println(doc.toJson());
//            }
//        } finally {
//            cursor.close();
//        }
    }
}
