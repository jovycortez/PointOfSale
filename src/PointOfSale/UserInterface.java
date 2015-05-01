/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PointOfSale;

import java.awt.Color;
import java.sql.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jovy
 */
public class UserInterface extends javax.swing.JFrame {

    /**
     * Creates new form UserInterface
     */
    Database dbconn = new Database();
    TimeCard tCard = new TimeCard();
    DefaultTableModel model = new DefaultTableModel();
    Object[][] menu = dbconn.LoadMenu();
    StringBuilder keypadTransaction = new StringBuilder("");
    StringBuilder keypadLogin = new StringBuilder("");
    ArrayList<Order> orderLst = new ArrayList<>();
    Receipt receipt = new Receipt();
    Transaction transaction = new Transaction();
    StringBuilder paymentType = new StringBuilder();
    int[] numberOrder = dbconn.viewSavedOrderNumberFromDB();
    boolean isNewOrder = true;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    boolean isShown = true;
    String waiter = null;
    String userPass = null;
    
    BigDecimal amountdue = null;
    
   

    //receipt.setOrderList(r);
    public UserInterface() {
        initComponents();
        //lblTempOrderNumber.setText(dbconn.getSavedOrderNumberFromDB(NORMAL));
//
        Toolkit fillScreenSize = Toolkit.getDefaultToolkit();
        Dimension dim = new Dimension(fillScreenSize.getScreenSize());
        int h = (int) dim.getHeight();
        int w = (int) dim.getWidth();
        setSize(w, h);
        
        new Thread() {
            public void run() {
                while (true) {
                    Date date = new Date();
                    String[] time = date.toString().split(" ");
                    lblSystemClock.setText(time[3]);
                    lblSystemDate.setText(time[0] + " " + time[1] + " " + time[2] + " " + time[5]);
                    
                }
            }
        }.start();
       
        conn = Database.ConnecttoDB();
        
       
        btnLogin.setVisible(false);
       btnLogout.setVisible(false);
        
       
        
    }
    private void loadEmployees() {
        
        try {
            String sql = "SELECT firstName, lastName from employeeDB ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblEmployees.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            System.out.println("Inventory : " + e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                
            }
        }
        
    }
    private void updateOpenOrderTable() {
        
        try {
            String sql = "SELECT OrderNumber, TableNumber, ServerName from Revenue WHERE PaymentMethod = \"null\"";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblOpenOrders.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            System.out.println("updateOpenOrderTable: " + e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                
            }
        }
        
    }
    
    private void updatePaidOrderTable() {
        
        try {
            String sql = "SELECT OrderNumber, TableNumber, Total, ServerName from Revenue WHERE PaymentMethod = \"DebitCard\"";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblPaidOrders.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            System.out.println("updateOpenOrderTable: " + e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                
            }
        }
     
        
    }
    private void loadInventory() {
        
        try {
            String sql = "SELECT itemId, itemNameDB, itemPriceDB from Menu ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblInventory.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            System.out.println("Inventory : " + e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                
            }
        }
        
    }
    private void loadRevenue() {
        
        try {
            String sql = "SELECT Subtotal, TaxAmount, Total, PaymentMethod, OrderNumber, Tendered,TableNumber, ServerName from Revenue ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblRevenue.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            System.out.println("Inventory : " + e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                
            }
        }
        
    }
private void loadSales() {
        
        try {
            String sql = "SELECT itemId, OrderNumber, itemNameSold, itemPriceSold from SalesLog ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblSales.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            System.out.println("Inventory : " + e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                
            }
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PanelBackground = new javax.swing.JPanel();
        Panel_Navigation = new javax.swing.JPanel();
        PanelHeader = new javax.swing.JPanel();
        btnTables = new javax.swing.JButton();
        btnTimeCard = new javax.swing.JButton();
        btnManager = new javax.swing.JButton();
        btnViewOrders = new javax.swing.JButton();
        lblSystemClock = new javax.swing.JLabel();
        lblSystemDate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblWaiterHeader = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        PanelCardView = new javax.swing.JPanel();
        ViewTables = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnTable1 = new javax.swing.JButton();
        btnTable2 = new javax.swing.JButton();
        btnTable3 = new javax.swing.JButton();
        btnTable6 = new javax.swing.JButton();
        btnTable5 = new javax.swing.JButton();
        btnTable4 = new javax.swing.JButton();
        btnTable7 = new javax.swing.JButton();
        btnTable8 = new javax.swing.JButton();
        btnTable9 = new javax.swing.JButton();
        btnTable12 = new javax.swing.JButton();
        btnTable11 = new javax.swing.JButton();
        btnTable10 = new javax.swing.JButton();
        btnTable13 = new javax.swing.JButton();
        btnTable14 = new javax.swing.JButton();
        btnTable15 = new javax.swing.JButton();
        btnTable16 = new javax.swing.JButton();
        btnTable17 = new javax.swing.JButton();
        btnTable18 = new javax.swing.JButton();
        lblBooth = new javax.swing.JLabel();
        lblBar = new javax.swing.JLabel();
        lblPatio = new javax.swing.JLabel();
        ViewPOS = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        CardAppetizers = new javax.swing.JPanel();
        PanelAppetizers = new javax.swing.JPanel();
        btnChipsSalsa = new javax.swing.JButton();
        btnSpinachArtiDip = new javax.swing.JButton();
        btnFourWay = new javax.swing.JButton();
        btnMozarellaSticks = new javax.swing.JButton();
        btnSantaFeChicQue = new javax.swing.JButton();
        btnCalamari = new javax.swing.JButton();
        lblAppetizers = new javax.swing.JLabel();
        CardBurgers = new javax.swing.JPanel();
        PanelBurgers = new javax.swing.JPanel();
        btnSouthwest = new javax.swing.JButton();
        btnThreeCheese = new javax.swing.JButton();
        btnBaconCheddar = new javax.swing.JButton();
        btnCATCHBurger = new javax.swing.JButton();
        btnSwissMelt = new javax.swing.JButton();
        btnTheWorks = new javax.swing.JButton();
        lblBurgers = new javax.swing.JLabel();
        CardWings = new javax.swing.JPanel();
        PanelWings = new javax.swing.JPanel();
        btnOriginal = new javax.swing.JButton();
        btnLemonPepper = new javax.swing.JButton();
        btnHoneyBBQ = new javax.swing.JButton();
        btnCATCHWings = new javax.swing.JButton();
        btnTeriyaki = new javax.swing.JButton();
        btnHotWingsNaked = new javax.swing.JButton();
        lblWings = new javax.swing.JLabel();
        CardSalads = new javax.swing.JPanel();
        PanelSalads = new javax.swing.JPanel();
        btnCaesar = new javax.swing.JButton();
        btnGrilledSalmon = new javax.swing.JButton();
        btnChipotle = new javax.swing.JButton();
        btnRefreshingCATCH = new javax.swing.JButton();
        btnChickenAvocado = new javax.swing.JButton();
        btnAsian = new javax.swing.JButton();
        lblSalads = new javax.swing.JLabel();
        CardExtras = new javax.swing.JPanel();
        PanelExtras = new javax.swing.JPanel();
        btnFries = new javax.swing.JButton();
        btnMashedPotatoes = new javax.swing.JButton();
        btnQuesoDip = new javax.swing.JButton();
        btnPotatoWedges = new javax.swing.JButton();
        btnCorn = new javax.swing.JButton();
        btnColeslaw = new javax.swing.JButton();
        lblExtras = new javax.swing.JLabel();
        CardDesserts = new javax.swing.JPanel();
        PanelDesserts = new javax.swing.JPanel();
        btnChocolateMousse = new javax.swing.JButton();
        btnCannoli = new javax.swing.JButton();
        btnMilkshake = new javax.swing.JButton();
        btnCATCHIceCream = new javax.swing.JButton();
        btnTiramisu = new javax.swing.JButton();
        btnRedVelvet = new javax.swing.JButton();
        lblDesserts = new javax.swing.JLabel();
        CardDrinks = new javax.swing.JPanel();
        PanelDrinks = new javax.swing.JPanel();
        btnCoke = new javax.swing.JButton();
        btnSprite = new javax.swing.JButton();
        btnSweetTea = new javax.swing.JButton();
        btnCoffee = new javax.swing.JButton();
        btnLemonade = new javax.swing.JButton();
        btnDietCoke = new javax.swing.JButton();
        lblDrinks = new javax.swing.JLabel();
        PanelCategories = new javax.swing.JPanel();
        btnDrinks = new javax.swing.JButton();
        btnExtras = new javax.swing.JButton();
        btnAppetizers = new javax.swing.JButton();
        btnBurgers = new javax.swing.JButton();
        btnWings = new javax.swing.JButton();
        btnSalads = new javax.swing.JButton();
        btnDesserts = new javax.swing.JButton();
        PanelOrder = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        Panel_OrderLabel = new javax.swing.JPanel();
        lblOrderNumber = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblOrderNumberObtained = new javax.swing.JLabel();
        lblTable = new javax.swing.JLabel();
        lblTableNum = new javax.swing.JLabel();
        lblParty = new javax.swing.JLabel();
        lblPartyNum = new javax.swing.JLabel();
        lblWaiter = new javax.swing.JLabel();
        lblDiscountAmount = new javax.swing.JPanel();
        lblSubtotal = new javax.swing.JLabel();
        lblTax = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblSubtotalAmount = new javax.swing.JLabel();
        lblTaxAmount = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        btnPrintCheck = new javax.swing.JButton();
        btnClearTable = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblCashPaidAmount = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblChangeAmount = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ViewTimeCard = new javax.swing.JPanel();
        CardLogin = new javax.swing.JPanel();
        PanelLogin = new javax.swing.JPanel();
        lblEmpId = new javax.swing.JLabel();
        txtEmpId = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CardClockInClockOut = new javax.swing.JPanel();
        PanelHolderForClocking = new javax.swing.JPanel();
        btnClockInClockOut = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblLoggedInAs = new javax.swing.JLabel();
        lblEmpName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ViewManager = new javax.swing.JPanel();
        LetsGo_WIP = new javax.swing.JPanel();
        Manager_BackOffice = new javax.swing.JTabbedPane();
        BackOffice_Employees = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        BackOffice_Inventory = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        BackOffice_CostOfSales = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblRevenue = new javax.swing.JTable();
        BackOffice_Reports = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSales = new javax.swing.JTable();
        ViewOrders = new javax.swing.JPanel();
        PanelOrders = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        scrollOpenOrders = new javax.swing.JScrollPane();
        tblOpenOrders = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaidOrders = new javax.swing.JTable();
        btnViewOrder = new javax.swing.JButton();
        btnDeleteOrder = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        PanelTransaction = new javax.swing.JPanel();
        PanelKeypad = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btnDecimalPoint = new javax.swing.JButton();
        PanelTextEntry = new javax.swing.JPanel();
        txtKeypad = new javax.swing.JTextField();
        PanelPaymentMethod = new javax.swing.JPanel();
        btnCreditDebit = new javax.swing.JButton();
        btnGiftCard = new javax.swing.JButton();
        btnCash = new javax.swing.JButton();
        btnPay = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelBackground.setBackground(new java.awt.Color(102, 102, 102));
        PanelBackground.setMinimumSize(new java.awt.Dimension(100, 100));

        PanelHeader.setBackground(new java.awt.Color(204, 204, 204));

        btnTables.setText("Tables");
        btnTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablesActionPerformed(evt);
            }
        });

        btnTimeCard.setText("Time Card");
        btnTimeCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeCardActionPerformed(evt);
            }
        });

        btnManager.setText("Manager");
        btnManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagerActionPerformed(evt);
            }
        });

        btnViewOrders.setText("View Orders");
        btnViewOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOrdersActionPerformed(evt);
            }
        });

        lblSystemClock.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblSystemClock.setText("time");

        lblSystemDate.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblSystemDate.setText("date");

        jLabel9.setText("Server Name:");

        lblWaiterHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblWaiterHeader.setText("waiter");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHeaderLayout = new javax.swing.GroupLayout(PanelHeader);
        PanelHeader.setLayout(PanelHeaderLayout);
        PanelHeaderLayout.setHorizontalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSystemClock)
                    .addComponent(lblSystemDate))
                .addGap(255, 255, 255)
                .addComponent(jLabel9)
                .addGap(64, 64, 64)
                .addComponent(lblWaiterHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(18, 18, 18)
                .addComponent(btnTables)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimeCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManager)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewOrders)
                .addGap(37, 37, 37))
        );

        PanelHeaderLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnManager, btnTables, btnTimeCard, btnViewOrders});

        PanelHeaderLayout.setVerticalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addGroup(PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelHeaderLayout.createSequentialGroup()
                        .addComponent(lblSystemDate)
                        .addGap(3, 3, 3)
                        .addComponent(lblSystemClock))
                    .addGroup(PanelHeaderLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnTimeCard)
                                .addComponent(btnTables)
                                .addComponent(jLabel9)
                                .addComponent(lblWaiterHeader)
                                .addComponent(btnLogout))
                            .addComponent(btnManager)
                            .addComponent(btnViewOrders))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHeaderLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnManager, btnTables, btnTimeCard, btnViewOrders});

        javax.swing.GroupLayout Panel_NavigationLayout = new javax.swing.GroupLayout(Panel_Navigation);
        Panel_Navigation.setLayout(Panel_NavigationLayout);
        Panel_NavigationLayout.setHorizontalGroup(
            Panel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_NavigationLayout.createSequentialGroup()
                .addComponent(PanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Panel_NavigationLayout.setVerticalGroup(
            Panel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelCardView.setBackground(new java.awt.Color(223, 223, 246));
        PanelCardView.setLayout(new java.awt.CardLayout());

        btnTable1.setText("Table 1");
        btnTable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable1ActionPerformed(evt);
            }
        });

        btnTable2.setText("Table 2");
        btnTable2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable2ActionPerformed(evt);
            }
        });

        btnTable3.setText("Table 3");
        btnTable3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable3ActionPerformed(evt);
            }
        });

        btnTable6.setText("Table 6");
        btnTable6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable6ActionPerformed(evt);
            }
        });

        btnTable5.setText("Table 5");
        btnTable5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable5ActionPerformed(evt);
            }
        });

        btnTable4.setText("Table 4");
        btnTable4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable4ActionPerformed(evt);
            }
        });

        btnTable7.setText("Table 7");
        btnTable7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable7ActionPerformed(evt);
            }
        });

        btnTable8.setText("Table 8");
        btnTable8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable8ActionPerformed(evt);
            }
        });

        btnTable9.setText("Table 9");
        btnTable9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable9ActionPerformed(evt);
            }
        });

        btnTable12.setText("Table 12");
        btnTable12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable12ActionPerformed(evt);
            }
        });

        btnTable11.setText("Table 11");
        btnTable11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable11ActionPerformed(evt);
            }
        });

        btnTable10.setText("Table 10");
        btnTable10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable10ActionPerformed(evt);
            }
        });

        btnTable13.setText("Table 13");
        btnTable13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable13ActionPerformed(evt);
            }
        });

        btnTable14.setText("Table 14");
        btnTable14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable14ActionPerformed(evt);
            }
        });

        btnTable15.setText("Table 15");
        btnTable15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable15ActionPerformed(evt);
            }
        });

        btnTable16.setText("Table 16");
        btnTable16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable16ActionPerformed(evt);
            }
        });

        btnTable17.setText("Table 17");
        btnTable17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable17ActionPerformed(evt);
            }
        });

        btnTable18.setText("Table 18");
        btnTable18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTable18ActionPerformed(evt);
            }
        });

        lblBooth.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblBooth.setText("Booth");

        lblBar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblBar.setText("Bar");

        lblPatio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPatio.setText("Patio");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTable6, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTable7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTable10)
                            .addComponent(btnTable11)
                            .addComponent(btnTable12)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblBooth)
                        .addGap(163, 163, 163)
                        .addComponent(lblBar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatio)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnTable13, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTable14, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTable15, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTable16, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTable17, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTable18, javax.swing.GroupLayout.Alignment.TRAILING))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnTable1, btnTable10, btnTable11, btnTable12, btnTable13, btnTable14, btnTable15, btnTable16, btnTable17, btnTable18, btnTable2, btnTable3, btnTable4, btnTable5, btnTable6, btnTable7, btnTable8, btnTable9});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBooth)
                    .addComponent(lblBar)
                    .addComponent(lblPatio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTable7)
                    .addComponent(btnTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTable13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTable8)
                    .addComponent(btnTable14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTable9)
                    .addComponent(btnTable15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnTable4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTable10)
                    .addComponent(btnTable16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnTable5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTable11)
                    .addComponent(btnTable17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnTable12)
                    .addComponent(btnTable6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTable18))
                .addContainerGap(733, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnTable1, btnTable10, btnTable11, btnTable12, btnTable13, btnTable14, btnTable15, btnTable16, btnTable17, btnTable18, btnTable2, btnTable3, btnTable4, btnTable5, btnTable6, btnTable7, btnTable8, btnTable9});

        javax.swing.GroupLayout ViewTablesLayout = new javax.swing.GroupLayout(ViewTables);
        ViewTables.setLayout(ViewTablesLayout);
        ViewTablesLayout.setHorizontalGroup(
            ViewTablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewTablesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(958, Short.MAX_VALUE))
        );
        ViewTablesLayout.setVerticalGroup(
            ViewTablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelCardView.add(ViewTables, "card9");

        ViewPOS.setBackground(new java.awt.Color(204, 204, 204));

        PanelMenu.setBackground(new java.awt.Color(223, 223, 246));
        PanelMenu.setLayout(new java.awt.CardLayout());

        CardAppetizers.setBackground(new java.awt.Color(153, 153, 153));

        PanelAppetizers.setBackground(new java.awt.Color(153, 153, 153));

        btnChipsSalsa.setText("Chips and Salsa");
        btnChipsSalsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChipsSalsaActionPerformed(evt);
            }
        });

        btnSpinachArtiDip.setText("Spinach Artichoke Dip");
        btnSpinachArtiDip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpinachArtiDipActionPerformed(evt);
            }
        });

        btnFourWay.setText("Four-Way Sampler");
        btnFourWay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFourWayActionPerformed(evt);
            }
        });

        btnMozarellaSticks.setText("Mozarrella Sticks");
        btnMozarellaSticks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMozarellaSticksActionPerformed(evt);
            }
        });

        btnSantaFeChicQue.setText("Santa Fe Chicken Quesadilla");
        btnSantaFeChicQue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSantaFeChicQueActionPerformed(evt);
            }
        });

        btnCalamari.setText("Calamari");
        btnCalamari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalamariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAppetizersLayout = new javax.swing.GroupLayout(PanelAppetizers);
        PanelAppetizers.setLayout(PanelAppetizersLayout);
        PanelAppetizersLayout.setHorizontalGroup(
            PanelAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAppetizersLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(PanelAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAppetizersLayout.createSequentialGroup()
                        .addComponent(btnChipsSalsa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMozarellaSticks))
                    .addGroup(PanelAppetizersLayout.createSequentialGroup()
                        .addComponent(btnSpinachArtiDip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSantaFeChicQue))
                    .addGroup(PanelAppetizersLayout.createSequentialGroup()
                        .addComponent(btnFourWay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalamari)))
                .addContainerGap(412, Short.MAX_VALUE))
        );

        PanelAppetizersLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCalamari, btnChipsSalsa, btnFourWay, btnMozarellaSticks, btnSantaFeChicQue, btnSpinachArtiDip});

        PanelAppetizersLayout.setVerticalGroup(
            PanelAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAppetizersLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(PanelAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChipsSalsa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMozarellaSticks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSantaFeChicQue)
                    .addComponent(btnSpinachArtiDip, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFourWay)
                    .addComponent(btnCalamari))
                .addGap(54, 54, 54))
        );

        PanelAppetizersLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCalamari, btnChipsSalsa, btnFourWay, btnMozarellaSticks, btnSantaFeChicQue});

        lblAppetizers.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblAppetizers.setText("Appetizers");

        javax.swing.GroupLayout CardAppetizersLayout = new javax.swing.GroupLayout(CardAppetizers);
        CardAppetizers.setLayout(CardAppetizersLayout);
        CardAppetizersLayout.setHorizontalGroup(
            CardAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardAppetizersLayout.createSequentialGroup()
                .addComponent(lblAppetizers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(PanelAppetizers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CardAppetizersLayout.setVerticalGroup(
            CardAppetizersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardAppetizersLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblAppetizers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelAppetizers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(838, Short.MAX_VALUE))
        );

        PanelMenu.add(CardAppetizers, "card3");

        CardBurgers.setBackground(new java.awt.Color(223, 223, 246));

        PanelBurgers.setBackground(new java.awt.Color(223, 223, 246));

        btnSouthwest.setText("Southwest ");
        btnSouthwest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSouthwestActionPerformed(evt);
            }
        });

        btnThreeCheese.setText("Three Cheese");
        btnThreeCheese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThreeCheeseActionPerformed(evt);
            }
        });

        btnBaconCheddar.setText("Bacon Cheddar");
        btnBaconCheddar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaconCheddarActionPerformed(evt);
            }
        });

        btnCATCHBurger.setText("C.A.T.C.H Burger");
        btnCATCHBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCATCHBurgerActionPerformed(evt);
            }
        });

        btnSwissMelt.setText("Swiss Melt");
        btnSwissMelt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwissMeltActionPerformed(evt);
            }
        });

        btnTheWorks.setText("The Works");
        btnTheWorks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTheWorksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBurgersLayout = new javax.swing.GroupLayout(PanelBurgers);
        PanelBurgers.setLayout(PanelBurgersLayout);
        PanelBurgersLayout.setHorizontalGroup(
            PanelBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBurgersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelBurgersLayout.createSequentialGroup()
                        .addComponent(btnBaconCheddar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCATCHBurger, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(PanelBurgersLayout.createSequentialGroup()
                        .addComponent(btnSouthwest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTheWorks))
                    .addGroup(PanelBurgersLayout.createSequentialGroup()
                        .addComponent(btnThreeCheese)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSwissMelt)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PanelBurgersLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBaconCheddar, btnSouthwest, btnSwissMelt, btnTheWorks, btnThreeCheese});

        PanelBurgersLayout.setVerticalGroup(
            PanelBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBurgersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSouthwest)
                    .addComponent(btnTheWorks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThreeCheese)
                    .addComponent(btnSwissMelt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBaconCheddar)
                    .addComponent(btnCATCHBurger))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        PanelBurgersLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBaconCheddar, btnCATCHBurger, btnSouthwest, btnSwissMelt, btnTheWorks, btnThreeCheese});

        lblBurgers.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblBurgers.setText("Burgers");

        javax.swing.GroupLayout CardBurgersLayout = new javax.swing.GroupLayout(CardBurgers);
        CardBurgers.setLayout(CardBurgersLayout);
        CardBurgersLayout.setHorizontalGroup(
            CardBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardBurgersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardBurgersLayout.createSequentialGroup()
                        .addComponent(lblBurgers)
                        .addContainerGap(752, Short.MAX_VALUE))
                    .addComponent(PanelBurgers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        CardBurgersLayout.setVerticalGroup(
            CardBurgersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardBurgersLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblBurgers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBurgers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMenu.add(CardBurgers, "card4");

        CardWings.setBackground(new java.awt.Color(223, 223, 246));

        PanelWings.setBackground(new java.awt.Color(223, 223, 246));

        btnOriginal.setText("Original");
        btnOriginal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOriginalActionPerformed(evt);
            }
        });

        btnLemonPepper.setText("Lemon Pepper");
        btnLemonPepper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLemonPepperActionPerformed(evt);
            }
        });

        btnHoneyBBQ.setText("Honey Barbecue");
        btnHoneyBBQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoneyBBQActionPerformed(evt);
            }
        });

        btnCATCHWings.setText("C.A.T.C.H Wings");
        btnCATCHWings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCATCHWingsActionPerformed(evt);
            }
        });

        btnTeriyaki.setText("Teriyaki");
        btnTeriyaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTeriyakiActionPerformed(evt);
            }
        });

        btnHotWingsNaked.setText("Hot Wings Naked");
        btnHotWingsNaked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHotWingsNakedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelWingsLayout = new javax.swing.GroupLayout(PanelWings);
        PanelWings.setLayout(PanelWingsLayout);
        PanelWingsLayout.setHorizontalGroup(
            PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWingsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLemonPepper)
                    .addComponent(btnHoneyBBQ)
                    .addComponent(btnOriginal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCATCHWings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnTeriyaki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(btnHotWingsNaked, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(78, 78, 78))
        );

        PanelWingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHoneyBBQ, btnLemonPepper, btnOriginal});

        PanelWingsLayout.setVerticalGroup(
            PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWingsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHotWingsNaked)
                    .addComponent(btnOriginal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnLemonPepper)
                    .addComponent(btnTeriyaki))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCATCHWings)
                    .addComponent(btnHoneyBBQ))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelWingsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCATCHWings, btnHoneyBBQ, btnHotWingsNaked, btnLemonPepper, btnOriginal, btnTeriyaki});

        lblWings.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblWings.setText("Wings");

        javax.swing.GroupLayout CardWingsLayout = new javax.swing.GroupLayout(CardWings);
        CardWings.setLayout(CardWingsLayout);
        CardWingsLayout.setHorizontalGroup(
            CardWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardWingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelWings, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWings))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CardWingsLayout.setVerticalGroup(
            CardWingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardWingsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblWings)
                .addGap(18, 18, 18)
                .addComponent(PanelWings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMenu.add(CardWings, "card5");

        CardSalads.setBackground(new java.awt.Color(153, 153, 153));

        PanelSalads.setBackground(new java.awt.Color(153, 153, 153));

        btnCaesar.setText("Caesar ");
        btnCaesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaesarActionPerformed(evt);
            }
        });

        btnGrilledSalmon.setText("Grilled Salmon");
        btnGrilledSalmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrilledSalmonActionPerformed(evt);
            }
        });

        btnChipotle.setText("Chipotle");
        btnChipotle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChipotleActionPerformed(evt);
            }
        });

        btnRefreshingCATCH.setText("Refreshing C.A.T.C.H");
        btnRefreshingCATCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshingCATCHActionPerformed(evt);
            }
        });

        btnChickenAvocado.setText("Chicken Avocado");
        btnChickenAvocado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChickenAvocadoActionPerformed(evt);
            }
        });

        btnAsian.setText("Asian");
        btnAsian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSaladsLayout = new javax.swing.GroupLayout(PanelSalads);
        PanelSalads.setLayout(PanelSaladsLayout);
        PanelSaladsLayout.setHorizontalGroup(
            PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSaladsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGrilledSalmon)
                    .addComponent(btnChipotle)
                    .addComponent(btnCaesar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAsian, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnChickenAvocado, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnRefreshingCATCH))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        PanelSaladsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAsian, btnCaesar, btnChickenAvocado, btnChipotle, btnGrilledSalmon, btnRefreshingCATCH});

        PanelSaladsLayout.setVerticalGroup(
            PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSaladsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsian)
                    .addComponent(btnCaesar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnGrilledSalmon)
                    .addComponent(btnChickenAvocado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshingCATCH)
                    .addComponent(btnChipotle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelSaladsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAsian, btnCaesar, btnChickenAvocado, btnChipotle, btnGrilledSalmon, btnRefreshingCATCH});

        lblSalads.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblSalads.setText("Salads");

        javax.swing.GroupLayout CardSaladsLayout = new javax.swing.GroupLayout(CardSalads);
        CardSalads.setLayout(CardSaladsLayout);
        CardSaladsLayout.setHorizontalGroup(
            CardSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardSaladsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSalads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSalads))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CardSaladsLayout.setVerticalGroup(
            CardSaladsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardSaladsLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblSalads)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelSalads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMenu.add(CardSalads, "card6");

        CardExtras.setBackground(new java.awt.Color(223, 223, 246));

        PanelExtras.setBackground(new java.awt.Color(223, 223, 246));

        btnFries.setText("Fries");
        btnFries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFriesActionPerformed(evt);
            }
        });

        btnMashedPotatoes.setText("Mashed Potatoes");
        btnMashedPotatoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMashedPotatoesActionPerformed(evt);
            }
        });

        btnQuesoDip.setText("Queso Dip");
        btnQuesoDip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuesoDipActionPerformed(evt);
            }
        });

        btnPotatoWedges.setText("Potato Wedges");
        btnPotatoWedges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPotatoWedgesActionPerformed(evt);
            }
        });

        btnCorn.setText("Corn");
        btnCorn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCornActionPerformed(evt);
            }
        });

        btnColeslaw.setText("Coleslaw");
        btnColeslaw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColeslawActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelExtrasLayout = new javax.swing.GroupLayout(PanelExtras);
        PanelExtras.setLayout(PanelExtrasLayout);
        PanelExtrasLayout.setHorizontalGroup(
            PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExtrasLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMashedPotatoes)
                    .addComponent(btnQuesoDip)
                    .addComponent(btnFries))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnColeslaw)
                    .addComponent(btnPotatoWedges)
                    .addComponent(btnCorn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        PanelExtrasLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnColeslaw, btnFries, btnMashedPotatoes, btnPotatoWedges, btnQuesoDip});

        PanelExtrasLayout.setVerticalGroup(
            PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExtrasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnColeslaw)
                    .addComponent(btnFries))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnMashedPotatoes)
                    .addComponent(btnCorn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPotatoWedges)
                    .addComponent(btnQuesoDip))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelExtrasLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnColeslaw, btnCorn, btnFries, btnMashedPotatoes, btnPotatoWedges, btnQuesoDip});

        lblExtras.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblExtras.setText("Extras");

        javax.swing.GroupLayout CardExtrasLayout = new javax.swing.GroupLayout(CardExtras);
        CardExtras.setLayout(CardExtrasLayout);
        CardExtrasLayout.setHorizontalGroup(
            CardExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardExtrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelExtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExtras))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CardExtrasLayout.setVerticalGroup(
            CardExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardExtrasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblExtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelExtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMenu.add(CardExtras, "card7");

        CardDesserts.setBackground(new java.awt.Color(223, 223, 246));

        PanelDesserts.setBackground(new java.awt.Color(223, 223, 246));

        btnChocolateMousse.setText("Chocolate Mousse Cheesecake");
        btnChocolateMousse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChocolateMousseActionPerformed(evt);
            }
        });

        btnCannoli.setText("Cannoli");
        btnCannoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCannoliActionPerformed(evt);
            }
        });

        btnMilkshake.setText("Milkshake");
        btnMilkshake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMilkshakeActionPerformed(evt);
            }
        });

        btnCATCHIceCream.setText("C.A.T.C.H Ice Cream Suprise");
        btnCATCHIceCream.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCATCHIceCreamActionPerformed(evt);
            }
        });

        btnTiramisu.setText("Tiramisu Cake");
        btnTiramisu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiramisuActionPerformed(evt);
            }
        });

        btnRedVelvet.setText("Red Velvet Cake");
        btnRedVelvet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedVelvetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDessertsLayout = new javax.swing.GroupLayout(PanelDesserts);
        PanelDesserts.setLayout(PanelDessertsLayout);
        PanelDessertsLayout.setHorizontalGroup(
            PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDessertsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCannoli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMilkshake, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(btnChocolateMousse, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTiramisu)
                    .addComponent(btnRedVelvet, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCATCHIceCream, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelDessertsLayout.setVerticalGroup(
            PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDessertsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRedVelvet)
                    .addComponent(btnChocolateMousse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCannoli)
                    .addComponent(btnTiramisu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMilkshake)
                    .addComponent(btnCATCHIceCream))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelDessertsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCATCHIceCream, btnCannoli, btnChocolateMousse, btnMilkshake, btnRedVelvet, btnTiramisu});

        lblDesserts.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDesserts.setText("Desserts");

        javax.swing.GroupLayout CardDessertsLayout = new javax.swing.GroupLayout(CardDesserts);
        CardDesserts.setLayout(CardDessertsLayout);
        CardDessertsLayout.setHorizontalGroup(
            CardDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardDessertsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDesserts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDesserts))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CardDessertsLayout.setVerticalGroup(
            CardDessertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardDessertsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblDesserts)
                .addGap(18, 18, 18)
                .addComponent(PanelDesserts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMenu.add(CardDesserts, "card8");

        CardDrinks.setBackground(new java.awt.Color(223, 223, 246));

        PanelDrinks.setBackground(new java.awt.Color(223, 223, 246));

        btnCoke.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnCoke.setText("Coke");
        btnCoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCokeActionPerformed(evt);
            }
        });

        btnSprite.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSprite.setText("Sprite");
        btnSprite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpriteActionPerformed(evt);
            }
        });

        btnSweetTea.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSweetTea.setText("Sweet Tea");
        btnSweetTea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSweetTeaActionPerformed(evt);
            }
        });

        btnCoffee.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnCoffee.setText("Coffee");
        btnCoffee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoffeeActionPerformed(evt);
            }
        });

        btnLemonade.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnLemonade.setText("Lemonade");
        btnLemonade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLemonadeActionPerformed(evt);
            }
        });

        btnDietCoke.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDietCoke.setText("Diet Coke");
        btnDietCoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDietCokeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDrinksLayout = new javax.swing.GroupLayout(PanelDrinks);
        PanelDrinks.setLayout(PanelDrinksLayout);
        PanelDrinksLayout.setHorizontalGroup(
            PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDrinksLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSprite)
                    .addComponent(btnSweetTea)
                    .addComponent(btnCoke))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDietCoke, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnLemonade, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnCoffee))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelDrinksLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCoffee, btnCoke, btnDietCoke, btnLemonade, btnSprite, btnSweetTea});

        PanelDrinksLayout.setVerticalGroup(
            PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDrinksLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDietCoke)
                    .addComponent(btnCoke))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSprite)
                    .addComponent(btnLemonade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCoffee)
                    .addComponent(btnSweetTea))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelDrinksLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCoffee, btnCoke, btnDietCoke, btnLemonade, btnSprite, btnSweetTea});

        lblDrinks.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDrinks.setText("Drinks");

        javax.swing.GroupLayout CardDrinksLayout = new javax.swing.GroupLayout(CardDrinks);
        CardDrinks.setLayout(CardDrinksLayout);
        CardDrinksLayout.setHorizontalGroup(
            CardDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardDrinksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDrinks)
                    .addComponent(PanelDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CardDrinksLayout.setVerticalGroup(
            CardDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardDrinksLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblDrinks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMenu.add(CardDrinks, "card2");

        PanelCategories.setBackground(new java.awt.Color(204, 204, 204));

        btnDrinks.setBackground(new java.awt.Color(182, 225, 225));
        btnDrinks.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDrinks.setText("Drinks");
        btnDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrinksActionPerformed(evt);
            }
        });

        btnExtras.setBackground(new java.awt.Color(182, 225, 225));
        btnExtras.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnExtras.setText("Extras");
        btnExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrasActionPerformed(evt);
            }
        });

        btnAppetizers.setBackground(new java.awt.Color(182, 225, 225));
        btnAppetizers.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAppetizers.setText("Appetizers");
        btnAppetizers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppetizersActionPerformed(evt);
            }
        });

        btnBurgers.setBackground(new java.awt.Color(182, 225, 225));
        btnBurgers.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnBurgers.setText("Burgers");
        btnBurgers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBurgersActionPerformed(evt);
            }
        });

        btnWings.setBackground(new java.awt.Color(182, 225, 225));
        btnWings.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnWings.setText("Wings");
        btnWings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWingsActionPerformed(evt);
            }
        });

        btnSalads.setBackground(new java.awt.Color(182, 225, 225));
        btnSalads.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSalads.setText("Salads");
        btnSalads.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaladsActionPerformed(evt);
            }
        });

        btnDesserts.setBackground(new java.awt.Color(182, 225, 225));
        btnDesserts.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDesserts.setText("Desserts");
        btnDesserts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDessertsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCategoriesLayout = new javax.swing.GroupLayout(PanelCategories);
        PanelCategories.setLayout(PanelCategoriesLayout);
        PanelCategoriesLayout.setHorizontalGroup(
            PanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCategoriesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDrinks)
                    .addComponent(btnDesserts)
                    .addComponent(btnExtras)
                    .addComponent(btnSalads)
                    .addComponent(btnWings)
                    .addComponent(btnBurgers)
                    .addComponent(btnAppetizers))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelCategoriesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAppetizers, btnBurgers, btnDesserts, btnDrinks, btnExtras, btnSalads, btnWings});

        PanelCategoriesLayout.setVerticalGroup(
            PanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCategoriesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAppetizers)
                .addGap(1, 1, 1)
                .addComponent(btnBurgers)
                .addGap(1, 1, 1)
                .addComponent(btnWings)
                .addGap(0, 0, 0)
                .addComponent(btnSalads)
                .addGap(1, 1, 1)
                .addComponent(btnExtras)
                .addGap(0, 0, 0)
                .addComponent(btnDesserts)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelCategoriesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAppetizers, btnBurgers, btnDesserts, btnDrinks, btnExtras, btnSalads, btnWings});

        PanelOrder.setBackground(new java.awt.Color(204, 204, 204));

        tblOrder.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.setRowHeight(24);
        tblOrder.setSelectionBackground(new java.awt.Color(153, 255, 255));
        jScrollPane2.setViewportView(tblOrder);
        if (tblOrder.getColumnModel().getColumnCount() > 0) {
            tblOrder.getColumnModel().getColumn(0).setResizable(false);
            tblOrder.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblOrder.getColumnModel().getColumn(1).setResizable(false);
            tblOrder.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        Panel_OrderLabel.setBackground(new java.awt.Color(247, 245, 245));

        lblOrderNumber.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblOrderNumber.setText("Order Number:");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setText("Server: ");

        lblOrderNumberObtained.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblOrderNumberObtained.setText("order number");

        lblTable.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblTable.setText("Table #:");

        lblTableNum.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblTableNum.setText("table num");

        lblParty.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblParty.setText("No. of Party:");

        lblPartyNum.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblPartyNum.setText("party");

        lblWaiter.setText("Waiter Name");

        javax.swing.GroupLayout Panel_OrderLabelLayout = new javax.swing.GroupLayout(Panel_OrderLabel);
        Panel_OrderLabel.setLayout(Panel_OrderLabelLayout);
        Panel_OrderLabelLayout.setHorizontalGroup(
            Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                        .addComponent(lblOrderNumber)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrderNumberObtained))
                    .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblWaiter, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                        .addComponent(lblParty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPartyNum))
                    .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                        .addComponent(lblTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(lblTableNum)))
                .addGap(37, 37, 37))
        );
        Panel_OrderLabelLayout.setVerticalGroup(
            Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber)
                    .addComponent(lblOrderNumberObtained)
                    .addComponent(lblTable)
                    .addComponent(lblTableNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_OrderLabelLayout.createSequentialGroup()
                        .addGroup(Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblParty)
                            .addComponent(lblPartyNum))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_OrderLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblWaiter)))
                .addContainerGap())
        );

        lblDiscountAmount.setBackground(new java.awt.Color(246, 244, 244));

        lblSubtotal.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblSubtotal.setText("Subtotal:        $");

        lblTax.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblTax.setText("Tax:                 $");

        lblTotal.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblTotal.setText("Total:              $");

        lblSubtotalAmount.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblSubtotalAmount.setText("0.00");

        lblTaxAmount.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblTaxAmount.setText("0.00");

        lblTotalAmount.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblTotalAmount.setText("0.00");

        btnRemove.setBackground(new java.awt.Color(204, 255, 255));
        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnRemove.setText("REMOVE");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnPrintCheck.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnPrintCheck.setText("PRINT");
        btnPrintCheck.setEnabled(false);
        btnPrintCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintCheckActionPerformed(evt);
            }
        });

        btnClearTable.setBackground(new java.awt.Color(204, 255, 255));
        btnClearTable.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnClearTable.setText("CLEAR");
        btnClearTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTableActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Cash Paid:     $");

        lblCashPaidAmount.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblCashPaidAmount.setText("0.00");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setText("Change:        $");

        lblChangeAmount.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblChangeAmount.setText("0.00");

        btnSend.setBackground(new java.awt.Color(255, 51, 51));
        btnSend.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSend.setText("SEND");
        btnSend.setEnabled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 255, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton7.setText("DONE");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setText("Discount:            $");

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("0.00");

        javax.swing.GroupLayout lblDiscountAmountLayout = new javax.swing.GroupLayout(lblDiscountAmount);
        lblDiscountAmount.setLayout(lblDiscountAmountLayout);
        lblDiscountAmountLayout.setHorizontalGroup(
            lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblDiscountAmountLayout.createSequentialGroup()
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrintCheck)
                            .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnClearTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                        .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotal)
                            .addComponent(lblTax)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotalAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTaxAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblChangeAmount, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCashPaidAmount))
                    .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalAmount))
                    .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );

        lblDiscountAmountLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClearTable, btnPrintCheck, btnRemove, btnSend});

        lblDiscountAmountLayout.setVerticalGroup(
            lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal)
                    .addComponent(lblSubtotalAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTax)
                    .addComponent(lblTaxAmount))
                .addGap(18, 18, 18)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblTotalAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblCashPaidAmount))
                .addGap(1, 1, 1)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblChangeAmount))
                .addContainerGap())
            .addGroup(lblDiscountAmountLayout.createSequentialGroup()
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrintCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(lblDiscountAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearTable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jButton7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblDiscountAmountLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClearTable, btnPrintCheck, btnRemove, btnSend, jButton7});

        javax.swing.GroupLayout PanelOrderLayout = new javax.swing.GroupLayout(PanelOrder);
        PanelOrder.setLayout(PanelOrderLayout);
        PanelOrderLayout.setHorizontalGroup(
            PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOrderLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Panel_OrderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDiscountAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        PanelOrderLayout.setVerticalGroup(
            PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_OrderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ViewPOSLayout = new javax.swing.GroupLayout(ViewPOS);
        ViewPOS.setLayout(ViewPOSLayout);
        ViewPOSLayout.setHorizontalGroup(
            ViewPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewPOSLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ViewPOSLayout.setVerticalGroup(
            ViewPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewPOSLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(ViewPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ViewPOSLayout.createSequentialGroup()
                        .addComponent(PanelOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(48, 48, 48)))
                .addGap(0, 0, 0))
        );

        PanelCardView.add(ViewPOS, "card2");

        ViewTimeCard.setBackground(new java.awt.Color(223, 223, 246));
        ViewTimeCard.setLayout(new java.awt.CardLayout());

        CardLogin.setBackground(new java.awt.Color(204, 204, 204));
        CardLogin.setLayout(new java.awt.GridBagLayout());

        PanelLogin.setBackground(new java.awt.Color(204, 204, 204));
        PanelLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblEmpId.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblEmpId.setText("Employee ID:");

        txtEmpId.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnLogin.setBackground(new java.awt.Color(204, 255, 255));
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel6.setText("Login");

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLoginLayout.createSequentialGroup()
                        .addComponent(lblEmpId)
                        .addGap(28, 28, 28)
                        .addComponent(txtEmpId, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(126, 126, 126))
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmpId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpId))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 87;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(228, 437, 464, 395);
        CardLogin.add(PanelLogin, gridBagConstraints);

        ViewTimeCard.add(CardLogin, "card2");

        CardClockInClockOut.setBackground(new java.awt.Color(153, 153, 153));
        CardClockInClockOut.setLayout(new java.awt.GridBagLayout());

        PanelHolderForClocking.setBackground(new java.awt.Color(153, 153, 153));
        PanelHolderForClocking.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnClockInClockOut.setBackground(new java.awt.Color(204, 255, 255));
        btnClockInClockOut.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnClockInClockOut.setText("Clock In / Clock Out");
        btnClockInClockOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClockInClockOutActionPerformed(evt);
            }
        });

        lblLoggedInAs.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lblLoggedInAs.setText("Logged in as:");

        lblEmpName.setBackground(new java.awt.Color(51, 0, 204));
        lblEmpName.setFont(new java.awt.Font("Century Gothic", 2, 36)); // NOI18N
        lblEmpName.setForeground(new java.awt.Color(0, 255, 0));
        lblEmpName.setText("name");

        javax.swing.GroupLayout PanelHolderForClockingLayout = new javax.swing.GroupLayout(PanelHolderForClocking);
        PanelHolderForClocking.setLayout(PanelHolderForClockingLayout);
        PanelHolderForClockingLayout.setHorizontalGroup(
            PanelHolderForClockingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHolderForClockingLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PanelHolderForClockingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelHolderForClockingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnClockInClockOut, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelHolderForClockingLayout.createSequentialGroup()
                        .addComponent(lblLoggedInAs)
                        .addGap(63, 63, 63)
                        .addComponent(lblEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        PanelHolderForClockingLayout.setVerticalGroup(
            PanelHolderForClockingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHolderForClockingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelHolderForClockingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoggedInAs)
                    .addComponent(lblEmpName))
                .addGap(118, 118, 118)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(btnClockInClockOut, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 163;
        gridBagConstraints.ipady = 101;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 452, 509, 367);
        CardClockInClockOut.add(PanelHolderForClocking, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel4.setText("Time Card");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(129, 452, 0, 0);
        CardClockInClockOut.add(jLabel4, gridBagConstraints);

        ViewTimeCard.add(CardClockInClockOut, "card6");

        PanelCardView.add(ViewTimeCard, "card3");

        ViewManager.setBackground(new java.awt.Color(223, 223, 246));

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Catagory", "Unit Price", "QTY", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblEmployees);

        javax.swing.GroupLayout BackOffice_EmployeesLayout = new javax.swing.GroupLayout(BackOffice_Employees);
        BackOffice_Employees.setLayout(BackOffice_EmployeesLayout);
        BackOffice_EmployeesLayout.setHorizontalGroup(
            BackOffice_EmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_EmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        BackOffice_EmployeesLayout.setVerticalGroup(
            BackOffice_EmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_EmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        Manager_BackOffice.addTab("Employees", BackOffice_Employees);

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Catagory", "Unit Price", "QTY", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblInventory);

        javax.swing.GroupLayout BackOffice_InventoryLayout = new javax.swing.GroupLayout(BackOffice_Inventory);
        BackOffice_Inventory.setLayout(BackOffice_InventoryLayout);
        BackOffice_InventoryLayout.setHorizontalGroup(
            BackOffice_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_InventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        BackOffice_InventoryLayout.setVerticalGroup(
            BackOffice_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_InventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        Manager_BackOffice.addTab("Inventory", BackOffice_Inventory);

        tblRevenue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Catagory", "Unit Price", "QTY", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblRevenue);

        javax.swing.GroupLayout BackOffice_CostOfSalesLayout = new javax.swing.GroupLayout(BackOffice_CostOfSales);
        BackOffice_CostOfSales.setLayout(BackOffice_CostOfSalesLayout);
        BackOffice_CostOfSalesLayout.setHorizontalGroup(
            BackOffice_CostOfSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_CostOfSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        BackOffice_CostOfSalesLayout.setVerticalGroup(
            BackOffice_CostOfSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_CostOfSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        Manager_BackOffice.addTab("Revenue", BackOffice_CostOfSales);

        tblSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Catagory", "Unit Price", "QTY", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblSales);

        javax.swing.GroupLayout BackOffice_ReportsLayout = new javax.swing.GroupLayout(BackOffice_Reports);
        BackOffice_Reports.setLayout(BackOffice_ReportsLayout);
        BackOffice_ReportsLayout.setHorizontalGroup(
            BackOffice_ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        BackOffice_ReportsLayout.setVerticalGroup(
            BackOffice_ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackOffice_ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        Manager_BackOffice.addTab("Sales", BackOffice_Reports);

        javax.swing.GroupLayout LetsGo_WIPLayout = new javax.swing.GroupLayout(LetsGo_WIP);
        LetsGo_WIP.setLayout(LetsGo_WIPLayout);
        LetsGo_WIPLayout.setHorizontalGroup(
            LetsGo_WIPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LetsGo_WIPLayout.createSequentialGroup()
                .addComponent(Manager_BackOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 1278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LetsGo_WIPLayout.setVerticalGroup(
            LetsGo_WIPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LetsGo_WIPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Manager_BackOffice))
        );

        javax.swing.GroupLayout ViewManagerLayout = new javax.swing.GroupLayout(ViewManager);
        ViewManager.setLayout(ViewManagerLayout);
        ViewManagerLayout.setHorizontalGroup(
            ViewManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewManagerLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(LetsGo_WIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ViewManagerLayout.setVerticalGroup(
            ViewManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewManagerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(LetsGo_WIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        PanelCardView.add(ViewManager, "card4");

        ViewOrders.setBackground(new java.awt.Color(223, 223, 246));

        tblOpenOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Table No.", "Waiter"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOpenOrders.setColumnSelectionAllowed(true);
        tblOpenOrders.getTableHeader().setReorderingAllowed(false);
        scrollOpenOrders.setViewportView(tblOpenOrders);
        tblOpenOrders.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jTabbedPane1.addTab("OPEN", scrollOpenOrders);

        tblPaidOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Table No.", "Waiter"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPaidOrders.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblPaidOrders);
        tblPaidOrders.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jTabbedPane1.addTab("PAID", jScrollPane1);

        btnViewOrder.setText("View");
        btnViewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOrderActionPerformed(evt);
            }
        });

        btnDeleteOrder.setText("Delete");
        btnDeleteOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteOrderActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("ORDERS");

        javax.swing.GroupLayout PanelOrdersLayout = new javax.swing.GroupLayout(PanelOrders);
        PanelOrders.setLayout(PanelOrdersLayout);
        PanelOrdersLayout.setHorizontalGroup(
            PanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOrdersLayout.createSequentialGroup()
                .addGroup(PanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelOrdersLayout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(PanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnViewOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelOrdersLayout.setVerticalGroup(
            PanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PanelOrdersLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnViewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelOrdersLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelOrdersLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDeleteOrder, btnViewOrder});

        javax.swing.GroupLayout ViewOrdersLayout = new javax.swing.GroupLayout(ViewOrders);
        ViewOrders.setLayout(ViewOrdersLayout);
        ViewOrdersLayout.setHorizontalGroup(
            ViewOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(835, Short.MAX_VALUE))
        );
        ViewOrdersLayout.setVerticalGroup(
            ViewOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
        );

        PanelCardView.add(ViewOrders, "card5");

        PanelTransaction.setBackground(new java.awt.Color(102, 102, 102));

        btn1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn0.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btn7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 51, 51));
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btnDecimalPoint.setBackground(new java.awt.Color(204, 204, 204));
        btnDecimalPoint.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnDecimalPoint.setText(".");
        btnDecimalPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecimalPointActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKeypadLayout = new javax.swing.GroupLayout(PanelKeypad);
        PanelKeypad.setLayout(PanelKeypadLayout);
        PanelKeypadLayout.setHorizontalGroup(
            PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKeypadLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKeypadLayout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn0)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDecimalPoint))
                    .addGroup(PanelKeypadLayout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn9))
                    .addGroup(PanelKeypadLayout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn6))
                    .addGroup(PanelKeypadLayout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelKeypadLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnClear, btnDecimalPoint});

        PanelKeypadLayout.setVerticalGroup(
            PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKeypadLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1)
                    .addComponent(btn2)
                    .addComponent(btn3))
                .addGap(0, 0, 0)
                .addGroup(PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn5)
                    .addComponent(btn6)
                    .addComponent(btn4))
                .addGap(0, 0, 0)
                .addGroup(PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn8)
                    .addComponent(btn9)
                    .addComponent(btn7))
                .addGap(0, 0, 0)
                .addGroup(PanelKeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnClear)
                    .addComponent(btn0)
                    .addComponent(btnDecimalPoint))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelKeypadLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnClear, btnDecimalPoint});

        txtKeypad.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        txtKeypad.setForeground(new java.awt.Color(0, 0, 255));
        txtKeypad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeypadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTextEntryLayout = new javax.swing.GroupLayout(PanelTextEntry);
        PanelTextEntry.setLayout(PanelTextEntryLayout);
        PanelTextEntryLayout.setHorizontalGroup(
            PanelTextEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTextEntryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtKeypad)
                .addContainerGap())
        );
        PanelTextEntryLayout.setVerticalGroup(
            PanelTextEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTextEntryLayout.createSequentialGroup()
                .addComponent(txtKeypad, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        btnCreditDebit.setBackground(new java.awt.Color(0, 255, 0));
        btnCreditDebit.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCreditDebit.setText("Credit/Debit");
        btnCreditDebit.setEnabled(false);
        btnCreditDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditDebitActionPerformed(evt);
            }
        });

        btnGiftCard.setBackground(new java.awt.Color(255, 153, 102));
        btnGiftCard.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnGiftCard.setText("Gift Card");
        btnGiftCard.setEnabled(false);
        btnGiftCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiftCardActionPerformed(evt);
            }
        });

        btnCash.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCash.setText("Cash");
        btnCash.setEnabled(false);
        btnCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPaymentMethodLayout = new javax.swing.GroupLayout(PanelPaymentMethod);
        PanelPaymentMethod.setLayout(PanelPaymentMethodLayout);
        PanelPaymentMethodLayout.setHorizontalGroup(
            PanelPaymentMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPaymentMethodLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelPaymentMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCash)
                    .addComponent(btnGiftCard, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreditDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        PanelPaymentMethodLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCash, btnCreditDebit, btnGiftCard});

        PanelPaymentMethodLayout.setVerticalGroup(
            PanelPaymentMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPaymentMethodLayout.createSequentialGroup()
                .addComponent(btnCreditDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGiftCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCash, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        PanelPaymentMethodLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCreditDebit, btnGiftCard});

        btnPay.setText("Pay");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayCashActionPerformed(evt);
            }
        });

        btnConfirm.setText("Confirm ");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTransactionLayout = new javax.swing.GroupLayout(PanelTransaction);
        PanelTransaction.setLayout(PanelTransactionLayout);
        PanelTransactionLayout.setHorizontalGroup(
            PanelTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTransactionLayout.createSequentialGroup()
                .addGroup(PanelTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTransactionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelPaymentMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PanelTransactionLayout.createSequentialGroup()
                                .addGroup(PanelTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(PanelTextEntry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PanelKeypad, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanelTransactionLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelTransactionLayout.setVerticalGroup(
            PanelTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTransactionLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(PanelTextEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelKeypad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelBackgroundLayout = new javax.swing.GroupLayout(PanelBackground);
        PanelBackground.setLayout(PanelBackgroundLayout);
        PanelBackgroundLayout.setHorizontalGroup(
            PanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelBackgroundLayout.createSequentialGroup()
                        .addComponent(PanelCardView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelBackgroundLayout.setVerticalGroup(
            PanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelCardView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private BigDecimal runningTotal() {
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        BigDecimal subtotal = BigDecimal.valueOf(0.0);
        for (int i = 0; i < model.getDataVector().size(); i++) {

            //orderLst.add(new Order(model.getValueAt(i, 0).toString(),
            //BigDecimal.valueOf;
            // for (int i = 0; i < orderList.size(); i++) {
            subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(model.getValueAt(i, 1).toString())));
            
        }
        
        lblSubtotalAmount.setText(subtotal.toString());
        lblTaxAmount.setText(subtotal.multiply(BigDecimal.valueOf(0.070)).setScale(2, RoundingMode.DOWN).toString());
        lblTotalAmount.setText(subtotal.add(subtotal.multiply(BigDecimal.valueOf(0.070)).setScale(2, RoundingMode.DOWN)).toString());
        return subtotal;
    }
    
    private void viewOrders() {
        PanelCardView.removeAll();
        PanelCardView.add(ViewOrders);
        PanelCardView.repaint();
        PanelCardView.revalidate();
        
    }
    
    private void viewTables() {
        PanelCardView.removeAll();
        PanelCardView.add(ViewTables);
        PanelCardView.repaint();
        PanelCardView.revalidate();
    }
    
    private void viewPOS() {
        PanelCardView.removeAll();
        PanelCardView.add(ViewPOS);
        PanelCardView.repaint();
        PanelCardView.revalidate();
        btnPrintCheck.setEnabled(true);
        btnDecimalPoint.setEnabled(true);
        btnSend.setEnabled(true);
        
        btnCreditDebit.setEnabled(true);
        btnGiftCard.setEnabled(true);
        btnCash.setEnabled(true);
        txtKeypad.setVisible(true);
        receipt.setOrderNumber(dbconn.getOrderNumberFromDB());
        lblOrderNumberObtained.setText(String.valueOf(receipt.getOrderNumber()));
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        model.setRowCount(0);
        
        lblCashPaidAmount.setVisible(false);
        lblChangeAmount.setVisible(false);
        
    }
    
    private void getNumberOfGuests() {
        String numOfGuests = null;
        do {
            numOfGuests = JOptionPane.showInputDialog("How many guests?");
        } while ((Integer.parseInt(numOfGuests) < 1) || (numOfGuests == null));
        lblPartyNum.setText(numOfGuests);
    }
    private void btnDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrinksActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardDrinks);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnDrinksActionPerformed

    private void btnAppetizersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppetizersActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardAppetizers);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnAppetizersActionPerformed

    private void btnBurgersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBurgersActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardBurgers);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnBurgersActionPerformed

    private void btnWingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWingsActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardWings);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnWingsActionPerformed

    private void btnSaladsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaladsActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardSalads);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnSaladsActionPerformed

    private void btnExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrasActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardExtras);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnExtrasActionPerformed

    private void btnDessertsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDessertsActionPerformed
        // TODO add your handling code here:
        PanelMenu.removeAll();
        PanelMenu.add(CardDesserts);
        PanelMenu.repaint();
        PanelMenu.revalidate();
    }//GEN-LAST:event_btnDessertsActionPerformed

    private void btnTimeCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeCardActionPerformed
        txtEmpId.setText(null);
        keypadLogin.delete(0, keypadLogin.length());
        PanelCardView.removeAll();
        PanelCardView.add(ViewTimeCard);
        PanelCardView.repaint();
        PanelCardView.revalidate();

        /*TODO add your handling code here:
         *This remove the cash, credit/debit card, and the discount button 
         *from the transaction panel so that the employee do not see these buttons when clocking in
         */
        btnDecimalPoint.setEnabled(false);
        txtKeypad.setVisible(false);
        
        btnCreditDebit.setVisible(false);
        btnGiftCard.setVisible(false);
        btnCash.setVisible(false);
    }//GEN-LAST:event_btnTimeCardActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        if (dbconn.isEmployee(Integer.parseInt(txtEmpId.getText()))) {
            
            lblEmpName.setText(dbconn.Login(Integer.parseInt(txtEmpId.getText())));
            PanelCardView.removeAll();
            PanelCardView.add(CardClockInClockOut);
            PanelCardView.repaint();
            PanelCardView.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed");
        }
        

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablesActionPerformed
        // TODO add your handling code here:

        PanelCardView.removeAll();
        PanelCardView.add(ViewTables);
        PanelCardView.repaint();
        PanelCardView.revalidate();
        /*TODO add your handling code here:
         *This remove the cash, credit/debit card, and the discount button 
         *from the transaction panel so that the employee do not see these buttons when clocking in
         */
        btnDecimalPoint.setEnabled(true);
        txtKeypad.setEnabled(false);
        
        btnSend.setEnabled(false);
        
        btnCreditDebit.setEnabled(false);
        btnGiftCard.setEnabled(false);
        btnCash.setEnabled(false);
    }//GEN-LAST:event_btnTablesActionPerformed

    private void btnClockInClockOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClockInClockOutActionPerformed
        // TODO add your handling code here:

        dbconn.Clockin(Integer.parseInt(txtEmpId.getText()));
        
        viewTables();
        // Resets the Transaction Panel to normal state
        btnDecimalPoint.setEnabled(true);
        txtKeypad.setVisible(true);
        
        btnCreditDebit.setVisible(true);
        btnGiftCard.setVisible(true);
        btnCash.setVisible(true);
    }//GEN-LAST:event_btnClockInClockOutActionPerformed

    private void btnManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerActionPerformed
        // TODO add your handling code here:
        PanelCardView.removeAll();
        PanelCardView.add(ViewManager);
        PanelCardView.repaint();
        PanelCardView.revalidate();
        loadInventory();
        loadRevenue();
        loadSales();
       loadEmployees();
    }//GEN-LAST:event_btnManagerActionPerformed

    private void btnCokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCokeActionPerformed
        // TODO add your handling code here:
        /**
         * This is how you display an item and price from the database into a
         * DefaultTableModel
         */
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[0][0], menu[0][1]};
        model.addRow(row);
        runningTotal();

    }//GEN-LAST:event_btnCokeActionPerformed

    private void btnDietCokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDietCokeActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[1][0], menu[1][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnDietCokeActionPerformed

    private void btnSpriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpriteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[2][0], menu[2][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnSpriteActionPerformed

    private void btnLemonadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLemonadeActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[3][0], menu[3][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnLemonadeActionPerformed

    private void btnSweetTeaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSweetTeaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[4][0], menu[4][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnSweetTeaActionPerformed

    private void btnCoffeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoffeeActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[5][0], menu[5][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnCoffeeActionPerformed

    private void btnChipsSalsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChipsSalsaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[6][0], menu[6][1]};
        model.addRow(row);
        runningTotal();

    }//GEN-LAST:event_btnChipsSalsaActionPerformed

    private void btnCalamariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalamariActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[7][0], menu[7][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnCalamariActionPerformed

    private void btnMozarellaSticksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMozarellaSticksActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[8][0], menu[8][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnMozarellaSticksActionPerformed

    private void btnFourWayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFourWayActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[9][0], menu[9][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnFourWayActionPerformed

    private void btnSpinachArtiDipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpinachArtiDipActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[10][0], menu[10][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnSpinachArtiDipActionPerformed

    private void btnSantaFeChicQueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSantaFeChicQueActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[11][0], menu[11][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnSantaFeChicQueActionPerformed

    private void btnSouthwestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSouthwestActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[12][0], menu[12][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnSouthwestActionPerformed

    private void btnTheWorksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTheWorksActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[13][0], menu[13][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnTheWorksActionPerformed

    private void btnThreeCheeseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThreeCheeseActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[14][0], menu[14][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnThreeCheeseActionPerformed

    private void btnSwissMeltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwissMeltActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[15][0], menu[15][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnSwissMeltActionPerformed

    private void btnBaconCheddarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaconCheddarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[16][0], menu[16][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnBaconCheddarActionPerformed

    private void btnCATCHBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCATCHBurgerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[17][0], menu[17][1]};
        model.addRow(row);
    }//GEN-LAST:event_btnCATCHBurgerActionPerformed

    private void btnOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOriginalActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[18][0], menu[18][1]};
        model.addRow(row);
    }//GEN-LAST:event_btnOriginalActionPerformed

    private void btnHotWingsNakedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHotWingsNakedActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[19][0], menu[19][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnHotWingsNakedActionPerformed

    private void btnLemonPepperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLemonPepperActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[20][0], menu[20][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnLemonPepperActionPerformed

    private void btnTeriyakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTeriyakiActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[21][0], menu[21][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnTeriyakiActionPerformed

    private void btnHoneyBBQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoneyBBQActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[22][0], menu[22][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnHoneyBBQActionPerformed

    private void btnCATCHWingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCATCHWingsActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[23][0], menu[23][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnCATCHWingsActionPerformed

    private void btnCaesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaesarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[24][0], menu[24][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnCaesarActionPerformed

    private void btnAsianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsianActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[25][0], menu[25][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnAsianActionPerformed

    private void btnGrilledSalmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrilledSalmonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[26][0], menu[26][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnGrilledSalmonActionPerformed

    private void btnChickenAvocadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChickenAvocadoActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[27][0], menu[27][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnChickenAvocadoActionPerformed

    private void btnChipotleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChipotleActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[28][0], menu[28][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnChipotleActionPerformed

    private void btnRefreshingCATCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshingCATCHActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[29][0], menu[29][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnRefreshingCATCHActionPerformed

    private void btnFriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFriesActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[30][0], menu[30][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnFriesActionPerformed

    private void btnColeslawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColeslawActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[31][0], menu[31][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnColeslawActionPerformed

    private void btnMashedPotatoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMashedPotatoesActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[32][0], menu[32][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnMashedPotatoesActionPerformed

    private void btnCornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCornActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[33][0], menu[33][1]};
        model.addRow(row);
    }//GEN-LAST:event_btnCornActionPerformed

    private void btnQuesoDipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuesoDipActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[34][0], menu[34][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnQuesoDipActionPerformed

    private void btnPotatoWedgesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPotatoWedgesActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[35][0], menu[35][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnPotatoWedgesActionPerformed

    private void btnChocolateMousseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChocolateMousseActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[36][0], menu[36][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnChocolateMousseActionPerformed

    private void btnRedVelvetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedVelvetActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[37][0], menu[37][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnRedVelvetActionPerformed

    private void btnCannoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCannoliActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[38][0], menu[38][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnCannoliActionPerformed

    private void btnTiramisuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiramisuActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[39][0], menu[39][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnTiramisuActionPerformed

    private void btnMilkshakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMilkshakeActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[40][0], menu[40][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnMilkshakeActionPerformed

    private void btnCATCHIceCreamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCATCHIceCreamActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        // Item and price
        Object[] row = {menu[41][0], menu[41][1]};
        model.addRow(row);
        runningTotal();
    }//GEN-LAST:event_btnCATCHIceCreamActionPerformed

    private void btnClearTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTableActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        
        model.setRowCount(0);
        lblSubtotalAmount.setText("0.00");
        lblTaxAmount.setText("0.00");
        lblTotalAmount.setText("0.00");

    }//GEN-LAST:event_btnClearTableActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        model.removeRow(tblOrder.getSelectedRow());
        runningTotal();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:

        txtKeypad.setText(keypadTransaction.append(btn1.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn1.getText()).toString());

    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn2.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn2.getText()).toString());
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn3.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn3.getText()).toString());
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn4.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn4.getText()).toString());
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn5.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn5.getText()).toString());
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn6.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn6.getText()).toString());
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn7.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn7.getText()).toString());
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn8.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn8.getText()).toString());
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btn9.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn9.getText()).toString());
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        // TODO add your handling code here:

        txtKeypad.setText(keypadTransaction.append(btn0.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btn0.getText()).toString());
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(null);
        keypadTransaction.delete(0, keypadTransaction.length());
        txtEmpId.setText(null);
        keypadLogin.delete(0, keypadLogin.length());
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnPrintCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintCheckActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        
        JOptionPane.showMessageDialog(null, "Bill Printed!");
        
        for (int i = 0; i < model.getDataVector().size(); i++) {
            
            orderLst.add(new Order(model.getValueAt(i, 0).toString(),
                    BigDecimal.valueOf(Double.parseDouble(model.getValueAt(i, 1).toString()))));
            
        }
        for (int i = 0; i < orderLst.size(); i++) {
            Order order = orderLst.get(i);
            dbconn.salesLog(orderLst.get(i).getItemName(), Double.parseDouble(orderLst.get(i).getItemPrice().toString()), Integer.parseInt(lblOrderNumberObtained.getText()));
            System.out.println(orderLst.get(i).getItemName() + " " + Double.parseDouble(orderLst.get(i).getItemPrice().toString()));
        }
        if (model.getRowCount() != 0) {
            receipt.setOrderList(orderLst);
            // txtAmount.setText(receipt.getReceipt());
            //@params: Subtotal, tax, total, PaymentMethod,OrderNumber, tablenumber, servername 
//                dbconn.saveRevenue(Double.parseDouble(receipt.getSubtotal().toString()),
//                        Double.parseDouble(receipt.getTaxAmount().toString()),
//                        Double.parseDouble(receipt.getTotal().toString()),
//                        receipt.getPaymentMethod(),
//                        receipt.getOrderNumber(),
//                        Integer.parseInt(lblTableNum.getText()),
//                        lblWaiter.getText());
            dbconn.updateRevenue(Double.parseDouble(lblSubtotalAmount.getText()),
                    Double.parseDouble(lblTaxAmount.getText()),
                    Double.parseDouble(lblTotalAmount.getText()),
                    Integer.parseInt(lblOrderNumberObtained.getText()));
        }

        //end of if for new orders
        //DELeted the previous print check at 11:50 PM 4/19/15
        //cbOpenOrder.addItem(receipt.getOrderNumber());
        //cbOpenOrder.setVisible(true);
    }//GEN-LAST:event_btnPrintCheckActionPerformed

    private void btnCreditDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditDebitActionPerformed
        txtKeypad.setVisible(false);
        dbconn.updatePaymentMethodtoDB("Credit/Debit", Double.parseDouble(lblTotalAmount.getText()), Integer.parseInt(lblOrderNumberObtained.getText()));
        
        JOptionPane.showMessageDialog(null, "Tendered: $" + lblTotalAmount.getText());
        
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        
        model.setRowCount(0);
        lblSubtotalAmount.setText("0.00");
        lblTaxAmount.setText("0.00");
        lblTotalAmount.setText("0.00");
        PanelPaymentMethod.setVisible(false);
        
        viewTables();
        

    }//GEN-LAST:event_btnCreditDebitActionPerformed

    private void btnGiftCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiftCardActionPerformed
        // TODO add your handling code here:
        PanelPaymentMethod.setVisible(false);
        btnConfirm.setVisible(true);
        txtKeypad.setVisible(true);
        txtKeypad.setEnabled(true);
//        int cardNum = Integer.parseInt(txtKeypad.getText());
//
//        //dbconn.updatePaymentMethodtoDB("Gift Card", Double.parseDouble(lblTotalAmount.getText()), Integer.parseInt(lblOrderNumberObtained.toString()));
//        double giftCardOrigAmount = dbconn.getGiftCardBalance(cardNum);
//        double total = Double.parseDouble(lblTotalAmount.getText());
//        double giftCardBalance = giftCardOrigAmount - Double.parseDouble(lblTotalAmount.getText());
//        if (total < giftCardOrigAmount) {
//            //
//
//            giftCardBalance = giftCardOrigAmount - total;
//            dbconn.updategiftCardBalance(giftCardBalance);
//            JOptionPane.showMessageDialog(null, "Gift Card Amount: $ " + giftCardOrigAmount + "\nPaid: $" + lblTotalAmount + "\nRemaining Balance in GiftCard: $" + giftCardBalance);
//             dbconn.updatePaymentMethodtoDB("Gift Card", total, Integer.parseInt(lblOrderNumberObtained.getText()));
//        } else if (total > giftCardOrigAmount) {
//            double amountdue = total - giftCardOrigAmount;
//            dbconn.updategiftCardBalance(0);
//            JOptionPane.showMessageDialog(null, "Gift Card Amount: $ " + giftCardOrigAmount + "\nAmount due $" + amountdue + ".");
//            lblTotalAmount.setText(amountdue + "");
//             dbconn.updatePaymentMethodtoDB("Gift Card", total, Integer.parseInt(lblOrderNumberObtained.getText()));
//            btnPay.setVisible(true);
////
//        } else {
//            //
//            dbconn.updategiftCardBalance(0);
//            dbconn.updatePaymentMethodtoDB("Gift Card", total, Integer.parseInt(lblOrderNumberObtained.getText()));
//
//        }
////not good
////        if (giftCardBalance < 0) {
////
////            JOptionPane.showMessageDialog(null, "Not enough amount in the card.\nTendered: $" + giftCardOrigAmount + ""
////                    + "\nAmount due: $" + Math.abs(giftCardBalance));
////            giftCardBalance = 0.00;
////            dbconn.updategiftCardBalance(giftCardBalance);
////        } else {
////            dbconn.updategiftCardBalance(giftCardBalance);
////            JOptionPane.showMessageDialog(null, "Gift Card Amount: $ " + giftCardOrigAmount + "\nPaid: $" + lblTotalAmount + "\nRemaining Balance in GiftCard: $" + giftCardBalance);
////            lblCashPaidAmount.setText("" + giftCardOrigAmount);
////        }
////        btnPay.setVisible(true);
////        paymentType.append(transaction.payByGiftCard(BigDecimal.valueOf(Double.parseDouble(txtKeypad.getText())), receipt.getTotal()));
//
//        keypadTransaction.delete(0, keypadTransaction.length());
//
//        //after lunch
//        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
//
//        model.setRowCount(0);
//        lblSubtotalAmount.setText("0.00");
//        lblTaxAmount.setText("0.00");
//        lblTotalAmount.setText("0.00");


    }//GEN-LAST:event_btnGiftCardActionPerformed

    private void btnCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashActionPerformed
        PanelPaymentMethod.setVisible(false);
        btnPay.setVisible(true);
        
        txtKeypad.setVisible(true);
        txtKeypad.setEnabled(true);

        //dbconn.updatePaymentMethodtoDB("Cash", Integer.parseInt(lblOrderNumberObtained.toString()));
        lblCashPaidAmount.setVisible(true);
        lblChangeAmount.setVisible(true);
        double change = transaction.getChange(txtKeypad.getText(), lblTotalAmount.getText());
        double cashPaid = Double.parseDouble(txtKeypad.getText());
        lblChangeAmount.setText("" + change);
        lblCashPaidAmount.setText("" + cashPaid);
        paymentType.append(transaction.payByCash(BigDecimal.valueOf(Double.parseDouble(txtKeypad.getText())), receipt.getTotal()));
        txtKeypad.setText(null);
        keypadTransaction.delete(0, keypadTransaction.length());

    }//GEN-LAST:event_btnCashActionPerformed

    private void btnDecimalPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecimalPointActionPerformed
        // TODO add your handling code here:
        txtKeypad.setText(keypadTransaction.append(btnDecimalPoint.getText()).toString());
        txtEmpId.setText(keypadLogin.append(btnDecimalPoint.getText()).toString());
    }//GEN-LAST:event_btnDecimalPointActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:

        if (isNewOrder) {
            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            
            JOptionPane.showMessageDialog(null, "Order number " + lblOrderNumberObtained.getText() + " sent!");
            
            for (int i = 0; i < model.getDataVector().size(); i++) {
                
                orderLst.add(new Order(model.getValueAt(i, 0).toString(),
                        BigDecimal.valueOf(Double.parseDouble(model.getValueAt(i, 1).toString()))));
                
            }
            for (int i = 0; i < orderLst.size(); i++) {
                Order order = orderLst.get(i);
                dbconn.salesLog(orderLst.get(i).getItemName(), Double.parseDouble(orderLst.get(i).getItemPrice().toString()), Integer.parseInt(lblOrderNumberObtained.getText()));
                System.out.println(orderLst.get(i).getItemName() + " " + Double.parseDouble(orderLst.get(i).getItemPrice().toString()));
            }
            if (model.getRowCount() != 0) {
                receipt.setOrderList(orderLst);
                
                dbconn.saveRevenue(Double.parseDouble(receipt.getSubtotal().toString()),
                        Double.parseDouble(receipt.getTaxAmount().toString()),
                        Double.parseDouble(receipt.getTotal().toString()),
                        receipt.getPaymentMethod(),
                        receipt.getOrderNumber(),
                        Integer.parseInt(lblTableNum.getText()),
                        lblWaiter.getText());
                
            }

            //end of if for new orders
            model = (DefaultTableModel) tblOrder.getModel();
            
            model.setRowCount(0);
            lblSubtotalAmount.setText("0.00");
            lblTaxAmount.setText("0.00");
            lblTotalAmount.setText("0.00");
            lblOrderNumberObtained.setText(dbconn.getOrderNumberFromDB() + "");
        } else {
            //this is an open order

            dbconn.updateRevenue(Double.parseDouble(lblSubtotalAmount.getText()),
                    Double.parseDouble(lblTaxAmount.getText()),
                    Double.parseDouble(lblTotalAmount.getText()),
                    Integer.parseInt(lblOrderNumberObtained.getText()));
            
            dbconn.deleteFromSalesLog(Integer.parseInt(lblOrderNumberObtained.getText()));
            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            
            JOptionPane.showMessageDialog(null, "Order number " + lblOrderNumberObtained.getText() + " updated sent!");
            
            for (int i = 0; i < model.getDataVector().size(); i++) {
                
                orderLst.add(new Order(model.getValueAt(i, 0).toString(),
                        BigDecimal.valueOf(Double.parseDouble(model.getValueAt(i, 1).toString()))));
                
            }
            for (int i = 0; i < orderLst.size(); i++) {
                Order order = orderLst.get(i);
                dbconn.salesLog(orderLst.get(i).getItemName(), Double.parseDouble(orderLst.get(i).getItemPrice().toString()), Integer.parseInt(lblOrderNumberObtained.getText()));
                System.out.println(orderLst.get(i).getItemName() + " " + Double.parseDouble(orderLst.get(i).getItemPrice().toString()));
            }
            if (model.getRowCount() != 0) {
                receipt.setOrderList(orderLst);
                // txtAmount.setText(receipt.getReceipt());
//                dbconn.saveRevenue(Double.parseDouble(receipt.getSubtotal().toString()),
//                        Double.parseDouble(receipt.getTaxAmount().toString()),
//                        Double.parseDouble(receipt.getTotal().toString()),
//                        receipt.getPaymentMethod(),
//                        receipt.getOrderNumber(),
//                        Integer.parseInt(lblTableNum.getText()),
//                        lblWaiter.getText());
                dbconn.updateRevenue(Double.parseDouble(lblSubtotalAmount.getText()),
                        Double.parseDouble(lblTaxAmount.getText()),
                        Double.parseDouble(lblTotalAmount.getText()),
                        Integer.parseInt(lblOrderNumberObtained.getText()));
            }
            
        }
        isNewOrder = true;
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        
        model.setRowCount(0);
        lblSubtotalAmount.setText("0.00");
        lblTaxAmount.setText("0.00");
        lblTotalAmount.setText("0.00");
        

    }//GEN-LAST:event_btnSendActionPerformed

    private void btnTable4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable4ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("4");
    }//GEN-LAST:event_btnTable4ActionPerformed

    private void btnTable5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable5ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("5");
    }//GEN-LAST:event_btnTable5ActionPerformed

    private void btnTable6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable6ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("6");
    }//GEN-LAST:event_btnTable6ActionPerformed

    private void btnTable3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable3ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("3");
    }//GEN-LAST:event_btnTable3ActionPerformed

    private void btnTable2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable2ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        
        viewPOS();
        lblTableNum.setText("2");
    }//GEN-LAST:event_btnTable2ActionPerformed

    private void btnTable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable1ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("1");
    }//GEN-LAST:event_btnTable1ActionPerformed

    private void btnTable10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable10ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("10");
    }//GEN-LAST:event_btnTable10ActionPerformed

    private void btnTable11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable11ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("11");
    }//GEN-LAST:event_btnTable11ActionPerformed

    private void btnTable12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable12ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("12");
    }//GEN-LAST:event_btnTable12ActionPerformed

    private void btnTable9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable9ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("9");
    }//GEN-LAST:event_btnTable9ActionPerformed

    private void btnTable8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable8ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("8");
    }//GEN-LAST:event_btnTable8ActionPerformed

    private void btnTable7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable7ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("7");
    }//GEN-LAST:event_btnTable7ActionPerformed

    private void btnTable16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable16ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("16");
    }//GEN-LAST:event_btnTable16ActionPerformed

    private void btnTable17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable17ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        lblTableNum.setText("17");
    }//GEN-LAST:event_btnTable17ActionPerformed

    private void btnTable18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable18ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("18");
    }//GEN-LAST:event_btnTable18ActionPerformed

    private void btnTable15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable15ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("15");
    }//GEN-LAST:event_btnTable15ActionPerformed

    private void btnTable14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable14ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("14");
    }//GEN-LAST:event_btnTable14ActionPerformed

    private void btnTable13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTable13ActionPerformed
        // TODO add your handling code here:
        getNumberOfGuests();
        viewPOS();
        lblTableNum.setText("13");
    }//GEN-LAST:event_btnTable13ActionPerformed

    private void btnViewOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOrdersActionPerformed

// TODO add your handling code here:
        tblOpenOrders.setColumnSelectionAllowed(false);
        
        tblOpenOrders.setRowSelectionAllowed(isShown);
        tblOpenOrders.setSelectionForeground(Color.lightGray);
        tblOpenOrders.setSelectionBackground(Color.blue);
        tblOpenOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        PanelCardView.removeAll();
        PanelCardView.add(ViewOrders);
        PanelCardView.repaint();
        PanelCardView.revalidate();
        conn = Database.ConnecttoDB();
        updateOpenOrderTable();
        updatePaidOrderTable();
        

    }//GEN-LAST:event_btnViewOrdersActionPerformed

    private void btnViewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOrderActionPerformed
        btnPay.setVisible(true);
        PanelPaymentMethod.setVisible(false);
        isNewOrder = false;
        PanelCardView.removeAll();
        PanelCardView.add(ViewPOS);
        PanelCardView.repaint();
        PanelCardView.revalidate();
        btnSend.setEnabled(true);
        btnPrintCheck.setEnabled(true);
        
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        model.setRowCount(0);
        int selectedData = 0;
        int[] selectedRow = tblOpenOrders.getSelectedRows();
        int[] selectedColumn = tblOpenOrders.getSelectedColumns();
        for (int i = 0; i < selectedRow.length; i++) {
            for (int j = 0; j < selectedColumn.length; j++) {
                selectedData = Integer.parseInt(tblOpenOrders.getValueAt(selectedRow[i], selectedColumn[j]).toString());
            }
        }
        
        Object[] savedItemName = dbconn.retrieveItemNameFromSalesLog(selectedData);
        Object[] savedItemPrice = dbconn.retrieveItemPriceFromSalesLog(selectedData);

        // Item and price
        for (int i = 0; i < savedItemPrice.length; i++) {
            
            Object[] row = {savedItemName[i], savedItemPrice[i]};
            model.addRow(row);
            
            runningTotal();
        }
        lblWaiter.setText("" + dbconn.getWaiterNameFromDB(selectedData));
        lblTableNum.setText("" + dbconn.getSavedTableNumberFromDB(selectedData));
        lblOrderNumberObtained.setText("" + dbconn.getSavedOrderNumberFromDB(selectedData));
        btnCash.setEnabled(true);
        btnCreditDebit.setEnabled(true);
        btnGiftCard.setEnabled(true);
    }//GEN-LAST:event_btnViewOrderActionPerformed

    private void btnDeleteOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteOrderActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        model.removeRow(tblOrder.getSelectedRow());
        runningTotal();
    }//GEN-LAST:event_btnDeleteOrderActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        PanelTransaction.setVisible(false);
        PanelCardView.setVisible(false);
        Panel_Navigation.setVisible(false);        
        keypadLogin.delete(0, keypadLogin.length());
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPayCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayCashActionPerformed
        // TODO add your handling code here:

        PanelPaymentMethod.setVisible(true);
        btnPay.setVisible(false);
       
        
        if (txtKeypad.getText() != null && !"".equals(txtKeypad.getText())) {
            if (Double.parseDouble(txtKeypad.getText()) == Double.parseDouble(lblTotalAmount.getText())) {
                double change = dbconn.getChange(txtKeypad.getText(), lblTotalAmount.getText());
                
                dbconn.updatePaymentMethodtoDB("Cash", Double.parseDouble(txtKeypad.getText()), Integer.parseInt(lblOrderNumberObtained.getText()));
                
                JOptionPane.showMessageDialog(null, "Tendered: $" + txtKeypad.getText() + "\n"
                        + "Change: $" + change + "");
                txtKeypad.setText(null);
                DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
                
                model.setRowCount(0);
                lblSubtotalAmount.setText("0.00");
                lblTaxAmount.setText("0.00");
                lblTotalAmount.setText("0.00");
                viewTables();
            } else {
                if (!amountdue.equals(BigDecimal.ZERO)) {
                  
                   
                    double change = dbconn.getChange(txtKeypad.getText(), lblTotalAmount.getText());
                
                dbconn.updatePaymentMethodtoDB("Cash", Double.parseDouble(txtKeypad.getText()), Integer.parseInt(lblOrderNumberObtained.getText()));
                
                JOptionPane.showMessageDialog(null, "Tendered: $" + txtKeypad.getText() + "\n"
                        + "Change: $" + change + "");
                txtKeypad.setText(null);
                DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
                
                model.setRowCount(0);
                lblSubtotalAmount.setText("0.00");
                lblTaxAmount.setText("0.00");
                lblTotalAmount.setText("0.00");
                viewTables();
                }else{
                 JOptionPane.showMessageDialog(null, "Insuffficient payment. \nYou entered: $ " + txtKeypad.getText() + "");
                }
               
            }
            
        }

    }//GEN-LAST:event_btnPayCashActionPerformed

    private void txtKeypadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeypadActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtKeypadActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        int cardNum = Integer.parseInt(txtKeypad.getText());
        //dbconn.updatePaymentMethodtoDB("Gift Card", Double.parseDouble(lblTotalAmount.getText()), Integer.parseInt(lblOrderNumberObtained.toString()));
        double giftCardOrigAmount = dbconn.getGiftCardBalance(cardNum);
        double total = Double.parseDouble(lblTotalAmount.getText());
        // double giftCardBalance = giftCardOrigAmount - Double.parseDouble(lblTotalAmount.getText());
        if (total < giftCardOrigAmount) {
            //

            double giftCardBalance = giftCardOrigAmount - total;
            dbconn.updategiftCardBalance(giftCardBalance, cardNum);
            JOptionPane.showMessageDialog(null, "Gift Card Amount: $ " + giftCardOrigAmount + "\nPaid: $" + lblTotalAmount.getText() + "\nRemaining Balance in GiftCard: $" + giftCardBalance);
            dbconn.updatePaymentMethodtoDB("Gift Card", total, Integer.parseInt(lblOrderNumberObtained.getText()));
            txtKeypad.setText(null);
            txtKeypad.setVisible(false);
            btnConfirm.setVisible(false);
            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            
            model.setRowCount(0);
            lblSubtotalAmount.setText("0.00");
            lblTaxAmount.setText("0.00");
            lblTotalAmount.setText("0.00");
            viewTables();
        } else if (total > giftCardOrigAmount) {
             amountdue = BigDecimal.valueOf(total).subtract(BigDecimal.valueOf(giftCardOrigAmount)).setScale(2, RoundingMode.FLOOR).stripTrailingZeros();
            dbconn.updategiftCardBalance(0, cardNum);
            JOptionPane.showMessageDialog(null, "Gift Card Amount: $ " + giftCardOrigAmount + "\nAmount due $" + amountdue + ".");
            lblTotalAmount.setText(amountdue + "");
            //  dbconn.updatePaymentMethodtoDB("Gift Card", total, Integer.parseInt(lblOrderNumberObtained.getText()));
            btnConfirm.setVisible(false);
            txtKeypad.setText(null);
            btnPay.setVisible(true);
//
        } else {
            //
            dbconn.updategiftCardBalance(0, cardNum);
            dbconn.updatePaymentMethodtoDB("Gift Card", total, Integer.parseInt(lblOrderNumberObtained.getText()));
            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            
            model.setRowCount(0);
            lblSubtotalAmount.setText("0.00");
            lblTaxAmount.setText("0.00");
            lblTotalAmount.setText("0.00");
        }
//not good
//        if (giftCardBalance < 0) {
//
//            JOptionPane.showMessageDialog(null, "Not enough amount in the card.\nTendered: $" + giftCardOrigAmount + ""
//                    + "\nAmount due: $" + Math.abs(giftCardBalance));
//            giftCardBalance = 0.00;
//            dbconn.updategiftCardBalance(giftCardBalance);
//        } else {
//            dbconn.updategiftCardBalance(giftCardBalance);
//            JOptionPane.showMessageDialog(null, "Gift Card Amount: $ " + giftCardOrigAmount + "\nPaid: $" + lblTotalAmount + "\nRemaining Balance in GiftCard: $" + giftCardBalance);
//            lblCashPaidAmount.setText("" + giftCardOrigAmount);
//        }
//        btnPay.setVisible(true);
//        paymentType.append(transaction.payByGiftCard(BigDecimal.valueOf(Double.parseDouble(txtKeypad.getText())), receipt.getTotal()));

        keypadTransaction.delete(0, keypadTransaction.length());

        //after lunch

    }//GEN-LAST:event_btnConfirmActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackOffice_CostOfSales;
    private javax.swing.JPanel BackOffice_Employees;
    private javax.swing.JPanel BackOffice_Inventory;
    private javax.swing.JPanel BackOffice_Reports;
    private javax.swing.JPanel CardAppetizers;
    private javax.swing.JPanel CardBurgers;
    private javax.swing.JPanel CardClockInClockOut;
    private javax.swing.JPanel CardDesserts;
    private javax.swing.JPanel CardDrinks;
    private javax.swing.JPanel CardExtras;
    private javax.swing.JPanel CardLogin;
    private javax.swing.JPanel CardSalads;
    private javax.swing.JPanel CardWings;
    private javax.swing.JPanel LetsGo_WIP;
    private javax.swing.JTabbedPane Manager_BackOffice;
    private javax.swing.JPanel PanelAppetizers;
    private javax.swing.JPanel PanelBackground;
    private javax.swing.JPanel PanelBurgers;
    private javax.swing.JPanel PanelCardView;
    private javax.swing.JPanel PanelCategories;
    private javax.swing.JPanel PanelDesserts;
    private javax.swing.JPanel PanelDrinks;
    private javax.swing.JPanel PanelExtras;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelHolderForClocking;
    private javax.swing.JPanel PanelKeypad;
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JPanel PanelOrder;
    private javax.swing.JPanel PanelOrders;
    private javax.swing.JPanel PanelPaymentMethod;
    private javax.swing.JPanel PanelSalads;
    private javax.swing.JPanel PanelTextEntry;
    private javax.swing.JPanel PanelTransaction;
    private javax.swing.JPanel PanelWings;
    private javax.swing.JPanel Panel_Navigation;
    private javax.swing.JPanel Panel_OrderLabel;
    private javax.swing.JPanel ViewManager;
    private javax.swing.JPanel ViewOrders;
    private javax.swing.JPanel ViewPOS;
    private javax.swing.JPanel ViewTables;
    private javax.swing.JPanel ViewTimeCard;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAppetizers;
    private javax.swing.JButton btnAsian;
    private javax.swing.JButton btnBaconCheddar;
    private javax.swing.JButton btnBurgers;
    private javax.swing.JButton btnCATCHBurger;
    private javax.swing.JButton btnCATCHIceCream;
    private javax.swing.JButton btnCATCHWings;
    private javax.swing.JButton btnCaesar;
    private javax.swing.JButton btnCalamari;
    private javax.swing.JButton btnCannoli;
    private javax.swing.JButton btnCash;
    private javax.swing.JButton btnChickenAvocado;
    private javax.swing.JButton btnChipotle;
    private javax.swing.JButton btnChipsSalsa;
    private javax.swing.JButton btnChocolateMousse;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearTable;
    private javax.swing.JButton btnClockInClockOut;
    private javax.swing.JButton btnCoffee;
    private javax.swing.JButton btnCoke;
    private javax.swing.JButton btnColeslaw;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnCorn;
    private javax.swing.JButton btnCreditDebit;
    private javax.swing.JButton btnDecimalPoint;
    private javax.swing.JButton btnDeleteOrder;
    private javax.swing.JButton btnDesserts;
    private javax.swing.JButton btnDietCoke;
    private javax.swing.JButton btnDrinks;
    private javax.swing.JButton btnExtras;
    private javax.swing.JButton btnFourWay;
    private javax.swing.JButton btnFries;
    private javax.swing.JButton btnGiftCard;
    private javax.swing.JButton btnGrilledSalmon;
    private javax.swing.JButton btnHoneyBBQ;
    private javax.swing.JButton btnHotWingsNaked;
    private javax.swing.JButton btnLemonPepper;
    private javax.swing.JButton btnLemonade;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManager;
    private javax.swing.JButton btnMashedPotatoes;
    private javax.swing.JButton btnMilkshake;
    private javax.swing.JButton btnMozarellaSticks;
    private javax.swing.JButton btnOriginal;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnPotatoWedges;
    private javax.swing.JButton btnPrintCheck;
    private javax.swing.JButton btnQuesoDip;
    private javax.swing.JButton btnRedVelvet;
    private javax.swing.JButton btnRefreshingCATCH;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSalads;
    private javax.swing.JButton btnSantaFeChicQue;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSouthwest;
    private javax.swing.JButton btnSpinachArtiDip;
    private javax.swing.JButton btnSprite;
    private javax.swing.JButton btnSweetTea;
    private javax.swing.JButton btnSwissMelt;
    private javax.swing.JButton btnTable1;
    private javax.swing.JButton btnTable10;
    private javax.swing.JButton btnTable11;
    private javax.swing.JButton btnTable12;
    private javax.swing.JButton btnTable13;
    private javax.swing.JButton btnTable14;
    private javax.swing.JButton btnTable15;
    private javax.swing.JButton btnTable16;
    private javax.swing.JButton btnTable17;
    private javax.swing.JButton btnTable18;
    private javax.swing.JButton btnTable2;
    private javax.swing.JButton btnTable3;
    private javax.swing.JButton btnTable4;
    private javax.swing.JButton btnTable5;
    private javax.swing.JButton btnTable6;
    private javax.swing.JButton btnTable7;
    private javax.swing.JButton btnTable8;
    private javax.swing.JButton btnTable9;
    private javax.swing.JButton btnTables;
    private javax.swing.JButton btnTeriyaki;
    private javax.swing.JButton btnTheWorks;
    private javax.swing.JButton btnThreeCheese;
    private javax.swing.JButton btnTimeCard;
    private javax.swing.JButton btnTiramisu;
    private javax.swing.JButton btnViewOrder;
    private javax.swing.JButton btnViewOrders;
    private javax.swing.JButton btnWings;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAppetizers;
    private javax.swing.JLabel lblBar;
    private javax.swing.JLabel lblBooth;
    private javax.swing.JLabel lblBurgers;
    private javax.swing.JLabel lblCashPaidAmount;
    private javax.swing.JLabel lblChangeAmount;
    private javax.swing.JLabel lblDesserts;
    private javax.swing.JPanel lblDiscountAmount;
    private javax.swing.JLabel lblDrinks;
    private javax.swing.JLabel lblEmpId;
    private javax.swing.JLabel lblEmpName;
    private javax.swing.JLabel lblExtras;
    private javax.swing.JLabel lblLoggedInAs;
    private javax.swing.JLabel lblOrderNumber;
    private javax.swing.JLabel lblOrderNumberObtained;
    private javax.swing.JLabel lblParty;
    private javax.swing.JLabel lblPartyNum;
    private javax.swing.JLabel lblPatio;
    private javax.swing.JLabel lblSalads;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalAmount;
    private javax.swing.JLabel lblSystemClock;
    private javax.swing.JLabel lblSystemDate;
    private javax.swing.JLabel lblTable;
    private javax.swing.JLabel lblTableNum;
    private javax.swing.JLabel lblTax;
    private javax.swing.JLabel lblTaxAmount;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalAmount;
    private javax.swing.JLabel lblWaiter;
    private javax.swing.JLabel lblWaiterHeader;
    private javax.swing.JLabel lblWings;
    private javax.swing.JScrollPane scrollOpenOrders;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTable tblOpenOrders;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblPaidOrders;
    private javax.swing.JTable tblRevenue;
    private javax.swing.JTable tblSales;
    private javax.swing.JTextField txtEmpId;
    private javax.swing.JTextField txtKeypad;
    // End of variables declaration//GEN-END:variables
}
