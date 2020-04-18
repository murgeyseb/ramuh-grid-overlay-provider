/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Countries geometry file loading component.
 * The file is charged in memory at application startup.
 *
 * @author Sebastien Murgey {@literal <sebastien@murgey.net>}
 */
@Component
public class CountriesGeometryLoader {
    private static final String COUNTRIES_RESOURCE = "/countries.geojson";
    private static final Logger LOGGER = LoggerFactory.getLogger(CountriesGeometryLoader.class);

    private final Map<String, Feature> countriesGeometry = new HashMap<>();

    @PostConstruct
    private void loadCountriesGeometry() throws IOException {
        LOGGER.info("Loading countries geometry...");
        FeatureCollection featureCollection = new ObjectMapper().readValue(getClass().getResourceAsStream(COUNTRIES_RESOURCE), FeatureCollection.class);
        featureCollection.getFeatures()
                .forEach(this::addFeature);
        LOGGER.info("Countries geometry has been loaded.");
    }

    private void addFeature(Feature feature) {
        if (feature.getProperties().containsKey("ISO_A3")) {
            countriesGeometry.put(feature.getProperties().get("ISO_A3").toString(), feature);
        } else {
            LOGGER.warn("Feature '{}' in countries geometry file was not associated to any country code", feature.getId());
        }
    }

    public Map<String, Feature> getCountriesGeometry() {
        return countriesGeometry;
    }
}
