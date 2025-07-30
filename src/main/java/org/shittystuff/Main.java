package org.shittystuff;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.shittystuff.ui.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(MainScreen::new);
    }
}
