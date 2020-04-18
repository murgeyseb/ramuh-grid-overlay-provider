/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import com.powsybl.iidm.network.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryIsoMapperTest {
    @Test
    public void checkCorrectConversionOfSomeCountries() {
        assertEquals("CHN", CountryIsoMapper.countryToIso3CountryCode(Country.CN));
        assertEquals("DEU", CountryIsoMapper.countryToIso3CountryCode(Country.DE));
        assertEquals("FRA", CountryIsoMapper.countryToIso3CountryCode(Country.FR));
        assertEquals("USA", CountryIsoMapper.countryToIso3CountryCode(Country.US));
    }

    @Test
    public void checkThatAnyIidmCountryWouldWork() {
        for (Country country: Country.values()) {
            assertEquals(3, CountryIsoMapper.countryToIso3CountryCode(country).length());
        }
    }
}