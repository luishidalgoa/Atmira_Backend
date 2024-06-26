package com.project.atmiraFCT.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.atmiraFCT.security.User.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name="colaborator")
@JsonIgnoreProperties({"projects", "colaboratorProjects","task","expenses"})
public class Colaborator  implements UserDetails {

    @Id
    @Column(name = "id_alias")
    private String id_alias;

    @Column(name="email", length = 100)
    private String email;

    @Column(name="isActive")
    private Boolean isActive;

    @Column(name="relaseDate")
    private Date relaseDate;

    @Column(name="hours")
    private Integer hours;

    @Column(name="guards")
    private Boolean guards;

    @Column(name="expense")
    private Boolean expense;

    @Column(name="name", length = 50)
    private String name;

    @Column(name="surname", length = 100)
    private String surname;

    @Column(name="password", length = 256)
    private String password;

    @Column(name="responsible ", length = 10)
    private String responsible ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborator")
    @JsonManagedReference
    private List<Expense> expenses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborator")
    @JsonManagedReference
    private List<Task> task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_workplace")
    @JsonBackReference
    private WorkPlace workPlace;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborator", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ColaboratorProject> colaboratorProjects;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "colaborator_department",
            joinColumns = @JoinColumn(name = "colaborator_id_alias"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departments;

    @Enumerated(EnumType.STRING)
    Role role;


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Colaborator(String id_alias, String email, Boolean isActive, Date relaseDate, Integer hours, Boolean guards, Boolean expense, String name, String surname, String password, List<Expense> expenses, List<Task> task, WorkPlace workPlace, List<ColaboratorProject> colaboratorProjects) {
        this.id_alias = id_alias;
        this.email = email;
        this.isActive = isActive;
        this.relaseDate = relaseDate;
        this.hours = hours;
        this.guards = guards;
        this.expense = expense;
        this.name = name;
        this.surname = surname;
        setPassword(password);
        this.expenses = expenses;
        this.task = task;
        this.workPlace = workPlace;
        this.colaboratorProjects = colaboratorProjects;
    }

    public Colaborator() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getId_alias() {
        return id_alias;
    }

    public void setId_alias(String id_alias) {
        this.id_alias = id_alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getRelaseDate() {
        return relaseDate;
    }

    public void setRelaseDate(Date relaseDate) {
        this.relaseDate = relaseDate;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Boolean getGuards() {
        return guards;
    }

    public void setGuards(Boolean guards) {
        this.guards = guards;
    }

    public Boolean getExpense() {
        return expense;
    }

    public void setExpense(Boolean expense) {
        this.expense = expense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public List<ColaboratorProject> getColaboratorProjects() {
        return colaboratorProjects;
    }

    public void setColaboratorProjects(List<ColaboratorProject> colaboratorProjects) {
        this.colaboratorProjects = colaboratorProjects;
    }

}

 /*public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }*/