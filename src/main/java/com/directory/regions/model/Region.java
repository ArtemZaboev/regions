package com.directory.regions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region implements Serializable {
    private long id;
    @NonNull
    private String name;
    private String shortName;

    public Region(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return id == region.id &&
                name.equals(region.name) &&
                Objects.equals(shortName, region.shortName);
    }

    @Override
    public int hashCode() {
        final int prime=31;
        int res=1;
        res=prime*res+((id==0)?0:Long.hashCode(id));
        res=prime*res+((name==null)?0:name.hashCode());
        res=prime*res+((shortName==null)?0:shortName.hashCode());
        return res;
    }
}
