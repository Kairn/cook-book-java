package io.esoma.cbj.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class ResourceLoaderTest {

    private static final String EXAMPLE_FILE = "example.txt";
    private static final String INNER_EXAMPLE_FILE = "example/example-2.txt";
    private static final String NON_EXISTENT_FILE = "nosuchfile.txt";

    @Test
    void testGetExampleFile() {
        InputStream inputStream = ResourceLoader.getResourceAsReader(EXAMPLE_FILE);
        assertNotNull(inputStream);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            assertEquals("2022", br.readLine());
            assertEquals("EOF", br.readLine());
            assertNull(br.readLine());
        } catch (Exception e) {
            Logger.error("Exception encountered while reading example file");
            Logger.error(e);
            fail();
        }
    }

    @Test
    void testGetExampleFileFromInnerDirectory() {
        InputStream inputStream = ResourceLoader.getResourceAsReader(INNER_EXAMPLE_FILE);
        assertNotNull(inputStream);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            assertEquals("YET another example.", br.readLine());
            assertNull(br.readLine());
        } catch (Exception e) {
            Logger.error("Exception encountered while reading example-2 file");
            Logger.error(e);
            fail();
        }
    }

    @Test
    void testGetNonExistentFile() {
        assertThrowsExactly(
                IllegalArgumentException.class, () -> ResourceLoader.getResourceAsReader(NON_EXISTENT_FILE));
    }
}
