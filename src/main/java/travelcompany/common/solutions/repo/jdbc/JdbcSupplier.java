package travelcompany.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcSupplier<T> {
    T get() throws Exception;
}
