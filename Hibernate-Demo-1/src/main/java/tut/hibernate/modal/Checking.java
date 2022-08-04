package tut.hibernate.modal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
//@Entity
@DiscriminatorValue(value = "chek")
public class Checking extends Account {

    @Column(name="[limit]")
    private double limit;

    public Checking(double balance, double limit) {
        super(balance);
        this.limit = limit;
    }
}
