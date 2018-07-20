package com.citic.zktd.smart.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CommonService<T, ID extends Serializable> {

    <S extends T> S insert(S var1);

    void delete(ID id);

    void delete(T t);

    <S extends T> S update(S var1);

    <S extends T> S updateIgnoreNull(S var1, ID id);

    List<T> findAll(T t);

    T findOneById(ID id);

    Optional<T> findOneByExample(T t);

    Page<T> findByPage(T t, Pageable pageable);

}
