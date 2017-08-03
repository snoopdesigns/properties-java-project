package org.snoopdesigns.props.ml.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snoopdesigns.props.ml.entity.MetroInfo;

public class Utils {

    public static Double distance(Double lat1, Double lat2, Double lon1, Double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // convert to meters
    }

    public static MetroInfo findClosestMetro(Float lat, Float lng) {
        List<String> metros = loadMetroData();
        int closestIndex = -1;
        double closestDist = 100000000;
        for (int i = 0; i < metros.size(); i++) {
            Double metroLat = Double.valueOf(metros.get(i).split(";")[1]);
            Double metroLng = Double.valueOf(metros.get(i).split(";")[2]);
            Double dist = distance(lat.doubleValue(), metroLat, lng.doubleValue(), metroLng);
            if (dist < closestDist) {
                closestDist = dist;
                closestIndex = i;
            }
        }
        MetroInfo metroInfo = new MetroInfo();
        metroInfo.setDistanceToMetro(closestDist);
        String[] metroInfos = metros.get(closestIndex).split(";");
        metroInfo.setMetroName(metroInfos[0]);
        metroInfo.setMetroLat(Float.valueOf(metroInfos[1]));
        metroInfo.setMetroLng(Float.valueOf(metroInfos[2]));
        return metroInfo;
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
