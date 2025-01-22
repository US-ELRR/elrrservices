/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */

@Service
@Slf4j
public class PersonSvc implements CommonSvc<Person, UUID> {
    /**
     *
     */
    
    private final PersonRepository personRepository;
    
    @Autowired
    private LocationSvc locationSvc;
    /**
     *
     * @param argsPersonRepository
     */
    public PersonSvc(final PersonRepository argsPersonRepository) {
        this.personRepository = argsPersonRepository;
    }
    /**
     *
     * @return CrudRepository<Person, Long>
     */
    @Override
    public CrudRepository<Person, UUID> getRepository() {
        return this.personRepository;
    }
    /**
     *
     * @return Long
     */
    @Override
    public UUID getId(final Person person) {
        return person.getId();
    }
    /**
     *
     * @return Person
     */
    @Override
    public Person save(final Person person) {
        if (person.getMailingAddress() != null)
            person.setMailingAddress(locationSvc.save(person.getMailingAddress()));
            
        return CommonSvc.super.save(person);
    }

}
