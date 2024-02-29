package mockserver.com.gateway.app;

import com.gateway.app.provider.CommunicationClient;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MockServer {

    @Autowired
    protected com.gateway.app.provider.horoscopeai.Client client;

    protected ClientAndServer mockServer;

    @Autowired
    protected CommunicationClient communicationClient;

    @Value("${local.mock.server.testing.port}")
    protected int port_testing;

    @Value("${local.mock.server.testing.host}")
    protected String host_testing;

    @Value("${horoscopeai.user}")
    protected String user;

    @Value("${horoscopeai.password}")
    protected String password;

    @Value("${local.mock.server.testing.host}#{':'}${local.mock.server.testing.port}")
    protected String baseUrl;
}
