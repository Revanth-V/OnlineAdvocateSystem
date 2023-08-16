package com.advocate;

import java.sql.*;
import java.io.*;

public class Customer {
	private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    public Customer(int id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    
    public static void registerCustomer() {
    	
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	
        	System.out.println("Register Customer:");

            // Get user input
            System.out.print("Enter first name: ");
            String firstName = br.readLine();
            System.out.print("Enter last name: ");
            String lastName = br.readLine();
            System.out.print("Enter email: ");
            String email = br.readLine();
            System.out.print("Enter phone: ");
            String phone = br.readLine();

            // Create a Customer object with the provided data
            Customer newCustomer = new Customer(0, firstName, lastName, email, phone);
            
            // Insert the customer record into the database
            Connection connection = ConnectionProvider.getConnection();
            
            String insertQuery = "INSERT INTO customers (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, newCustomer.getFirstName());
            preparedStatement.setString(2, newCustomer.getLastName());
            preparedStatement.setString(3, newCustomer.getEmail());
            preparedStatement.setString(4, newCustomer.getPhone());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer registered successfully.");
            } else {
                System.out.println("Failed to register customer.");
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void modifyCustomerDetails() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("Modify Customer Details:");

            // Get customer ID to identify the customer to modify
            System.out.print("Enter customer ID: ");
            
            int customerId = Integer.parseInt(br.readLine());
            
            // Retrieve the existing customer details from the database
            Customer existingCustomer = null;
            
            Connection connection = ConnectionProvider.getConnection();
            
            String selectQuery = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, customerId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                existingCustomer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
            } else {
                System.out.println("Customer not found.");
                return;
            }
            
            // Get updated customer details from the user
            System.out.print("Enter new first name (" + existingCustomer.getFirstName() + "): ");
            String newFirstName = br.readLine();
            if (!newFirstName.isEmpty()) {
                existingCustomer.setFirstName(newFirstName);
            }

            System.out.print("Enter new last name (" + existingCustomer.getLastName() + "): ");
            String newLastName = br.readLine();
            if (!newLastName.isEmpty()) {
                existingCustomer.setLastName(newLastName);
            }

            System.out.print("Enter new email (" + existingCustomer.getEmail() + "): ");
            String newEmail = br.readLine();
            if (!newEmail.isEmpty()) {
                existingCustomer.setEmail(newEmail);
            }

            System.out.print("Enter new phone (" + existingCustomer.getPhone() + "): ");
            String newPhone = br.readLine();
            if (!newPhone.isEmpty()) {
                existingCustomer.setPhone(newPhone);
            }
            
            String updateQuery = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
            preparedStatement1.setString(1, existingCustomer.getFirstName());
            preparedStatement1.setString(2, existingCustomer.getLastName());
            preparedStatement1.setString(3, existingCustomer.getEmail());
            preparedStatement1.setString(4, existingCustomer.getPhone());
            preparedStatement1.setInt(5, existingCustomer.getId());
            
            int rowsAffected = preparedStatement1.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer details updated successfully.");
            } else {
                System.out.println("Failed to update customer details.");
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void deleteCustomerRecord() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("Delete Customer Record:");

            // Get customer ID to identify the customer to delete
            System.out.print("Enter customer ID: ");
            
            int customerId = Integer.parseInt(br.readLine());
            
            Connection connection = ConnectionProvider.getConnection();
            
            String deleteQuery = "DELETE FROM customers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, customerId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer record deleted successfully.");
            } else {
                System.out.println("Failed to delete customer record.");
            }
    	} catch (Exception e) {
    		
    	}
    }
    
    public static void viewSingleRecord() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("View Single Customer Record:");

            // Get customer ID to identify the customer to view
            System.out.print("Enter customer ID: ");
            
            int customerId = Integer.parseInt(br.readLine());
            
            Connection connection = ConnectionProvider.getConnection();
            
            String selectQuery = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, customerId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
                System.out.println("Customer Details:");
                System.out.println(customer);
            } else {
                System.out.println("Customer not found.");
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void viewAllRecords() {
    	try {
        	System.out.println("View All Customer Records:");

            // Retrieve all customer records from the database
        	Connection connection = ConnectionProvider.getConnection();
        	
        	String selectQuery = "SELECT * FROM customers";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
                System.out.println(customer);
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}

