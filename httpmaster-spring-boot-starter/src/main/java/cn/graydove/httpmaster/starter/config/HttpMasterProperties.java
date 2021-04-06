package cn.graydove.httpmaster.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Data
@Component
@ConfigurationProperties(prefix = ConfigConstant.PROPERTIES_PREFIX)
public class HttpMasterProperties {

    private String engine = ConfigConstant.Engine.HTTP_CLIENT;
}
