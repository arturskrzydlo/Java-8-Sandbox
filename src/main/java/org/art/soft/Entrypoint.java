package org.art.soft;

import org.art.soft.streams.TerminalOperations;
import org.art.soft.tasks.photocollection.Photo;
import org.art.soft.tasks.photocollection.PhotoCollection;

import java.util.List;

/**
 * Created by artur.skrzydlo on 2017-11-16.
 */
public class Entrypoint {

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("photo.jpg, Warsaw, 2013-09-05 14:08:15\n");
        stringBuilder.append("john.png, London, 2015-06-20 15:13:22\n");
        stringBuilder.append("myFriends.png, Warsaw, 2013-09-05 14:07:13\n");
        stringBuilder.append("Eiffel.jpg, Paris, 2015-07-23 08:03:02\n");
        stringBuilder.append("pisatower.jpg, Paris, 2015-07-22 23:59:59\n");
        stringBuilder.append("BOB.jpg, London, 2015-08-05 00:02:03\n");
        stringBuilder.append("notredame.png, Paris, 2015-09-01 12:00:00\n");
        stringBuilder.append("me.jpg, Warsaw, 2013-09-06 15:40:22\n");
        stringBuilder.append("a.png, Warsaw, 2016-02-13 13:33:50\n");
        stringBuilder.append("b.jpg, Warsaw, 2016-01-02 15:12:22\n");
        stringBuilder.append("c.jpg, Warsaw, 2016-01-02 14:34:30\n");
        stringBuilder.append("d.jpg, Warsaw, 2016-01-02 15:15:01\n");
        stringBuilder.append("e.png, Warsaw, 2016-01-02 09:49:09\n");
        stringBuilder.append("f.png, Warsaw, 2016-01-02 10:55:32\n");
        stringBuilder.append("g.jpg, Warsaw, 2016-02-29 22:13:11\n");

        PhotoCollection photoCollection = new PhotoCollection(stringBuilder.toString());
        List<Photo> photos = photoCollection.organize();

        TerminalOperations terminalOperations = new TerminalOperations(photos);
        System.out.println(terminalOperations.getNumberOfPhotosByPlacesTaken());
        System.out.println(terminalOperations.getNumberOfPhotosTakenIn("Warsaw"));
        System.out.println(terminalOperations.getPhotosNamesByPlaceTaken());
    }
}
