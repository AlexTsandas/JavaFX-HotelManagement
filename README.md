# HotelNest - JavaFX Hotel Management System

## About the Project

This project was developed as part of a university assignment. The purpose was to design and implement a hotel management system using JavaFX, focusing on functionality, usability, and proper handling of application data.

HotelNest is a desktop application that provides a simple and effective way to manage hotel rooms and bookings. It supports two different roles, administrators and users, each with their own set of functionalities.

## Project Goal

The goal of this project is to simulate a real-world hotel management environment where different types of users interact with the system. Administrators are responsible for managing rooms and bookings, while users are able to view available rooms and make reservations.

The application demonstrates how a structured system can manage multiple users and dynamically update data without the use of a database, relying instead on local JSON files.

## How It Works

The application is based on a role-based system. When logging in, the user selects a role and enters valid credentials. If the credentials match a record in the users file, the system redirects to the corresponding interface.

In the administrator interface, rooms can be added or removed, and bookings can be managed by changing the status of each room. In the user interface, only available rooms are displayed, and users can book a room by selecting its ID. Each booking is linked to the username of the user, allowing the system to track personal reservations. Users can also view and manage their own bookings.

## Data Management

The application uses JSON files for storing and updating data. The users file contains login credentials and roles, while the rooms file contains information about each room such as its ID, type, price, and status.

The status field plays a key role in the system. When a room is available, its status is set to "available". When a user books a room, the status changes to the username of that user. This allows the system to identify which rooms are booked and by whom.

## Technologies Used

Java, JavaFX, Gson, and Scene Builder were used for the development of this application.
