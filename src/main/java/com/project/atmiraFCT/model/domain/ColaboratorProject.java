package com.project.atmiraFCT.model.domain;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
@Table(name = "colaborator_project")
public class ColaboratorProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborator_id")
    private Colaborator colaborator;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public ColaboratorProject(Long id, Colaborator colaborator, Project project) {
        this.id = id;
        this.colaborator = colaborator;
        this.project = project;
    }

    public ColaboratorProject() {

    }

    public ColaboratorProject(Project project, Colaborator colaborator) {
        this.project=project;
        this.colaborator=colaborator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colaborator getColaborator() {
        return colaborator;
    }

    public void setColaborator(Colaborator colaborator) {
        this.colaborator = colaborator;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ColaboratorProject{" +
                "id=" + id +
                ", colaborator=" + colaborator.getId_alias() +
                '}';
    }
}
