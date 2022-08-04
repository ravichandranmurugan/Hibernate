package tut.hibernate.modal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
//@Entity
@DiscriminatorValue(value = "sav")
public class Savings extends Account {

    private double interest;

    public Savings(double balance, double interest) {
        super(balance);
        this.interest = interest;
    }
}