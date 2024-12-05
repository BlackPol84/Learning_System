package ru.ykul.model.dto;

import ru.ykul.model.MessageType;

public record CourseNotificationDto(MessageType type,
                                    String email,
                                    String message
                                    ) { }
