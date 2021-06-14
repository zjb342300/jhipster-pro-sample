package com.mycompany.myapp.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName() + ".viewPermissions");
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName() + ".users");
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName() + ".apiPermissions");
            createCache(cm, com.mycompany.myapp.domain.CommonString.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonTableField.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonFloat.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OssConfig.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonDouble.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ApiPermission.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ApiPermission.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.ApiPermission.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.CommonExtData.class.getName());
            createCache(cm, com.mycompany.myapp.domain.UploadImage.class.getName());
            createCache(cm, com.mycompany.myapp.domain.SmsConfig.class.getName());
            createCache(cm, com.mycompany.myapp.domain.UReportFile.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Department.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Department.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.Department.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.Department.class.getName() + ".users");
            createCache(cm, com.mycompany.myapp.domain.StatisticsApi.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BusinessType.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonZonedDateTime.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonTableRelationship.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonBoolean.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonBigDecimal.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonLong.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonLocalDate.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ViewPermission.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ViewPermission.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.ViewPermission.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.CommonConditionItem.class.getName());
            createCache(cm, com.mycompany.myapp.domain.AdministrativeDivision.class.getName());
            createCache(cm, com.mycompany.myapp.domain.AdministrativeDivision.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.CommonTable.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonTable.class.getName() + ".commonTableFields");
            createCache(cm, com.mycompany.myapp.domain.CommonTable.class.getName() + ".relationships");
            createCache(cm, com.mycompany.myapp.domain.CommonTextBlob.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonInteger.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ResourceCategory.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ResourceCategory.class.getName() + ".files");
            createCache(cm, com.mycompany.myapp.domain.ResourceCategory.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.ResourceCategory.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.UploadFile.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Position.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Position.class.getName() + ".users");
            createCache(cm, com.mycompany.myapp.domain.DataDictionary.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DataDictionary.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.CommonCondition.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CommonCondition.class.getName() + ".items");
            createCache(cm, com.mycompany.myapp.domain.GpsInfo.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
