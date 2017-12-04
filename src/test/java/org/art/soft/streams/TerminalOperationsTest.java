package org.art.soft.streams;

import org.art.soft.tasks.photocollection.PhotoCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalOperationsTest {

    private StringBuilder photos;
    private int photosCount;
    private TerminalOperations terminalOperations;

    @Before
    public void setUp() throws Exception {

        photos = new StringBuilder();
        photos.append("photo.jpg, Warsaw, 2013-09-05 14:08:15\n");
        photos.append("john.png, London, 2015-06-20 15:13:22\n");
        photos.append("myFriends.png, Warsaw, 2013-09-05 14:07:13\n");
        photos.append("Eiffel.jpg, Paris, 2015-07-23 08:03:02\n");
        photos.append("pisatower.jpg, Paris, 2015-07-22 23:59:59\n");
        photos.append("BOB.jpg, London, 2015-08-05 00:02:03\n");
        photos.append("notredame.png, Paris, 2015-09-01 12:00:00\n");
        photos.append("me.jpg, Warsaw, 2013-09-06 15:40:22\n");
        photos.append("a.png, Warsaw, 2016-02-13 13:33:50\n");
        photos.append("b.jpg, Warsaw, 2016-01-02 15:12:22\n");
        photos.append("c.jpg, Warsaw, 2016-01-02 14:34:30\n");
        photos.append("d.jpg, Warsaw, 2016-01-02 15:15:01\n");
        photos.append("e.png, Warsaw, 2016-01-02 09:49:09\n");
        photos.append("f.png, Warsaw, 2016-01-02 10:55:32\n");
        photos.append("g.jpg, Warsaw, 2016-02-29 22:13:11\n");

        photosCount = photos.toString().split("\n").length;
        PhotoCollection photoCollection = new PhotoCollection(photos.toString());
        terminalOperations = new TerminalOperations(photoCollection.organize());
    }

    @Test
    public void photoTakenByPlaceReturnCorrectNumberOfPhotos() throws Exception {

        long numberOfWarsawPhotos = terminalOperations.getNumberOfPhotosTakenIn("Warsaw");
        assertEquals(numberOfWarsawPhotos, 10L);

    }

    @Test
    public void getNumberOfPhotosByPlacesTaken() throws Exception {
    }

    @Test
    public void getPhotosNamesByPlaceTaken() throws Exception {
    }

    @Test
    public void noPhotoHasBeenTakenInPlace() throws Exception {

        assertFalse(terminalOperations.checkIfAnyPhotoHasBeenTakenInPlace(""));
    }

    @Test
    public void photoHasBeenTakenInPlace() {

        assertTrue(terminalOperations.checkIfAnyPhotoHasBeenTakenInPlace("Warsaw"));
    }

    @Test
    public void findFirstPhotoTakenInWarsaw() {

        Assert.assertNotNull(terminalOperations.findFirstPhotoTakenIn("Warsaw").get());
        Assert.assertEquals(terminalOperations.findFirstPhotoTakenIn("Warsaw").get().getPlaceTaken(), "Warsaw");
    }
}
