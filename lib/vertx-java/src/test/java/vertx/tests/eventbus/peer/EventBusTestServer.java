package vertx.tests.eventbus.peer;

import org.apache.thrift.server.TEventBusServer;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.testframework.TestClientBase;

import tutorial.Calculator;
import tutorial.handler.CalculatorAsyncHandler;
import tutorial.handler.CalculatorHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EventBusTestServer extends TestClientBase {

  private static final String address = "calculator_service";
  
  private TEventBusServer server = null;

  private Handler<AsyncResult<Void>> serveCompletionHandler = new Handler<AsyncResult<Void>>() {
    @Override
    public void handle(AsyncResult<Void> event) {
      if (event.succeeded()) {
        tu.testComplete();
      } else {
        event.cause().printStackTrace();
        tu.azzert(false, "Failed to start server");
      }
    }
  };

  @Override
  public void start() {
    super.start();
    tu.appReady();
  }

  public void testSyncProcessorInitialize() {
    CalculatorHandler handler = new CalculatorHandler();
    Calculator.Processor processor = new Calculator.Processor(handler);
    TEventBusServer.Args args = new TEventBusServer.Args(vertx, address);
    args.processor(processor)
        .serveCompletionHandler(serveCompletionHandler);
    server = new TEventBusServer(args);
    server.serve();
  }

  public void testSyncProcessorOnNonWorker() {
    CalculatorHandler handler = new CalculatorHandler();
    Calculator.Processor processor = new Calculator.Processor(handler);
    TEventBusServer.Args args = new TEventBusServer.Args(vertx, address);
    args.processor(processor)
        .serveCompletionHandler(serveCompletionHandler);
    server = new TEventBusServer(args);
    try {
      server.serve();
      tu.azzert(false, "Synchronized processors should not run on a EventLoop thread.");
      System.out.println("testOnNonWorker failed.");
    } catch (Exception e) {
      tu.azzert(e instanceof IllegalStateException);
      tu.testComplete();
      System.out.println("testOnNonWorker succeeded.");
    }
  }

  public void testAsyncProcessorInitialize() {
    CalculatorAsyncHandler handler = new CalculatorAsyncHandler();
    Calculator.AsyncProcessor processor = new Calculator.AsyncProcessor(handler);
    TEventBusServer.Args args = new TEventBusServer.Args(vertx, address);
    args.processor(processor)
        .serveCompletionHandler(serveCompletionHandler);
    server = new TEventBusServer(args);
    server.serve();
  }

}
