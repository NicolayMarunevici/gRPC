package com.technical.store;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  // 1 параметр это request, 2 параметр это response. мы значение не возвращаем, потому что на каждом response у нас идет вызов метода и идет очередной response на клиента
  // благодаря StreamObserver мы можем в возвращаемые данные получить любое количество HelloResponse
  @Override
  public void greeting(GreetingServiceOuterClass.HelloRequest request,
                       StreamObserver<GreetingServiceOuterClass.HelloResponse> responseStream) {

    for (int i = 0; i < 10000; i++) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      GreetingServiceOuterClass.HelloResponse response =
          GreetingServiceOuterClass.HelloResponse.newBuilder()
              .setGreeting("Hello from server, " + request.getName())
              .build();

      responseStream.onNext(response); // заполняем response
    }

    responseStream.onCompleted(); // показываем, что мы не будем больше пересылать данные
  }
}
