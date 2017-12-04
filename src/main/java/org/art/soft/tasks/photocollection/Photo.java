package org.art.soft.tasks.photocollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by artur.skrzydlo on 2017-11-16.
 */
public class Photo {

    private String name;
    private String placeTaken;
    private LocalDateTime dateOfCreation;

    public Photo(String name, String placeTaken, LocalDateTime dateOfCreation) {
        this.name = name;
        this.placeTaken = placeTaken;
        this.dateOfCreation = dateOfCreation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceTaken() {
        return placeTaken;
    }

    public void setPlaceTaken(String placeTaken) {
        this.placeTaken = placeTaken;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override public String toString() {
        return
                name + ',' +
                        placeTaken + ',' +
                        dateOfCreation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ;
    }
}
