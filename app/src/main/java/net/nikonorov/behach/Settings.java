package net.nikonorov.behach;

/**
 * Created by vitaly on 05.12.15.
 */
public class Settings {

    private static String ADDRESS = "192.168.0.100";

    public static String getAddress() {
        return ADDRESS;
    }

    public static void setAddress(String address) {
        Settings.ADDRESS = address;
    }
}
