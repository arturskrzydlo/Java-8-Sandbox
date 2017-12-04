package org.art.soft.tasks.photocollection;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class PhotoCollectionTest {

    private StringBuilder photos;
    private int photosCount;
    private PhotoCollection photoCollection;

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
        photoCollection = new PhotoCollection(photos.toString());

    }

    @Test
    public void organizedPhotoCountIsTheSameAsFromInput() {

        List<Photo> organizedPhotos = photoCollection.organize();
        Assertions.assertThat(organizedPhotos.size()).isEqualTo(photosCount);

    }

    @Test
    public void organizedPhotosNamesHasOrderNumber() {

        List<Photo> organizedPhotos = photoCollection.organize();
        organizedPhotos.stream().forEach(photo -> Assert.assertTrue(Pattern.matches("^.*\\d$", photo.getName())));
    }

    @Test
    public void organizedPhotosAreSortedByDateAscending() {

        List<Photo> organizedPhotos = photoCollection.organize();
        List<Photo> sorted = new ArrayList<Photo>(organizedPhotos);
        Collections.sort(sorted, Comparator.comparing(Photo::getDateOfCreation));
        Assertions.assertThat(organizedPhotos).isEqualTo(sorted);
    }
}
