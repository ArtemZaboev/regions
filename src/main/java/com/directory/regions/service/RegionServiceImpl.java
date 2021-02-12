package com.directory.regions.service;

import com.directory.regions.mapper.RegionMapper;
import com.directory.regions.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    @Cacheable("region")
    public Region getRegionById(long id) throws NotFoundException{
        log.info("getting region by id: {}", id);
        Region region=regionMapper.getRegionById(id);
        if (region==null) {
            log.error("Region not found by id: {}",id);
            throw new NotFoundException("Region not found by id: "+id);
        }
        return region;
    }

    @Override
    public List<Region> getRegions() throws NotFoundException {
        log.info("getting all regions");
        List<Region> regions=regionMapper.getRegions();
        if (regions==null){
            log.error("No regions found");
            throw new NotFoundException("No regions found");
        }
        return regions;
    }

    @Override
    @CacheEvict("region")
    public void deleteRegion(long id) {
        log.info("deleting region with id: {}", id);
        regionMapper.deleteRegion(id);

    }

    @Override
    public void addRegion(Region region) {
        log.info("adding new region with name: {}",region.getName());
        regionMapper.saveRegion(region);
    }

    @Override
    public void updateRegion(Region region) {
        log.info("updating region with id: {}",region.getId());
        regionMapper.updateRegion(region);
    }
}
