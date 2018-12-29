package com.ygsoft.kpiviewer.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ygsoft.kpiviewer.entity.ServerConfig;

@Repository
public interface ServerConfigRepository extends JpaRepository<ServerConfig, String> {

}
