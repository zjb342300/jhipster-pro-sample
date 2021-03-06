package com.mycompany.myapp.oss.builder;

import com.mycompany.myapp.config.ApplicationProperties;
import com.mycompany.myapp.domain.OssConfig;
import com.mycompany.myapp.domain.enumeration.OssProvider;
import com.mycompany.myapp.oss.BladeOssRule;
import com.mycompany.myapp.oss.OssRule;
import com.mycompany.myapp.oss.OssTemplate;
import com.mycompany.myapp.service.OssConfigService;
import com.mycompany.myapp.service.dto.OssConfigDTO;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.util.ObjectUtils;

/**
 * Oss云存储统一构建类
 *
 */
public class OssBuilder {

    public static final String OSS_CODE = "oss:code:";
    public static final String OSS_PARAM_KEY = "code";

    private final ApplicationProperties applicationProperties;
    private final OssConfigService ossConfigService;

    public OssBuilder(ApplicationProperties applicationProperties, OssConfigService ossConfigService) {
        this.applicationProperties = applicationProperties;
        this.ossConfigService = ossConfigService;
    }

    /**
     * OssTemplate配置缓存池
     */
    private final Map<String, OssTemplate> templatePool = new ConcurrentHashMap<>();

    /**
     * oss配置缓存池
     */
    private final Map<String, OssConfig> ossPool = new ConcurrentHashMap<>();

    /**
     * 获取template
     *
     * @return OssTemplate
     */
    public OssTemplate template() throws InvalidPortException, InvalidEndpointException {
        return template("");
    }

    /**
     * 获取template
     *
     * @param code 资源编号
     * @return OssTemplate
     */
    public OssTemplate template(String code) throws InvalidPortException, InvalidEndpointException {
        //		String tenantId = AuthUtil.getTenantId();
        OssConfig oss = getOss(code);
        OssConfig ossCached = ossPool.get("admin-oss");
        OssTemplate template = templatePool.get("admin-ossTemplate");
        // 若为空或者不一致，则重新加载
        if (
            ObjectUtils.isEmpty(template) ||
            ObjectUtils.isEmpty(ossCached) ||
            !oss.getEndpoint().equals(ossCached.getEndpoint()) ||
            !oss.getAccessKey().equals(ossCached.getAccessKey())
        ) {
            synchronized (OssBuilder.class) {
                template = templatePool.get("admin-ossTemplate");
                if (
                    ObjectUtils.isEmpty(template) ||
                    ObjectUtils.isEmpty(ossCached) ||
                    !oss.getEndpoint().equals(ossCached.getEndpoint()) ||
                    !oss.getAccessKey().equals(ossCached.getAccessKey())
                ) {
                    OssRule ossRule;
                    // 若采用默认设置则开启多租户模式, 若是用户自定义oss则不开启
                    if (
                        oss.getEndpoint().equals(applicationProperties.getOss().getEndpoint()) &&
                        oss.getAccessKey().equals(applicationProperties.getOss().getAccessKey()) &&
                        applicationProperties.getOss().getTenantMode()
                    ) {
                        ossRule = new BladeOssRule(Boolean.TRUE);
                    } else {
                        ossRule = new BladeOssRule(Boolean.FALSE);
                    }
                    if (oss.getProvider() == OssProvider.MINIO) {
                        template = MinioOssBuilder.template(oss, ossRule);
                    } else if (oss.getProvider() == OssProvider.QINIU) {
                        template = QiniuOssBuilder.template(oss, ossRule);
                    } else if (oss.getProvider() == OssProvider.ALI) {
                        template = AliOssBuilder.template(oss, ossRule);
                    } else if (oss.getProvider() == OssProvider.TENCENT) {
                        template = TencentOssBuilder.template(oss, ossRule);
                    } else {
                        template = LocalOssBuilder.template(oss, ossRule);
                    }
                    templatePool.put("admin-ossTemplate", template);
                    ossPool.put("admin-oss", oss);
                }
            }
        }
        return template;
    }

    /**
     * 获取对象存储实体
     *
     * @return Oss
     */
    public OssConfig getOss(String ossCode) {
        OssConfig example = new OssConfig();
        // 获取传参的资源编号并查询，若有则返回，若没有则调启用的配置
        String key = "";
        // String ossCode = StringUtils.isBlank(code) ? WebUtil.getParameter(OSS_PARAM_KEY) : code;
        if (StringUtils.isNotBlank(ossCode)) {
            key = key.concat("-").concat(ossCode);
            example.ossCode(ossCode);
        } else {
            example.enabled(true);
        }
        Optional<OssConfigDTO> oss = ossConfigService.findOneByExample(Example.of(example));
        OssConfig result = new OssConfig();
        if (!oss.isPresent()) {
            result.setId(0L);
            result.setProvider(OssProvider.valueOf(StringUtils.upperCase(applicationProperties.getOss().getName())));
            result.setEndpoint(applicationProperties.getOss().getEndpoint());
            result.setBucketName(applicationProperties.getOss().getBucketName());
            result.setAccessKey(applicationProperties.getOss().getAccessKey());
            result.setSecretKey(applicationProperties.getOss().getSecretKey());
        } else {
            result.setId(oss.get().getId());
            result.setProvider(oss.get().getProvider());
            result.setEndpoint(oss.get().getEndpoint());
            result.setBucketName(oss.get().getBucketName());
            result.setAccessKey(oss.get().getAccessKey());
            result.setSecretKey(oss.get().getSecretKey());
        }
        return result;
    }
}
