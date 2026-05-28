package com.example.practo.Payload;

public class BloodReportRequestDto {

    private String patientName;

    private String hemoglobin;

    private String bloodSugar;

    private String cholesterol;

    private String bloodPressure;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(
            String patientName) {

        this.patientName = patientName;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(
            String hemoglobin) {

        this.hemoglobin = hemoglobin;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(
            String bloodSugar) {

        this.bloodSugar = bloodSugar;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(
            String cholesterol) {

        this.cholesterol = cholesterol;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(
            String bloodPressure) {

        this.bloodPressure = bloodPressure;
    }
}