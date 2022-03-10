package com.news.newsspringboot.utils;

import com.github.ksuid.KsuidGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 自动生成ksuid码
 */
public class KsuidIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return KsuidGenerator.generate();
    }
}
