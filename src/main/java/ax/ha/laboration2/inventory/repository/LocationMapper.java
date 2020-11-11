package ax.ha.laboration2.inventory.repository;

import ax.ha.laboration2.inventory.model.Location;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LocationMapper {

    List<Location> getLocations();

    void saveLocation(Location location);

    Location getLocation(Integer id);

    void deleteLocation(Integer id);

}
