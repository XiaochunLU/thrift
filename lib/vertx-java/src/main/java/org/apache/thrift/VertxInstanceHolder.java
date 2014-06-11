package org.apache.thrift;

import org.vertx.java.core.Vertx;

public class VertxInstanceHolder {

  private static Vertx vertx_;

  public static void set(Vertx vertx) {
    vertx_ = vertx;
  }

  public static Vertx get() {
    if (vertx_ == null)
      throw new IllegalStateException("Vert.x instance is not set.");
    return vertx_;
  }

}
