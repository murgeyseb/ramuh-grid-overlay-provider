/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountriesGeometryLoaderTest {
    @Autowired
    private CountriesGeometryLoader loader;

    @Test
    public void checkLoadingCorrectly() {
        assertFalse(loader.getCountriesGeometry().isEmpty());
        assertTrue(loader.getCountriesGeometry().containsKey("CHN"));
        assertTrue(loader.getCountriesGeometry().containsKey("DEU"));
        assertTrue(loader.getCountriesGeometry().containsKey("FRA"));
        assertTrue(loader.getCountriesGeometry().containsKey("USA"));
    }
}