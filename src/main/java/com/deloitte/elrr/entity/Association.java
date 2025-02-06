package com.deloitte.elrr.entity;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.deloitte.elrr.entity.types.AssociationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "association")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Association extends Auditable<String> {

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;
    
    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @Column(name = "association_type", columnDefinition = "elrr.association_type")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private AssociationType associationType;

    @Override
    public String toString() {
        return "Association [id=" + id + ", organization=" + organization + ", person=" + person + ", associationType="
                + associationType + "]";
    }

}
