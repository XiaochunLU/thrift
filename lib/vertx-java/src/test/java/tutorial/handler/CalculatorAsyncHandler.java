package tutorial.handler;
import java.util.HashMap;

import org.apache.thrift.TException;
import org.vertx.java.core.Future;

import shared.SharedStruct;
import tutorial.Calculator;
import tutorial.InvalidOperation;
import tutorial.Work;

public class CalculatorAsyncHandler implements Calculator.AsyncIface {

  private HashMap<Integer,SharedStruct> log;

  public CalculatorAsyncHandler() {
    log = new HashMap<Integer, SharedStruct>();
    System.out.println("CalculatorAsyncHandler initialized");
  }
  
  @Override
  public void getStruct(int key, Future<SharedStruct> future) throws TException {
    System.out.println("getStruct(" + key + ")");
    future.setResult(log.get(key));
  }

  @Override
  public void ping(Future<Void> future) throws TException {
    System.out.println("ping()");
    future.setResult(null);
  }

  @Override
  public void add(int n1, int n2, Future<Integer> future) throws TException {
    System.out.println("add(" + n1 + "," + n2 + ")");
    future.setResult(n1 + n2);
  }

  @Override
  public void calculate(int logid, Work work, Future<Integer> future) throws TException {
    System.out.println("calculate(" + logid + ", {" + work.op + "," + work.num1 + "," + work.num2 + "})");
    int val = 0;
    switch (work.op) {
    case ADD:
      val = work.num1 + work.num2;
      break;
    case SUBTRACT:
      val = work.num1 - work.num2;
      break;
    case MULTIPLY:
      val = work.num1 * work.num2;
      break;
    case DIVIDE:
      if (work.num2 == 0) {
        InvalidOperation io = new InvalidOperation();
        io.what = work.op.getValue();
        io.why = "Cannot divide by 0";
        future.setFailure(io);
        return;
      }
      val = work.num1 / work.num2;
      break;
    default:
      InvalidOperation io = new InvalidOperation();
      io.what = work.op.getValue();
      io.why = "Unknown operation";
      future.setFailure(io);
      return;
    }

    SharedStruct entry = new SharedStruct();
    entry.key = logid;
    entry.value = Integer.toString(val);
    log.put(logid, entry);

    future.setResult(val);
  }

  @Override
  public void zip(Future<Void> future) throws TException {
    System.out.println("zip()");
    future.setResult(null);
  }

}
