package info.e_konkursy.stats.Interface;

import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.e_konkursy.stats.Utils.RestServiceTestHelper;

import static org.junit.Assert.*;

/**
 * Created by Adrian Pionka on 2017-03-31.
 */
public class ApiServiceTest {
    MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("articles_last_added")) {
                    return new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile("article_list.json"));
                } else if (request.getPath().equals("get_top_users")) {
                    return new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile("top_people.json"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void getLastAdded() throws Exception {
        RecordedRequest request1 = server.takeRequest();
        assertEquals("/articles_last_added/", request1.getPath());
        assertNotNull(request1.getHeader("Authorization"));
    }

    @Test
    public void getTopUsers() throws Exception {
        server.url("/get_top_users");
        RecordedRequest request = server.takeRequest();
        assertEquals("/get_top_users/", request.getPath());
        assertNotNull(request.getHeader("Authorization"));
    }

    @Test
    public void sendMessage() throws Exception {

    }

}