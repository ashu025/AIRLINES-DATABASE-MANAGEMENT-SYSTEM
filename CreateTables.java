package DBMS;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//1602-19-737-068 M Ashwini
@SuppressWarnings("serial")
public class CreateTables extends Frame implements ActionListener 
{
	MenuBar mb;
	MenuItem m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20;
	
	Menu employees,passengers,airport,flight_hours,payments;
	Button insertButton, submit;
	
	TextField e_idText,e_nameText,e_ageText,e_phoneText,e_roleText,e_salaryText;//employees
	TextField p_idText,p_f_nameText,p_l_nameText,p_countryText,p_phoneText;//passengers
	TextField a_idText,a_nameText,a_countryText,a_stateText,a_cityText,a_zipText;//airport
	TextField airplane_noText,departure_airportText,departure_timeText,arrival_airportText,arrival_timeText;//flight_hours
	TextField pay_idText,amountText,discountText,taxText,total_amountText;//payments
	
	TextArea errorText;
	Connection connection;
	Statement statement;
	
	
	//For updates
	Button modify;
	List employeesList,passengersList,airportList,flight_hoursList,paymentsList;
	
	ResultSet rs;
	
	
		//For delete
		Button deleteRowButton;
		
		public CreateTables()
		{
			try 
			{
				Class.forName ("oracle.jdbc.driver.OracleDriver");
			} 
			catch (Exception e) 
			{
				System.err.println("Unable to find and load driver");
				System.exit(1);
			}
			connectToDB ();
		}
		
		public void connectToDB() 
	    {
			try 
			{
			  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ashwini","vasavi");
			  statement = connection.createStatement();

			} 
			catch (SQLException connectException) 
			{
			  System.out.println(connectException.getMessage());
			  System.out.println(connectException.getSQLState());
			  System.out.println(connectException.getErrorCode());
			  System.exit(1);
			}
	    }
		public void buildFrame()
		{
			//Basic Frame Properties
			setTitle("Airlines Management System");
			setSize(500, 600);
			setVisible(true);
			
			//menubar
			mb = new MenuBar();
			setMenuBar(mb);  
	        setSize(550,500);  
	        setLayout(null);  
	        setVisible(true);
	        
	        //Employees 
	        employees=new Menu("Employees");  
	        
	         m1=new MenuItem("Insert Employees");  
	         m2=new MenuItem("Update Employees");  
	         m3=new MenuItem("Delete Employees");  
	         m4=new MenuItem("View Employees");
	           
	        employees.add(m1);  
	        employees.add(m2);  
	        employees.add(m3); 
	        employees.add(m4);
	        
	        mb.add(employees);
	        
	        //Passengers
	        passengers=new Menu("Passengers"); 
	        m5=new MenuItem("Insert Passengers");
	        m6=new MenuItem("Update Passengers");
	        m7=new MenuItem("Delete Passengers");
	        m8=new MenuItem("View Passengers");
	        
	        passengers.add(m5);
	        passengers.add(m6);
	        passengers.add(m7);
	        passengers.add(m8);
	        
	        mb.add(passengers);
	        
	        //Airport
	        airport=new Menu("Airport");  
	        m9=new MenuItem("Insert Airport");
	        m10=new MenuItem("Update Airport");
	        m11=new MenuItem("Delete Airport");
	        m12=new MenuItem("View Airport");
	       
	        airport.add(m9);
	        airport.add(m10);
	        airport.add(m11);
	        airport.add(m12);
	        
	        mb.add(airport);
	        
	        //Flight Hours
	        flight_hours=new Menu("Flight_Hours");
	        m13=new MenuItem("Insert Flight_Hours");  
	        m14=new MenuItem("Update Flight_Hours");  
	        m15=new MenuItem("Delete Flight_Hours");  
	        m16=new MenuItem("View Flight_Hours");
	           
	        flight_hours.add(m13);  
	        flight_hours.add(m14);  
	        flight_hours.add(m15); 
	        flight_hours.add(m16);
	        
	        mb.add(flight_hours);
	        
	        //Payments
	        payments=new Menu("Payments");  
	        
	        m17=new MenuItem("Insert Payments");  
	        m18=new MenuItem("Update Payments");  
	        m19=new MenuItem("Delete Payments");  
	        m20=new MenuItem("View Payments");
	           
	        payments.add(m17);  
	        payments.add(m18);  
	        payments.add(m19); 
	        payments.add(m20);
	        
	        mb.add(payments);
	        
	      //Registering action Listeners
	        m1.addActionListener(this);
	        m2.addActionListener(this);
	        m3.addActionListener(this);
	        m4.addActionListener(this);
	        m5.addActionListener(this);
	        m6.addActionListener(this);
	        m7.addActionListener(this);
	        m8.addActionListener(this);
	        m9.addActionListener(this);
	        m10.addActionListener(this);
	        m11.addActionListener(this);
	        m12.addActionListener(this);
	        m13.addActionListener(this);
	        m14.addActionListener(this);
	        m15.addActionListener(this);
	        m16.addActionListener(this);
	        m17.addActionListener(this);
	        m18.addActionListener(this);
	        m19.addActionListener(this);
	        m20.addActionListener(this);
	        
		}
		public void actionPerformed(ActionEvent ae)
		{
			String arg = ae.getActionCommand();
			if(arg.equals("Insert Employees"))
				this.buildGUIEmployees();
			if(arg.equals("Update Employees"))
				this.updateEmployeesGUI();
			if(arg.equals("Delete Employees"))
				this.deleteGUIEmployees();
			if(arg.equals("View Employees"))
				this.viewEmployeesGUI();
			if(arg.equals("Insert Passengers"))
				this.buildGUIPassengers();
			if(arg.equals("Update Passengers"))
				this.updatePassengersGUI();
			if(arg.equals("Delete Passengers"))
				this.deleteGUIPassengers();
			if(arg.equals("View Passengers"))
				this.viewPassengersGUI();
			if(arg.equals("Insert Airport"))
				this.buildGUIAirport();
			if(arg.equals("Update Airport"))
				this.updateAirportGUI();
			if(arg.equals("Delete Airport"))
				this.deleteGUIAirport();
			if(arg.equals("View Airport"))
				this.viewAirportGUI();
			if(arg.equals("Insert Flight_Hours"))
				this.buildGUIFlight_Hours();
			if(arg.equals("Update Flight_Hours"))
				this.updateFlight_HoursGUI();
			if(arg.equals("Delete Flight_Hours"))
				this.deleteGUIFlight_Hours();
			if(arg.equals("View Flight_Hours"))
				this.viewFlight_HoursGUI();
			if(arg.equals("Insert Payments"))
				this.buildGUIPayments();
			if(arg.equals("Update Payments"))
				this.updatePaymentsGUI();
			if(arg.equals("Delete Payments"))
				this.deleteGUIPayments();
			if(arg.equals("View Payments"))
				this.viewPaymentsGUI();
		}
		public void buildGUIEmployees() 
		{	
			removeAll();
			//Handle Insert Account Button
			insertButton = new Button("Submit");
			insertButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{	
						String query= "INSERT INTO employees VALUES('" + e_idText.getText() + "', '" + e_nameText.getText() + "','" + e_ageText.getText() + "','" + e_phoneText.getText() + "','" + e_roleText.getText()+ "','" +e_salaryText.getText()+ "')";
						  int i = statement.executeUpdate(query);
						  errorText.append("\nInserted " + i + " rows successfully");
						} 
						catch (SQLException insertException) 
						{
						  displaySQLErrors(insertException);
						}
					}
				});	
			e_idText = new TextField(15);
			e_nameText = new TextField(15);
			e_ageText = new TextField(15);
			e_phoneText= new TextField(15);
			e_roleText= new TextField(15);
			e_salaryText= new TextField(15);

			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Employee ID:"));
			first.add(e_idText);
			first.add(new Label("Employee Name:"));
			first.add(e_nameText);
			first.add(new Label("Employee Age:"));
			first.add(e_ageText);
			first.add(new Label("Employee Phone Number:"));
			first.add(e_phoneText);
			first.add(new Label("Employee Role:"));
			first.add(e_roleText);
			first.add(new Label("Employee Salary:"));
			first.add(e_salaryText);
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(insertButton);
	        		second.setBounds(125,220,150,100);         
			
			Panel third = new Panel();
			third.add(errorText);
			third.setBounds(125,320,300,200);
			
			//setLayout(null);

			add(first);
			add(second);
			add(third);
			
			setLayout(new FlowLayout());
			setVisible(true);
		    
		}
		public void buildGUIPassengers() 
		{	
			removeAll();
			//Handle Insert Account Button
			insertButton = new Button("Submit");
			insertButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{	
						String query= "INSERT INTO passengers VALUES('" + p_idText.getText() + "', '" + p_f_nameText.getText() + "','" + p_l_nameText.getText() + "','" + p_countryText.getText() + "','" + p_phoneText.getText()+"')";
						  int i = statement.executeUpdate(query);
						  errorText.append("\nInserted " + i + " rows successfully");
						} 
						catch (SQLException insertException) 
						{
						  displaySQLErrors(insertException);
						}
					}
				});	
			p_idText = new TextField(15);
			p_f_nameText = new TextField(15);
			p_l_nameText = new TextField(15);
			p_countryText= new TextField(15);
			p_phoneText= new TextField(15);

			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Passenger ID:"));
			first.add(p_idText);
			first.add(new Label("Passenger First Name:"));
			first.add(p_f_nameText);
			first.add(new Label("Passenger Last Name:"));
			first.add(p_l_nameText);
			first.add(new Label("Passenger Country:"));
			first.add(p_countryText);
			first.add(new Label("Passenger Phone Number:"));
			first.add(p_phoneText);
			
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(insertButton);
	        		second.setBounds(125,220,150,100);         
			
			Panel third = new Panel();
			third.add(errorText);
			third.setBounds(125,320,300,200);
			
			//setLayout(null);

			add(first);
			add(second);
			add(third);
			
			setLayout(new FlowLayout());
			setVisible(true);
		    
		}
		public void buildGUIAirport() 
		{	
			removeAll();
			//Handle Insert Account Button
			insertButton = new Button("Submit");
			insertButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{	
						String query= "INSERT INTO airport VALUES('" + a_idText.getText() + "', '" + a_nameText.getText() + "','" + a_countryText.getText() + "','" + a_stateText.getText() + "','" + a_cityText.getText()+"', '"+a_zipText.getText()+"')";
						  int i = statement.executeUpdate(query);
						  errorText.append("\nInserted " + i + " rows successfully");
						} 
						catch (SQLException insertException) 
						{
						  displaySQLErrors(insertException);
						}
					}
				});	
			a_idText = new TextField(15);
			a_nameText = new TextField(15);
			a_countryText = new TextField(15);
			a_stateText= new TextField(15);
			a_cityText= new TextField(15);
			a_zipText= new TextField(15);

			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airport ID:"));
			first.add(a_idText);
			first.add(new Label("Airport Name:"));
			first.add(a_nameText);
			first.add(new Label("Airport Country:"));
			first.add(a_countryText);
			first.add(new Label("Airport State:"));
			first.add(a_stateText);
			first.add(new Label("Airport City:"));
			first.add(a_cityText);
			first.add(new Label("Airport Zip:"));
			first.add(a_zipText);
			
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(insertButton);
	        		second.setBounds(125,220,150,100);         
			
			Panel third = new Panel();
			third.add(errorText);
			third.setBounds(125,320,300,200);
			
			//setLayout(null);

			add(first);
			add(second);
			add(third);
			
			setLayout(new FlowLayout());
			setVisible(true);
		    
		}
		
		public void buildGUIFlight_Hours() 
		{	
			removeAll();
			//Handle Insert Account Button
			insertButton = new Button("Submit");
			insertButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{	
						String query= "INSERT INTO flight_hours VALUES('" + airplane_noText.getText() + "', '" + departure_airportText.getText() + "','" + departure_timeText.getText() + "','" + arrival_airportText.getText() + "','" + arrival_timeText.getText()+"')";
						  int i = statement.executeUpdate(query);
						  errorText.append("\nInserted " + i + " rows successfully");
						} 
						catch (SQLException insertException) 
						{
						  displaySQLErrors(insertException);
						}
					}
				});	
			airplane_noText = new TextField(15);
			departure_airportText = new TextField(15);
			departure_timeText = new TextField(15);
			arrival_airportText= new TextField(15);
			arrival_timeText= new TextField(15);

			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airplane No:"));
			first.add(airplane_noText);
			first.add(new Label("Departure Airport:"));
			first.add(departure_airportText);
			first.add(new Label("Departure Time:"));
			first.add(departure_timeText);
			first.add(new Label("Arrival Airport:"));
			first.add(arrival_airportText);
			first.add(new Label("Arrival Time:"));
			first.add(arrival_timeText);
			
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(insertButton);
	        		second.setBounds(125,220,150,100);         
			
			Panel third = new Panel();
			third.add(errorText);
			third.setBounds(125,320,300,200);
			
			//setLayout(null);

			add(first);
			add(second);
			add(third);
			
			setLayout(new FlowLayout());
			setVisible(true);
		    
		}
		
		public void buildGUIPayments() 
		{	
			removeAll();
			//Handle Insert Account Button
			insertButton = new Button("Submit");
			insertButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{	
						String query= "INSERT INTO payments VALUES('" + pay_idText.getText() + "', '" + amountText.getText() + "','" + discountText.getText() + "','" + taxText.getText() + "','" + total_amountText.getText()+ "')";
						  int i = statement.executeUpdate(query);
						  errorText.append("\nInserted " + i + " rows successfully");
						} 
						catch (SQLException insertException) 
						{
						  displaySQLErrors(insertException);
						}
					}
				});	
			pay_idText = new TextField(15);
			amountText = new TextField(15);
			discountText = new TextField(15);
			taxText= new TextField(15);
			total_amountText= new TextField(15);
			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Payment ID:"));
			first.add(pay_idText);
			first.add(new Label("Payment Amount:"));
			first.add(amountText);
			first.add(new Label("Payment discount:"));
			first.add(discountText);
			first.add(new Label("Payment tax:"));
			first.add(taxText);
			first.add(new Label("Payment total amount:"));
			first.add(total_amountText);
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(insertButton);
	        		second.setBounds(125,220,150,100);         
			
			Panel third = new Panel();
			third.add(errorText);
			third.setBounds(125,320,300,200);
			
			//setLayout(null);

			add(first);
			add(second);
			add(third);
			
			setLayout(new FlowLayout());
			setVisible(true);
		    
		}

		private void loadEmployees() 
		{	   
			try 
			{
			  rs = statement.executeQuery("SELECT * FROM employees");
			  while (rs.next()) 
			  {
				  employeesList.add(rs.getString("e_id"));
			  }
			} 
			catch (SQLException e) 
			{ 
			  displaySQLErrors(e);
			}
		}
		
		private void loadPassengers() 
		{	   
			try 
			{
			  rs = statement.executeQuery("SELECT * FROM passengers");
			  while (rs.next()) 
			  {
				  passengersList.add(rs.getString("p_id"));
			  }
			} 
			catch (SQLException e) 
			{ 
			  displaySQLErrors(e);
			}
		}
		
		private void loadAirport() 
		{	   
			try 
			{
			  rs = statement.executeQuery("SELECT * FROM airport");
			  while (rs.next()) 
			  {
				  airportList.add(rs.getString("a_id"));
			  }
			} 
			catch (SQLException e) 
			{ 
			  displaySQLErrors(e);
			}
		}
		
		private void loadFlight_Hours() 
		{	   
			try 
			{
			  rs = statement.executeQuery("SELECT * FROM flight_hours");
			  while (rs.next()) 
			  {
				  flight_hoursList.add(rs.getString("airplane_no"));
			  }
			} 
			catch (SQLException e) 
			{ 
			  displaySQLErrors(e);
			}
		}
		
		private void loadPayments() 
		{	   
			try 
			{
			  rs = statement.executeQuery("SELECT * FROM payments");
			  while (rs.next()) 
			  {
				  paymentsList.add(rs.getString("pay_id"));
			  }
			} 
			catch (SQLException e) 
			{ 
			  displaySQLErrors(e);
			}
		}
		public void updateEmployeesGUI()
		{
			removeAll();
			employeesList = new List(6);
			loadEmployees();
			add(employeesList);
			
			//When a list item is selected populate the text fields
			employeesList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM employees");
						while (rs.next()) 
						{
							if (rs.getString("e_id").equals(employeesList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							e_idText.setText(rs.getString("e_id"));
							e_nameText.setText(rs.getString("e_name"));
							e_ageText.setText(rs.getString("e_age"));
							e_phoneText.setText(rs.getString("e_phone"));
							e_roleText.setText(rs.getString("e_role"));
							e_salaryText.setText(rs.getString("e_salary"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	    
			//Handle Update Employees Button
			modify = new Button("Modify");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE employees "
						+ "SET e_salary=" + e_salaryText.getText() +",e_name='"+e_nameText.getText()+"',e_age="+e_ageText.getText()+",e_phone="+e_phoneText.getText()+",e_role='"+e_roleText.getText()+"'"
						+ " WHERE e_id = '" + employeesList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						employeesList.removeAll();
						loadEmployees();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			e_idText = new TextField(15);
			//e_idText.setEditable(false);
			e_nameText = new TextField(15);
			//e_nameText.setEditable(false);
			e_salaryText = new TextField(15);
			e_ageText = new TextField(15);
			//e_ageText.setEditable(false);
			e_roleText = new TextField(15);
			//e_roleText.setEditable(false);
			e_phoneText = new TextField(15);
			//e_phoneText.setEditable(false);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Employee ID:"));
			first.add(e_idText);
			first.add(new Label("Employee Name:"));
			first.add(e_nameText);
			first.add(new Label("Employee Age"));
			first.add(e_ageText);
			first.add(new Label("Employee Phone Number:"));
			first.add(e_phoneText);
			first.add(new Label("Employee Role:"));
			first.add(e_roleText);
			first.add(new Label("Employee Salary:"));
			first.add(e_salaryText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		
		public void updatePassengersGUI()
		{
			removeAll();
			passengersList = new List(6);
			loadPassengers();
			add(passengersList);
			
			//When a list item is selected populate the text fields
			passengersList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM passengers");
						while (rs.next()) 
						{
							if (rs.getString("p_id").equals(passengersList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							p_idText.setText(rs.getString("p_id"));
							p_f_nameText.setText(rs.getString("p_f_name"));
							p_l_nameText.setText(rs.getString("p_l_name"));
							p_countryText.setText(rs.getString("p_country"));
							p_phoneText.setText(rs.getString("p_phone"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	    
			//Handle Update Employees Button
			modify = new Button("Modify");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE passengers "
						+ "SET p_f_name='" + p_f_nameText.getText() +"',p_l_name='"+p_l_nameText.getText()+"',p_country='"+p_countryText.getText()+"',p_phone="+p_phoneText.getText()
						+ " WHERE p_id = '" + passengersList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						passengersList.removeAll();
						loadPassengers();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			p_idText = new TextField(15);
			p_f_nameText = new TextField(15);
			p_l_nameText = new TextField(15);
			p_countryText = new TextField(15);
			p_phoneText = new TextField(15);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Passenger ID:"));
			first.add(p_idText);
			first.add(new Label("Passenger First Name:"));
			first.add(p_f_nameText);
			first.add(new Label("Passenger Last Name"));
			first.add(p_l_nameText);
			first.add(new Label("Passenger Country:"));
			first.add(p_countryText);
			first.add(new Label("Passenger Phone Number:"));
			first.add(p_phoneText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void updateAirportGUI()
		{
			removeAll();
			airportList = new List(6);
			loadAirport();
			add(airportList);
			
			//When a list item is selected populate the text fields
			airportList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM airport");
						while (rs.next()) 
						{
							if (rs.getString("a_id").equals(airportList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							a_idText.setText(rs.getString("a_id"));
							a_nameText.setText(rs.getString("a_name"));
							a_countryText.setText(rs.getString("a_country"));
							a_stateText.setText(rs.getString("a_state"));
							a_cityText.setText(rs.getString("a_city"));
							a_zipText.setText(rs.getString("a_zip"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	    
			//Handle Update Employees Button
			modify = new Button("Modify");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE airport "
						+ "SET a_country='" + a_countryText.getText() +"',a_name='"+a_nameText.getText()+"',a_state='"+a_stateText.getText()+"',a_city='"+a_cityText.getText()+"',a_zip='"+a_zipText.getText()+"'"
						+ " WHERE a_id = '" + airportList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						airportList.removeAll();
						loadAirport();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			a_idText = new TextField(15);
			a_nameText = new TextField(15);
			a_countryText = new TextField(15);
			a_stateText = new TextField(15);
			a_cityText = new TextField(15);
			a_zipText = new TextField(15);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airport ID:"));
			first.add(a_idText);
			first.add(new Label("Airport Name:"));
			first.add(a_nameText);
			first.add(new Label("Airport Country"));
			first.add(a_countryText);
			first.add(new Label("Airport State:"));
			first.add(a_stateText);
			first.add(new Label("Airport City:"));
			first.add(a_cityText);
			first.add(new Label("Airport zip code:"));
			first.add(a_zipText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		
		public void updateFlight_HoursGUI()
		{
			removeAll();
			flight_hoursList = new List(6);
			loadFlight_Hours();
			add(flight_hoursList);
			
			//When a list item is selected populate the text fields
			flight_hoursList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM flight_hours");
						while (rs.next()) 
						{
							if (rs.getString("airplane_no").equals(flight_hoursList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							airplane_noText.setText(rs.getString("airplane_no"));
							departure_airportText.setText(rs.getString("departure_airport"));
							departure_timeText.setText(rs.getString("departure_time"));
							arrival_airportText.setText(rs.getString("arrival_airport"));
							arrival_timeText.setText(rs.getString("arrival_time"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	    
			//Handle Update Employees Button
			modify = new Button("Modify");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE flight_hours "
						+ "SET departure_airport='" + departure_airportText.getText() +"',departure_time='"+departure_timeText.getText()+"',arrival_airport='"+arrival_airportText.getText()+"',arrival_time='"+arrival_timeText.getText()+"'"
						+ " WHERE airplane_no = '" + flight_hoursList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						flight_hoursList.removeAll();
						loadFlight_Hours();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			airplane_noText = new TextField(15);
			departure_airportText = new TextField(15);
			departure_timeText = new TextField(15);
			arrival_airportText= new TextField(15);
			arrival_timeText= new TextField(15);
		
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airplane No:"));
			first.add(airplane_noText);
			first.add(new Label("Departure Airport:"));
			first.add(departure_airportText);
			first.add(new Label("Departure Time:"));
			first.add(departure_timeText);
			first.add(new Label("Arrival Airport:"));
			first.add(arrival_airportText);
			first.add(new Label("Arrival Time:"));
			first.add(arrival_timeText);
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void updatePaymentsGUI()
		{
			removeAll();
			paymentsList = new List(6);
			loadPayments();
			add(paymentsList);
			
			//When a list item is selected populate the text fields
			paymentsList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM payments");
						while (rs.next()) 
						{
							if (rs.getString("pay_id").equals(paymentsList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							pay_idText.setText(rs.getString("pay_id"));
							amountText.setText(rs.getString("amount"));
							discountText.setText(rs.getString("discount"));
							taxText.setText(rs.getString("tax"));
							total_amountText.setText(rs.getString("total_amount"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	    
			//Handle Update Employees Button
			modify = new Button("Modify");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE payments "
						+ "SET amount=" + amountText.getText() +",discount="+discountText.getText()+",tax="+taxText.getText()+",total_amount="+total_amountText.getText()+""
						+ " WHERE pay_id = '" + paymentsList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						paymentsList.removeAll();
						loadPayments();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			pay_idText = new TextField(15);
			amountText = new TextField(15);
			discountText = new TextField(15);
			taxText= new TextField(15);
			total_amountText= new TextField(15);
						
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Payment ID:"));
			first.add(pay_idText);
			first.add(new Label("Payment Amount:"));
			first.add(amountText);
			first.add(new Label("Payment discount:"));
			first.add(discountText);
			first.add(new Label("Payment tax:"));
			first.add(taxText);
			first.add(new Label("Payment total amount:"));
			first.add(total_amountText);
			first.setBounds(125,90,200,100);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		
		public void deleteGUIEmployees() 
		{	
			removeAll();
		    employeesList = new List(10);
			loadEmployees();
			add(employeesList);
			
			//When a list item is selected populate the text fields
		
			employeesList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM employees");
						while (rs.next()) 
						{
							if (rs.getString("e_id").equals(employeesList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							e_idText.setText(rs.getString("e_id"));
							e_nameText.setText(rs.getString("e_name"));
							e_ageText.setText(rs.getString("e_age"));
							e_phoneText.setText(rs.getString("e_phone"));
							e_roleText.setText(rs.getString("e_role"));
							e_salaryText.setText(rs.getString("e_salary"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});
			
			//Handle Delete employees Button
			deleteRowButton = new Button("Delete Row");
			deleteRowButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("DELETE FROM employees WHERE e_id = '" + employeesList.getSelectedItem()+"'");
						errorText.append("\nDeleted " + i + " rows successfully");
						e_idText.setText(null);
						e_nameText.setText(null);
						e_ageText.setText(null);
						e_phoneText.setText(null);
						e_roleText.setText(null);
						e_salaryText.setText(null);
						employeesList.removeAll();
						loadEmployees();
					} 
					catch (SQLException deleteException) 
					{
						displaySQLErrors(deleteException);
					}
				}
			});
			
			e_idText = new TextField(15);
			e_nameText = new TextField(15);
			e_ageText = new TextField(15);
			e_phoneText = new TextField(15);
			e_roleText = new TextField(15);
			e_salaryText = new TextField(15);
			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);
			
			e_idText.setEditable(false);
			e_nameText.setEditable(false);
			e_ageText.setEditable(false);
			e_phoneText.setEditable(false);
			e_roleText.setEditable(false);
			e_salaryText.setEditable(false);
		

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Employee ID:"));
			first.add(e_idText);
			first.add(new Label("Employee Name:"));
			first.add(e_nameText);
			first.add(new Label("Employee Age:"));
			first.add(e_ageText);
			first.add(new Label("Employee Phone Number:"));
			first.add(e_phoneText);
			first.add(new Label("Employee Role:"));
			first.add(e_roleText);
			first.add(new Label("Employee Salary:"));
			first.add(e_salaryText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(deleteRowButton);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			add(second);
			add(third);
		    

			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		
		public void deleteGUIPassengers() 
		{	
			removeAll();
		    passengersList = new List(10);
			loadPassengers();
			add(passengersList);
			
			//When a list item is selected populate the text fields
		
			passengersList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM passengers");
						while (rs.next()) 
						{
							if (rs.getString("p_id").equals(passengersList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							p_idText.setText(rs.getString("p_id"));
							p_f_nameText.setText(rs.getString("p_f_name"));
							p_l_nameText.setText(rs.getString("p_l_name"));
							p_countryText.setText(rs.getString("p_country"));
							p_phoneText.setText(rs.getString("p_phone"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});
			
			//Handle Delete employees Button
			deleteRowButton = new Button("Delete Row");
			deleteRowButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("DELETE FROM passengers WHERE p_id = '" + passengersList.getSelectedItem()+"'");
						errorText.append("\nDeleted " + i + " rows successfully");
						p_idText.setText(null);
						p_f_nameText.setText(null);
						p_l_nameText.setText(null);
						p_phoneText.setText(null);
						p_countryText.setText(null);
						passengersList.removeAll();
						loadPassengers();
					} 
					catch (SQLException deleteException) 
					{
						displaySQLErrors(deleteException);
					}
				}
			});
			p_idText = new TextField(15);
			p_f_nameText = new TextField(15);
			p_l_nameText = new TextField(15);
			p_countryText = new TextField(15);
			p_phoneText = new TextField(15);
			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);
			
			p_idText.setEditable(false);
			p_f_nameText.setEditable(false);
			p_l_nameText.setEditable(false);
			p_phoneText.setEditable(false);
			p_countryText.setEditable(false);
		
			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Passenger ID:"));
			first.add(p_idText);
			first.add(new Label("Passenger First Name:"));
			first.add(p_f_nameText);
			first.add(new Label("Passenger Last Name"));
			first.add(p_l_nameText);
			first.add(new Label("Passenger Country:"));
			first.add(p_countryText);
			first.add(new Label("Passenger Phone Number:"));
			first.add(p_phoneText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(deleteRowButton);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			add(second);
			add(third);
		    

			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		public void deleteGUIAirport() 
		{	
			removeAll();
		    airportList = new List(10);
			loadAirport();
			add(airportList);
			
			//When a list item is selected populate the text fields
		
			airportList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM airport");
						while (rs.next()) 
						{
							if (rs.getString("a_id").equals(airportList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							a_idText.setText(rs.getString("a_id"));
							a_nameText.setText(rs.getString("a_name"));
							a_countryText.setText(rs.getString("a_country"));
							a_stateText.setText(rs.getString("a_state"));
							a_cityText.setText(rs.getString("a_city"));
							a_zipText.setText(rs.getString("a_zip"));
							
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});
			
			//Handle Delete employees Button
			deleteRowButton = new Button("Delete Row");
			deleteRowButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("DELETE FROM airport WHERE a_id = '" + airportList.getSelectedItem()+"'");
						errorText.append("\nDeleted " + i + " rows successfully");
						a_idText.setText(null);
						a_nameText.setText(null);
						a_countryText.setText(null);
						a_stateText.setText(null);
						a_cityText.setText(null);
						a_zipText.setText(null);
						airportList.removeAll();
						loadAirport();
					} 
					catch (SQLException deleteException) 
					{
						displaySQLErrors(deleteException);
					}
				}
			});
			
			a_idText = new TextField(15);
			a_nameText = new TextField(15);
			a_countryText = new TextField(15);
			a_stateText = new TextField(15);
			a_cityText = new TextField(15);
			a_zipText = new TextField(15);
			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);
			
			a_idText.setEditable(false);
			a_nameText.setEditable(false);
			a_countryText.setEditable(false);
			a_stateText.setEditable(false);
			a_cityText.setEditable(false);
			a_zipText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airport ID:"));
			first.add(a_idText);
			first.add(new Label("Airport Name:"));
			first.add(a_nameText);
			first.add(new Label("Airport Country"));
			first.add(a_countryText);
			first.add(new Label("Airport State:"));
			first.add(a_stateText);
			first.add(new Label("Airport City:"));
			first.add(a_cityText);
			first.add(new Label("Airport zip code:"));
			first.add(a_zipText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(deleteRowButton);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			add(second);
			add(third);
		    

			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void deleteGUIFlight_Hours() 
		{	
			removeAll();
		    flight_hoursList = new List(10);
			loadFlight_Hours();
			add(flight_hoursList);
			
			//When a list item is selected populate the text fields
		
			flight_hoursList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM flight_hours");
						while (rs.next()) 
						{
							if (rs.getString("airplane_no").equals(flight_hoursList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							airplane_noText.setText(rs.getString("airplane_no"));
							departure_airportText.setText(rs.getString("departure_airport"));
							departure_timeText.setText(rs.getString("departure_time"));
							arrival_airportText.setText(rs.getString("arrival_airport"));
							arrival_timeText.setText(rs.getString("arrival_time"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});
			
			//Handle Delete employees Button
			deleteRowButton = new Button("Delete Row");
			deleteRowButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("DELETE FROM flight_hours WHERE airplane_no = '" + flight_hoursList.getSelectedItem()+"'");
						errorText.append("\nDeleted " + i + " rows successfully");
						airplane_noText.setText(null);
						departure_airportText.setText(null);
						departure_timeText.setText(null);
						arrival_airportText.setText(null);
						arrival_timeText.setText(null);
						flight_hoursList.removeAll();
						loadFlight_Hours();
					} 
					catch (SQLException deleteException) 
					{
						displaySQLErrors(deleteException);
					}
				}
			});
			
			airplane_noText = new TextField(15);
			departure_airportText = new TextField(15);
			departure_timeText = new TextField(15);
			arrival_airportText = new TextField(15);
			arrival_timeText = new TextField(15);
			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);
			
			airplane_noText.setEditable(false);
			departure_airportText.setEditable(false);
			departure_timeText.setEditable(false);
			arrival_airportText.setEditable(false);
			arrival_timeText.setEditable(false);
		
			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airplane No:"));
			first.add(airplane_noText);
			first.add(new Label("Departure Airport:"));
			first.add(departure_airportText);
			first.add(new Label("Departure Time:"));
			first.add(departure_timeText);
			first.add(new Label("Arrival Airport:"));
			first.add(arrival_airportText);
			first.add(new Label("Arrival Time:"));
			first.add(arrival_timeText);
			
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(deleteRowButton);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			add(second);
			add(third);
		    

			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void deleteGUIPayments() 
		{	
			removeAll();
		    paymentsList = new List(10);
			loadPayments();
			add(paymentsList);
			
			//When a list item is selected populate the text fields
		
			paymentsList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM payments");
						while (rs.next()) 
						{
							if (rs.getString("pay_id").equals(paymentsList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							pay_idText.setText(rs.getString("pay_id"));
							amountText.setText(rs.getString("amount"));
							discountText.setText(rs.getString("discount"));
							taxText.setText(rs.getString("tax"));
							total_amountText.setText(rs.getString("total_amount"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});
			
			//Handle Delete employees Button
			deleteRowButton = new Button("Delete Row");
			deleteRowButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("DELETE FROM payments WHERE pay_id = '" + paymentsList.getSelectedItem()+"'");
						errorText.append("\nDeleted " + i + " rows successfully");
						pay_idText.setText(null);
						amountText.setText(null);
						discountText.setText(null);
						taxText.setText(null);
						total_amountText.setText(null);
						paymentsList.removeAll();
						loadPayments();
					} 
					catch (SQLException deleteException) 
					{
						displaySQLErrors(deleteException);
					}
				}
			});
			
			pay_idText = new TextField(15);
			amountText = new TextField(15);
			discountText = new TextField(15);
			taxText = new TextField(15);
			total_amountText = new TextField(15);
			
			errorText = new TextArea(10, 40);
			errorText.setEditable(false);
			
			pay_idText.setEditable(false);
			amountText.setEditable(false);
			discountText.setEditable(false);
			taxText.setEditable(false);
			total_amountText.setEditable(false);
		
			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Payment ID:"));
			first.add(pay_idText);
			first.add(new Label("Payment Amount:"));
			first.add(amountText);
			first.add(new Label("Payment discount:"));
			first.add(discountText);
			first.add(new Label("Payment tax:"));
			first.add(taxText);
			first.add(new Label("Payment total amount:"));
			first.add(total_amountText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(deleteRowButton);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			add(second);
			add(third);
		    

			setLayout(new FlowLayout());
			setVisible(true);
		}
		
		public void viewEmployeesGUI() 
		{	
			removeAll();
			employeesList = new List(6);
			loadEmployees();
			add(employeesList);
			
			//When a list item is selected populate the text fields
			employeesList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM employees");
						while (rs.next()) 
						{
							if (rs.getString("e_id").equals(employeesList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							e_idText.setText(rs.getString("e_id"));
							e_nameText.setText(rs.getString("e_name"));
							e_ageText.setText(rs.getString("e_age"));
							e_phoneText.setText(rs.getString("e_phone"));
							e_roleText.setText(rs.getString("e_role"));
							e_salaryText.setText(rs.getString("e_salary"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	  
			//Handle Update Menu Button
			modify = new Button("Update Employees");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE employees "
						+ "SET e_salary=" + e_salaryText.getText()  
						+ " WHERE e_id = '" + employeesList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						employeesList.removeAll();
						loadEmployees();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			
			e_idText = new TextField(15);
			e_idText.setEditable(false);
			e_nameText = new TextField(15);
			e_nameText.setEditable(false);
			e_ageText = new TextField(15);
			e_ageText.setEditable(false);
			e_phoneText = new TextField(15);
			e_phoneText.setEditable(false);
			e_roleText = new TextField(15);
			e_roleText.setEditable(false);
			e_salaryText = new TextField(15);
			e_salaryText.setEditable(false);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Employee ID:"));
			first.add(e_idText);
			first.add(new Label("Employee Name:"));
			first.add(e_nameText);
			first.add(new Label("Employee Age:"));
			first.add(e_ageText);
			first.add(new Label("Employee Phone Number:"));
			first.add(e_phoneText);
			first.add(new Label("Employee Role:"));
			first.add(e_roleText);
			first.add(new Label("Employee Salary:"));
			first.add(e_salaryText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			//second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		
		public void viewPassengersGUI() 
		{	
			removeAll();
			passengersList = new List(6);
			loadPassengers();
			add(passengersList);
			
			//When a list item is selected populate the text fields
			passengersList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM passengers");
						while (rs.next()) 
						{
							if (rs.getString("p_id").equals(passengersList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							p_idText.setText(rs.getString("p_id"));
							p_f_nameText.setText(rs.getString("p_f_name"));
							p_l_nameText.setText(rs.getString("p_l_name"));
							p_countryText.setText(rs.getString("p_country"));
							p_phoneText.setText(rs.getString("p_phone"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	  
			//Handle Update Menu Button
			modify = new Button("Update Passengers");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE passengers "
						+ "SET p_f_name='" + p_f_nameText.getText()+"'"  
						+ " WHERE p_id = '" + passengersList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						passengersList.removeAll();
						loadPassengers();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			
			p_idText = new TextField(15);
			p_idText.setEditable(false);
			p_f_nameText = new TextField(15);
			p_f_nameText.setEditable(false);
			p_l_nameText = new TextField(15);
			p_l_nameText.setEditable(false);
			p_phoneText = new TextField(15);
			p_phoneText.setEditable(false);
			p_countryText = new TextField(15);
			p_countryText.setEditable(false);
			

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Passenger ID:"));
			first.add(p_idText);
			first.add(new Label("Passenger First Name:"));
			first.add(p_f_nameText);
			first.add(new Label("Passenger Last Name"));
			first.add(p_l_nameText);
			first.add(new Label("Passenger Country:"));
			first.add(p_countryText);
			first.add(new Label("Passenger Phone Number:"));
			first.add(p_phoneText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			//second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);
			
		}
		
		public void viewAirportGUI() 
		{	
			removeAll();
			airportList = new List(6);
			loadAirport();
			add(airportList);
			
			//When a list item is selected populate the text fields
			airportList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM airport");
						while (rs.next()) 
						{
							if (rs.getString("a_id").equals(airportList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							a_idText.setText(rs.getString("a_id"));
							a_nameText.setText(rs.getString("a_name"));
							a_countryText.setText(rs.getString("a_country"));
							a_stateText.setText(rs.getString("a_state"));
							a_cityText.setText(rs.getString("a_city"));
							a_zipText.setText(rs.getString("a_zip"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	  
			//Handle Update Airport Button
			modify = new Button("Update Airport");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE airport "
						+ "SET a_name='" + a_nameText.getText()+"'"  
						+ " WHERE a_id = '" + airportList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						airportList.removeAll();
						loadAirport();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			
			a_idText = new TextField(15);
			a_idText.setEditable(false);
			a_nameText = new TextField(15);
			a_nameText.setEditable(false);
			a_countryText = new TextField(15);
			a_countryText.setEditable(false);
			a_stateText = new TextField(15);
			a_stateText.setEditable(false);
			a_cityText = new TextField(15);
			a_cityText.setEditable(false);
			a_zipText = new TextField(15);
			a_zipText.setEditable(false);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airport ID:"));
			first.add(a_idText);
			first.add(new Label("Airport Name:"));
			first.add(a_nameText);
			first.add(new Label("Airport Country"));
			first.add(a_countryText);
			first.add(new Label("Airport State:"));
			first.add(a_stateText);
			first.add(new Label("Airport City:"));
			first.add(a_cityText);
			first.add(new Label("Airport zip code:"));
			first.add(a_zipText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			//second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void viewFlight_HoursGUI() 
		{	
			removeAll();
			flight_hoursList = new List(6);
			loadFlight_Hours();
			add(flight_hoursList);
			
			//When a list item is selected populate the text fields
			flight_hoursList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM flight_hours");
						while (rs.next()) 
						{
							if (rs.getString("airplane_no").equals(flight_hoursList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							airplane_noText.setText(rs.getString("airplane_no"));
							departure_airportText.setText(rs.getString("departure_airport"));
							departure_timeText.setText(rs.getString("departure_time"));
							arrival_airportText.setText(rs.getString("arrival_airport"));
							arrival_timeText.setText(rs.getString("arrival_time"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	  
			//Handle Update Menu Button
			modify = new Button("Update Flight Hours");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE flight_hours "
						+ "SET departure_airport='" + departure_airportText.getText()+"'"  
						+ " WHERE airplane_no = '" + flight_hoursList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						flight_hoursList.removeAll();
						loadFlight_Hours();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			
			airplane_noText = new TextField(15);
			airplane_noText.setEditable(false);
			departure_airportText = new TextField(15);
			departure_airportText.setEditable(false);
			departure_timeText = new TextField(15);
			departure_timeText.setEditable(false);
			arrival_airportText = new TextField(15);
			arrival_airportText.setEditable(false);
			arrival_timeText = new TextField(15);
			arrival_timeText.setEditable(false);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Airplane No:"));
			first.add(airplane_noText);
			first.add(new Label("Departure Airport:"));
			first.add(departure_airportText);
			first.add(new Label("Departure Time:"));
			first.add(departure_timeText);
			first.add(new Label("Arrival Airport:"));
			first.add(arrival_airportText);
			first.add(new Label("Arrival Time:"));
			first.add(arrival_timeText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			//second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void viewPaymentsGUI() 
		{	
			removeAll();
			paymentsList = new List(6);
			loadPayments();
			add(paymentsList);
			
			//When a list item is selected populate the text fields
			paymentsList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM payments");
						while (rs.next()) 
						{
							if (rs.getString("pay_id").equals(paymentsList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							pay_idText.setText(rs.getString("pay_id"));
							amountText.setText(rs.getString("amount"));
							discountText.setText(rs.getString("discount"));
							taxText.setText(rs.getString("tax"));
							total_amountText.setText(rs.getString("total_amount"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	  
			//Handle Update Menu Button
			modify = new Button("Update Payments");
			modify.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("UPDATE payments "
						+ "SET amount='" + amountText.getText()+"'"  
						+ " WHERE pay_id = '" + paymentsList.getSelectedItem() + "'");
						errorText.append("\nUpdated " + i + " rows successfully");
						paymentsList.removeAll();
						loadPayments();
					} 
					catch (SQLException insertException) 
					{
						displaySQLErrors(insertException);
					}
				}
			});
			
			pay_idText = new TextField(15);
			pay_idText.setEditable(false);
			amountText = new TextField(15);
			amountText.setEditable(false);
			discountText = new TextField(15);
			discountText.setEditable(false);
			taxText = new TextField(15);
			taxText.setEditable(false);
			total_amountText = new TextField(15);
			total_amountText.setEditable(false);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(4, 2));
			first.add(new Label("Payment ID:"));
			first.add(pay_idText);
			first.add(new Label("Payment Amount:"));
			first.add(amountText);
			first.add(new Label("Payment discount:"));
			first.add(discountText);
			first.add(new Label("Payment tax:"));
			first.add(taxText);
			first.add(new Label("Payment total amount:"));
			first.add(total_amountText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			//second.add(modify);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			//setTitle("Update ....");
			//setSize(500, 600);
			setLayout(new FlowLayout());
			setVisible(true);	
		}
		
		public void displaySQLErrors(SQLException e) 
		{
			errorText.append("\nSQLException: " + e.getMessage() + "\n");
			errorText.append("SQLState:     " + e.getSQLState() + "\n");
			errorText.append("VendorError:  " + e.getErrorCode() + "\n");
		}
		public static void main(String[] args) 
		{
			CreateTables it = new CreateTables();
			it.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent e) 
			  {
				System.exit(0);
			  }
			});
			it.buildFrame();
		}
	}
		