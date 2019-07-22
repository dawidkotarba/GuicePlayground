import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import protobuf.DataSummaryServiceGrpc;
import protobuf.GrpcProtos;

public class GrpcClient {

    public static void main(final String[] args) {
        createClient();
    }

    private static void createClient() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", Constants.SERVER_CLIENT_PORT)
                .usePlaintext()
                .build();

        final DataSummaryServiceGrpc.DataSummaryServiceBlockingStub stub = DataSummaryServiceGrpc.newBlockingStub(channel);
        final GrpcProtos.DataSummary summary = stub.getSummary(GrpcProtos.Empty.newBuilder().build()).next();

        System.out.println("Data summary: blog post count: " + summary.getBlogPostCount() + ", user count: " + summary.getUserCount());
        channel.shutdown();
    }
}
