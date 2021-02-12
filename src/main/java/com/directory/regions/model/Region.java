package com.directory.regions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region implements Serializable {
    private long id;
    private String name;
    private String shortName;

    public Region(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }
}
