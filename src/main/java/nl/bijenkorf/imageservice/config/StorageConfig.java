package nl.bijenkorf.imageservice.config;

import nl.bijenkorf.imageservice.storage.LocalStorageImpl;
import nl.bijenkorf.imageservice.storage.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    public Storage storage()
    {
        return new LocalStorageImpl();
    }
}
