package course.homework;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by zmwilliam on 1/16/16.
 */
public class hw23 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        final MongoCollection<Document> collection = database.getCollection("grades");

        ArrayList<Document> allHomerworks = collection
                .find(new Document("type", "homework"))
                .sort(Sorts.ascending("student_id", "score"))
                .into(new ArrayList<Document>());

        Integer lastStudent = null;
        for (Document document : allHomerworks) {
            Integer currentStudent = document.getInteger("student_id");
            if (!currentStudent.equals(lastStudent)) {
                collection.deleteOne(document);
            }
            lastStudent = currentStudent;
        }

    }
}
