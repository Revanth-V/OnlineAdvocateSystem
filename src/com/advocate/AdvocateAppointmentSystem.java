package com.advocate;

import java.io.*;

public class AdvocateAppointmentSystem {
	public static void main(String args[]) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int flag = 0;
			
			while (flag == 0) {
				System.out.println("Welcome to the Online Advocate Appointment System.");
				System.out.println("Please enter a number to choose from the menu given below:");
				
				System.out.println("Main Menu:");
				System.out.println("1. Customer");
				System.out.println("2. Advocate");
				System.out.println("3. Service");
				System.out.println("4. Exit");
				
				int ch = Integer.parseInt(br.readLine());
				
				switch(ch) {
				case 1: 
					System.out.println("You have selected Customer.");
					System.out.println("Please enter a number to choose from the menu given below:");
					
					System.out.println("Customer Menu:");
					System.out.println("1. Register Customer");
					System.out.println("2. Modify Customer details");
					System.out.println("3. Delete Customer record");
					System.out.println("4. View single record");
					System.out.println("5. View all records");
					System.out.println("0. Exit");
					
					int ch1 = Integer.parseInt(br.readLine());
					
					switch(ch1) {
					case 1:
	                    // Register Customer
	                    // Create a Customer object, take input from the user, and insert into the database
						Customer.registerCustomer();
						break;
	                case 2:
	                    // Modify Customer details
	                    // Update the customer record in the database
	                	Customer.modifyCustomerDetails();
	                    break;
	                case 3:
	                    // Delete Customer record
	                    // Delete the customer record from the database
	                	Customer.deleteCustomerRecord();
	                    break;
	                case 4:
	                    // View single record
	                    // Retrieve and display a single customer record
	                	Customer.viewSingleRecord();
	                    break;
	                case 5:
	                    // View all records
	                    // Retrieve and display all customer records
	                	Customer.viewAllRecords();
	                    break;
	                case 0:
	                	// Exit from the application
	                	flag = 1;
	                    break;
	                default:
	                    System.out.println("Invalid choice");
					}
					
					break;
					
				case 2:
					System.out.println("You have selected Advocate.");
					System.out.println("Please enter a number to choose from the menu given below:");
					
					System.out.println("Advocate Menu:");
					System.out.println("1. Book an appointment");
					System.out.println("2. Modify appointment details");
					System.out.println("3. Delete an appointment");
					System.out.println("4. View single record");
					System.out.println("5. View all records");
					System.out.println("0. Exit");
					
					int ch2 = Integer.parseInt(br.readLine());
					
					switch(ch2) {
					case 1:
	                    // Book an appointment
	                    // Create an Appointment object, take input from the user, and insert into the database
						Advocate.bookAppointment();
	                    break;
	                case 2:
	                    // Modify appointment details
	                    // Update the appointment record in the database
	                	Advocate.modifyAppointmentDetails();
	                    break;
	                case 3:
	                    // Delete an appointment
	                    // Delete the appointment record from the database
	                	Advocate.deleteAppointment();
	                    break;
	                case 4:
	                    // View single record
	                    // Retrieve and display a single appointment record
	                	Advocate.viewSingleRecord();
	                    break;
	                case 5:
	                    // View all records
	                    // Retrieve and display all appointment records
	                	Advocate.viewAllRecords();
	                    break;
	                case 0:
	                	// Exit from the application
	                	flag = 1;
	                    break;
	                default:
	                    System.out.println("Invalid choice");
					}
					
					break;
		            
				case 3:
					System.out.println("You have selected Service.");
					System.out.println("Please enter a number to choose from the menu given below:");
					
					System.out.println("Service Menu:");
		            System.out.println("1. Add a new service");
		            System.out.println("2. Modify service details");
		            System.out.println("3. Delete a service");
		            System.out.println("4. View single record");
		            System.out.println("5. View all records");
		            System.out.println("0. Exit");
		            
		            int ch3 = Integer.parseInt(br.readLine());
		            
		            switch(ch3) {
		            case 1:
	                    // Add a new service
	                    // Create a Service object, take input from the user, and insert into the database
		            	Service.addNewService();
	                    break;
	                case 2:
	                    // Modify service details
	                    // Update the service record in the database
	                	Service.modifyServiceDetails();
	                    break;
	                case 3:
	                    // Delete a service
	                    // Delete the service record from the database
	                	Service.deleteService();
	                    break;
	                case 4:
	                    // View single record
	                    // Retrieve and display a single service record
	                	Service.viewSingleRecord();
	                    break;
	                case 5:
	                    // View all records
	                    // Retrieve and display all service records
	                	Service.viewAllRecords();
	                    break;
	                case 0:
	                	// Exit from the application
	                	flag = 1;
	                    break;
	                default:
	                    System.out.println("Invalid choice");
		            }
		            
		            break;
		            
				case 4:
					// Exit from the application
					flag = 1;
					break;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
