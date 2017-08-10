package org.snoopdesigns.props;

import javax.annotation.PostConstruct;
import java.util.Set;

import org.snoopdesigns.props.persistence.converters.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;

/**
 * Created by dmmorozo on 10.08.2017.
 */
@Configuration
public class ConverterConfiguration {

    @Autowired(required = false)
    @TypeConverter
    private Set<Converter<?, ?>> autoRegisteredConverters;

    @Autowired(required = false)
    @TypeConverter
    private Set<ConverterFactory<?, ?>> autoRegisteredConverterFactories;

    @Autowired
    private ConverterRegistry converterRegistry;

    @PostConstruct
    public void conversionService() {
        if (autoRegisteredConverters != null) {
            for (Converter<?, ?> converter : autoRegisteredConverters) {
                converterRegistry.addConverter(converter);
            }
        }
        if (autoRegisteredConverterFactories != null) {
            for (ConverterFactory<?, ?> converterFactory : autoRegisteredConverterFactories) {
                converterRegistry.addConverterFactory(converterFactory);
            }
        }
    }
}
