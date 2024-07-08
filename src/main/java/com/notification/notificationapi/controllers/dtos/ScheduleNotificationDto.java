package com.notification.notificationapi.controllers.dtos;

import com.notification.notificationapi.entities.Channel;
import com.notification.notificationapi.entities.Notification;
import com.notification.notificationapi.entities.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime, String destination, String message,
                                      Channel.ValuesChannel channel) {

    public Notification toNotification() {
        return new Notification(dateTime, destination, message,
                channel.toChannel(), Status.ValuesStatus.Pending.toStatus());
    }
}
