package com.advocate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDate;

public class Advocate {
	private int id;
    private String name;
    private String specialization;

    public Advocate(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public static void bookAppointment() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Book an Appointment:");
			
			// Get user input for appointment details
	        System.out.print("Enter customer ID: ");
	        int customerId = Integer.parseInt(br.readLine());
	        
	        System.out.print("Enter advocate ID: ");
	        int advocateId = Integer.parseInt(br.readLine());
	        
	        System.out.print("Enter service type: ");
	        String serviceType = br.readLine();
	        
	        // Create a Date object for the appointment date (you may need to implement date parsing)
	        LocalDate appointmentDate = LocalDate.now(); // Replace this with actual date parsing
	        
	        Connection connection = ConnectionProvider.getConnection();
	        
	        String insertQuery = "INSERT INTO appointments (customer_id, advocate_id, service_type, appointment_date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, advocateId);
            preparedStatement.setString(3, serviceType);
            preparedStatement.setDate(4, java.sql.Date.valueOf(appointmentDate));
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment booked successfully.");
            } else {
                System.out.println("Failed to book appointment.");
            }
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void modifyAppointmentDetails() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Modify Appointment Details:");
			
			// Get appointment ID to identify the appointment to modify
	        System.out.print("Enter appointment ID: ");
	        
	        int appointmentId = Integer.parseInt(br.readLine());
	        
	        // Retrieve the existing appointment details from the database
	        Appointment existingAppointment = null;
	        
	        Connection connection = ConnectionProvider.getConnection();
	        
	        String selectQuery = "SELECT * FROM appointments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, appointmentId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                existingAppointment = new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getDate("appointment_date"),
                    resultSet.getInt("customer_id"),
                    resultSet.getInt("advocate_id"),
                    resultSet.getString("service_type")
                );
            } else {
                System.out.println("Appointment not found.");
                return;
            }
            
            // Get updated appointment details from the user
            System.out.print("Enter new service type (" + existingAppointment.getServiceType() + "): ");
            String newServiceType = br.readLine();
            if (!newServiceType.isEmpty()) {
                existingAppointment.setServiceType(newServiceType);
            }

            // Create a Date object for the new appointment date (you may need to implement date parsing)
            LocalDate newAppointmentDate = LocalDate.now(); // Replace this with actual date parsing
            
            // Update the appointment record in the database
            String updateQuery = "UPDATE appointments SET service_type = ?, appointment_date = ? WHERE id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
            preparedStatement1.setString(1, existingAppointment.getServiceType());
            preparedStatement1.setDate(2, java.sql.Date.valueOf(newAppointmentDate));
            preparedStatement1.setInt(3, existingAppointment.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment details updated successfully.");
            } else {
                System.out.println("Failed to update appointment details.");
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteAppointment() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Delete an Appointment:");

	        // Get appointment ID to identify the appointment to delete
	        System.out.print("Enter appointment ID: ");
	        
	        int appointmentId = Integer.parseInt(br.readLine());
	        
	        // Delete the appointment record from the database
	        Connection connection = ConnectionProvider.getConnection();
	        
	        String deleteQuery = "DELETE FROM appointments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, appointmentId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment deleted successfully.");
            } else {
                System.out.println("Failed to delete appointment.");
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void viewSingleRecord() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("View Single Appointment Record:");

	        // Get appointment ID to identify the appointment to view
	        System.out.print("Enter appointment ID: ");
	        
	        int appointmentId = Integer.parseInt(br.readLine());
	        
	        // Retrieve the appointment record from the database
	        Connection connection = ConnectionProvider.getConnection();
	        
	        String selectQuery = "SELECT * FROM appointments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, appointmentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Appointment appointment = new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getDate("appointment_date"),
                    resultSet.getInt("customer_id"),
                    resultSet.getInt("advocate_id"),
                    resultSet.getString("service_type")
                );
                System.out.println("Appointment Details:");
                System.out.println(appointment);
            } else {
                System.out.println("Appointment not found.");
            }
		} catch(Exception e) {
			
		}
	}
	
	public static void viewAllRecords() {
		try {
			System.out.println("View All Appointment Records:");
			
			// Retrieve all appointment records from the database
			Connection connection = ConnectionProvider.getConnection();
			String selectQuery = "SELECT * FROM appointments";
	        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("appointment_date"),
                    resultSet.getInt("customer_id"),
                    resultSet.getInt("advocate_id"),
                    resultSet.getString("service_type")
                );
                System.out.println(appointment);
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

