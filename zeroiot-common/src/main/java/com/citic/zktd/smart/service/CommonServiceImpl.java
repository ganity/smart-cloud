package com.citic.zktd.smart.service;

import com.citic.zktd.smart.util.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 通用service 实现增删改查等基本操作, 如子类有特殊需求请覆盖重写
 * @param <M> 具体子类需要的JpaRepository类型
 * @param <T> entry类型
 * @param <ID> 主键类型
 */
public class CommonServiceImpl<M extends JpaRepository<T, ID>, T, ID extends Serializable> implements CommonService<T, ID> {

    @Autowired
    protected M baseRepository;

    @Override
    public <S extends T> S insert(S var1) {
        return baseRepository.save(var1);
    }


    @Override
    public void delete(ID id) {

        baseRepository.deleteById(id);
    }

    @Override
    public void delete(T t) {

        baseRepository.delete(t);
    }

    @Override
    public <S extends T> S updateIgnoreNull(S var1, ID id) {
        T t = baseRepository.getOne(id);
        BeanCopierUtils.copyPropertiesIgnoreNull(var1, t);
        return baseRepository.save((S)t);
    }

    @Override
    public <S extends T> S update(S var1) {
        return baseRepository.save(var1);
    }

    @Override
    public List<T> findAll(T t) {
        Example example = Example.of(t);
        return baseRepository.findAll(example);
    }

    @Override
    public T findOneById(ID id) {
        return baseRepository.getOne(id);
    }

    @Override
    public Optional<T> findOneByExample(T t) {
        Example<T> example = Example.of(t);
        return baseRepository.findOne(example);
    }

    @Override
    public Page<T> findByPage(T t, Pageable pageable) {
        Example example = Example.of(t);
        return baseRepository.findAll(example, pageable);
    }


}
