package ru.evsmanko.mankoff.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "transfer")
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;
    private Long receiverId;
    private Long senderId;
    private float sum;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

}
