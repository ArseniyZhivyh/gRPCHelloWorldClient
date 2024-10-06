package org.ars;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;

import java.util.Iterator;

public class Client {

    public static void main() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder().setName("Ars").build();

        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);

        while(response.hasNext())
            System.out.println(response.next());

        channel.shutdownNow();
    }
}
