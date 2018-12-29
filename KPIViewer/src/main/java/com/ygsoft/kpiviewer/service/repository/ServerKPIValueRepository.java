package com.ygsoft.kpiviewer.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ygsoft.kpiviewer.entity.ServerKPIValue;

@Repository
public interface ServerKPIValueRepository extends JpaRepository<ServerKPIValue, String> {
	List <ServerKPIValue> findByServerIdOrderByInsertDateAsc(String serverId);

}
