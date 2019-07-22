package service;

import dao.Dao;
import io.grpc.stub.StreamObserver;
import model.BlogPostModel;
import model.UserModel;
import protobuf.DataSummaryServiceGrpc;
import protobuf.GrpcProtos;

import javax.inject.Inject;

public class BlogPostSummaryService extends DataSummaryServiceGrpc.DataSummaryServiceImplBase {

    private final Dao<UserModel> userDao;
    private final Dao<BlogPostModel> blogPostDao;

    @Inject
    public BlogPostSummaryService(final Dao<UserModel> userDao, final Dao<BlogPostModel> blogPostDao) {
        this.userDao = userDao;
        this.blogPostDao = blogPostDao;
    }

    @Override
    public void getSummary(final GrpcProtos.Empty request, final StreamObserver<GrpcProtos.DataSummary> responseObserver) {
        final int userCount = userDao.findAll().size();
        final int blogPostCount = blogPostDao.findAll().size();

        final GrpcProtos.DataSummary result = GrpcProtos.DataSummary.newBuilder()
                .setBlogPostCount(blogPostCount)
                .setUserCount(userCount)
                .build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
