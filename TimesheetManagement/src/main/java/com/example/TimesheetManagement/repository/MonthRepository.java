package com.example.TimesheetManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TimesheetManagement.model.MonthModel;

@Repository
public interface MonthRepository extends JpaRepository<MonthModel, Integer> {
	
	@Query("SELECT m.totalDays FROM MonthModel m WHERE m.id = :id")
	Optional<Integer> findTotalDaysById(@Param("id") Integer id);
	
	@Query("SELECT m.workingDays FROM MonthModel m WHERE m.id = :id")
	Optional<Integer> findWorkingDaysById(@Param("id") Integer id);
	
}
