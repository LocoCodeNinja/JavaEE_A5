import com.prog3060.kitlouanglath_assignment_5.resource.EmployeeResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class EmployeeApplication extends Application {
    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> singletons = new HashSet<>();
        singletons.add(new EmployeeResource());
        return singletons;
    }
}
