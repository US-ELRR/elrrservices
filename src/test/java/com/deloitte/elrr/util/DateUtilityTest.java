/**
 *
 */
package com.deloitte.elrr.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author mnelakurti
 *
 */
class DateUtilityTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() throws Exception {
        assertNotNull(DateUtility.addUXDate(new Date(), 1));
        assertNotNull(DateUtility.addUXDate("2022-11-11", 1));
        assertEquals(DateUtility.getCurrentDate(), new Date());
        assertNotNull(DateUtility.getDate("2022-11-11"));
        //assertNotNull(DateUtility.getDateRange(new Date(), new Date());
        assertNotNull(DateUtility.getYearIndex("2020-05-30", 2, "2022-11-11"));
        assertNotNull(DateUtility.getLastDayOfMonth("2022-11-11"));
        //assertNotNull(DateUtility.addUXDate("11/11/2022", 1));
        //assertNotNull(DateUtility.addUXDate(new Date(),1));
       // fail("Not yet implemented");
        DateUtility.getUXDate(new Date());
        DateUtility.getUXDate("2022-11-11");
        DateUtility.getUXDateFromEndDate("2022-11-11", 1);
        DateUtility.getYear("2022-11-11");
        DateUtility.getYearIndex("2022-11-11", 1, "2022-11-11");
        DateUtility.getYearIndex("2022-11-11", 1, "2022-11-12");
        DateUtility.getYearIndex("2022-11-12", 1, "2022-11-11");
        assertNotNull(DateUtility.getDate(new Date()));
        DateUtility.getDateRange(new GregorianCalendar(), new GregorianCalendar());
    }

}
