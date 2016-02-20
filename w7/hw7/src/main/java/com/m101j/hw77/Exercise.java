package com.m101j.hw77;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Collections;

import static com.google.common.base.Preconditions.checkState;

public class Exercise {

    public static void main(String[] args) {
        Morphia morphia = new Morphia();
        Datastore photobook = morphia.createDatastore(new MongoClient(), "photobook");

        checkState(countAllImages(photobook) == 100000,
                "Expected 100000 documents into Images collection");
        checkState(photobook.createQuery(Album.class).countAll() == 1000,
                "Expected 1000 documents into Albums collection");

        photobook.ensureIndex(Album.class, Album.FIELD_IMAGES);

        photobook.createQuery(Image.class).asList().stream().
                filter(image -> photobook.createQuery(Album.class)
                        .field(Album.FIELD_IMAGES)
                        .in(Collections.singletonList(image.getId()))
                        .countAll() == 0).
                forEach(photobook::delete);

        showResults(photobook);
    }

    private static void showResults(Datastore photobook) {
        System.out.println("> Images count: " + countAllImages(photobook));

        long c = photobook.createQuery(Image.class)
                .field(Image.FIELD_TAGS)
                .in(Collections.singletonList("sunrises"))
                .countAll();

        System.out.println("> Sunrises count: " + c);
    }

    private static long countAllImages(Datastore photobook) {
        return photobook.createQuery(Image.class).countAll();
    }
}
