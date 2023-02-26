package ru.evsmanko.mankoff.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.evsmanko.mankoff.entity.PaymentEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final RowMapper<PaymentEntity> rowMapper = (rs, rowNum) -> {
        PaymentEntity payment = new PaymentEntity();
        payment.setId(rs.getLong("id"));
        payment.setShopperId(rs.getLong("shopper_id"));
        payment.setMCC(rs.getLong("mcc"));
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setTimeStamp(rs.getTimestamp("timestamp"));
        return payment;
    };
    private static final String GET_PAYMENTS_BY_SHOPPER_ID = "SELECT *" +
            " FROM payments" +
            " WHERE shopper_id = :id";

    private static final String GET_ALL_PAYMENTS = "SELECT *" +
            " FROM payments";

    private static final String SAVE_PAYMENT = "INSERT INTO payments (id, shopper_id, mcc, amount, timestamp)" +
            "VALUES(:id, :shopper_id, :mcc, :amount, :timestamp)";

    @Override
    public List<PaymentEntity> findAll() {
        return parameterJdbcTemplate.query(GET_ALL_PAYMENTS, rowMapper);
    }

    @Override
    public List<PaymentEntity> findAllByShopperId(long shopperId) {
        return parameterJdbcTemplate.query(GET_PAYMENTS_BY_SHOPPER_ID,
                new MapSqlParameterSource("shopper_id", shopperId), rowMapper);
    }

    @Override
    public PaymentEntity save(PaymentEntity payment) {
        long id = parameterJdbcTemplate.query(GET_ALL_PAYMENTS, rowMapper)
                .stream()
                .map(PaymentEntity::getId)
                .max(Long::compareTo)
                .orElse(0L);
        parameterJdbcTemplate.update(SAVE_PAYMENT, new MapSqlParameterSource()
                .addValue("id", ++id)
                .addValue("shopper_id", payment.getShopperId())
                .addValue("mcc", payment.getMCC())
                .addValue("amount", payment.getAmount())
                .addValue("timestamp", payment.getTimeStamp()));
        payment.setId(id);
        return payment;
    }
}

