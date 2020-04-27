/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import com.powsybl.iidm.import_.Importers;
import com.powsybl.iidm.network.Network;
import org.geojson.FeatureCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * @author Sebastien Murgey {@literal <sebastien@murgey.net>}
 */
@Service
public class GridOverlayService {
    private final CountriesGeometryLoader countriesGeometryLoader;

    public GridOverlayService(CountriesGeometryLoader countriesGeometryLoader) {
        this.countriesGeometryLoader = countriesGeometryLoader;
    }

    public FeatureCollection generateOverlayForFile(MultipartFile gridFile) throws IOException {
        Network network = loadGridFile(gridFile);
        FeatureCollection featureCollection = new FeatureCollection();
        featureCollection.addAll(
                network.getCountries().stream()
                        .map(CountryIsoMapper::countryToIso3CountryCode)
                        .map(isoA3Code -> countriesGeometryLoader.getCountriesGeometry().get(isoA3Code))
                        .collect(Collectors.toList())
        );
        return featureCollection;
    }

    /**
     * Load input multipart file into a network object.
     * Save the input file into a temporary directory to allow usage of
     * automatic datasource creation in PowSyBl.
     *
     * @param gridFile input grid multipart file
     * @return imported Network object
     */
    private Network loadGridFile(MultipartFile gridFile) {
        try {
            Path tempDir = Files.createTempDirectory("grid-overlay-provider");
            Path tempFile = Path.of(tempDir.toString(), gridFile.getOriginalFilename());
            gridFile.transferTo(tempFile);
            Network network = Importers.loadNetwork(tempFile);
            Files.delete(tempFile);
            Files.delete(tempDir);
            return network;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
