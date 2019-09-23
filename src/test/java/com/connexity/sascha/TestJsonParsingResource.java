package com.connexity.sascha;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class TestJsonParsingResource {

    private static final String JSON_RESPONSE_WITH_UNRECOGNIZED_PROPERTY = "{\"knownProperty1\": \"value1\", \"knownProperty2\": \"value2\", \"unknownProperty\": \"dummy\"}";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    private JsonParsingResource jsonParsingResource;

    @Before
    public void before() {
        stubFor(get(urlEqualTo("/test/getData"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(JSON_RESPONSE_WITH_UNRECOGNIZED_PROPERTY)));
        jsonParsingResource = new JsonParsingResource();
    }

    @Test
    public void testGetDataBean() {
        DataBean dataBean = jsonParsingResource.getDataBean();

        Assert.assertNotNull(dataBean);
        Assert.assertEquals("value1", dataBean.getKnownProperty1());
        Assert.assertEquals("value2", dataBean.getKnownProperty2());
    }

}
