package com.sns.invest.post.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`like`") // "like" is a reserved keyword in SQL, hence the backticks
@Getter
@Setter
public class Like {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false, length = 16)
    private String type;

    @Column(name = "postId", nullable = false)
    private int postId;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "createdAt", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
}
