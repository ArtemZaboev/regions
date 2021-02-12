package com.directory.regions.controller;

import com.directory.regions.exception.RegionCreationException;
import com.directory.regions.exception.RegionNotFoundException;
import com.directory.regions.model.Region;
import com.directory.regions.service.RegionServiceImpl;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/region")
public class RegionController {

    private final RegionServiceImpl regionService;

    @Autowired
    public RegionController(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping("{id}")
    public Region getRegionById(@PathVariable("id") long id)  {
        try {
            return regionService.getRegionById(id);
        } catch (NotFoundException e) {
            throw new RegionNotFoundException();
        }
    }

    @GetMapping
    public List<Region> getRegions()  {
        List<Region> regions= null;
        try {
            regions = regionService.getRegions();
        } catch (NotFoundException e) {
            throw new RegionNotFoundException();
        }
        return regions;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRegion(@PathVariable("id") long id) {
        regionService.deleteRegion(id);

    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRegion(@RequestBody Region region) {
        if (region.getName()==null||region.getShortName()==null){
            log.info("region creation error");
            throw new RegionCreationException();
        }
        regionService.addRegion(region);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateRegion(@RequestBody Region region) {
        regionService.updateRegion(region);
    }
}
