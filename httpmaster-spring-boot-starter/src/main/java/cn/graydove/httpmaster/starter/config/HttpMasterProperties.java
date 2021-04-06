package cn.graydove.httpmaster.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Data
@Component
@ConfigurationProperties(prefix = "http-master")
public class HttpMasterProperties {
}
