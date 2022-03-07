package Crusie.SCBS;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Features {
	public static void main(String[] args) {
		System.out.println(" -------------------------------------------------");
		System.out.println("|           Smart Cruise Booking System           |");
		System.out.println(" -------------------------------------------------");

		int ch;
		Scanner input = new Scanner(System.in);
		Features f = new Features();
		do {
			System.out.print("\n===================== MAIN MENU =====================\n"
					+ "1.Read Customer data into list from file \n" + "2.Display all customer id and names from list \n"
					+ "3.Search for customer in list \n" + "4.Allow customer to book cruise \n"
					+ "5.View customer who booked cruise \n" + "6.Save customers to file \n" + "0.Exit \n"
					+ "Enter your option ---> ");
			ch = input.nextInt();
			switch (ch) {
			case 1: // Read Customer data into list from file
				f.csvtolist();
				break;

			case 2: // Display all customer id and names from list
				if (f.checkreadcsv() == true)
					f.addtolist();
				else
					System.out.println("You have to read any csv file first for that Choose option 1 ");
				break;

			case 3: // Search for customer in list
//				f.searchCustomerbyName();
				if (f.checkreadcsv() == true) {
					System.out.println("Search booked cruise details by \n1.Name \n2.ID");
					int ser_ch = input.nextInt();

					switch (ser_ch) {
					case 1:
						f.searchbyMatchName();
						break;
					case 2:
						f.searchbyMatchID();
						break;
					}

				}

				else {

					System.out.println("You have to read any csv file first for that Choose option 1 ");
				}
				break;

			case 4: // Allow customer to book cruise
				if (f.checkreadcsv() == true)
					f.book_cruise();
				else
					System.out.println("You have to read any csv file first for that Choose option 1 ");
				break;

			case 5: // View customer who booked cruise
				if (f.checkreadcsv() == true) {
					f.printbookedcruise();
				} else {

					System.out.println("You have to read any csv file first for that Choose option 1 ");
				}
				break;

			case 6: // Save customers to file
				if (f.checkreadcsv() == true) {
					System.out.println("Cruise Date");
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
					
					String d=(day+"/"+month+"/"+year);
					String d1=(day+""+month+""+year);	
					f.savefile(d,d1);
//					f.savefile2(d);
				} else
					System.out.println("You have to read any csv file first for that Choose option 1 ");
				break;

			case 0:
				System.out.println("Thank you");
				break;

			default:
				System.out.println("You enter wrong option number");
				break;
			}
		} while (ch != 0);

	}
}
