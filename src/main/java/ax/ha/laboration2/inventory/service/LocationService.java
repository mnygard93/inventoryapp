package ax.ha.laboration2.inventory.service;

import ax.ha.laboration2.inventory.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> getLocations();

    void addLocation(Location location);

    Location getLocation(Integer id);

    void deleteLocation(Integer id);

}
