package com.directory.regions.service;

import com.directory.regions.model.Region;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface RegionService {
    Region getRegionById(long id);
    List<Region> getRegions();
    void deleteRegion(long id);
    void addRegion(Region region);
    void updateRegion(Region region);
}
