package ru.evsmanko.mankoff.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Target;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TRANSFER")
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
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
