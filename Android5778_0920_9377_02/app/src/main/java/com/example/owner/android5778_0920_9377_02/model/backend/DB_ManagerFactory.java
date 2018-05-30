package com.example.owner.android5778_0920_9377_02.model.backend;

import com.example.owner.android5778_0920_9377_02.model.datasource.MySQL_DBManager;

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
