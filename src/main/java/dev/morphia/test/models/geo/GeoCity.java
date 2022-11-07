package dev.morphia.test.models.geo;

import com.mongodb.client.model.geojson.Point;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import dev.morphia.utils.IndexDirection;

import org.bson.types.ObjectId;

@Entity
public class GeoCity {
    @Id
    private ObjectId id;
    @Indexed(IndexDirection.GEO2DSPHERE)
    private Point location;
    private String name;

    @SuppressWarnings("unused")
    public GeoCity() {
    }

    public GeoCity(String name, Point location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoCity)) {
            return false;
        }

        final GeoCity city = (GeoCity) o;

        if (location != null ? !location.equals(city.location) : city.location != null) {
            return false;
        }
        return name.equals(city.name);

    }

    @Override
    public String toString() {
        return "City{"
                + "location=" + location
                + ", name='" + name + '\''
                + '}';
    }
}
