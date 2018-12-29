package com.ygsoft.kpiviewer.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ygsoft.kpiviewer.entity.ServerOfflineLog;

@Repository
public interface ServerOfflineLogRepository extends JpaRepository<ServerOfflineLog, String> {

}
