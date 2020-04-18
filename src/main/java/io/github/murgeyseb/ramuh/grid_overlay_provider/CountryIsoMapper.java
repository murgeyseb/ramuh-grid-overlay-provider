/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package io.github.murgeyseb.ramuh.grid_overlay_provider;

import com.powsybl.iidm.network.Country;

import java.util.Locale;

/**
 * Utility class used for conversion between IIDM country enum and
 * and ISO3 country code
 *
 * @author Sebastien Murgey {@literal <sebastien@murgey.net>}
 */
public final class CountryIsoMapper {

    private CountryIsoMapper() {
        throw new IllegalStateException("Utility class are not meant to be instantiated");
    }
    /**
     * Convert IIDM country to the equivalent ISO3 country code.
     *
     * Thanks to https://blog.oio.de/2010/12/31/mapping-iso2-and-iso3-country-codes-with-java/ for the trick
     * @param iidmCountry IIDM country to convert
     * @return ISO3 country code of the input country
     */
    public static String countryToIso3CountryCode(Country iidmCountry){
        Locale locale = new Locale("", iidmCountry.toString());
        return locale.getISO3Country();
    }
}
