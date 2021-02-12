package com.directory.regions.service;

import com.directory.regions.exception.RegionCreationException;
import com.directory.regions.exception.RegionNotFoundException;
import com.directory.regions.mapper.RegionMapper;
import com.directory.regions.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Region getRegionById(long id) {
        log.info("Getting region by id: {}", id);
        Region region;
        try {
            region = Objects.requireNonNull(regionMapper.getRegionById(id));

        }catch (NullPointerException e){
            log.error("Region not found by id: {}",id);
            throw new RegionNotFoundException();
        }
        return region;
    }

    @Override
    public List<Region> getRegions()  {
        log.info("getting all regions");
        List<Region> regions;
        try {
            regions = Objects.requireNonNull(regionMapper.getRegions());
        }catch (NullPointerException e){
            log.error("No regions found");
            throw new RegionNotFoundException();
        }
        return regions;
    }

    @Override
    @CacheEvict("region")
    public void deleteRegion(long id) {
        log.info("Deleting region with id: {}", id);
        regionMapper.deleteRegion(id);

    }

    @Override
    public void addRegion(Region region) {
        log.info("Adding new region with name: {}",region.getName());
        try {
            if (region.getName()==null||region.getShortName()==null){
                log.info("Name or abbreviated name cannot be equal to zero");
            throw new RegionCreationException();
        }
            regionMapper.saveRegion(region);
        }catch (Exception e){
            log.info("Region creation error");
            throw new RegionCreationException();

        }
    }

    @Override
    public void updateRegion(Region region) {
        log.info("updating region with id: {}",region.getId());
            if(getRegionById(region.getId())==null){
                throw new RegionNotFoundException();
            }
        regionMapper.updateRegion(region);
    }
}
