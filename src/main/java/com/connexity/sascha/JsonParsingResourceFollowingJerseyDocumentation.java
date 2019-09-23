package com.connexity.sascha;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * This class tries to parse JSON that is read via a Jersey client and should ignore unrecognized properties.
 *
 * The implementation follows Jersey documentation as can be found at https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/media.html#json.jackson
 */
public class JsonParsingResourceFollowingJerseyDocumentation {

    private static final Logger LOG = LoggerFactory.getLogger(JsonParsingResourceFollowingJerseyDocumentation.class);

    private static final String SERVICE_URL = "http://localhost:8080/test/getData";

    public DataBean getDataBean() {
        final Client client = ClientBuilder.newBuilder()
                .register(MyObjectMapperProvider.class)  // No need to register this provider if no special configuration is required.
                .register(JacksonFeature.class)
                .build();

        WebTarget target = client.target(SERVICE_URL);
        DataBean serviceResponse = target.request(MediaType.APPLICATION_JSON_TYPE).get(DataBean.class);

        LOG.debug("Found and parsed {}", serviceResponse);

        return serviceResponse;
    }


}
