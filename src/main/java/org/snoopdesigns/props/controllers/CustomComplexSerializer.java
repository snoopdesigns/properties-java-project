package org.snoopdesigns.props.controllers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.snoopdesigns.props.crawler.nextgen.entities.Complex;

/**
 * Created by dmmorozo on 11.08.2017.
 */
public class CustomComplexSerializer extends JsonSerializer<Complex> {
    @Override
    public void serialize(Complex complex, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("name", complex.getName());
        jsonGenerator.writeObjectField("address", complex.getAddress());
        jsonGenerator.writeObjectField("lat", complex.getLat());
        jsonGenerator.writeObjectField("lng", complex.getLng());
        jsonGenerator.writeObjectField("numApartments", complex.getApartments().size());

        jsonGenerator.writeEndObject();
    }
}
