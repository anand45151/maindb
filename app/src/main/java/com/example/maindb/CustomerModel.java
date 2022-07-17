package com.example.maindb;

public class CustomerModel {
    private String name;
    private String age;

    public CustomerModel(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public CustomerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
