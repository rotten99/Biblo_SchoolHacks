import Persistence.Facade;

import javax.xml.stream.FactoryConfigurationError;

public class Main {
    public static void main(String[] args) {

        // Opgave 3.1
        //System.out.println(Facade.getBorrowerFromId(1));

        //Opgave 3.2 og 3.4
        //System.out.println(Facade.getAllBorrowers());

        //Opgave 3.3
        //System.out.println(Facade.getAllBooks());


        //Opgave 4.1
        //Facade.createBorrower("Ib", "Lærkevej 4", 7500);

        //Opgave 4.2
        //Facade.createNewLoanFrom(Facade.getBookFromId(1),Facade.getBorrowerFromId(3));

        //Opgave 4.3
        //Facade.deleteLoanFromIds(Facade.getBookFromId(1),Facade.getBorrowerFromId(10));

        //Opgave 4.4
        Facade.updateBookTitle(Facade.getBookFromId(1),"Hans og Grethe");

    }
}
