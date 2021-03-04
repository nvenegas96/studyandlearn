package com.tesis.studyandlearn.model.constans;

public class Querys {
    public final static String specialtyNameQuery = "SELECT s.name " +
            "FROM user_specialty AS us " +
            "JOIN specialty AS s ON (us.specialty_id = s.specialty_id) " +
            "WHERE us.user_id = :userId";
    public final static String assessmentProm = "SELECT ROUND(AVG(assessment)) as prom " +
            "FROM lesson_schedule " +
            "WHERE lesson_id = :lessonId " +
            "AND teacher_id = :teacherId ";
}

