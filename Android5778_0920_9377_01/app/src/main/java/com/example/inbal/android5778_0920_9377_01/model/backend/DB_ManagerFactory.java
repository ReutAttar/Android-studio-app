package com.example.inbal.android5778_0920_9377_01.model.backend;

import com.example.inbal.android5778_0920_9377_01.model.datasource.List_DBManager;
import com.example.inbal.android5778_0920_9377_01.model.datasource.MySQL_DBManager;

/**
 * Created by inbal on 19/11/2017.
 */

public class DB_ManagerFactory {
    static DB_manager manager = null;

    public static DB_manager getManager() {
        if (manager == null)
            manager = new MySQL_DBManager();
        return manager;
    }
}
