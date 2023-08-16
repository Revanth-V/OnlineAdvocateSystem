package com.advocate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Service {
	private int id;
    private String name;
    private String description;
    private double fee;

    public Service(int id, String name, String description, double fee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public double getFee() {
		return fee;
	}
    
    public void setFee(double fee) {
		this.fee = fee;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Service ID: " + id + "\n" +
               "Service Name: " + name + "\n" +
               "Service Description: " + description + "\n" + 
               "Service Fee: " + fee + "\n";
    }
    
    public static void addNewService() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("Add a New Service:");

            // Get user input for new service details
            System.out.print("Enter service name: ");
            String serviceName = br.readLine();
            
            System.out.print("Enter service description: ");
            String serviceDescription = br.readLine();
            
            System.out.print("Enter service fee: ");
            double serviceFee = Double.parseDouble(br.readLine());
            
            // Insert the new service record into the database
            Connection connection = ConnectionProvider.getConnection();
            
            String insertQuery = "INSERT INTO services (name, description, fee) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, serviceName);
            preparedStatement.setString(2, serviceDescription);
            preparedStatement.setDouble(3, serviceFee);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New service added successfully.");
            } else {
                System.out.println("Failed to add new service.");
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void modifyServiceDetails() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("Modify Service Details:");

            // Get service ID to identify the service to modify
            System.out.print("Enter service ID: ");
            
            int serviceId = Integer.parseInt(br.readLine());
            
            // Retrieve the existing service details from the database
            Service existingService = null;
            
            Connection connection = ConnectionProvider.getConnection();
            String selectQuery = "SELECT * FROM services WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, serviceId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                existingService = new Service(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("fee")
                );
            } else {
                System.out.println("Service not found.");
                return;
            }
            
            // Get updated service details from the user
            System.out.print("Enter new service name (" + existingService.getName() + "): ");
            String newServiceName = br.readLine();
            if (!newServiceName.isEmpty()) {
                existingService.setName(newServiceName);
            }

            System.out.print("Enter new service description (" + existingService.getDescription() + "): ");
            String newServiceDescription = br.readLine();
            if (!newServiceDescription.isEmpty()) {
                existingService.setDescription(newServiceDescription);
            }

            System.out.print("Enter new service fee (" + existingService.getFee() + "): ");
            double newServiceFee = Double.parseDouble(br.readLine());
            existingService.setFee(newServiceFee);
            
            String updateQuery = "UPDATE services SET name = ?, description = ?, fee = ? WHERE id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
            preparedStatement1.setString(1, existingService.getName());
            preparedStatement1.setString(2, existingService.getDescription());
            preparedStatement1.setDouble(3, existingService.getFee());
            preparedStatement1.setInt(4, existingService.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Service details updated successfully.");
            } else {
                System.out.println("Failed to update service details.");
            }

    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void deleteService() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("Delete a Service:");

            // Get service ID to identify the service to delete
            System.out.print("Enter service ID: ");
            
            int serviceId = Integer.parseInt(br.readLine());
            
            // Delete the service record from the database
            Connection connection = ConnectionProvider.getConnection();
            
            String deleteQuery = "DELETE FROM services WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, serviceId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Service deleted successfully.");
            } else {
                System.out.println("Failed to delete service.");
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void viewSingleRecord() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("View Single Service Record:");

            // Get service ID to identify the service to view
            System.out.print("Enter service ID: ");
            
            int serviceId = Integer.parseInt(br.readLine());
            
            // Retrieve the service record from the database
            Connection connection = ConnectionProvider.getConnection();
            
            String selectQuery = "SELECT * FROM services WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, serviceId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Service service = new Service(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("fee")
                );
                System.out.println("Service Details:");
                System.out.println(service);
            } else {
                System.out.println("Service not found.");
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void viewAllRecords() {
    	try {
    		System.out.println("View All Service Records:");

            // Retrieve all service records from the database
            Connection connection = ConnectionProvider.getConnection();
            String selectQuery = "SELECT * FROM services";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("fee")
                );
                System.out.println(service);
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
