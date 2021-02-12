package com.directory.regions.mapper;

import com.directory.regions.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Select("select * from regions where id=${id}")
    Region getRegionById(@Param("id") long id);

    @Select("select * from regions")
    List<Region> getRegions();

    @Delete("delete from regions where id=${id}")
    void deleteRegion(@Param("id") long id);

    @Insert("insert into regions (name,shortName) values (#{name},#{shortName})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void saveRegion(Region region);
    @Update("UPDATE regions SET name=#{name}, shortName =#{shortName} WHERE id =#{id}")
    void updateRegion(Region region);
}
