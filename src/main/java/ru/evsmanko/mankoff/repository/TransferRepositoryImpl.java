package ru.evsmanko.mankoff.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.evsmanko.mankoff.entity.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransferRepositoryImpl implements TransferRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_ALL_FROM_TABLE = "select * from TRANSFER";
    private final String SELECT_FROM_TABLE_WHERE_SENDER_ID = "select * from TRANSFER where SENDER_ID=?";
    private final String INSERT_INTO_TABLE = "insert into TRANSFER (RECEIVER_ID, SENDER_ID, SUM, TIMESTAMP) values (?, ?, ?, ?)";

    @Override
    public List<Transfer> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL_FROM_TABLE,
                this::mapRowToTransfer);
    }

    @Override
    public List<Transfer> findTransfersBySenderId(long senderId) {
        List<Transfer> transfers = jdbcTemplate.query(
                SELECT_FROM_TABLE_WHERE_SENDER_ID,
                this::mapRowToTransfer, senderId);
        return transfers;
    }

    @Override
    public Transfer save(Transfer transfer) {
        jdbcTemplate.update(
                INSERT_INTO_TABLE,
                transfer.getReceiverId(),
                transfer.getSenderId(),
                transfer.getSum(),
                transfer.getTimestamp().toString());
        return transfer;
    }

    private Transfer mapRowToTransfer(ResultSet row, int rowNum) throws SQLException {
        return new Transfer(
                row.getLong("ID"),
                row.getLong("RECEIVER_ID"),
                row.getLong("SENDER_ID"),
                row.getFloat("SUM"),
                row.getTimestamp("TIMESTAMP"));
    }
}
