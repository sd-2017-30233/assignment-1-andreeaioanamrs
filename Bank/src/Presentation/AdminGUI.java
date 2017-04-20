package Presentation;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Business.AdminBusiness;
import Business.EmployeeBusiness;
import Business.Logare;
import Database.Employee;

public class AdminGUI {
	private JFrame fr;
	private JButton addE,updateE,viewE,deleteE,genR,viewall;
	private DefaultTableModel tabel;
	private DefaultTableModel model;
	private Object[] columnNames = {"#","USERNAME","PASSWORD","NAME","CNP","ADRESS"};
	private JTable table;
	AdminGUI(){
		fr = new JFrame("Admin");
		fr.setBounds(400, 300, 540, 500);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().setLayout(null);
		
		viewall = new JButton("View Employees");
		viewall.setBounds(20, 10, 150, 20);
		viewE = new JButton("View Employee");
		viewE.setBounds(20, 40, 150, 20);
		deleteE = new JButton("Delete Employee");
		deleteE.setBounds(20, 70, 150, 20);
		addE = new JButton("Add Employee");
		addE.setBounds(370, 10, 150, 20);
		updateE = new JButton("Update Employee");
		updateE.setBounds(370, 40, 150, 20);
		genR = new JButton("Generate Raport");
		genR.setBounds(370, 70, 150, 20);
		
		
		model = new DefaultTableModel(columnNames, 0);
		table=new JTable(model);
		//table.setEnabled(false);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setForeground(SystemColor.scrollbar);
		tablePane.setBounds(20, 150, 500, 300);
		
		fr.getContentPane().add(tablePane);
		fr.getContentPane().add(addE);
		fr.getContentPane().add(updateE);
		fr.getContentPane().add(viewE);
		fr.getContentPane().add(deleteE);
		fr.getContentPane().add(viewall);
		fr.getContentPane().add(genR);
		
		viewall.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == viewall){
					model = new DefaultTableModel(columnNames, 0);
					table=new JTable(model);
					//table.setEnabled(false);
					JScrollPane tablePane = new JScrollPane(table);
					tablePane.setForeground(SystemColor.scrollbar);
					tablePane.setBounds(20, 150, 500, 300);
					fr.getContentPane().add(tablePane);
					try  {
						   List<Employee> empl = AdminBusiness.getEmployees();
							for (int i = 0; i < empl.size(); i++) {
						    	String[] row = new String[6];
						    	row[0] = String.valueOf(i+1);
						    	row[1] = empl.get(i).getEUsername();
						    	row[2] = empl.get(i).getPass();
						    	row[3] = empl.get(i).getNume();
						    	row[4] = empl.get(i).getCNP();
						    	row[5] = empl.get(i).getAdress();
						    	model.addRow(row);
						    	}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "This Action cannot be performed!");
						e1.printStackTrace();
					}
				}
			}
		});
		
		viewE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == viewE){
					int rowCount = model.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    model.removeRow(i);}
					
					table = new JTable(model);
					table.repaint();
					JScrollPane tablePane = new JScrollPane(table);
					tablePane.setForeground(SystemColor.scrollbar);
					tablePane.setBounds(20, 150, 500, 300);
					fr.getContentPane().add(tablePane);
					
					JFrame f = new JFrame("Find Employee");
					f.setBounds(400, 300, 170, 150);
					f.getContentPane().setLayout(null);
//					f.setDefaultCloseOperation(JFrame.);
					String[] possibilities = {"Username", "CNP"};
					JLabel lbl = new JLabel("Find Employee by");
					lbl.setBounds(25,10,150,20);
					JComboBox posList = new JComboBox(possibilities);
					posList.setBounds(10,40,150,20);
					JButton find = new JButton("Find");
					find.setBounds(40,100,100,20);
					JTextField opt = new JTextField();
					opt.setBounds(10, 70, 150, 20);
					table.repaint();
					f.getContentPane().add(lbl);
					f.getContentPane().add(posList);
					f.getContentPane().add(find);
					f.getContentPane().add(opt);
					f.setVisible(true);
					find.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == find){
								String optiune =  opt.getText();
								System.out.println(optiune);
								System.out.println(posList.getSelectedItem());
								f.dispose();
								Employee found = null;
								if (optiune == null) JOptionPane.showMessageDialog(null, "This Action cannot be performed!Insert Value!");
								else if (Objects.equals(posList.getSelectedItem(),"Username") ) found = AdminBusiness.getEmployeeUsername(optiune);
								else found = AdminBusiness.getEmployeeCNP(optiune);
								if (found != null){	
									String[] row = new String[6];
							    	row[0] = String.valueOf(1);
							    	row[1] = found.getEUsername();
							    	row[2] = found.getPass();
							    	row[3] = found.getNume();
							    	row[4] = found.getCNP();
							    	row[5] = found.getAdress();
							    	model.addRow(row);}
							   else JOptionPane.showMessageDialog(null, "Employee not found!");
								f.setVisible(false);
							}
						}
					});
				}
			}
		});
		
		
		addE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == addE){
					JFrame f = new JFrame("Add Employee");
					f.setBounds(400, 300, 470, 450);
					f.getContentPane().setLayout(null);
					JLabel n = new JLabel("Name");
					n.setBounds(20,20,150,20);
					JLabel u = new JLabel("Username");
					u.setBounds(20,50,150,20);
					JLabel p = new JLabel("Password");
					p.setBounds(20,80,150,20);
					JLabel c = new JLabel("CNP");
					c.setBounds(20,110,150,20);
					JLabel a = new JLabel("Adress");
					a.setBounds(20,140,150,20);
					JButton add = new JButton("Add");
					add.setBounds(40,170,150,20);
					
					JTextField na = new JTextField();
					na.setBounds(120, 20, 150, 20);
					JTextField us = new JTextField();
					us.setBounds(120, 50, 150, 20);
					JTextField pa = new JTextField();
					pa.setBounds(120, 80, 150, 20);
					JTextField cn = new JTextField();
					cn.setBounds(120, 110, 150, 20);
					JTextField ad = new JTextField();
					ad.setBounds(120, 140, 150, 20);
					
					f.getContentPane().add(n);
					f.getContentPane().add(na);
					f.getContentPane().add(u);
					f.getContentPane().add(us);
					f.getContentPane().add(p);
					f.getContentPane().add(pa);
					f.getContentPane().add(c);
					f.getContentPane().add(cn);
					f.getContentPane().add(a);
					f.getContentPane().add(ad);
					f.getContentPane().add(add);
					
				
					f.setVisible(true);
					
					add.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == add){
								String name = na.getText();
								String user = us.getText();
								String pass = pa.getText();
								String cnp = cn.getText();
								String adr = ad.getText();
								System.out.println(name+user+pass+cnp+adr);
								String rs = AdminBusiness.addEmployee(user,pass,name,cnp,adr);
								if (Objects.equals(rs,"true")){
									String[] row = new String[6];
							    	row[0] = String.valueOf(model.getRowCount()+1);
							    	row[1] = user;
							    	row[2] = pass;
							    	row[3] = name;
							    	row[4] = cnp;
							    	row[5] = adr;
							    	model.addRow(row);
							    	JOptionPane.showMessageDialog(null, "Employee added!" );
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
								
							}
						}
					});
		
				}
			}
		});
		
		deleteE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == deleteE){
					JFrame f = new JFrame("Delete Employee");
					f.setBounds(400, 300, 200, 200);
					f.getContentPane().setLayout(null);
			
					JLabel u = new JLabel("Username");
					u.setBounds(20,20,150,20);
					JButton delete = new JButton("Delete");
					delete.setBounds(20,80,150,20);
					
					JTextField us = new JTextField();
					us.setBounds(20, 50, 150, 20);
					
					f.getContentPane().add(u);
					f.getContentPane().add(us);
					f.getContentPane().add(delete);
					
				
					f.setVisible(true);
					
					delete.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == delete){
								String user = us.getText();
								String rs = AdminBusiness.deleteEmployee(user);
								if (Objects.equals(rs,"true")){
									 for (int k = 0; k < model.getRowCount(); k++) 
				    	                {
				    	                    if (model.getValueAt(k, 1) == user)
				    	                        model.removeRow(k);
				    	                }
									 
							    	JOptionPane.showMessageDialog(null, "Employee deleted!" );
							    
							    	
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
								
							}
						}
					});
		
				}
			}
		});
		
		
		updateE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == updateE){
					JFrame f = new JFrame("Update Employee");
					f.setBounds(400, 300, 470, 450);
					f.getContentPane().setLayout(null);
					JLabel n = new JLabel("Name");
					n.setBounds(20,50,150,20);
					JLabel u = new JLabel("Username");
					u.setBounds(20,20,150,20);
					JLabel p = new JLabel("Password");
					p.setBounds(20,80,150,20);
					JLabel c = new JLabel("CNP");
					c.setBounds(20,110,150,20);
					JLabel a = new JLabel("Adress");
					a.setBounds(20,140,150,20);
					JButton add = new JButton("Add");
					add.setBounds(40,170,150,20);
					
					JTextField na = new JTextField();
					na.setBounds(120, 20, 150, 20);
					JTextField us = new JTextField();
					us.setBounds(120, 50, 150, 20);
					JTextField pa = new JTextField();
					pa.setBounds(120, 80, 150, 20);
					JTextField cn = new JTextField();
					cn.setBounds(120, 110, 150, 20);
					JTextField ad = new JTextField();
					ad.setBounds(120, 140, 150, 20);
					
					f.getContentPane().add(n);
					f.getContentPane().add(na);
					f.getContentPane().add(u);
					f.getContentPane().add(us);
					f.getContentPane().add(p);
					f.getContentPane().add(pa);
					f.getContentPane().add(c);
					f.getContentPane().add(cn);
					f.getContentPane().add(a);
					f.getContentPane().add(ad);
					f.getContentPane().add(add);
					
				
					f.setVisible(true);
					
					add.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == add){
								String name = na.getText();
								String user = us.getText();
								String pass = pa.getText();
								String cnp = cn.getText();
								String adr = ad.getText();
								System.out.println(name+user+pass+cnp+adr);
								String rs = AdminBusiness.addEmployee(user,pass,name,cnp,adr);
								if (Objects.equals(rs,"true")){
									String[] row = new String[6];
							    	row[0] = String.valueOf(model.getRowCount()+1);
							    	row[1] = user;
							    	row[2] = pass;
							    	row[3] = name;
							    	row[4] = cnp;
							    	row[5] = adr;
							    	model.addRow(row);
							    	JOptionPane.showMessageDialog(null, "Employee added!" );
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
								
							}
						}
					});
		
				}
			}
		});
		
		genR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == genR){
					JFrame f = new JFrame("Generate Raport");
					f.setBounds(400, 300, 470, 450);
					f.getContentPane().setLayout(null);
					JLabel u = new JLabel("Username");
					u.setBounds(20,20,150,20);

					JButton add = new JButton("Generate");
					add.setBounds(40,170,150,20);
	
					JTextField us = new JTextField();
					us.setBounds(20, 50, 150, 20);
			
					f.getContentPane().add(u);
					f.getContentPane().add(us);
		
					f.getContentPane().add(add);
				
					f.setVisible(true);
					
					add.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == add){
					
								String user = us.getText();
								
								boolean rs = false;
								try {
									rs = AdminBusiness.generateReport(user);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, rs );
								
							}
						}
					});
		
				}
			}
		});
		
		fr.setVisible(true);
	}

}
