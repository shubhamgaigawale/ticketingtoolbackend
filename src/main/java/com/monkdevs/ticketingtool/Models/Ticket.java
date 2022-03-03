package com.monkdevs.ticketingtool.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticketId;
    private String ticketName;
    private String ticketDescription;
    private String ticketType;
    private String priority;
    private String stepsToReproduce;
    private boolean isActive;
    private String status;
    private boolean isRegressions;
    private String applicationName;

    @ManyToOne
    private User assignedTo;
    
    public Ticket() {
    }

    public Ticket(Long id, String ticketId, String ticketName, String ticketDescription, String ticketType,
            String priority, String stepsToReproduce, boolean isActive, String status, boolean isRegressions,
            String applicationName, User assignedTo) {
        this.id = id;
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
        this.ticketType = ticketType;
        this.priority = priority;
        this.stepsToReproduce = stepsToReproduce;
        this.isActive = isActive;
        this.status = status;
        this.isRegressions = isRegressions;
        this.applicationName = applicationName;
        this.assignedTo = assignedTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRegressions() {
        return isRegressions;
    }

    public void setRegressions(boolean isRegressions) {
        this.isRegressions = isRegressions;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    
}
