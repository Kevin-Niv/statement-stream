package com.meraki.statementstreamkafka.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "statement_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recId;
    private String xmlRecord;

}
