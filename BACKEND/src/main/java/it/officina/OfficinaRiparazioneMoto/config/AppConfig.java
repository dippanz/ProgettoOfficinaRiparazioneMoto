package it.officina.OfficinaRiparazioneMoto.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig is a configuration class that defines application-wide beans.
 * <p>
 * It provides a ModelMapper bean configured to map only strictly matching
 * fields,
 * and with null values being skipped during the mapping process.
 * </p>
 */
@Configuration
public class AppConfig {

    /**
     * Creates and returns a ModelMapper bean.
     * <p>
     * The returned ModelMapper is configured with:
     * <ul>
     * <li>A strict matching strategy, ensuring only fields with exactly the same
     * names are mapped.</li>
     * <li>Skip-null enabled, preventing null values in the source from overwriting
     * non-null values in the destination.</li>
     * </ul>
     * </p>
     *
     * @return a configured instance of ModelMapper
     */
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // mappa solo i campi strettamente uguali
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }
}
