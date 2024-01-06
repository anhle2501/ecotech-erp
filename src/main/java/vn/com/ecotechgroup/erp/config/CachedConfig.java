package vn.com.ecotechgroup.erp.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
public class CachedConfig {
	@Bean
	public JedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("192.168.100.44");
		config.setPort(6379);
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisCacheManager cacheManager(
			RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
				.defaultCacheConfig(
						Thread.currentThread().getContextClassLoader())
				.entryTtl(Duration.ofMinutes(30));
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(redisCacheConfiguration).build();
	}

}
