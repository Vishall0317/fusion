package com.fusion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "fusion")
@Data
public class MySqlEntity {

    @Id
    private String id;
    private String name;
    private String city;
    private String salary;
}
