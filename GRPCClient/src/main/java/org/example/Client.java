package org.example;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;

public class Client {
  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:1010")
        .usePlaintext().build();

    // STUB сделает запрос по сети и вызовет метод greeting из GRPCService проекта
    GreetingServiceGrpc.GreetingServiceBlockingStub stub =
        GreetingServiceGrpc.newBlockingStub(channel);

    GreetingServiceOuterClass.HelloRequest request =
        GreetingServiceOuterClass.HelloRequest.newBuilder().setName("Nicolay")
            .build();

    // если много объектов response, то должен быть Iterator
    Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request); // это и есть RPC

    while(response.hasNext()) {
      System.out.println(response.next());
    }
    channel.shutdownNow();
  }
}
