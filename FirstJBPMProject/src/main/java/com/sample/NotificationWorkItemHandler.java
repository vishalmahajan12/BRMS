package com.sample;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import mail.send.SendMail;

public class NotificationWorkItemHandler implements WorkItemHandler {

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

		// extract parameters

		String from = (String) workItem.getParameter("From");
		String to = (String) workItem.getParameter("To");
		String message = (String) workItem.getParameter("Message");
		String priority = (String) workItem.getParameter("Priority");

		// send email
		SendMail service = new SendMail();
		try {
			service.sendMail("Subject", message, from, to);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// service.sendEmail(from, to, "Notification", message);

		// notify manager that work item has been completed

		manager.completeWorkItem(workItem.getId(), null);

	}

	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {

		// Do nothing, notifications cannot be aborted

	}

}