package org.art.soft.tasks.photocollection;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by artur.skrzydlo on 2017-11-16.
 */

//Stream parallelizing  - use only when it's sense to use it - its faster - but you use more resource
// While can be done with Stream iterate (with numeric values)

public class PhotoCollection {

    private String photoCollectionString;

    public PhotoCollection(String collection) {
        this.photoCollectionString = collection;
    }

    public String organize() {

        StringBuilder organizedPhotos = new StringBuilder();


        Map<String, List<Photo>> photosGroupedByCities = Arrays.stream(photoCollectionString.split("\n"))
                                                               .map(PhotoCollection::createPhotoFromString)
                                                               .collect(groupingBy(Photo::getPlaceTaken)
                                                               );

        // Do not create multilne lambdas ! It should be a glue, really easy to read code
        photosGroupedByCities.values().stream()
                             .map(PhotoCollection::changePhotosName)
                             .flatMap(collection -> collection.stream())
                             .sorted(Comparator.comparing(Photo::getDateOfCreation))

                            // Don't mutate elements in pipeline ! Just don't change shared variable, avoid shared mutability
                            // use collect(toList()) or other safe thread method
                             //.forEach(photo1 -> organizedPhotos.append(photo1.toString() + "\n"));

                            .map(photo -> photo.toString())
                            .collect(Collectors.joining("\n"));

        return organizedPhotos.toString();
    }

    private static Photo createPhotoFromString(String photo) {

        String[] photoElements = photo.split(",");
        LocalDateTime localDateTime = LocalDateTime
                .parse(photoElements[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new Photo(photoElements[0].trim(), photoElements[1].trim(), localDateTime);
    }

    private static List<Photo> changePhotosName(List<Photo> photoMadeInTheSameCity) {

        DecimalFormat decimalFormat = new DecimalFormat("#00");

        final AtomicInteger count = new AtomicInteger();
        photoMadeInTheSameCity.forEach(photo -> photo.setName(photo.getPlaceTaken() + decimalFormat.format(count.incrementAndGet())));
        return photoMadeInTheSameCity;
    }

}
