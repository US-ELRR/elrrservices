package com.deloitte.elrr.services.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.deloitte.elrr.util.ValueObjectTestUtility;

class MilitaryRecordDtoTest {

    /**
    *
    */
    @Test
    void test() {
        ValueObjectTestUtility.validateAccessors(MilitaryRecordDto.class);
    }

    /**
     *
     */
    @Test
    void testToString() {
        assertNotNull(new MilitaryRecordDto().toString());
    }
}
