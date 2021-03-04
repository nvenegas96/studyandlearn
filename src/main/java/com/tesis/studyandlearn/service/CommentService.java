package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonCommentEntity;
import com.tesis.studyandlearn.model.dto.LessonCommentDTO;

import java.util.List;

public interface CommentService {

    List<LessonCommentDTO> showAllCommentDTO();

    LessonCommentEntity createOrUpdateComment(LessonCommentEntity lessonCommentEntity, String email);

    boolean checkCorrectDTO(LessonCommentEntity lessonCommentEntity);

    //LessonDTO findByCommentId(int commentId);


}
