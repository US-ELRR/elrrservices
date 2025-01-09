/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.deloitte.elrr.exception.RuntimeServiceException;

/**
 * @author mnelakurti
 * @param <T>
 * @param <I>
 */
public interface CommonSvc<T, I extends Serializable> {
    /**
     *
     * @return Iterable<T>
     */
    default Iterable<T> findAll() {
        return getRepository().findAll();
    }
    /**
     *
     * @param i
     * @return Optional<T>
     */
    default Optional<T> get(I i) {
        return getRepository().findById(i);
    }
    /**
     *
     * @param entity
     * @return T
     */
    default T save(T entity) {
        return getRepository().save(entity);
    }
    /**
     *
     * @param entities
     * @return Iterable<T>
     */
    default Iterable<T> saveAll(Iterable<T> entities) {
        return getRepository().saveAll(entities);
    }
    /**
     *
     * @param i
     */
    default void delete(I i) {
        if (getRepository().existsById(i)) {
            getRepository().deleteById(i);
        } else {
            throw new RuntimeServiceException(
                    " Id not found for delete : " + i);
        }
    }
    /**
     *
     */
    default void deleteAll() {
        getRepository().deleteAll();

    }
    /**
     *
     * @param entity
     */
    default void update(T entity) {
        if (getRepository().existsById(getId(entity))) {
            getRepository().save(entity);
        } else {

            throw new RuntimeServiceException(
                    "Not found record in DB to update: " + entity);
        }
    }
    /**
     *
     * @param entity
     * @return Id
     */
    I getId(T entity);
    /**
     *
     * @return CrudRepository<T, ID>
     */
    CrudRepository<T, I> getRepository();
}
