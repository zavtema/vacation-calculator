package com.example.vacation.calculator.api.dto;

public class VacationResponse {

    private int vacationDays; // Отпуск, сколько в днях
    private int totalAmount; // Сумма отпускных

    public VacationResponse(int vacationDays, int totalAmount) {
        this.vacationDays = vacationDays;
        this.totalAmount = totalAmount;
    }

    public int getVacationDays() {
        return vacationDays;
    }
}
