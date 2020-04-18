/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import org.geojson.FeatureCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EndToEndTest {
    @Autowired
    private GridOverlayController controller;

    @Test
    public void testWithFourCountriesUcteFile() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("20170215_0830_2d4_uc1.uct", "20170215_0830_2d4_uc1.uct", null, getClass().getResourceAsStream("/20170215_0830_2d4_uc1.uct"));
        FeatureCollection returnedCollection = controller.overlayCreator(mockMultipartFile);
        assertEquals(4, returnedCollection.getFeatures().size());
        assertTrue(returnedCollection.getFeatures().stream().anyMatch(feature -> feature.getProperties().get("ISO_A3").equals("FRA")));
        assertTrue(returnedCollection.getFeatures().stream().anyMatch(feature -> feature.getProperties().get("ISO_A3").equals("DEU")));
        assertTrue(returnedCollection.getFeatures().stream().anyMatch(feature -> feature.getProperties().get("ISO_A3").equals("BEL")));
        assertTrue(returnedCollection.getFeatures().stream().anyMatch(feature -> feature.getProperties().get("ISO_A3").equals("NLD")));
    }

    @Test
    public void testWithNoCountryXiidmFile() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("ieee14.xiidm", "ieee14.xiidm", null, getClass().getResourceAsStream("/ieee14.xiidm"));
        FeatureCollection returnedCollection = controller.overlayCreator(mockMultipartFile);
        assertTrue(returnedCollection.getFeatures().isEmpty());
    }
}
