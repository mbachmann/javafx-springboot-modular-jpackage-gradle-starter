/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.javafx.service.impl;


import com.example.javafx.domain.Project;
import com.example.javafx.domain.Task;
import com.example.javafx.repository.ProjectRepository;
import com.example.javafx.repository.TaskRepository;
import com.example.javafx.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findAllAndFetchTasksEagerly() {
        return projectRepository.findAllAndFetchTasksEagerly();
    }

    @Override
    public Optional<Project> findOneAndFetchTasksEagerly(Long id) {
        return projectRepository.findOneAndFetchTasksEagerly(id);
    }

    @Override
    public List<Task> findAllTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
