package org.snoopdesigns.props.ml;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import quickml.data.AttributesMap;
import quickml.data.instances.RegressionInstance;
import quickml.supervised.ensembles.randomForest.randomRegressionForest.RandomRegressionForest;
import quickml.supervised.ensembles.randomForest.randomRegressionForest.RandomRegressionForestBuilder;

@Component
public class ProcessingService {

    public static void main(String[] args) throws Exception {
        ProcessingService service = new ProcessingService();
        service.process();
    }

    public void process() {
        List<RegressionInstance> dataset = new ArrayList<>();
        AttributesMap map = new AttributesMap();
        map.put("a", 1);
        map.put("b", 2);
        dataset.add(new RegressionInstance(map, 100000d));
        final RandomRegressionForest randomForest = new RandomRegressionForestBuilder<>().buildPredictiveModel(dataset);
        Double res = randomForest.predict(map);
        System.out.println(res);
    }
}
