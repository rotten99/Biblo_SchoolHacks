package Entities;

import java.util.List;

public class Borrower {

    private int id;
    private String name;
    private String address;
    private int postalCode;
    private List<Loan>  loanList;

    public Borrower(int id, String name, String address, int postalCode, List<Loan> loanList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.loanList = loanList;
    }

    public Borrower(int id, String name, String address, int postalCode) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }


    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", loanList=" + loanList +
                '}' +"\n";
    }
}
