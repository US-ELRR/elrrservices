/**
 *
 */
package com.deloitte.elrr.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author mnelakurti
 * @param <U>
 * This Entity class is
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Auditable<U> extends Entity {

    @Column(name = "inserted_date", updatable = false)
    @CreationTimestamp
    private Timestamp insertedDate;

    @Column(name = "updated_by")
    @LastModifiedBy
    private U updatedBy;

    @Column(name = "last_modified")
    @UpdateTimestamp
    private Timestamp lastModified;
}
