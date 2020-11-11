package ax.ha.laboration2.inventory.api;

import ax.ha.laboration2.inventory.model.Location;
import ax.ha.laboration2.inventory.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(final LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocation(@PathVariable final Integer id) {
        return ResponseEntity.ok(locationService.getLocation(id));
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST, consumes = "application/json")
    public void addLocation(@RequestBody final Location location) {
        locationService.addLocation(location);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLocation(@PathVariable final Integer id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

}
