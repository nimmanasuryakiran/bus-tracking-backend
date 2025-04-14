
package com.college.bus.service;

import com.college.bus.model.Route;
import java.util.List;

public interface RouteService {
    Route addRoute(Route route);
    Route getRouteById(Long id);
    Route getRouteByName(String routeName);
    List<Route> getAllRoutes();
    void deleteRoute(Long id);
}
