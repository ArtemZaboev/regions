package com.directory.regions.service;

import com.directory.regions.model.Region;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface RegionService {
    Region getRegionById(long id) throws NotFoundException;
    List<Region> getRegions() throws NotFoundException;
    void deleteRegion(long id);
    void addRegion(Region region);
    void updateRegion(Region region);
}
