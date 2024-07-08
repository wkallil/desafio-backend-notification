package com.notification.notificationapi.configs;


import com.notification.notificationapi.entities.Channel;
import com.notification.notificationapi.entities.Status;
import com.notification.notificationapi.repositories.ChannelRepository;
import com.notification.notificationapi.repositories.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.ValuesChannel.values())
                .map(Channel.ValuesChannel::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(Status.ValuesStatus.values())
                .map(Status.ValuesStatus::toStatus)
                .forEach(statusRepository::save);
    }
}
