/**
 *
 */
package com.deloitte.elrr.repository;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.elrr.entity.Accreditation;

/**
 * @author mnelakurti
 *
 */

@ExtendWith(MockitoExtension.class)
class AccreditationRepositoryTest {

    /**
    *
    */
   @Mock
   private AccreditationRepository mockAccreditationRepository;

   /**
    *
    */
   @Mock
   private Accreditation acccreditation;

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
    void test() {

        acccreditation.setAccreditationid(1L);
        mockAccreditationRepository.save(acccreditation);
        acccreditation.getAccreditationid();
    }

}
