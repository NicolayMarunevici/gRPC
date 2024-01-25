package com.technical.store;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws IOException, InterruptedException {
    Server server = ServerBuilder.forPort(1010).addService(new GreetingServiceImpl()).build();

    server.start();

    System.out.println("Server started");

    server.awaitTermination(); // метод будет работать пока сервер будет работать
  }
}
