package org.snoopdesigns.props.ml;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.snoopdesigns.props.ml.entity.ApartmentExtended;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.springframework.stereotype.Component;

@Component
public class DataPreprocessor {

    public List<ApartmentExtended> preprocessData(List<Apartment> apartments) {
        List<ApartmentExtended> apartmentExtendedList = new ArrayList<>();
        for (Apartment apartment : apartments) {
            ApartmentExtended extended = new ApartmentExtended(apartment);
            extended.setFirstFloor(ValueGenerators.IS_FIRST_FLOOR.generate(extended));
            extended.setLastFloor(ValueGenerators.IS_LAST_FLOOR.generate(extended));
            extended.setDistanceToCenter(ValueGenerators.DISTANCE_TO_CENTER.generate(extended));
            extended.setMetroInfo(ValueGenerators.CLOSEST_METRO.generate(extended));
            extended.setHouseTypeEnum(ValueGenerators.HOUSE_TYPE.generate(extended));
            extended.setBuildingType(ValueGenerators.BUILDING_TYPE.generate(extended));
            apartmentExtendedList.add(extended);
        }
        this.generateChart(apartmentExtendedList);
        return apartmentExtendedList;
    }

    private void generateChart(List<ApartmentExtended> apartments) {
        DateAxis domainAxis = new DateAxis("X");
        NumberAxis rangeAxis = new NumberAxis("Y");

        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        renderer.setSeriesItemLabelGenerator(1, new StandardXYItemLabelGenerator());

        XYSeries series = new XYSeries("test");
        apartments.stream().filter(a -> a.getComplex()!=null && a.getComplex().getLat() != null)
                .forEach(a -> series.add(a.getComplex().getLat().doubleValue(), a.getComplex().getLng().doubleValue()));

        XYDataset dataset = new XYSeriesCollection(series);

        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(0D, 0D, 10D, 10D));
        plot.setOutlinePaint(null);

        JFreeChart chart = new JFreeChart(plot);
        chart.setBackgroundPaint(Color.white);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.TOP);

        BufferedImage image = chart.createBufferedImage(500, 500);
        File outputfile = new File("saved.png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
