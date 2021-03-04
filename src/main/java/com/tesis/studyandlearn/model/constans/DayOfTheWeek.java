package com.tesis.studyandlearn.model.constans;

public enum DayOfTheWeek {
    MONDAY("Lunes"),
    TUESDAY("Martes"),
    WEDNESDAY("Miercoles"),
    THURSDAY("Jueves"),
    FRIDAY("Viernes"),
    SATURDAY("Sabado"),
    SUNDAY("Domingo");



    private String readable;

    DayOfTheWeek(String readable) {
        this.readable = readable;
    }

    public String getReadable() {
        return readable;
    }
}
