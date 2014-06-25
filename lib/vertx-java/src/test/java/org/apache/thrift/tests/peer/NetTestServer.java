package org.apache.thrift.tests.peer;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TNetServer;
import org.vertx.java.core.Handler;
import org.vertx.java.testframework.TestClientBase;

import tutorial.Calculator;
import tutorial.handler.CalculatorAsyncHandler;
import tutorial.handler.CalculatorHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NetTestServer extends TestClientBase {

  private static final int port = 9990;

  @Override
  public void start() {
    super.start();
    tu.appReady();
  }

  public void testBinaryProtocolInitialize() {
    CalculatorHandler handler = new CalculatorHandler();
    Calculator.Processor processor = new Calculator.Processor(handler);
    TNetServer.Args args = new TNetServer.Args(vertx, port);
    args.processor(processor)
        .protocolFactory(new TBinaryProtocol.Factory());
    TNetServer server = new TNetServer(args);
    server.serve();
    vertx.setTimer(500, new Handler<Long>() {
      @Override
      public void handle(Long event) {
        tu.testComplete();
      }
    });
  }

  public void testCompactProtocolInitialize() {
    CalculatorAsyncHandler handler = new CalculatorAsyncHandler();
    Calculator.AsyncProcessor processor = new Calculator.AsyncProcessor(handler);
    TNetServer.Args args = new TNetServer.Args(vertx, port);
    args.processor(processor)
        .protocolFactory(new TCompactProtocol.Factory());
    TNetServer server = new TNetServer(args);
    server.serve();
    vertx.setTimer(500, new Handler<Long>() {
      @Override
      public void handle(Long event) {
        tu.testComplete();
      }
    });
  }

  public void testJSONProtocolInitialize() {
    CalculatorAsyncHandler handler = new CalculatorAsyncHandler();
    Calculator.AsyncProcessor processor = new Calculator.AsyncProcessor(handler);
    TNetServer.Args args = new TNetServer.Args(vertx, port);
    args.processor(processor)
        .protocolFactory(new TJSONProtocol.Factory());
    TNetServer server = new TNetServer(args);
    server.serve();
    vertx.setTimer(500, new Handler<Long>() {
      @Override
      public void handle(Long event) {
        tu.testComplete();
      }
    });
  }
}
