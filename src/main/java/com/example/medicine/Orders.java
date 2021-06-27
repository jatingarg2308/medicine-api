package com.example.medicine;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    public long id;

    public int uniqueCode;
    public String name;
    public int quantity;

}
