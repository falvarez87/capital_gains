package com.nubank.capitalgains.domain;

import java.io.Serializable;

public class Output implements Serializable {

    private Double tax;

    public Output(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public boolean equals(Object obj){
        Output emp = (Output) obj;
        boolean status = false;
        if(this.tax.equals(emp.tax)){
            status = true;
        }
        return status;
    }
}
