package site.weic.lark.connection;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ZhangWei
 * @since 2023-01-05  23:33
 */
@Getter
@Setter
@Slf4j
public class LarkConnection implements AutoCloseable {
    final ConnectionDB connectionDB;
    final Connection connection;

    public LarkConnection(ConnectionDB connectionDB, Connection connection) {
        this.connectionDB = connectionDB;
        this.connection = connection;
    }

    boolean closed = false;
    boolean expired = false;

    @Override
    public void close() {
        this.closed = true;
        try {
            if (connection.isClosed()) {
                return;
            }
            connection.close();
        } catch (SQLException e) {
            // to log
            log.warn("close LarkConnection error", e);
        }
    }
}
