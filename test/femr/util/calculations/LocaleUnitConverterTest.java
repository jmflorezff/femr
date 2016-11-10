package femr.util.calculations;

import femr.utd.tests.BaseTest;
import femr.util.DataStructure.Mapping.VitalMultiMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for LocaleUnitConverter.
 */
public class LocaleUnitConverterTest extends BaseTest {
    @Test
    public void testToMetric() {
        String date = "2016-1-1 00:00:00";
        VitalMultiMap vitalMap = new VitalMultiMap();
        vitalMap.put("temperature", date, 32);
        vitalMap.put("heightFeet", date, 5);
        vitalMap.put("heightInches", date, 1);
        vitalMap.put("weight", date, 100);

        VitalMultiMap convertedMap = LocaleUnitConverter.toMetric(vitalMap);
        assertEquals(new Float(0), Float.valueOf(convertedMap.get("temperatureCelsius", date)));
        assertEquals(new Float(1), Float.valueOf(convertedMap.get("heightMeters", date)));
        assertEquals(new Float(55), Float.valueOf(convertedMap.get("heightCm", date)));
        assertEquals(new Float(45.36F), Float.valueOf(convertedMap.get("weightKgs", date)));
    }

    @Test
    public void testToImperial() {
        Map<String, Float> vitalMap = new HashMap<>();
        vitalMap.put("temperature", 0F);
        vitalMap.put("weight", 80F);
        vitalMap.put("heightFeet", 1F);
        vitalMap.put("heightInches", 55F);

        Map<String, Float> converted = LocaleUnitConverter.toImperial(vitalMap);

        assertEquals(new Float(32F), converted.get("temperature"));
        assertTrue(Math.abs(176.37F - converted.get("weight")) < 0.01D);
        assertTrue(Math.abs(5F - converted.get("heightFeet")) < 0.1D);
        assertTrue(Math.abs(1F - converted.get("heightInches")) < 0.1D);
    }
}