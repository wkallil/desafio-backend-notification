package com.notification.notificationapi.services;


import com.notification.notificationapi.controllers.dtos.ScheduleNotificationDto;
import com.notification.notificationapi.entities.Notification;
import com.notification.notificationapi.entities.Status;
import com.notification.notificationapi.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto notificationDto) {
        notificationRepository.save(notificationDto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
        var notification = findById(notificationId);

        if (notification.isPresent()) {
            notification.get().setStatus(Status.ValuesStatus.Canceled.toStatus());
            notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var notification = notificationRepository.findByStatusInAndDateTimeBefore(
                List.of(Status.ValuesStatus.Pending.toStatus(), Status.ValuesStatus.Error.toStatus()),
                dateTime);

        notification.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return n -> {
            // TODO - Realizar o Envio da Notificacao
            n.setStatus(Status.ValuesStatus.Success.toStatus());
            notificationRepository.save(n);
        };
    }
}
