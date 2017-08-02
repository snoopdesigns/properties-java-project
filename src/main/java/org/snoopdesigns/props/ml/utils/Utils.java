package org.snoopdesigns.props.ml.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // convert to meters
    }

    public static String findClosestMetro(Float lat, Float lng) {
        List<String> metros = loadMetroData();
        int closestIndex = -1;
        double closestDist = 100000000;
        for (int i = 0; i < metros.size(); i++) {
            double metroLat = Double.valueOf(metros.get(i).split(";")[1]);
            double metroLng = Double.valueOf(metros.get(i).split(";")[2]);
            double dist = distance(lat, metroLat, lng, metroLng);
            if (dist < closestDist) {
                closestDist = dist;
                closestIndex = i;
            }
        }
        return metros.get(closestIndex).split(";")[0];
    }

    private static List<String> loadMetroData() {
        List<String> data = new ArrayList<>();
        String fileName = Utils.class.getClassLoader().getResource("metro/metro.csv").getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
