package com.example.TimesheetManagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimesheetManagement.model.Notification;
import com.example.TimesheetManagement.model.UserIdRoleDto;
import com.example.TimesheetManagement.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/send")
	public ResponseEntity<Notification> createNotification(@RequestBody Notification noti) {

		Notification notification = notificationService.createNotification(noti);

		return ResponseEntity.ok(notification);
	}

	@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
	@GetMapping("/get/{eid}")
	public ResponseEntity<List<Notification>> getNotifications(@PathVariable Integer eid) {

		List<Notification> notifications = notificationService.getNotificationsByEid(eid);

		return ResponseEntity.ok(notifications);

	}

}
