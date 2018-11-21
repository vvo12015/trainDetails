package net.vrakin.model;

public enum OrderStateName {
    WAITING("waiting"),
    CANCELLED("cancelled"),
    DEADLINE1("deadline1"),
    DEADLINE2("deadline2"),
    BELATED("belated"),
    DONE("done");

    private final String name;

    OrderStateName(String name) {
        this.name = name;
    }

    public String get(){
        return name;
    }
}
