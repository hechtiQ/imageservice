package nl.bijenkorf.imageservice.config;

import nl.bijenkorf.imageservice.model.ImageType;
import nl.bijenkorf.imageservice.model.PredefinedTypes;
import nl.bijenkorf.imageservice.model.ScaleTypes;
import nl.bijenkorf.imageservice.optimization.ImageIOOptimizerImpl;
import nl.bijenkorf.imageservice.optimization.ImageOptimizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ImageConfig {

    @Bean
    public ImageOptimizer imageOptimizer()
    {
        return new ImageIOOptimizerImpl();
    }

    @Bean
    public HashMap<PredefinedTypes, ImageType> predefinedTypes()
    {
        HashMap<PredefinedTypes, ImageType> predefinedTypes = new HashMap<>();
        predefinedTypes.put(PredefinedTypes.THUMBNAIL, new ImageType(50,50, 70, ScaleTypes.SKEW, null, PredefinedTypes.THUMBNAIL));
        predefinedTypes.put(PredefinedTypes.SMALL, new ImageType(250,250, 85, ScaleTypes.FILL, null, PredefinedTypes.SMALL));
        predefinedTypes.put(PredefinedTypes.LARGE, new ImageType(500,500, 90, ScaleTypes.FILL, null, PredefinedTypes.LARGE));
        predefinedTypes.put(PredefinedTypes.ORIGINAL, new ImageType(0,0, 100, ScaleTypes.NONE, null, PredefinedTypes.ORIGINAL));

        predefinedTypes.put(PredefinedTypes.SMALLCROP, new ImageType(250,250, 85, ScaleTypes.CROP, null, PredefinedTypes.SMALLCROP));
        return predefinedTypes;
    }
}
