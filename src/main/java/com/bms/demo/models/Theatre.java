package com.bms.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theatre extends BaseModel{
    private String name;
    /*
    Theatre    Screen
    1           M
    1           1
     */
    @OneToMany
    private List<Screen> screens;
}
