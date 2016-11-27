package jp.supership.stuka.service

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Promise

/**
 * Created by y-nomura on 2016/11/27.
 */
class HogeServiceVerticle extends ScalaVerticle {

  override def start(startPromise: Promise[Unit]): Unit = {
    println("svasvad")
  }
}
