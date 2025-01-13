package com.deloitte.elrr.util;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.fasterxml.uuid.Generators;

public class SemiSeqUUIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return Generators.timeBasedEpochRandomGenerator().generate();
    }
}
