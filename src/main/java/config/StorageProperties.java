package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ConfigurationProperties("storage")
@EnableConfigurationProperties(StorageProperties.class)
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";
    /**
     * Max File Size
     */
    private long maxFileSize = 52428800;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}

