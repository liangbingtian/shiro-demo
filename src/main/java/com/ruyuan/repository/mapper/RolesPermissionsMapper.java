package com.ruyuan.repository.mapper;

import com.ruyuan.repository.bean.RolesPermissions;
import com.ruyuan.repository.bean.RolesPermissionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RolesPermissionsMapper {
    long countByExample(RolesPermissionsExample example);

    int deleteByExample(RolesPermissionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RolesPermissions record);

    int insertSelective(RolesPermissions record);

    List<RolesPermissions> selectByExample(RolesPermissionsExample example);

    RolesPermissions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RolesPermissions record, @Param("example") RolesPermissionsExample example);

    int updateByExample(@Param("record") RolesPermissions record, @Param("example") RolesPermissionsExample example);

    int updateByPrimaryKeySelective(RolesPermissions record);

    int updateByPrimaryKey(RolesPermissions record);
}