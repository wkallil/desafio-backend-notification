package com.notification.notificationapi.repositories;

import com.notification.notificationapi.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
