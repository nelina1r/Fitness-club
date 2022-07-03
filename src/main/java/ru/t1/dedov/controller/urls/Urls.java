package ru.t1.dedov.controller.urls;

public interface Urls {
    String ROOT = "/api";

    interface Card {
        String NAME = "card";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Card.FULL + "/" + NAME;
        }
    }

    interface Client {
        String NAME = "client";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Client.FULL + "/" + NAME;
        }
    }

    interface Employee {
        String NAME = "employee";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Employee.FULL + "/" + NAME;
        }
    }

    interface Gym {
        String NAME = "gym";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Gym.FULL + "/" + NAME;
        }
    }

    interface Product {
        String NAME = "product";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Product.FULL + "/" + NAME;
        }
    }

    interface Schedule {
        String NAME = "schedule";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Schedule.FULL + "/" + NAME;
        }
    }

    interface Store {
        String NAME = "store";
        String FULL = ROOT + "/" + NAME;

        interface Id {
            String NAME = "{id}";
            String FULL = Store.FULL + "/" + NAME;
        }
    }

}
