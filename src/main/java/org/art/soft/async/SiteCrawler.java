package org.art.soft.async;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SiteCrawler {

    private static String parse(String xml) {

        System.out.println("\nDoing parsing\n");
        return xml;
    }

    private static CompletableFuture<Double> calculateRelevance(String doc) {

        //time consuming operation
        System.out.println("Calculating relevance .... ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Relavance calucalated !");

        CompletableFuture<Double> relevanceFuture = CompletableFuture.supplyAsync(() -> new Random().nextDouble());
        return relevanceFuture;
    }

    private static String downloadSite(final String site) {
        try {
            System.out.println("Downloading {} " + site);
            final String res = IOUtils.toString(new URL("https://" + site), UTF_8);
            System.out.println("Done {} " + site);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<String> topSites = Arrays.asList(
                "www.onet.pl", "www.wp.pl", "www.yahoo.com", "www.msn.com", "www.o2.pl"
        );

        //System.out.println(downloadSite("www.wp.pl"));

        List<CompletableFuture<Double>> futures = topSites.stream()
                                                          .map(site -> CompletableFuture
                                                                  .supplyAsync(() -> downloadSite(site), executor))
                                                          .map(contentFuture -> contentFuture
                                                                  .thenApply(SiteCrawler::parse))
                                                          .map(stringFuture -> stringFuture
                                                                  .thenCompose(SiteCrawler::calculateRelevance))
                                                          .collect(Collectors.toList());

        CompletableFuture<List<Double>> allDone = sequence(futures);

        CompletableFuture<OptionalDouble> maxRelevance = allDone.thenApply(relevances ->
                relevances.stream().
                        mapToDouble(Double::valueOf).
                                  max()
        );

        maxRelevance.thenAccept(System.out::println);
        System.out.println(futures);

        Thread.sleep(40000);
    }

    private static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                               collect(Collectors.<T>toList())
        );
    }
}
