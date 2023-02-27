package ru.evsmanko.mankoff.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransferDTO {
    private Long id;
    private Long receiverId;
    private Long senderId;
    private float sum;
    private Timestamp timestamp;
}
