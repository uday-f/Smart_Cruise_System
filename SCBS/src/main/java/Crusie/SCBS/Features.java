package Crusie.SCBS;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class Features {
	Scanner input = new Scanner(System.in);
	String st1;
//	String fileName = "/home/v2stech/Desktop/";
	FileReader fr;
	List<Customer> cust_data = new ArrayList<Customer>();
	List<book> book_data = new ArrayList<book>();

	public boolean validate_email(String email) {
		if (email.charAt(0) >= 'a' && email.charAt(0) <= 'z' && email.contains(".")
				&& (email.endsWith(".com") || email.endsWith(".in"))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void csvtolist() {
		try {
			System.out.println("Enter the name of data file");
			String fi_nm = input.next();
			// fr = new FileReader("Cruise_Data.csv");
			fr = new FileReader(fi_nm);
			CSVReader reader = new CSVReader(fr);
			String[] nextLine;

			int c = 0;
			while ((nextLine = reader.readNext()) != null) {
				if(validate_email(nextLine[2]))
				{Customer obj = new Customer(nextLine[0], nextLine[1], nextLine[2]);
				cust_data.add(obj);
				c++;}
			}
			System.out.println("number of records read :" + c);

		} catch (IOException | CsvException e1) {
			System.out.println("File Not found");
		}

	}

	// convert csv to list
	public void addtolist() {
		System.out.println("Customer Id \t Name \t Email");
		for (Customer one : cust_data) {
			System.out.println(one.getCustID() + "\t" + one.getName() + "\t" + one.getEmailId());
		}
	}

	// search using proper name
	public void searchbyName() {
		String nm;
		nm = input.nextLine();
		boolean flag = false;
		for (Customer one : cust_data) {
			if (one.getName().equals(nm)) {
				System.out.println(one.getCustID() + "\t" + one.getName() + "\t" + one.getEmailId());
				flag = true;
			}
		}

		if (flag == false) {
			System.out.println("Please check name , " + nm + "not present in system.");
		}
	}

	// search using any part name
	public void searchbyMatchName() {
		String nm;
		System.out.print("Enter any part of name ---> ");
		Scanner sc = new Scanner(System.in);
		nm = sc.nextLine();
		int c = 0;
		for (Customer one : cust_data) {
			if (one.getName().contains(nm) && c == 0) {
				System.out.println("Customer Id \t Name \t Email");
			}
			if (one.getName().contains(nm)) {
				System.out.println(one.getCustID() + "\t" + one.getName() + "\t" + one.getEmailId());
				c++;
			}
		}
		if (c == 0) {
			System.out.println("Enter name part details not found");
		}
	}
	
	// search using ID
		public void searchbyMatchID() {
			String id;
			System.out.print("Enter ID ---> ");
			Scanner sc = new Scanner(System.in);
			id = sc.next();
			int c = 0;
			for (Customer one : cust_data) {
				if (one.getCustID().equals(id) && c == 0) {
					System.out.println("Customer Id \t Name \t Email");
				}
				if (one.getCustID().equals(id)) {
					System.out.println(one.getCustID() + "\t" + one.getName() + "\t" + one.getEmailId());
					c++;
				}
			}
			if (c == 0) {
				System.out.println("Enter Id details not found");
			}
		}

	public void book_cruise() {
		System.out.println("Allow Customer to book cruise on :");
		String date, nm, to = null, from = null, des = null;
		Scanner s = new Scanner(System.in);
		date = datecheck();
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Name : ");
		nm = s1.nextLine();
		int c = 0;
		for (Customer cust : cust_data) {
			if (cust.getName().equals(nm)) {
				System.out.println("Enter To : ");
				to = input.next();
				System.out.println("Enter Description : ");
				des = input.next();
				from = "cruisebooker@gmail.com";
				to = cust.getEmailId();
				book b = new book();
				b.setBcustid(cust.getCustID());
				b.setBname(cust.getName());
				b.setBdate(date);
				book_data.add(b);
				c++;
				break;
			}
		}
		if (c != 0)
			System.out.println("Booking of " + nm + " confirmed\nDetails as follow \nFrom : " 
		+ from + "\nTo : " + to
					+ "\nDescription : " + des+"\nBook Date : ");
		else
			System.out.println("Name not found");
	}

	public void printbookedcruise() {
		System.out.println("View customer who booked cruise on : ");
		String d = datecheck();
		for (book b : book_data) {
			if (b.getBdate().equals(d))
				System.out.println(b.getBcustid() + "\t" + b.getBname() + "\t" + b.getBdate());
		}
		System.out.println("Number of attendees : " + book_data.size());
	}

	public void savefile(String date) {
		if (book_data.isEmpty()) {
			System.out.println("No booking found add first");
		} else {
			String path = ("Cruise_Data2.csv");
			String filename = "Cruise" + date + ".csv";
			File f = new File(filename);
			FileWriter writer;
			boolean flag = false;
			try {
				writer = new FileWriter(f);
				for (book b : book_data) {
					if (b.getBdate().equals(date)) {
						writer.append(b.getBcustid() + "," + b.getBdate() + "," + b.getBname() + "\n");
						flag = true;
					}
				}
				writer.close();
				if (flag = false) {
					System.out.println("No booking found on " + date);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean checkreadcsv() {
		if (cust_data.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public String datecheck() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Date in DD/MM/YYYY format");
		System.out.println("Enter Year");
		int year=sc.nextInt();
		while(year<1000 || year>2022)
		{
			System.out.println("Enter correct year");
			year=sc.nextInt();
		}
		System.out.println("Enter Month");
		int month=sc.nextInt();
		while(month>12 || month<=0)
		{
			System.out.println("Enter correct month");
			month=sc.nextInt();
		}
		System.out.println("Enter Day");
		int day=sc.nextInt();
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
		{
			while(day<=0 || day>31)
			{
				System.out.println("Enter correct Day");
				day=sc.nextInt();
			}
		}
		else if(month==2)
		{
			if(year%4==0)
			{
				while(day<=0 || day>29)
				{
					System.out.println("Enter correct Day");
					day=sc.nextInt();
				}
			}
			else
			{
				while(day<=0 || day>28)
				{
					System.out.println("Enter correct Day");
					day=sc.nextInt();
				}
			}
			
		}
		else
		{
			while(day<=0 || day>30)
			{
				System.out.println("Enter correct Day");
				day=sc.nextInt();
			}
		}
		
		String date=(day+"/"+month+"/"+year);
		
		return date;
	}
	
	public String datedcheck() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Date in DD/MM/YYYY format");
		System.out.println("Enter Year");
		int year=sc.nextInt();
		while(year<1000 || year>2022)
		{
			System.out.println("Enter correct year");
			year=sc.nextInt();
		}
		System.out.println("Enter Month");
		int month=sc.nextInt();
		while(month>12 || month<=0)
		{
			System.out.println("Enter correct month");
			month=sc.nextInt();
		}
		System.out.println("Enter Day");
		int day=sc.nextInt();
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
		{
			while(day<=0 || day>31)
			{
				System.out.println("Enter correct Day");
				day=sc.nextInt();
			}
		}
		else if(month==2)
		{
			if(year%4==0)
			{
				while(day<=0 || day>29)
				{
					System.out.println("Enter correct Day");
					day=sc.nextInt();
				}
			}
			else
			{
				while(day<=0 || day>28)
				{
					System.out.println("Enter correct Day");
					day=sc.nextInt();
				}
			}
			
		}
		else
		{
			while(day<=0 || day>30)
			{
				System.out.println("Enter correct Day");
				day=sc.nextInt();
			}
		}
		
		String date=(day+""+month+""+year);
		
		return date;
	}

	


	
}
