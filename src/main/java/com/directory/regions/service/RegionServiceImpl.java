package com.directory.regions.service;

import com.directory.regions.mapper.RegionMapper;
import com.directory.regions.model.Region;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    public Region getRegionById(long id) throws NotFoundException{
        Region region=regionMapper.getRegionById(id);
        if (region==null)throw new NotFoundException("Region not found");
        return region;
    }

    @Override
    public List<Region> getRegions() throws NotFoundException {
        List<Region> regions=regionMapper.getRegions();
        if (regions==null)throw new NotFoundException("No regions found");
        return regions;
    }

    @Override
    public void deleteRegion(long id) {
        regionMapper.deleteRegion(id);

    }

    @Override
    public void addRegion(Region region) {
        regionMapper.saveRegion(region);
    }

    @Override
    public void updateRegion(Region region) {
        regionMapper.updateRegion(region);
    }
}
