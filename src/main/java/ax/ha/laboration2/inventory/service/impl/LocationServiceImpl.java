package ax.ha.laboration2.inventory.service.impl;

import ax.ha.laboration2.inventory.model.Location;
import ax.ha.laboration2.inventory.repository.LocationMapper;
import ax.ha.laboration2.inventory.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationRepository;

    public LocationServiceImpl(final LocationMapper locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getLocations() {
        return locationRepository.getLocations();
    }

    @Override
    public void addLocation(Location location) {
       locationRepository.saveLocation(location);
    }

    @Override
    public Location getLocation(Integer id) {
        return locationRepository.getLocation(id);
    }

    @Override
    public void deleteLocation(Integer id) {
        locationRepository.deleteLocation(id);
    }
}
