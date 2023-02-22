package ru.evsmanko.mankoff.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TRANSFER")
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "RECEIVER_ID")
    private Long receiverId;
    @Column(name = "SENDER_ID")
    private Long senderId;
    @Column(name = "SUM")
    private float sum;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

}
