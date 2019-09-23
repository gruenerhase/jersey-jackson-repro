package com.connexity.sascha;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class JsonParsingResource {

    private static final Logger LOG = LoggerFactory.getLogger(JsonParsingResource.class);

    private static final String SERVICE_URL = "http://localhost:8080/test/getData";

    public DataBean getDataBean() {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final JerseyClient jerseyClient = (JerseyClient) ClientBuilder.newClient().register(jacksonJsonProvider);

        JerseyWebTarget target = jerseyClient.target(SERVICE_URL);
        DataBean serviceResponse = target.request(MediaType.APPLICATION_JSON_TYPE).get(DataBean.class);

        LOG.debug("Found and parsed {}", serviceResponse);

        return serviceResponse;
    }


}
