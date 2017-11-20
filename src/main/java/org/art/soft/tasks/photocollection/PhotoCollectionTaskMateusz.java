package org.art.soft.tasks.photocollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class PhotoCollectionTaskMateusz {

    public static void main(String[] args) throws Exception {


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

        String[] a = stringBuilder.toString().split("\n");

        Map<String, Long> counter = new HashMap<>();

        List<Photo> collect = Arrays.stream(a)
                                    .map(e -> new Photo(e, resolveFinalName(e.split(",")[1], counter)))
                                    .sorted(Comparator.comparing(Photo::getDateTime))
                                    .collect(Collectors.toList());

        System.out.println(collect);
    }

    private static String resolveFinalName(String e, Map<String, Long> counter) {
        counter.computeIfPresent(e, (k, v) -> v + 1);
        counter.putIfAbsent(e, 1L);
        return e + counter.get(e);
    }

    static class Photo {
        private String place;
        private LocalDateTime dateTime;
        private String finalName;
        private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public Photo(String input, String finalName) {
            String[] split = input.split(",");
            this.place = split[1];
            this.dateTime = LocalDateTime.parse(split[2].trim(), dateTimeFormatter);
            this.finalName = finalName;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        @Override public String toString() {
            return "Photo{" +
                    "place='" + place + '\'' +
                    ", dateTime=" + dateTime +
                    ", finalName='" + finalName + '\'' +
                    ", dateTimeFormatter=" + dateTimeFormatter +
                    '}';
        }
    }
}
