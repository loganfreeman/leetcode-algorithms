package com.leetcode;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;

public class BillFor {
    /* test fixtures */

    Subscription newPlan = new Subscription(1, 1, 4);

    User[] noUsers = new User[0];

    User[] constantUsers = {
            new User(1, "Employee #1", LocalDate.of(2018, 11, 4), null, 1),
            new User(2, "Employee #2", LocalDate.of(2018, 12, 4), null, 1)
    };

    User[] userSignedUp = {
            new User(1, "Employee #1", LocalDate.of(2018, 11, 4), null, 1),
            new User(2, "Employee #2", LocalDate.of(2018, 12, 4), null, 1),
            new User(3, "Employee #3", LocalDate.of(2019, 01, 10), null, 1),
    };

    /* end test fixtures */


    @Test
    public void worksWhenTheCustomerHasNoActiveUsersDuringTheMonth() {
        assertEquals(0, Challenge.billFor("2019-01", newPlan, noUsers), 0.01);
    }

    @Test
    public void worksWhenEverythingStaysTheSameForAMonth() {
        assertEquals(8, Challenge.billFor("2019-01", newPlan, constantUsers), 0.01);
    }

    @Test
    public void worksWhenAUserIsActivatedDuringTheMonth() {
        assertEquals(10.84, Challenge.billFor("2019-01", newPlan, userSignedUp), 0.01);
    }
}