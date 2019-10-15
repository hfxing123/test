package com.example.service.dao;

import com.example.common.entry.tw.Tw;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TwMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tw record);

    int insertSelective(Tw record);

    Tw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tw record);

    int updateByPrimaryKey(Tw record);

    List<Tw> findListBySelective(Tw record);

}