package org.snoopdesigns.props.controllers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.snoopdesigns.props.persistence.entities.Apartment;

public class CustomApartmentsSerializer extends JsonSerializer<Apartment> {

    @Override
    public void serialize(Apartment apartment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("id", apartment.getId());
        jsonGenerator.writeObjectField("cianId", apartment.getCianId());
        jsonGenerator.writeObjectField("url", apartment.getUrl());
        jsonGenerator.writeObjectField("houseType", apartment.getHouseType());
        jsonGenerator.writeObjectField("sellType", apartment.getSellType());
        jsonGenerator.writeObjectField("totalArea", apartment.getTotalArea());
        jsonGenerator.writeObjectField("roomsArea", apartment.getRoomsArea());
        jsonGenerator.writeObjectField("livingArea", apartment.getLivingArea());
        jsonGenerator.writeObjectField("window", apartment.getWindow());
        jsonGenerator.writeObjectField("phone", apartment.getPhone());
        jsonGenerator.writeObjectField("repairs", apartment.getRepairs());
        jsonGenerator.writeObjectField("price", apartment.getPrice());
        jsonGenerator.writeObjectField("address", apartment.getAddress());

        jsonGenerator.writeObjectField("complexName", apartment.getComplex() != null ? apartment.getComplex().getName() : null);
        jsonGenerator.writeEndObject();
    }
}
