/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.PersonRepository;

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
        private PersonRepository personRepository;

        /**
         *
         *
         */
        @Test
        void test() {
            PersonSvc personSvc = new PersonSvc(
                    personRepository);
            Person person = new Person();
            UUID id = UUID.randomUUID();
            person.setId(id);
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            ReflectionTestUtils.setField(personSvc,
                    "personRepository", personRepository);
            Mockito.doReturn(person)
                    .when(personRepository).save(person);
            Mockito.doReturn(true).when(personRepository)
                    .existsById(id);
            Mockito.doNothing().when(personRepository).deleteById(id);

            personSvc.getId(person);
            personSvc.findAll();
            personSvc.get(id);
            personSvc.save(person);
            personSvc.deleteAll();
            personSvc.delete(id);
            personSvc.update(person);
            personSvc.saveAll(personList);
        }
    }
