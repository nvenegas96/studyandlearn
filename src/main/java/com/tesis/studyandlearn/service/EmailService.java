package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.dto.EmailDTO;

public interface EmailService {

    boolean welcomeEmail(int userId);

    boolean changeScheduleStatusEmail(int userId, String status, String date);

    boolean changeUserProfile(int userId);

}
