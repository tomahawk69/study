package org.study.hibernate.converter;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

/**
 * Created by yur on 25.10.2016.
 */
public class ConvertAddress implements AttributeConverter<Address, String> {


    @Override
    public String convertToDatabaseColumn(Address attribute) {
        Gson gson = new Gson();
        return gson.toJson(attribute).toString();
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        Gson gson = new Gson();
        return gson.fromJson(dbData, Address.class);
    }
}
