package br.com.catolicapb.introwebatividadefx.Util;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;

import java.util.ArrayList;

public class ScreenManager {

    private static ArrayList<IOnChangeScreen> listeners = new ArrayList<>();

    public static void addOnChangeScreenListener(IOnChangeScreen newListener) {
        listeners.add(newListener);
    }

    public static void notifyAllListeners(String newScreen, String userID, Object data) {
        for (IOnChangeScreen listener : listeners) {
            listener.onScreenChanged(newScreen, userID, data);
        }
    }

}