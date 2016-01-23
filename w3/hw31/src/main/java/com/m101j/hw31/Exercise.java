package com.m101j.hw31;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Comparator;
import java.util.List;

public class Exercise {

    public static void main(String[] args) {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.m101j.hw31");

        Datastore dbSchool = morphia.createDatastore(new MongoClient(), "school");

        List<Student> students = dbSchool.createQuery(Student.class).asList();

        //Sorry about this, someday i'll learn and use Morphia filter.
        students.stream().forEach(student -> student.getScores().stream()
                .filter(s -> s.getType().equals("homework"))
                .min(Comparator.comparing(Score::getScore))
                .ifPresent(score -> student.getScores().remove(score)));

        dbSchool.save(students);
    }

}
