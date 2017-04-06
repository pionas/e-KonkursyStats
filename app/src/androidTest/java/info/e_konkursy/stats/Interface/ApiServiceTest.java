package info.e_konkursy.stats.Interface;

import android.app.Instrumentation;

import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Helpers.RestServiceTestHelper;
import info.e_konkursy.stats.Utils.Constants;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Created by Adrian Pionka on 2017-03-31.
 */
public class ApiServiceTest {
    MockWebServer mockWebServer;
    private Instrumentation.ActivityMonitor monitor;
    @Before
    public void setUp() throws Exception {
        monitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
        monitor.waitForActivityWithTimeout(2000);
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().equals("/articles_last_added")) {
                    return new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile("article_list.json"));
                } else if (request.getPath().equals("/get_top_users")) {
                    return new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile("top_people.json"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        mockWebServer.setDispatcher(dispatcher);

    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
        getInstrumentation().removeMonitor(monitor);
    }


    @Test
    public void getTopUsers() throws Exception {
        URLConnection connection = mockWebServer.url("/get_top_users").url().openConnection();
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/get_top_users", request.getPath());
        assertNull(request.getHeader("Authorization"));
    }

}