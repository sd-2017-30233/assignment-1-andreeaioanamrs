package Presentation;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Business.EmployeeBusiness;
import Database.Account;
import Database.Client;
import Database.Employee;

public class EmployeeGUI {
	private JFrame fr;
	private JButton addC,updateC,viewC,deleteC;
	private JButton addAC,updateAC,viewAC,deleteAC;
	private JButton transfer,process;
	private DefaultTableModel model,model2;
	private Object[] modelclient = {"NAME","CNP","ADRESS"};
	private Object[] modelAccount = {"OWNER","ICN","TIP","DC","AMOUNT"};
	private JTable table,table2;
	EmployeeGUI(String EmplUsername){
		fr = new JFrame("Employee");
		fr.setBounds(400, 300, 390, 800);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().setLayout(null);
		
		viewC = new JButton("View Client");
		viewC.setBounds(20, 10, 150, 20);
		addC = new JButton("Add Client");
		addC.setBounds(20, 40, 150, 20);
		updateC = new JButton("Update Client");
		updateC.setBounds(20, 70, 150, 20);
		deleteC = new JButton("Delete Client");
		deleteC.setBounds(20, 100, 150, 20);
		transfer = new JButton("Transfer Money");
		transfer.setBounds(20,130,150,20);
		
		viewAC = new JButton("View Account");
		viewAC.setBounds(220, 10, 150, 20);
		addAC = new JButton("Add Account");
		addAC.setBounds(220, 40, 150, 20);
		updateAC = new JButton("Update Account");
		updateAC.setBounds(220, 70, 150, 20);
		deleteAC = new JButton("Delete Account");
		deleteAC.setBounds(220, 100, 150, 20);
		process = new JButton("Process Bill");
		process.setBounds(220,130,150,20);

		model = new DefaultTableModel(modelclient, 0);
	    table=new JTable(model);
	    table.setEnabled(false);
	    JScrollPane tablePane = new JScrollPane(table);
	    tablePane.setForeground(SystemColor.scrollbar);
	    tablePane.setBounds(20, 165, 350, 300);
	    
		model2 = new DefaultTableModel(modelAccount, 0);
	    table2=new JTable(model2);
	    table2.setEnabled(false);
	    JScrollPane tablePane2 = new JScrollPane(table2);
	    tablePane2.setForeground(SystemColor.scrollbar);
	    tablePane2.setBounds(20, 480, 350, 300);
		
		fr.getContentPane().add(addC);
		fr.getContentPane().add(updateC);
		fr.getContentPane().add(viewC);
		fr.getContentPane().add(deleteC);
		fr.getContentPane().add(addAC);
		fr.getContentPane().add(updateAC);
		fr.getContentPane().add(viewAC);
		fr.getContentPane().add(deleteAC);
		fr.getContentPane().add(process);
		fr.getContentPane().add(transfer);
		fr.getContentPane().add(tablePane);
		fr.getContentPane().add(tablePane2);
		
		viewC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == viewC){
					int rowCount = model.getRowCount();
					//Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
					    model.removeRow(i);}
					
					table = new JTable(model);
					table.repaint();
					JScrollPane tablePane = new JScrollPane(table);
					tablePane.setForeground(SystemColor.scrollbar);
					tablePane.setBounds(20, 150, 350, 300);
					fr.getContentPane().add(tablePane);
					
					JFrame f = new JFrame("Find Client");
					f.setBounds(400, 300, 170, 150);
					f.getContentPane().setLayout(null);
//					f.setDefaultCloseOperation(JFrame.);
					JLabel lbl = new JLabel("CNP");
					lbl.setBounds(25,10,150,20);
					JButton find = new JButton("Find");
					find.setBounds(40,100,100,20);
					JTextField opt = new JTextField();
					opt.setBounds(10, 70, 150, 20);
					table.repaint();
					f.getContentPane().add(lbl);
					f.getContentPane().add(find);
					f.getContentPane().add(opt);
					f.setVisible(true);
					find.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == find){
								String optiune =  opt.getText();
								f.dispose();
								Client found = null;
								if (optiune == null) JOptionPane.showMessageDialog(null, "This Action cannot be performed!Insert Value!");
								else found = EmployeeBusiness.getClient(optiune);
								if (found != null){	
									String[] row = new String[3];
							    	row[1] = found.getCNP();
							    	row[0] = found.getNume();
							    	row[2] = found.getAdress();
							    	model.addRow(row);}
							   else JOptionPane.showMessageDialog(null, "Client not found!");
								f.setVisible(false);}}});
					}
			}
		});
		
		addC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == addC){
			
					JFrame f = new JFrame("Add Client");
					f.setBounds(400, 300, 470, 450);
					f.getContentPane().setLayout(null);
					JLabel n = new JLabel("Name");
					n.setBounds(20,20,150,20);
					JLabel c = new JLabel("CNP");
					c.setBounds(20,110,150,20);
					JLabel a = new JLabel("Adress");
					a.setBounds(20,140,150,20);
					JButton add = new JButton("Add");
					add.setBounds(40,170,150,20);
					
					JTextField na = new JTextField();
					na.setBounds(120, 20, 150, 20);
					JTextField cn = new JTextField();
					cn.setBounds(120, 110, 150, 20);
					JTextField ad = new JTextField();
					ad.setBounds(120, 140, 150, 20);
					
					f.getContentPane().add(n);
					f.getContentPane().add(na);
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
								String cnp = cn.getText();
								String adr = ad.getText();
								System.out.println(name+cnp+adr);
								String rs = EmployeeBusiness.addClient(cnp,name,adr);
								if (Objects.equals(rs,"true")){
									String[] row = new String[3];
							    	row[0] = name;
							    	row[1] = cnp;
							    	row[2] = adr;
							    	model.addRow(row);
							    	JOptionPane.showMessageDialog(null, "Client added!" );
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
							}
						}
					});
				}
			}
		});
		deleteC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == deleteC){
					JFrame f = new JFrame("Delete Client");
					f.setBounds(400, 300, 200, 200);
					f.getContentPane().setLayout(null);
			
					JLabel u = new JLabel("CNP");
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
								String cnp = us.getText();
								String rs = EmployeeBusiness.deleteClient(cnp);
								if (Objects.equals(rs,"true")){
									 for (int k = 0; k < model.getRowCount(); k++) 
				    	                {
				    	                    if (model.getValueAt(k, 1) == cnp)
				    	                        model.removeRow(k);
				    	                }
									 
							    	JOptionPane.showMessageDialog(null, "Client deleted!" );
							    
							    	
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
								
							}
						}
					});
		
				}
			}
		});
		
		
		transfer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == transfer){
					JFrame f = new JFrame("Transfer Money");
					f.setBounds(400, 300, 300, 300);
					f.getContentPane().setLayout(null);
			
					JLabel ac1 = new JLabel("Account 1");
					ac1.setBounds(20,20,150,20);
					JTextField acc1 = new JTextField();
					acc1.setBounds(20, 50, 150, 20);
					JLabel ac2 = new JLabel("Account 2");
					ac2.setBounds(20,80,150,20);
					JTextField acc2 = new JTextField();
					acc2.setBounds(20, 110, 150, 20);
					JLabel amount = new JLabel("Amount");
					amount.setBounds(20,140,150,20);
					JTextField am = new JTextField();
					am.setBounds(20, 170, 150, 20);
					JButton tr = new JButton("Transfer");
					tr.setBounds(20,200,150,20);
					
					f.getContentPane().add(am);
					f.getContentPane().add(amount);
					f.getContentPane().add(ac2);
					f.getContentPane().add(acc2);
					f.getContentPane().add(ac1);
					f.getContentPane().add(acc1);
					f.getContentPane().add(tr);
					
					f.setVisible(true);
					
					tr.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == tr){
								String account1 = acc1.getText();
								String account2 = acc2.getText();
								Double val = Double.valueOf(am.getText());
								if(account1 != null && account2 != null && val!=null) JOptionPane.showMessageDialog(null,EmployeeBusiness.transfer(account1,account2,val));
								else JOptionPane.showMessageDialog(null,"Please insert valid values!");
							}
						}
					});
				}
			}
		});

		viewAC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == viewAC){
					JFrame f = new JFrame("View Account");
					f.setBounds(400, 300, 300, 300);
					f.getContentPane().setLayout(null);
					model = new DefaultTableModel(modelAccount, 0);
				    table=new JTable(model);
				    table.setEnabled(false);
			
					JLabel ac1 = new JLabel("Account 1");
					ac1.setBounds(20,20,150,20);
					JTextField acc1 = new JTextField();
					acc1.setBounds(20, 50, 150, 20);
					JButton tr = new JButton("View");
					tr.setBounds(20,200,150,20);
			
					f.getContentPane().add(ac1);
					f.getContentPane().add(acc1);
					f.getContentPane().add(tr);
					
					f.setVisible(true);
					
					tr.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == tr){
								String account1 = acc1.getText();
								Account ac;
								ac = EmployeeBusiness.findAccount(account1);
							
								if(ac!= null){
									String[] row = new String[5];
									row[0] = ac.getICN();
									row[1] = ac.getCNP();
									row[2] = ac.getTip();
									row[3] = ac.getDC();
									row[4] = String.valueOf(ac.getAmount());
									model2.addRow(row);
								}
								else JOptionPane.showMessageDialog(null,"Account does not exist!");
								f.dispose();
							}
						}
					});
				}
			}
		});
		
		updateAC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == updateAC){
					JFrame f = new JFrame("Update Account");
					f.setBounds(400, 300, 300, 300);
					f.getContentPane().setLayout(null);
				
			
					JLabel ac1 = new JLabel("Account");
					ac1.setBounds(20,20,150,20);
					JTextField acc1 = new JTextField();
					acc1.setBounds(20, 50, 150, 20);
					JLabel v = new JLabel("Amount");
					v.setBounds(20,50,150,20);
					JTextField val = new JTextField();
					val.setBounds(20, 80, 150, 20);
					JButton tr = new JButton("View");
					tr.setBounds(20,200,150,20);
			
					f.getContentPane().add(ac1);
					f.getContentPane().add(acc1);
					f.getContentPane().add(v);
					f.getContentPane().add(val);
			
					f.getContentPane().add(tr);
					f.setVisible(true);
					
					tr.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == tr){
								String account1 = acc1.getText();
								Double value = Double.valueOf(val.getText());
								JOptionPane.showMessageDialog(null,EmployeeBusiness.updateAccount(account1,value));
								f.dispose();
							}
						}
					});
				}
			}
		});
		
		deleteAC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == deleteAC){
					JFrame f = new JFrame("Delete Account");
					f.setBounds(400, 300, 200, 200);
					f.getContentPane().setLayout(null);
			
					JLabel u = new JLabel("ICN");
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
								String icn = us.getText();
								String rs = EmployeeBusiness.deleteAccount(icn);
								if (Objects.equals(rs,"true")){
									 for (int k = 0; k < model.getRowCount(); k++) 
				    	                {
				    	                    if (model2.getValueAt(k, 2) == icn)
				    	                        model2.removeRow(k);
				    	                }
									 
							    	JOptionPane.showMessageDialog(null, "Account deleted!" );
							    
							    	
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
								
							}
						}
					});
		
				}
			}
		});
		
		/*
		addAC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == addC){
			
					JFrame f = new JFrame("Add Account");
					f.setBounds(400, 300, 470, 450);
					f.getContentPane().setLayout(null);
					JLabel n = new JLabel("ICN");
					n.setBounds(20,20,150,20);
					JLabel c = new JLabel("CNP");
					c.setBounds(20,110,150,20);
					JLabel tip = new JLabel("TIP");
					tip.setBounds(20,140,150,20);
					JLabel cn = new JLabel("CN");
					cn.setBounds(20,140,150,20);
					JButton add = new JButton("Add Account");
					add.setBounds(40,170,150,20);
					
					JTextField icn = new JTextField();
					icn.setBounds(120, 20, 150, 20);
					JTextField cnn = new JTextField();
					cnn.setBounds(120, 110, 150, 20);
					JTextField tp = new JTextField();
					tp.setBounds(120, 140, 150, 20);
					
					f.getContentPane().add(n);
					f.getContentPane().add(icn);
					f.getContentPane().add(c);
					f.getContentPane().add(cn);
					f.getContentPane().add(tip);
					f.getContentPane().add(tp);
					f.getContentPane().add(add);
					
				
					f.setVisible(true);
					
					add.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if (e.getSource() == add){
								String icn = n.getText();
								String cnp = cn.getText();
								String tip = tp.getText();
								String rs = EmployeeBusiness.addAccount();
								if (Objects.equals(rs,"true")){
									String[] row = new String[3];
							    	row[0] = name;
							    	row[1] = cnp;
							    	row[2] = adr;
							    	model.addRow(row);
							    	JOptionPane.showMessageDialog(null, "Account added!" );
							    	f.setVisible(false);
								}
								else JOptionPane.showMessageDialog(null, rs );
							}
						}
					});
				}
			}
		});
		*/
		fr.setVisible(true);
	}
}