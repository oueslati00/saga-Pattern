package com.example.payementservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usertransaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTransaction {
    @Id
    private Integer id;
    private Integer userId;
    private Integer amount;
}
