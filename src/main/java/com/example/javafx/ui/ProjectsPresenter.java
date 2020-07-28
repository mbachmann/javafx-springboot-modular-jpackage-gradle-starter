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
package com.example.javafx.ui;


import com.example.javafx.domain.Project;
import com.example.javafx.domain.Task;
import com.example.javafx.service.ProjectService;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectsPresenter {

	@FXML
    TableView<Project> projectsTable;

	@FXML
    TableColumn<Project, String> projectNameColumn;
	@FXML
    TableColumn<Project, String> projectDescriptionColumn;

	@FXML
    TableView<Task> tasksTable;
	@FXML
    TableColumn<Task, String> taskNameColumn;
	@FXML
    TableColumn<Task, String> taskDescriptionColumn;
	@FXML
    TableColumn<Task, Task.Status> taskStatusColumn;

	@Autowired
	ProjectService projectTrackingService;

	@FXML
	public void initialize() {

		configureProjectsTable();
		configureTasksTable();

		for (Project project : projectTrackingService.findAllProjects()) {
			projectsTable.getItems().add(project);
		}

		projectsTable.getSelectionModel().selectFirst();
	}

	private void configureTasksTable() {

		taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		taskNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		taskDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		taskStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Task.Status.values()));

		taskNameColumn.setOnEditCommit(edit -> {
			edit.getRowValue().setName(edit.getNewValue());
			projectTrackingService.save(edit.getRowValue());
		});

		taskDescriptionColumn.setOnEditCommit(edit -> {
			edit.getRowValue().setDescription(edit.getNewValue());
			projectTrackingService.save(edit.getRowValue());
		});

		taskStatusColumn.setOnEditCommit(edit -> {
			edit.getRowValue().setStatus(edit.getNewValue());
			projectTrackingService.save(edit.getRowValue());
		});
	}

	private void configureProjectsTable() {

		projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		projectNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		projectDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		projectDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		ChangeListener<Project> projectSelectionChanged = (observable, oldValue, newValue) -> {
			tasksTable.getItems().clear();
			for (Task task : projectTrackingService.findAllTasksByProject(newValue)) {
				tasksTable.getItems().add(task);
			}
		};

		projectNameColumn.setOnEditCommit(edit -> {
			edit.getRowValue().setName(edit.getNewValue());
			projectTrackingService.save(edit.getRowValue());
		});

		projectDescriptionColumn.setOnEditCommit(edit -> {
			edit.getRowValue().setDescription(edit.getNewValue());
			projectTrackingService.save(edit.getRowValue());
		});

		projectsTable.getSelectionModel().selectedItemProperty().addListener(projectSelectionChanged);
	}
}
