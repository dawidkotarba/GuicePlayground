import com.google.inject.Guice;
import com.google.inject.Injector;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.BlogPostSummaryService;
import service.ServiceModule;

import java.io.IOException;

public class GrpcServer {

    public static void main(final String[] args) throws InterruptedException, IOException {
        final Injector injector = Guice.createInjector(new ServiceModule());
        final BlogPostSummaryService blogPostSummaryService = injector.getInstance(BlogPostSummaryService.class);
        startGrpcServer(blogPostSummaryService);
    }

    private static void startGrpcServer(final BlogPostSummaryService service) throws IOException, InterruptedException {
        final Server server = ServerBuilder.forPort(Constants.SERVER_CLIENT_PORT).addService(service).build();
        server.start();
        server.awaitTermination();
    }
}
