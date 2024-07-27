package Config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheManager {

    @Bean
    public CaffeineCacheManager cacheManager(Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("verificationcodes") ;
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager ;
    }
}
