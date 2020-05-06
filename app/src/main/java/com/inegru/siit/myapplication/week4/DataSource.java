package com.inegru.siit.myapplication.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
@SuppressWarnings("SameParameterValue")
class DataSource {

    static List<Item> getDummyItems(int itemCount) {
        List<Item> list = new ArrayList<>(itemCount);
        for (int i = 1; i <= itemCount; i++) {
            list.add(new Item("FirstName " + i, "LastName " + i));
        }
        return list;
    }

}
