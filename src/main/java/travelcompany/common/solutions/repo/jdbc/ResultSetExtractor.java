package travelcompany.common.solutions.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<EXTRACT_TO> {
    EXTRACT_TO extract(ResultSet rs) throws SQLException;
}
