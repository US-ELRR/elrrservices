/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.PersonalRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonSvcTest {



        /**
        *
        */
        @Mock
        private PersonalRepository personalRepository;

        /**
         *
         *
         */
        @Test
        void test() {
            PersonSvc personSvc = new PersonSvc(
                    personalRepository);
            Person person = new Person();
            person.setPersonid(1L);
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            ReflectionTestUtils.setField(personSvc,
                    "personalRepository", personalRepository);
            Mockito.doReturn(person)
                    .when(personalRepository).save(person);
            Mockito.doReturn(true).when(personalRepository)
                    .existsById(1L);
            Mockito.doNothing().when(personalRepository).deleteById(1L);

            personSvc.getI(person);
            personSvc.findAll();
            personSvc.get(1L);
            personSvc.save(person);
            personSvc.deleteAll();
            personSvc.delete(1L);
            personSvc.update(person);
            personSvc.saveAll(personList);
        }
    }
