package org.study.hibernate.criterias;

import javax.persistence.*;

@Entity
@Table(name = "PersonCriteria")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Department department;

    private String name;

    private Integer salary;

    private Integer age;


    public Person() {
    }

    public Person(Department department, String name, Integer salary, Integer age) {
        this.department = department;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", department=" + department +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

enum Department {
    DEV, QA, SUPPORT, BA, PM
}