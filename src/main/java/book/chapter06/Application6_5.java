package book.chapter06;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Application6_5 {
    public static void main(String[] args) {
        List<Customer> someCustomers = new LinkedList<>();
        someCustomers.add(new Customer(new Address("MA")));
        someCustomers.add(new Customer(new Address("RI")));
        someCustomers.add(new Customer(new Address("ME")));
        someCustomers.add(new Customer(new Address("MA")));
        someCustomers.add(new Customer(new Address("CT")));
        someCustomers.add(new Customer(new Address("NO")));
        someCustomers.add(new Customer(new Address("NO")));
        someCustomers.add(new Customer(new Address("NO")));
        someCustomers.add(new Customer(new Address("NO")));
        someCustomers.add(new Customer(new Address("NO")));

        long numberOfNewEngland =
                someCustomers.stream()
                        .filter(customer -> isNewEngland(customer.getAddress().getState()))
                        .count();
        System.out.println("numberOfNewEngland = " + numberOfNewEngland);
    }

    private static boolean isNewEngland(String stateCode) {
        return Arrays.stream(new String[]{"MA", "CT", "ME", "VT", "NH", "RI"})
                .anyMatch(code -> code.equals(stateCode));
    }
}

@Data
@AllArgsConstructor
class Customer {
    private Address address;
}

@Data
@AllArgsConstructor
class Address {
    private String state;
}
