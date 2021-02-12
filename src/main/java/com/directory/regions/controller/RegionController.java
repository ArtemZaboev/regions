package com.directory.regions.controller;

import com.directory.regions.model.Region;
import com.directory.regions.service.RegionServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("/api/region")
public class RegionController {

    private final RegionServiceImpl regionService;

    @Autowired
    public RegionController(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping("{id}")
    public Region getRegionById(@PathVariable("id") long id) throws NotFoundException {
            return regionService.getRegionById(id);
    }

    @GetMapping
    public List<Region> getRegions() {
        List<Region> regions= null;
        try {
            regions = regionService.getRegions();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return regions;
    }

    @DeleteMapping("/delete{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRegion(@PathVariable("id") long id) {
        regionService.deleteRegion(id);

    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRegion(@RequestBody Region region) {
        regionService.addRegion(region);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateRegion(@RequestBody Region region) {
        regionService.updateRegion(region);
    }
}
