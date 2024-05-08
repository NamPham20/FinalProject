package com.example.finalproject.doctor.medical_records_screen.recycle_view;

public class MedicalRecordFields {
    private String id;
    private String nameField;
    private String valueType;
    private String valueList;
    private String diagnoseType;
    private String type;

    public MedicalRecordFields(String id, String nameField, String valueType, String valueList, String diagnoseType, String type) {
        this.id = id;
        this.nameField = nameField;
        this.valueType = valueType;
        this.valueList = valueList;
        this.diagnoseType = diagnoseType;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public String getDiagnoseType() {
        return diagnoseType;
    }

    public void setDiagnoseType(String diagnoseType) {
        this.diagnoseType = diagnoseType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
