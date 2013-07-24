package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;
import controller.*;

/**
 * GUI
 * 
 * Still to do for Azian Mike:
 * Add capability for deleting accounts, vendors, buildings, and items
 * Set in safe guards for user idiocy
 * Display all items/accounts/vendors/buildings
 * 
 * @author AnnaSkorodumova
 *
 */
public class GUI extends JFrame{
	private static final long serialVersionUID = 1651343297257243910L;
	private AzianMike mike;
	final JSplitPane splitPane = new JSplitPane();

	final JSplitPane buttonSplitPane = new JSplitPane();
	final JSplitPane entrySplitPane = new JSplitPane();
	final JPanel entries = new JPanel();
	final JFrame windowFrame = new JFrame();
	final JPanel subMenu = new JPanel(new GridLayout(20,1));
	final JPanel menu = new JPanel(new GridLayout(8,1));
	final JTextArea window = new JTextArea();



	public GUI() {
		String inputValue = JOptionPane.showInputDialog("Please enter the location of the folder where databases files should be stored." + '\n' + "Ex: /Users/YourName/Desktop/AzianMikeFiles/");
		if (inputValue.equals("default"))
			inputValue = "/Users/AnnaSkorodumova/Desktop/AzianMikeFiles/";
		this.mike = new AzianMike(inputValue);
	}

	public void start(){

		/*
		 * Main frame set up
		 */
		setTitle("Azian Mike"); 
		setSize(1000, 700); 


		/*
		 * Setting up output window
		 */
		window.setEditable(false);
		windowFrame.add(window);
		final JScrollPane windowScroll = new JScrollPane(window);

		/*
		 * setting up split between entry space and output pane
		 */
		entrySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		entrySplitPane.setTopComponent(entries);
		entrySplitPane.setBottomComponent(windowScroll);
		entrySplitPane.setResizeWeight(0.12);


		/*
		 * setting up split between constant and changing buttons (sub menu)
		 */
		buttonSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		buttonSplitPane.setTopComponent(menu);
		buttonSplitPane.setBottomComponent(subMenu);

		/*
		 * setting up split between buttons and output/entry
		 */
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(buttonSplitPane);
		splitPane.setRightComponent(entrySplitPane);

		splitPane.setOneTouchExpandable(true);
		//splitPane.setDividerLocation(300);
		splitPane.setResizeWeight(0.12);
		add(splitPane);


		/*Initializing all main menu buttons*/
		JButton exit = new JButton("Exit");		
		JButton clear = new JButton("Clear display");		
		JButton vendorActions = new JButton("Display vendor actions.");
		JButton accountActions  = new JButton("Display account actions.");
		JButton itemActions = new JButton("Display item actions.");
		JButton buildingActions = new JButton("Display building actions.");
		JButton reportActions = new JButton("Display report actions.");
		JButton incovoiceActions = new JButton("Display invoice actions.");


		/*Adding all buttons to menu*/
		menu.add(vendorActions);
		menu.add(accountActions);
		menu.add(itemActions);
		menu.add(buildingActions);
		menu.add(reportActions);
		menu.add(incovoiceActions);
		menu.add(clear);
		menu.add(exit);

		/*Adding actions of all buttons*/
		/**
		 * Exit button saves and exists
		 */
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mike.save();
				System.exit(0);
			}
		});
		/**
		 * Clears text in output window
		 */
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.setText("");
			}
		});
		/**
		 * Displays all actions for vendor
		 */
		vendorActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subMenu.removeAll();
				displayVendorActions();
				subMenu.repaint();
			}
		});
		/**
		 * Displays all actions for accounts
		 */
		accountActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subMenu.removeAll();
				displayAccountActions();
				subMenu.repaint();
			}
		});
		/**
		 * Displays all actions on items
		 */
		itemActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subMenu.removeAll();
				displayItemActions();
				subMenu.repaint();
			}
		});
		/**
		 * Displays all building actions
		 */
		buildingActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subMenu.removeAll();
				displayBuildingActions();
				subMenu.repaint();
			}
		});
		/**
		 * Displays all actions on reports
		 */
		reportActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subMenu.removeAll();
				displayReportActions();
				subMenu.repaint();
			}
		});
		/**
		 * Displays all actions on invoices
		 */
		incovoiceActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subMenu.removeAll();
				displayInvoiceActions(subMenu);
				subMenu.repaint();
			}
		});	
		setVisible(true);
	}

	/**
	 * Displays menu for invoice actions
	 */
	protected void displayInvoiceActions(final JPanel subMenu) {		
		JButton generateInvoice = new JButton("Create invoice");		
		JButton displayInvoiceList = new JButton("Display invoice list.");
		JButton displayInvoice  = new JButton("Get invoice.");

		subMenu.add(generateInvoice);
		subMenu.add(displayInvoiceList);
		subMenu.add(displayInvoice);

		/*creates an invoice based on provided information*/
		generateInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO invoice info
				entries.removeAll();
				entries.repaint();
				mike.generateInvoice();
				window.append("Invoice generated." + System.getProperty("line.separator"));
			}
		});	
		/*displays list of all invoices created*/
		displayInvoiceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String[] invoices = mike.getInvoiceList();
				window.append("Listing all saved invoices: " + System.getProperty("line.separator") + System.getProperty("line.separator"));
				for( int i = 0; i < invoices.length; i++) window.append(invoices[i] + System.getProperty("line.separator"));
				window.append(System.getProperty("line.separator"));
			}
		});	
		/*displays invoice based on code*/
		displayInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entries.removeAll();
				entries.repaint();
				String [] required = {"Invoice code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasInvoice(code)) {
								window.append(mike.getInvoice(code) + System.getProperty("line.separator"));
							}
							else JOptionPane.showMessageDialog(entries, "This invoice does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	
		setVisible(true);
	}

	/**
	 * Displays report related actions
	 */
	protected void displayReportActions() {
		JButton generateReport = new JButton("Generate new report");		
		subMenu.add(generateReport);

		/*creates a report based on provided information*/
		generateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				//TODO get report info
				mike.generateReport();
				window.append("Report generated." + System.getProperty("line.separator"));
			}
		});	
		setVisible(true);
	}

	/**
	 * Displays building actions
	 */
	protected void displayBuildingActions() {
		JButton addBuilding = new JButton("Add new building");		
		JButton removeBuilding  = new JButton("Delete building.");
		JButton getBuidlingList  = new JButton("Get list of all buildings.");


		subMenu.add(addBuilding);
		subMenu.add(removeBuilding);
		subMenu.add(getBuidlingList);

		/* Add building */
		addBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String [] required = {"Building name", "Building code"};
				String [] optional = {"Building address", "Buidling supervisor", "Date of creation"};
				final JPanel [] submitted = setEntries(entries, required,  optional);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String name = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);
						String code = ((JTextField) submitted[1].getComponent(1)).getText();
						((JTextField) submitted[1].getComponent(1)).setText(null);
						String address = ((JTextField) submitted[2].getComponent(1)).getText();
						((JTextField) submitted[2].getComponent(1)).setText(null);
						String supervisor = ((JTextField) submitted[3].getComponent(1)).getText();
						((JTextField) submitted[3].getComponent(1)).setText(null);
						String date = ((JTextField) submitted[4].getComponent(1)).getText();
						((JTextField) submitted[4].getComponent(1)).setText(null);

						if (name.isEmpty() || code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							mike.newBuilding(code, name);
							String s = "New building " + code + ": " + name;
							if (!address.isEmpty()){
								mike.addBuildingAddress(code, address);
								s += " at " + address;
							}
							if (!supervisor.isEmpty()){
								mike.addBuildingSupervisor(code, supervisor);
								s += " supervised by " + supervisor;
							}
							if(!date.isEmpty()){
								if (isDate(date)){
									mike.addBuildingDate(code, date);
									s += " on " + date;
									window.append(s + System.getProperty("line.separator"));
								}
								else {
									JOptionPane.showMessageDialog(entries, "Please enter a valid date in format mm/dd/year.");
									window.append("Building " + code + " added to database without date. Enter name, code, and date to change building.");
								}
							}
							else window.append(s + System.getProperty("line.separator"));
						}
					}
				});
				entries.revalidate();
			}
		});		
		/* Remove building from db */
		removeBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entries.removeAll();
				entries.repaint();
				String [] required = {"Building code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasBuilding(code)) {
								mike.removeBuilding(code);
								window.append("Removed building " + code + " from database." + System.getProperty("line.separator"));
							}
							else JOptionPane.showMessageDialog(entries, "This building does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	
		/* Display building list */
		getBuidlingList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String[] buildings = mike.getBuildingList();
				window.append("Listing all saved buildings: " + System.getProperty("line.separator") + System.getProperty("line.separator"));
				for( int i = 0; i < buildings.length; i++) window.append(buildings[i] + System.getProperty("line.separator"));
				window.append(System.getProperty("line.separator"));
			}
		});	
		setVisible(true);
	}
	/**
	 * Displaying all item actions
	 */
	protected void displayItemActions() {
		JButton addItem = new JButton("Add new item");		
		JButton removeItem = new JButton("Remove item.");
		JButton getItemList  = new JButton("Get list of all items.");
		JButton newPurchase  = new JButton("Enter new purchase.");

		subMenu.add(addItem);
		subMenu.add(removeItem);
		subMenu.add(getItemList);
		subMenu.add(newPurchase);

		/* adding item */
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				String [] required = {"Item name", "Item code", "Vendor code", "Related account code", "Unit Price"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String name = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);
						String code = ((JTextField) submitted[1].getComponent(1)).getText();
						((JTextField) submitted[1].getComponent(1)).setText(null);
						String vendor = ((JTextField) submitted[2].getComponent(1)).getText();
						((JTextField) submitted[2].getComponent(1)).setText(null);
						String account = ((JTextField) submitted[3].getComponent(1)).getText();
						((JTextField) submitted[3].getComponent(1)).setText(null);
						String price = ((JTextField) submitted[4].getComponent(1)).getText();
						((JTextField) submitted[4].getComponent(1)).setText(null);
						if (name.isEmpty() || code.isEmpty() || vendor.isEmpty() || account.isEmpty() || price.isEmpty()) 
							JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if (isPrice(price)){
								double unitPrice = Double.parseDouble(price);
								if (!mike.hasVendor(vendor))  JOptionPane.showMessageDialog(entries, "This vendor does not exist. Please confirm code and try again");
								else if (!mike.hasAccount(account)) JOptionPane.showMessageDialog(entries, "This account does not exist. Please confirm code and try again");
								else {
									window.append("New item " + code + ": " + name + " " + unitPrice +  " related to vendor " + vendor + " and account " + account +  System.getProperty("line.separator"));
									mike.newItem(code, name, vendor, account, unitPrice);
								}
							}
							else{
								JOptionPane.showMessageDialog(entries, "Please use only numbers and period (.) in the price field.");
								window.append(name + " not added to items database due to invalid price.");
							}
						}
					}
				});
				entries.revalidate();
			}
		});	

		/*removing item based on code*/
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entries.removeAll();
				entries.repaint();
				String [] required = {"Item code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasItem(code)) {
								mike.removeItem(code);
								window.append("Removed item " + code + " from database, " + System.getProperty("line.separator"));
							}
							else JOptionPane.showMessageDialog(entries, "This item does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	
		/*Prints list of all items*/
		getItemList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String[] items = mike.getItemList();
				window.append("Listing all saved items: " + System.getProperty("line.separator") + System.getProperty("line.separator"));
				for( int i = 0; i < items.length; i++) window.append(items[i] + System.getProperty("line.separator"));
				window.append(System.getProperty("line.separator"));
			}
		});	
		/* Adds new purchase*/
		newPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String [] required = {"Item code", "Purchase price", "Purchase date", "Quantity"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);
						String price = ((JTextField) submitted[1].getComponent(1)).getText();
						((JTextField) submitted[1].getComponent(1)).setText(null);
						String date = ((JTextField) submitted[2].getComponent(1)).getText();
						((JTextField) submitted[2].getComponent(1)).setText(null);
						String quantity = ((JTextField) submitted[3].getComponent(1)).getText();
						((JTextField) submitted[3].getComponent(1)).setText(null);

						int q = -1;
						q = Integer.valueOf(quantity);

						if (code.isEmpty() || price.isEmpty() || date.isEmpty() || quantity.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else{
							if (q > 0){
								if (mike.itemExists(code)){
									if (isPrice(price)){
										if (isDate(date)){
											window.append("New purchase of " + code + " at $" + price + " on " + date +  System.getProperty("line.separator"));
											mike.newPurchase(code, Double.valueOf(price), date, q);
										}
										else{
											JOptionPane.showMessageDialog(entries, "Please enter date in format mm/dd/year.");
											window.append("Purchase of item " + code + " not added to database due to invalid date.");
										}
									}
									else {
										JOptionPane.showMessageDialog(entries, "Please use only numbers and period (.) in the price field.");
										window.append("Purchase of item " + code + " not added to database due to invalid price.");
									}
								}
								else {
									JOptionPane.showMessageDialog(entries, "This item does not exist. Please confirm code and try again");
									window.append("Purchase of item " + code + " not added to database due to invalid item code."+ System.getProperty("line.separator")) ;
								}
							}
							else {
								JOptionPane.showMessageDialog(entries, "The quanity you enetered was not valid. Please enter an integer greater than 0.");
								window.append("Purchase of item " + code + " not added to database due to invalid item quantity."+ System.getProperty("line.separator")) ;
							}}}});
				entries.revalidate();
			}
		});	
		setVisible(true);		
	}

	/**
	 * Displaying account actions
	 */
	protected void displayAccountActions() {
		JButton addAccount = new JButton("Add new account");		
		JButton removeAccount = new JButton("Remove account.");
		JButton getAccountList  = new JButton("Get list of all accounts.");
		JButton getAccountItems  = new JButton("Get related items.");


		subMenu.add(addAccount);
		subMenu.add(removeAccount);
		subMenu.add(getAccountList);
		subMenu.add(getAccountItems);

		/* add account */
		addAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				String [] required = {"Account name", "Account code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String name = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);
						String code = ((JTextField) submitted[1].getComponent(1)).getText();
						((JTextField) submitted[1].getComponent(1)).setText(null);

						if (name.isEmpty() || code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							mike.newAccount(code, name);
							window.append("New account " + code + ": " + name + System.getProperty("line.separator"));
						}
					}
				});
				entries.revalidate();
			}
		});	

		/* removing account based on code */
		removeAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entries.removeAll();
				entries.repaint();
				String [] required = {"Account code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasAccount(code)) {
								mike.removeAccount(code);
								window.append("Removed account " + code + " from database, " + System.getProperty("line.separator"));
							}
							else JOptionPane.showMessageDialog(entries, "This account does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	
		/* Prints list of all accounts */
		getAccountList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String[] accounts = mike.getAccountList();
				window.append("Listing all saved accounts: " + System.getProperty("line.separator") + System.getProperty("line.separator"));
				for( int i = 0; i < accounts.length; i++) window.append(accounts[i] + System.getProperty("line.separator"));
				window.append(System.getProperty("line.separator"));
			}
		});	
		/* Print list of all items related to account */
		getAccountItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String [] required = {"Account code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasAccount(code)) window.append(mike.getItemsRelatedToAccount(code));
							else JOptionPane.showMessageDialog(entries, "This account does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	

		setVisible(true);	
	}

	protected void displayVendorActions() {
		JButton addVendor = new JButton("Add new vendor");		
		JButton removeVendor = new JButton("Remove vendor.");
		JButton getVendorList  = new JButton("Get list of all vendors.");
		JButton getVendorItems  = new JButton("Get related items.");


		subMenu.add(addVendor);
		subMenu.add(removeVendor);
		subMenu.add(getVendorList);
		subMenu.add(getVendorItems);

		/* Adding new vendor */
		addVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				String [] required = {"Vendor name", "Vendor code"};
				String [] optional = {"Vendor address"};
				final JPanel [] submitted = setEntries(entries, required,  optional);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String name = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);
						String code = ((JTextField) submitted[1].getComponent(1)).getText();
						((JTextField) submitted[1].getComponent(1)).setText(null);
						String address = ((JTextField) submitted[2].getComponent(1)).getText();
						((JTextField) submitted[2].getComponent(1)).setText(null);

						if (name.isEmpty() || code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( address.isEmpty()){
								mike.newVendor(code, name);
								window.append("New vendor " + code + ": " + name + System.getProperty("line.separator"));
							}
							else {
								mike.newVendor(code, name, address);
								window.append("New vendor " + code + ": " + name + " at " + address + System.getProperty("line.separator"));
							}
						}
					}
				});
				entries.revalidate();
			}
		});	

		/* removing vendor based on code */
		removeVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entries.removeAll();
				entries.repaint();
				String [] required = {"Vendor code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasVendor(code)) {
								mike.removeVendor(code);
								window.append("Removed vendor " + code + " from database, " + System.getProperty("line.separator"));
							}
							else JOptionPane.showMessageDialog(entries, "This vendor does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	
		/* Prints list of all vendors */
		getVendorList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String[] vendors = mike.getVendorList();
				window.append(System.getProperty("line.separator") + "Listing all saved vendors: " + System.getProperty("line.separator") + System.getProperty("line.separator"));
				for( int i = 0; i < vendors.length; i++) window.append(vendors[i] + System.getProperty("line.separator"));
				window.append(System.getProperty("line.separator"));
			}
		});	
		
		/* Print list of all items related to vendor */
		getVendorItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				entries.removeAll();
				entries.repaint();
				String [] required = {"Vendor code"};
				final JPanel [] submitted = setEntries(entries, required,  new String[0]);

				for (int i = 0; i < submitted.length; i++) entries.add(submitted[i]);

				JButton submit = new JButton("Submit");
				entries.add(submit);

				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String code = ((JTextField) submitted[0].getComponent(1)).getText();
						((JTextField) submitted[0].getComponent(1)).setText(null);

						if (code.isEmpty()) JOptionPane.showMessageDialog(entries, "Please complete all required (*) fields.");
						else { 
							if ( mike.hasVendor(code)) window.append(mike.getItemsRelatedToVendor(code));
							else JOptionPane.showMessageDialog(entries, "This vendor does not exist. Please confirm code and try again");
						}
					}
				});
				entries.revalidate();
			}
		});	

		setVisible(true);			
	}

	protected JPanel[] setEntries(JPanel target, String[] required,
			String[] optional) {
		JPanel [] jPanels = new JPanel[required.length + optional.length];

		//setting required
		JLabel [] requiredJLabels = new JLabel[required.length];
		JTextField [] requiredTextFields = new JTextField[required.length];


		for (int i = 0; i < required.length; i++){
			requiredJLabels[i] = new JLabel();
			requiredJLabels[i].setText(required[i] + ":* ");
			requiredTextFields[i] = new JTextField(15);
			jPanels[i] = new JPanel();
			jPanels[i].add(requiredJLabels[i]);
			jPanels[i].add(requiredTextFields[i]);
		}


		//setting optional
		JLabel [] optionalJLabels = new JLabel[optional.length];
		JTextField [] optionalTextFields = new JTextField[optional.length];


		for (int i = 0; i < optional.length; i++){
			optionalJLabels[i] = new JLabel();
			optionalJLabels[i].setText(optional[i] + ": ");
			optionalTextFields[i] = new JTextField(15);
			jPanels[i+required.length] = new JPanel();
			jPanels[i+ required.length].add(optionalJLabels[i]);
			jPanels[i+ required.length].add(optionalTextFields[i]);
		}		
		return jPanels;
	}

	/** 
	 * Checks if given string is a number
	 * @param price
	 * @return
	 */
	protected boolean isPrice(String price){
		try{
			Double.valueOf(price);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

	/**
	 * checks if the given string corresponds to a date
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected boolean isDate(String date){
		if (date.length() < 8) return false;
		String sMonth = date.substring(0, 2);
		String sDay = date.substring(3, 5);
		String sYear = date.substring(6);
		int month;
		int day;
		int year;

		try{
			month = Integer.valueOf(sMonth);
			day = Integer.valueOf(sDay);
			year = Integer.valueOf(sYear);
			if ( month < 13 && month > 0 && day < 32 && day > 0 && year > 1800 && year <= Calendar.getInstance().getTime().getYear()+1900  )
				return true;
			else return false;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

	public static void main( String [] args){
		GUI gui = new GUI();
		gui.start();

	}
}
