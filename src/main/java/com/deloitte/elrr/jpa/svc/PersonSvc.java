/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.PersonalRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */

@Service
@Slf4j
public class PersonSvc implements CommonSvc<Person, Long> {
    /**
     *
     */
    private final PersonalRepository personalRepository;
    /**
     *
     * @param argsPersonalRepository
     */
    public PersonSvc(final PersonalRepository argsPersonalRepository) {
        this.personalRepository = argsPersonalRepository;
    }
    /**
     *
     * @return CrudRepository<Person, Long>
     */
    @Override
    public CrudRepository<Person, Long> getRepository() {
        return this.personalRepository;
    }
    /**
     *
     * @return Long
     */
    @Override
    public Long getI(final Person person) {
        return person.getPersonid();
    }
    /**
     *
     * @return Person
     */
    @Override
    public Person save(final Person person) {
        log.info("" + person);
        return CommonSvc.super.save(person);
    }

}
