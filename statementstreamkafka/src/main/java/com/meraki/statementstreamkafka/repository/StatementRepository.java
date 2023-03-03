package com.meraki.statementstreamkafka.repository;

import com.meraki.statementstreamkafka.entity.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatementRepository extends JpaRepository<StatementEntity, Long> {
    public Optional<StatementEntity> findByRecId(String recId);

}
