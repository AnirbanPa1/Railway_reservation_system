package org.shittystuff.ui;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public MainScreen() {
        setTitle("Railway Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        JPanel menuPanel = createMenuPanel();
        cardPanel.add(menuPanel, "menu");


        cardPanel.add(createAddTrainPanel(), "addTrain");
        cardPanel.add(createBookTrainPanel(), "bookTrain");
        cardPanel.add(createViewAllReservationsPanel(), "viewAll");
        cardPanel.add(createUpdateReservationPanel(), "update");
        cardPanel.add(createDeleteReservationPanel(), "delete");
        cardPanel.add(createSearchReservationPanel(), "search");

        add(cardPanel);
        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JButton addTrainButton = new JButton("➕ Add Train");
        JButton bookTrainButton = new JButton("🚆 Book Train");
        JButton viewAllButton = new JButton("📋 View All Reservations");
        JButton updateButton = new JButton("✏️ Update Reservation");
        JButton deleteButton = new JButton("❌ Delete Reservation");
        JButton searchButton = new JButton("🔍 Search Reservation by Name");

        JButton[] buttons = { addTrainButton, bookTrainButton, viewAllButton, updateButton, deleteButton, searchButton };

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        Font buttonFont = new Font("San Francisco", Font.PLAIN, 18);
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];
            btn.setFocusPainted(false);
            btn.setFont(buttonFont);
            btn.setPreferredSize(new Dimension(300, 40));
            gbc.gridy = i;
            panel.add(btn, gbc);
        }


        addTrainButton.addActionListener(e -> cardLayout.show(cardPanel, "addTrain"));
        bookTrainButton.addActionListener(e -> cardLayout.show(cardPanel, "bookTrain"));
        viewAllButton.addActionListener(e -> cardLayout.show(cardPanel, "viewAll"));
        updateButton.addActionListener(e -> cardLayout.show(cardPanel, "update"));
        deleteButton.addActionListener(e -> cardLayout.show(cardPanel, "delete"));
        searchButton.addActionListener(e -> cardLayout.show(cardPanel, "search"));

        return panel;
    }

    private JPanel createAddTrainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));


        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel[] labels = {
                new JLabel("Train ID:"),
                new JLabel("Train Name:"),
                new JLabel("Source:"),
                new JLabel("Destination:")
        };

        JTextField trainIdField = new JTextField(20);
        JTextField trainNameField = new JTextField(20);
        JTextField sourceField = new JTextField(20);
        JTextField destinationField = new JTextField(20);

        JTextField[] fields = { trainIdField, trainNameField, sourceField, destinationField };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            labels[i].setForeground(Color.WHITE);
            formPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }


        JButton saveButton = new JButton("💾 Save");
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);


        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            String trainId = trainIdField.getText();
            String trainName = trainNameField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();

            System.out.println("Train ID: " + trainId);
            System.out.println("Train Name: " + trainName);
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
        });

        return panel;
    }

    private JPanel createBookTrainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));


        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel[] labels = {
                new JLabel("Train ID:"),
                new JLabel("Passenger Name:"),
                new JLabel("Number of Seats:")
        };

        JTextField trainIdField = new JTextField(20);
        JTextField passengerNameField = new JTextField(20);
        JTextField seatCountField = new JTextField(20);

        JTextField[] fields = { trainIdField, passengerNameField, seatCountField };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            labels[i].setForeground(Color.WHITE);
            formPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }


        JButton saveButton = new JButton("💾 Save");
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            String trainId = trainIdField.getText();
            String passengerName = passengerNameField.getText();
            String seatCount = seatCountField.getText();

            System.out.println("Train ID: " + trainId);
            System.out.println("Passenger Name: " + passengerName);
            System.out.println("Number of Seats: " + seatCount);
        });

        return panel;
    }

    private JPanel createViewAllReservationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        String[][] reservations = {
                {"123", "Alice", "Delhi", "Mumbai", "2"},
                {"456", "Bob", "Kolkata", "Chennai", "1"},
                {"789", "Charlie", "Hyderabad", "Bangalore", "3"},
                {"321", "David", "Pune", "Goa", "4"}
        };

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(30, 30, 30));

        for (String[] res : reservations) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(50, 50, 50));
            card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
            card.setPreferredSize(new Dimension(700, 100));

            JLabel trainId = new JLabel("🚆 Train ID: " + res[0]);
            JLabel name = new JLabel("👤 Name: " + res[1]);
            JLabel route = new JLabel("📍 " + res[2] + " → " + res[3]);
            JLabel seats = new JLabel("💺 Seats: " + res[4]);

            for (JLabel label : new JLabel[]{trainId, name, route, seats}) {
                label.setForeground(Color.WHITE);
                label.setFont(new Font("San Francisco", Font.PLAIN, 16));
                label.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            }

            card.add(trainId);
            card.add(name);
            card.add(route);
            card.add(seats);

            listPanel.add(Box.createVerticalStrut(10));
            listPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUpdateReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel reservationIdLabel = new JLabel("Reservation ID:");
        JLabel modifiedSeatsLabel = new JLabel("Modified Number of Seats:");

        JTextField reservationIdField = new JTextField(20);
        JTextField modifiedSeatsField = new JTextField(20);

        reservationIdLabel.setForeground(Color.WHITE);
        modifiedSeatsLabel.setForeground(Color.WHITE);

        reservationIdLabel.setFont(new Font("San Francisco", Font.PLAIN, 16));
        modifiedSeatsLabel.setFont(new Font("San Francisco", Font.PLAIN, 16));

        gbc.gridy = 0;
        formPanel.add(reservationIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(reservationIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(modifiedSeatsLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(modifiedSeatsField, gbc);

        JButton saveButton = new JButton("💾 Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            String reservationId = reservationIdField.getText();
            String modifiedSeats = modifiedSeatsField.getText();

            System.out.println("Reservation ID: " + reservationId);
            System.out.println("Modified Number of Seats: " + modifiedSeats);
        });

        return panel;
    }

    private JPanel createDeleteReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel reservationIdLabel = new JLabel("Reservation ID:");
        reservationIdLabel.setForeground(Color.WHITE);
        reservationIdLabel.setFont(new Font("San Francisco", Font.PLAIN, 16));

        JTextField reservationIdField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(reservationIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(reservationIdField, gbc);

        JButton deleteButton = new JButton("🗑 Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(deleteButton, gbc);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        deleteButton.addActionListener(e -> {
            String reservationId = reservationIdField.getText();
            System.out.println("Deleted Reservation ID: " + reservationId);
        });

        return panel;
    }

    private JPanel createSearchReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        JPanel searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchBarPanel.setBackground(new Color(30, 30, 30));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("🔍 Search");

        searchBarPanel.add(new JLabel("🔎 Name: "));
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(searchBarPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        String[][] reservations = {
                {"123", "Alice", "Delhi", "Mumbai", "2"},
                {"456", "Bob", "Kolkata", "Chennai", "1"},
                {"789", "Charlie", "Hyderabad", "Bangalore", "3"},
                {"321", "David", "Pune", "Goa", "4"}
        };

        searchButton.addActionListener(e -> {
            listPanel.removeAll();

            for (String[] res : reservations) {
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBackground(new Color(50, 50, 50));
                card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
                card.setPreferredSize(new Dimension(700, 100));

                JLabel trainId = new JLabel("🚆 Train ID: " + res[0]);
                JLabel name = new JLabel("👤 Name: " + res[1]);
                JLabel route = new JLabel("📍 " + res[2] + " → " + res[3]);
                JLabel seats = new JLabel("💺 Seats: " + res[4]);

                for (JLabel label : new JLabel[]{trainId, name, route, seats}) {
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("San Francisco", Font.PLAIN, 16));
                    label.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
                }

                card.add(trainId);
                card.add(name);
                card.add(route);
                card.add(seats);

                listPanel.add(Box.createVerticalStrut(10));
                listPanel.add(card);
            }

            listPanel.revalidate();
            listPanel.repaint();
        });

        return panel;
    }


    private JPanel createScreenPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("San Francisco", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }
}
