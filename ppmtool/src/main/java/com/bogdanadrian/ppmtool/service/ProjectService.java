package com.bogdanadrian.ppmtool.service;

import com.bogdanadrian.ppmtool.domain.Project;
import com.bogdanadrian.ppmtool.exception.ProjectIDException;
import com.bogdanadrian.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(final Project project) {
        try {
            project.getProjectIdentifier().toUpperCase();
            return projectRepository.save(project);
        } catch (final Exception exception) {
            throw new ProjectIDException("Project ID: " + project.getProjectIdentifier() + " already exists.");
        }
    }

    public Project findProjectByIdentifier(final String projectID) {
        final Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
        if (project == null) {
            throw new ProjectIDException("Project ID: " + projectID + " doesn't exist.");
        }
        return  project;
    }

    public Iterable<Project> findAllProjects() {
        return  projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(final String projectID) {
        final Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
        if (project == null) {
            throw new ProjectIDException("Project ID: " + projectID + " can't be deleted. It doesn't exist.");
        }
        projectRepository.delete(project);
    }

}
