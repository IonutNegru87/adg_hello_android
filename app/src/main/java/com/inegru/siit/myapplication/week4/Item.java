package com.inegru.siit.myapplication.week4;

/**
 * .
 */
class Item {

    private final String firstName;
    private final String lastName;

    Item(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
}
