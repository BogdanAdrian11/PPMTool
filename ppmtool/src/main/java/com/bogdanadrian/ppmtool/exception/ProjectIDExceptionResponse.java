package com.bogdanadrian.ppmtool.exception;

public class ProjectIDExceptionResponse {
    private String projectIdentifier;

    public ProjectIDExceptionResponse(final String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
