package travelcompany.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcConsumer<T> {
    void consume(T t) throws Exception;
}
