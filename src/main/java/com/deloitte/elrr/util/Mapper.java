package com.deloitte.elrr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * A singleton wrapper for jackson.databind.ObjectMapper configured
 * for the serialization and deserialization of the xAPI Model.
 */
public final class Mapper {

    private static ObjectMapper instance;

    private Mapper() {
    }

    /**
     * @return The ObjectMapper instance configured for use with the
     * xAPI Model.
     */
    public static synchronized ObjectMapper getMapper() {
        if (instance == null) {
            ObjectMapper mapper = new ObjectMapper();

            //Add JSR310 Module for java.time support
            mapper.registerModule(new JavaTimeModule());

            instance = mapper;
        }
        return instance;
    }
}
