package org.art.soft.tasks.photocollection;

/**
 * Created by artur.skrzydlo on 2017-11-16.
 *
 * Your task is to write application for photo organization. Currently you have
 * photos with different names in directories which point in which place photo has been taken. Additionaly to each photo
 * date of this photo is taken.
 *
 * Imagine that all of these files are just one string in format
 *
 *   photo.jpg, Warsaw, 2013-09-05 14:08:15
     john.png, London, 2015-06-20 15:13:22
     myFriends.png, Warsaw, 2013-09-05 14:07:13
     Eiffel.jpg, Paris, 2015-07-23 08:03:02
     pisatower.jpg, Paris, 2015-07-22 23:59:59
     BOB.jpg, London, 2015-08-05 00:02:03
     notredame.png, Paris, 2015-09-01 12:00:00
     me.jpg, Warsaw, 2013-09-06 15:40:22
     a.png, Warsaw, 2016-02-13 13:33:50
     b.jpg, Warsaw, 2016-01-02 15:12:22
     c.jpg, Warsaw, 2016-01-02 14:34:30
     d.jpg, Warsaw, 2016-01-02 15:15:01
     e.png, Warsaw, 2016-01-02 09:49:09
     f.png, Warsaw, 2016-01-02 10:55:32
     g.jpg, Warsaw, 2016-02-29 22:13:11

    Now you need to change all photos names to name which is name of a place where it was taken with order number.
    Order number is always two digit number (for instance first photo will be ordered as 01). Then, when you have these photo names converted,
    sort files by it's creation date (ascending) and produce output in format

     Warsaw02.jpg
     London1.png
     Warsaw01.png
     Paris2.jpg
     Paris1.jpg
     London2.jpg
     Paris3.png
     Warsaw03.jpg
     Warsaw09.png
     Warsaw07.jpg
     Warsaw06.jpg
     Warsaw08.jpg
     Warsaw04.png
     Warsaw05.png
     Warsaw10.jpg
 */
public class PhotoCollectionTask {

    public static void main(String[] args) {

    }
}
