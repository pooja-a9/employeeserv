package com.paypal.bfs.test.employeeserv.entity.converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.entity.Address;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class AddressObjectConverter implements AttributeConverter<Address, String> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Address address) {
        String json;
        try {
            json = mapper.writeValueAsString(address);
        } catch (NullPointerException | JsonProcessingException ex) {
            json = "";
        }
        return json;

    }

    @Override
    public Address convertToEntityAttribute(String s) {
        Address address;
        try {
            address = mapper.readValue(s, Address.class);
        } catch (IOException e) {
            address = null;
        }
        return address;
    }
}

