package com.example.entrylistServer.repository;

import com.example.entrylistServer.entity.VisitantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<VisitantEntity, Integer> {
}
