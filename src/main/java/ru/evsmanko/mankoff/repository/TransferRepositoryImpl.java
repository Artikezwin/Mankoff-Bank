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

    @Override
    public List<Transfer> findAll() {
        return jdbcTemplate.query(
                "select * from TRANSFER",
                this::mapRowToTransfer);
    }

    @Override
    public List<Transfer> findTransfersBySenderId(long senderId) {
        List<Transfer> transfers = jdbcTemplate.query(
                "select * from TRANSFER where SENDER_ID=?",
                this::mapRowToTransfer, senderId);
        return transfers;
    }

    @Override
    public Transfer save(Transfer transfer) {

        List<Transfer> transfers = findAll();
        long vremId = 1;
        if (!transfers.isEmpty()) {
            for (int i = 0; i < transfers.size(); i++) {
                if (vremId < transfers.get(i).getId()) {
                    vremId = transfers.get(i).getId();
                }
            }
        }

        jdbcTemplate.update(
                "insert into TRANSFER (ID, RECEIVER_ID, SENDER_ID, SUM, TIMESTAMP) values (?, ?, ?, ?, ?)",
                vremId + 1,
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
