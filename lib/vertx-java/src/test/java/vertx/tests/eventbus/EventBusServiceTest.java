package vertx.tests.eventbus;

import org.junit.Test;
import org.vertx.java.testframework.TestBase;

import vertx.tests.eventbus.peer.EventBusTestClient;
import vertx.tests.eventbus.peer.EventBusTestServer;

public class EventBusServiceTest extends TestBase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testSyncProcessor() {
    String serverDeployId = "", clientDeployId = "";
    try {
      serverDeployId = startApp(true, EventBusTestServer.class.getName());
      clientDeployId = startApp(EventBusTestClient.class.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    startTest("testSyncProcessorInitialize");
    startTest("testNormalScenaro");

    try {
      stopApp(serverDeployId);
      stopApp(clientDeployId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSyncProcessorOnNonWorker() {
    String serverDeployId = "";
    try {
      serverDeployId = startApp(false, EventBusTestServer.class.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    startTest("testSyncProcessorOnNonWorker");
    
    try {
      stopApp(serverDeployId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAsyncProcessor() {
    String serverDeployId = "", clientDeployId = "";
    try {
      serverDeployId = startApp(EventBusTestServer.class.getName());
      clientDeployId = startApp(EventBusTestClient.class.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    startTest("testAsyncProcessorInitialize");
    startTest("testNormalScenaro");

    try {
      stopApp(serverDeployId);
      stopApp(clientDeployId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
