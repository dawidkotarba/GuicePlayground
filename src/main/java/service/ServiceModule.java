package service;


import com.google.inject.AbstractModule;
import dao.DaoModule;
import protobuf.DataSummaryServiceGrpc;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new DaoModule());
        bind(DataSummaryServiceGrpc.DataSummaryServiceImplBase.class).to(BlogPostSummaryService.class);
    }
}
