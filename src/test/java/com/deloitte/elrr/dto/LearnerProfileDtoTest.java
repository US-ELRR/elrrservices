package com.deloitte.elrr.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deloitte.elrr.util.ValueObjectTestUtility;

class LearnerProfileDtoTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    /**
    *
    */
    @Test
    void test() {
        ValueObjectTestUtility.validateAccessors(LearnerProfileDto.class);
    }

    /**
     *
     */
    @Test
    void testToString() {
        assertNotNull(new LearnerProfileDto().toString());
    }

}
