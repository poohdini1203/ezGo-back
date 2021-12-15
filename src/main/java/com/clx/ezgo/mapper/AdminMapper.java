package com.clx.ezgo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public int findAdmin(String id);
}
