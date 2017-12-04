package org.art.soft.streams;

import org.art.soft.tasks.photocollection.Photo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

public class TerminalOperations {

    private List<Photo> photosList;

    public TerminalOperations(List<Photo> photosList) {
        this.photosList = photosList;
    }

    public long getNumberOfPhotosTakenIn(String placeTaken) {

        return photosList.stream()
                         .filter(photo -> photo.getPlaceTaken().equals(placeTaken))
                         .count();
    }

    public Map<String, Long> getNumberOfPhotosByPlacesTaken() {

        //print number of photos which was taken in every location
        return photosList.stream()
                         .collect(groupingBy(Photo::getPlaceTaken, Collectors.counting()));

        // OR

        /*photos.stream()
              .collect(groupingBy(Photo::getPlaceTaken, Collectors.counting()))
              .forEach((s, aLong) -> System.out.println(s + "\t" + aLong));*/
    }

    public Map<String, List<String>> getPhotosNamesByPlaceTaken() {

        //print photo names by location where there were taken
        return photosList.stream()
                         .collect(groupingBy(Photo::getPlaceTaken,
                                 mapping(Photo::getName, Collectors.toList())));
    }

    public boolean checkIfAnyPhotoHasBeenTakenInPlace(String placeTaken) {

        return photosList.stream()
                         .anyMatch(photo -> photo.getPlaceTaken().equals(placeTaken));

    }

    public Optional<Photo> findFirstPhotoTakenIn(String placeTaken) {

        return photosList.stream()
                         .filter(photo -> photo.getPlaceTaken().equals(placeTaken))
                         .findFirst();
    }
}
