package com.javarush.alimova;

import com.javarush.alimova.configure.ConfigurationDB;
import com.javarush.alimova.configure.Creator;

public class QuestApplication {
    public static void main(String[] args) throws Exception {
        ConfigurationDB configurationDB = Creator.getComponent(ConfigurationDB.class);
        configurationDB.startInitDB();

    }
}
