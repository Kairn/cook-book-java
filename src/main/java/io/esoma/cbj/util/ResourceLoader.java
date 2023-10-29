package io.esoma.cbj.util;

import java.io.InputStream;
import java.util.Objects;

/**
 * Utility class for retrieving resource files in this project.
 *
 * @author Eddy Soma
 */
public class ResourceLoader {

    private static final ClassLoader CONTEXT_CLASS_LOADER =
            Thread.currentThread().getContextClassLoader();

    private ResourceLoader() {}

    /**
     * Fetches the resource file denoted by the given path and returns a {@link InputStream} for
     * reading. Exception will be thrown if the resource cannot be retrieved.
     *
     * @param resourceName the path to the resource file from the resource root
     * @return an input stream for the resource
     */
    public static InputStream getResourceAsReader(String resourceName) {
        Objects.requireNonNull(resourceName, "Resource name must not be null");

        InputStream inputStream = CONTEXT_CLASS_LOADER.getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Given resource name is invalid");
        }

        return inputStream;
    }
}
