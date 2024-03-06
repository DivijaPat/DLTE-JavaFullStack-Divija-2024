package org.filestorage;

import java.io.Serializable;

public class EmployeeAddress implements Serializable {
        private String permanentAddress;
        private String permanentHouseName;
        private String permanentState;
        private String permanentCity;
        private Integer permanentPinCode;
        private String temporaryAddress;
        private String temporaryHouseName;
        private String temporaryState;
        private String temporaryCity;
        private Integer temporaryPinCode;

        public EmployeeAddress(String permanentAddress, String permanentHouseName, String permanentState, String permanentCity, Integer permanentPinCode, String temporaryAddress, String temporaryHouseName, String temporaryState, String temporaryCity, Integer temporaryPinCode) {
            this.permanentAddress = permanentAddress;
            this.permanentHouseName = permanentHouseName;
            this.permanentState = permanentState;
            this.permanentCity = permanentCity;
            this.permanentPinCode = permanentPinCode;
            this.temporaryAddress = temporaryAddress;
            this.temporaryHouseName = temporaryHouseName;
            this.temporaryState = temporaryState;
            this.temporaryCity = temporaryCity;
            this.temporaryPinCode = temporaryPinCode;
        }

        public EmployeeAddress() {
        }

        @Override
        public String toString() {
            return "EmployeeAddress{" +
                    "permanentAddress='" + permanentAddress + '\'' +
                    ", permanentHouseName='" + permanentHouseName + '\'' +
                    ", permanentState='" + permanentState + '\'' +
                    ", permanentCity='" + permanentCity + '\'' +
                    ", permanentPinCode=" + permanentPinCode +
                    ", temporaryAddress='" + temporaryAddress + '\'' +
                    ", temporaryHouseNumber='" + temporaryHouseName + '\'' +
                    ", temporaryState='" + temporaryState + '\'' +
                    ", temporaryCity='" + temporaryCity + '\'' +
                    ", temporaryPinCode=" + temporaryPinCode +
                    '}';
        }

        public String getPermanentAddress() {
            return permanentAddress;
        }

        public void setPermanentAddress(String permanentAddress) {
            this.permanentAddress = permanentAddress;
        }

        public String getPermanentHouseName() {
            return permanentHouseName;
        }

        public void setPermanentHouseName(String permanentHouseName) {
            this.permanentHouseName = permanentHouseName;
        }

        public String getPermanentState() {
            return permanentState;
        }

        public void setPermanentState(String permanentState) {
            this.permanentState = permanentState;
        }

        public String getPermanentCity() {
            return permanentCity;
        }

        public void setPermanentCity(String permanentCity) {
            this.permanentCity = permanentCity;
        }

        public Integer getPermanentPinCode() {
            return permanentPinCode;
        }

        public void setPermanentPinCode(Integer permanentPinCode) {
            this.permanentPinCode = permanentPinCode;
        }

        public String getTemporaryAddress() {
            return temporaryAddress;
        }

        public void setTemporaryAddress(String temporaryAddress) {
            this.temporaryAddress = temporaryAddress;
        }

        public String getTemporaryHouseName() {
            return temporaryHouseName;
        }

        public void setTemporaryHouseNumber(String temporaryHouseNumber) {
            this.temporaryHouseName = temporaryHouseName;
        }

        public String getTemporaryState() {
            return temporaryState;
        }

        public void setTemporaryState(String temporaryState) {
            this.temporaryState = temporaryState;
        }

        public String getTemporaryCity() {
            return temporaryCity;
        }

        public void setTemporaryCity(String temporaryCity) {
            this.temporaryCity = temporaryCity;
        }

        public Integer getTemporaryPinCode() {
            return temporaryPinCode;
        }

        public void setTemporaryPinCode(Integer temporaryPinCode) {
            this.temporaryPinCode = temporaryPinCode;
        }
    }


