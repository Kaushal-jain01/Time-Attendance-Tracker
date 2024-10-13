package com.example.TimesheetManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimesheetManagement.model.Employee;
import com.example.TimesheetManagement.model.Notification;
import com.example.TimesheetManagement.repository.EmployeeRepository;
import com.example.TimesheetManagement.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Notification createNotification(Notification noti) {
		
		Notification savedNoti = notificationRepo.save(noti);
		
		return savedNoti;
		
	}

	public List<Notification> getNotificationsByEid(Integer eid) {
		
		Employee emp = empRepo.findById(eid).get();

		List<Notification> notifs =  notificationRepo.findAllByEmployee(eid);
		
		return notifs;
	}
	
	
}
