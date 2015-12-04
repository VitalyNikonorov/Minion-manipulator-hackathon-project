package net.nikonorov.behach;

/**
 * Created by vitaly on 05.12.15.
 */
public class Settings {

    private static String ADDRESS;

    public static String getAddress() {
        return ADDRESS;
    }

    public static void setAddress(String address) {
        Settings.ADDRESS = address;
    }
}
