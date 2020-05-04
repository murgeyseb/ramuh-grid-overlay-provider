/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import org.geojson.FeatureCollection;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Sebastien Murgey {@literal <sebastien@murgey.net>}
 */
@RestController
@RequestMapping(value = "/api/grid-overlay-provider")
public class GridOverlayController {
    private final GridOverlayService gridOverlayService;

    public GridOverlayController(GridOverlayService gridOverlayService) {
        this.gridOverlayService = gridOverlayService;
    }

    @PostMapping("/overlay")
    public FeatureCollection overlayCreator(MultipartFile gridFile) throws IOException {
        return gridOverlayService.generateOverlayForFile(gridFile);
    }
}
