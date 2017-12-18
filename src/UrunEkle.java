
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Barış Cömert Bu class main methodumuzun olduğu, ve tüm görsel öğeleri içeren Class.
 */
public class UrunEkle extends javax.swing.JFrame {

    Database database = new Database(); //database objesi yaratma

    /**
     * Creates new form UrunEkle
     */
    public UrunEkle() {
        //drive etmek için main objemizi yaratıyoruz
        initComponents();
        tvHdmi0.setSelected(true); //Başta HDMI 0 olarak default olarak seçilmiş durumda.
        new ChatAI(txtEnter, txtChat, database); //Yeni ChatAI objesi yaratıyor.

        //EKLEME TUŞLARININ LİSTENERLARI
        bilEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BilgisayarEkle();
            }
        });
        telEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelefonEkle();
            }
        });
        tvEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TvEkle();
            }
        });
        camEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CamasirEkle();
            }
        });
        buzEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuzdolabiEkle();
            }
        });
        //EKLEME LİSTENERLARİ SONU

    }

    public static void Error(String error) {//Error Mesajı dialog halinde gösterme
        JOptionPane.showMessageDialog(new JFrame(), error, "HATA", JOptionPane.ERROR_MESSAGE);//Eğer boş uer bırakırsa hata mesajı
    }

    public void Success(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "BAŞARILI", JOptionPane.INFORMATION_MESSAGE);//başarılı olduğuna dair mesajı
    }

    public static boolean isDouble(String str) {//Bir input String double mı? kontrol.
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int setHDMI() {//Hdmi sayısı döndür

        if (tvHdmi0.isSelected()) {
            return 0;
        } else if (tvHdmi1.isSelected()) {
            return 1;
        } else if (tvHdmi2.isSelected()) {
            return 2;
        } else if (tvHdmi3.isSelected()) {
            return 3;
        } else {
            return 4;
        }
    }

    public void BilgisayarEkle() {//Bilgisayar inputlarını alma, kontrol etme, obje oluşturup veri tabanına gönderme.
        String marka = bilTelMarka.getText();
        String model = bilTelModel.getText();
        String fiyatstr = bilTelFiyat.getText();
        String isletims = bilTelIsletim.getText();
        String ramstr = bilTelRam.getText();
        String ekrank = bilEkranKarti.getText();
        String islemci = bilTelIslemci.getText();

        boolean ssd = bilSSD.isSelected();

        //boş yer var mı kontrol.
        if (marka == null || marka.equals("") || model == null || model.equals("") || fiyatstr == null || fiyatstr.equals("") || isletims == null || isletims.equals("") || ramstr == null || ramstr.equals("") || ekrank == null || ekrank.equals("")) {
            Error("Lütfen boş yer bırakma");
            return;
        }
        if (!isDouble(fiyatstr) || !isDouble(ramstr)) {
            Error("Lütfen Fiyatı ve Rami sayı olarak giriniz");
        }
        double fiyat = Double.parseDouble(fiyatstr);
        double ram = Double.parseDouble(ramstr);

        Bilgisayar b = new Bilgisayar(ekrank, ssd, isletims, islemci, ram, marka, model, fiyat);
        database.AddBilgisayar(b);

        Success("PC Başarıyla eklendi");
        new TweetSearch((Urun) b);

    }

    public void TelefonEkle() {//Telefon inputlarını alma, kontrol etme, obje oluşturup veri tabanına gönderme.
        String marka = bilTelMarka.getText();
        String model = bilTelModel.getText();
        String fiyatstr = bilTelFiyat.getText();
        String isletims = bilTelIsletim.getText();
        String ramstr = bilTelRam.getText();
        String kamera = telKamera.getText();
        String islemci = bilTelIslemci.getText();
        String ekran = telEkran.getText();

        boolean ssd = bilSSD.isSelected();

        //boş yer var mı kontrol.
        if (marka.isEmpty() || model.isEmpty() || fiyatstr.isEmpty() || isletims.isEmpty() || ramstr.isEmpty() || ekran.isEmpty() || kamera.isEmpty() || islemci.isEmpty()) {
            Error("Lütfen boş yer bırakma");
            return;
        }
        if (!isDouble(fiyatstr) || !isDouble(ramstr)) {
            Error("Lütfen Fiyatı ve Rami sayı olarak giriniz");
        }
        double fiyat = Double.parseDouble(fiyatstr);
        double ram = Double.parseDouble(ramstr);

        Telefon t = new Telefon(ekran, kamera, isletims, islemci, ram, marka, model, fiyat);
        database.AddTelefon(t);

        Success("Telefon Başarıyla eklendi");
    }

    public void TvEkle() {//Tv inputlarını alma, kontrol etme, obje oluşturup veri tabanına gönderme.
        String marka = tvMarka.getText();
        String model = tvModel.getText();
        String fiyatstr = tvFiyat.getText();
        String ekran = tvEkran.getText();
        int hdmi = setHDMI();
        boolean isAkilli = tvAkilli.isSelected();

        //boş yer var mı kontrol.
        if (marka.isEmpty() || model.isEmpty() || fiyatstr.isEmpty() || ekran.isEmpty()) {
            Error("Lütfen boş yer bırakma");
            return;
        }
        if (!isDouble(fiyatstr)) {
            Error("Lütfen Fiyatı sayı olarak giriniz");
        }
        double fiyat = Double.parseDouble(fiyatstr);
        Televizyon t = new Televizyon(ekran, hdmi, isAkilli, marka, model, fiyat);
        database.AddTelevizyon(t);

        Success("TV Başarıyla eklendi");

    }

    public void BuzdolabiEkle() {//Buzdolabı inputlarını alma, kontrol etme, obje oluşturup veri tabanına gönderme.
        String marka = cbMarka.getText();
        String model = cbModel.getText();
        String fiyatstr = cbFiyat.getText();
        String renk = cbRenk.getText();
        String enerji = cbEnerji.getText();
        String hacim = cbHacim.getText();
        String yukseklikstr = buzYukseklik.getText();
        boolean tekkapi = buzTekkapi.getState();

        //boş yer var mı kontrol.
        if (marka.isEmpty() || model.isEmpty() || fiyatstr.isEmpty() || renk.isEmpty() || enerji.isEmpty() || hacim.isEmpty() || yukseklikstr.isEmpty()) {
            Error("Lütfen boş yer bırakma");
            return;
        }
        if (!isDouble(fiyatstr) || !isDouble(yukseklikstr)) {
            Error("Lütfen Fiyatı ve Yüksekliği sayı olarak giriniz");
        }
        double fiyat = Double.parseDouble(fiyatstr);
        double yukseklik = Double.parseDouble(yukseklikstr);

        Buzdolabi b = new Buzdolabi(yukseklik, tekkapi, renk, enerji, fiyat, marka, model, fiyat);
        database.AddBuzdolabi(b);

        Success("Buzdolabı Başarıyla eklendi");

    }

    public void CamasirEkle() {//Çamaşır Makinesi inputlarını alma, kontrol etme, obje oluşturup veri tabanına gönderme.
        String marka = cbMarka.getText();
        String model = cbModel.getText();
        String fiyatstr = cbFiyat.getText();
        String renk = cbRenk.getText();
        String enerji = cbEnerji.getText();
        String hacim = cbHacim.getText();
        String devirstr = camDevir.getText();
        boolean akilli = camAkilli.getState();

        //boş yer var mı kontrol.
        if (marka.isEmpty() || model.isEmpty() || fiyatstr.isEmpty() || renk.isEmpty() || enerji.isEmpty() || hacim.isEmpty() || devirstr.isEmpty()) {
            Error("Lütfen boş yer bırakma");
            return;
        }
        if (!isDouble(fiyatstr) || !isDouble(devirstr)) {
            Error("Lütfen Fiyatı ve Devri sayı olarak giriniz");
        }
        double fiyat = Double.parseDouble(fiyatstr);
        double devir = Double.parseDouble(devirstr);

        CamasirMakinesi cm = new CamasirMakinesi(fiyat, akilli, renk, enerji, fiyat, marka, model, devir);
        database.AddCamasir(cm);

        Success("Çamaşır Makinesi Başarıyla eklendi");

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tvBtnGrup = new javax.swing.ButtonGroup();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        txtEnter = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tvPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tvMarka = new javax.swing.JTextField();
        tvModel = new javax.swing.JTextField();
        tvFiyat = new javax.swing.JTextField();
        tvEkran = new javax.swing.JTextField();
        tvAkilli = new javax.swing.JCheckBox();
        tvHdmi1 = new javax.swing.JRadioButton();
        tvHdmi2 = new javax.swing.JRadioButton();
        tvHdmi3 = new javax.swing.JRadioButton();
        tvHdmi4 = new javax.swing.JRadioButton();
        tvHdmi0 = new javax.swing.JRadioButton();
        tvEkle = new javax.swing.JButton();
        pcTelLabel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bilTelMarka = new javax.swing.JTextField();
        bilTelModel = new javax.swing.JTextField();
        bilTelFiyat = new javax.swing.JTextField();
        bilTelIsletim = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        bilTelRam = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        bilTelIslemci = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bilEkranKarti = new javax.swing.JTextField();
        bilEkle = new java.awt.Button();
        bilSSD = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        telKamera = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        telEkran = new javax.swing.JTextField();
        telEkle = new java.awt.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbMarka = new javax.swing.JTextField();
        cbModel = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbFiyat = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbEnerji = new javax.swing.JTextField();
        cbHacim = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbRenk = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        camDevir = new javax.swing.JTextField();
        camAkilli = new java.awt.Checkbox();
        camEkle = new java.awt.Button();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        buzYukseklik = new javax.swing.JTextField();
        buzTekkapi = new java.awt.Checkbox();
        buzEkle = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtChat.setColumns(20);
        txtChat.setRows(5);
        scrollPane.setViewportView(txtChat);

        txtEnter.setText("Buraya yazın");
        txtEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEnterMouseClicked(evt);
            }
        });
        txtEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addComponent(txtEnter))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Chat", jPanel7);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Marka:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Model: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Fiyat:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Ekran:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Hdmi Giriş Sayısı:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Akıllı TV?:");

        tvMarka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvMarkaActionPerformed(evt);
            }
        });

        tvFiyat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvFiyatActionPerformed(evt);
            }
        });

        tvAkilli.setText("Evet");

        tvBtnGrup.add(tvHdmi1);
        tvHdmi1.setText("1");

        tvBtnGrup.add(tvHdmi2);
        tvHdmi2.setText("2");

        tvBtnGrup.add(tvHdmi3);
        tvHdmi3.setText("3");

        tvBtnGrup.add(tvHdmi4);
        tvHdmi4.setText("4");

        tvBtnGrup.add(tvHdmi0);
        tvHdmi0.setText("0");

        tvEkle.setText("Ekle");
        tvEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tvEkleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tvPanelLayout = new javax.swing.GroupLayout(tvPanel);
        tvPanel.setLayout(tvPanelLayout);
        tvPanelLayout.setHorizontalGroup(
            tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tvPanelLayout.createSequentialGroup()
                .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tvPanelLayout.createSequentialGroup()
                        .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tvPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(tvPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(15, 15, 15)))
                        .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tvFiyat, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(tvMarka))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tvModel)
                            .addComponent(tvEkran)))
                    .addGroup(tvPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tvHdmi0)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tvHdmi1)
                        .addGap(4, 4, 4)
                        .addComponent(tvHdmi2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tvHdmi3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tvHdmi4)
                        .addGap(0, 112, Short.MAX_VALUE))
                    .addGroup(tvPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tvAkilli)
                        .addGap(56, 56, 56)
                        .addComponent(tvEkle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tvPanelLayout.setVerticalGroup(
            tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tvPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tvMarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(tvModel))
                .addGap(18, 18, 18)
                .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tvFiyat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tvEkran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tvHdmi1)
                    .addComponent(tvHdmi2)
                    .addComponent(tvHdmi3)
                    .addComponent(tvHdmi4)
                    .addComponent(tvHdmi0)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(tvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tvAkilli)
                    .addComponent(tvEkle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Televizyon", tvPanel);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Marka:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Model: ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Fiyat:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("İşletim Sistemi:");

        bilTelMarka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilTelMarkaActionPerformed(evt);
            }
        });

        bilTelFiyat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilTelFiyatActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Ram:");

        bilTelRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilTelRamActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("İşlemci:");

        bilTelIslemci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilTelIslemciActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Ekran Kartı:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("SSD Socketi var: ");

        bilEkle.setLabel("Ekle");

        bilSSD.setText("Var");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(bilEkranKarti))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bilSSD)
                        .addGap(20, 20, 20)
                        .addComponent(bilEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(bilEkranKarti, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(bilSSD))
                    .addComponent(bilEkle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bilgisayar", jPanel1);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Kamera boyutu: ");

        telKamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telKameraActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Ekran Çözünürlüğü:");

        telEkle.setLabel("Ekle");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telKamera, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telEkran, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(telEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(telEkle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telKamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(telEkran, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Telefon", jPanel2);

        javax.swing.GroupLayout pcTelLabelLayout = new javax.swing.GroupLayout(pcTelLabel);
        pcTelLabel.setLayout(pcTelLabelLayout);
        pcTelLabelLayout.setHorizontalGroup(
            pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcTelLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(pcTelLabelLayout.createSequentialGroup()
                        .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pcTelLabelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(15, 15, 15)
                                .addComponent(bilTelRam, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pcTelLabelLayout.createSequentialGroup()
                                .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcTelLabelLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(pcTelLabelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(15, 15, 15)))
                                .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(bilTelFiyat)
                                    .addComponent(bilTelMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pcTelLabelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bilTelModel))
                            .addGroup(pcTelLabelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bilTelIsletim, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pcTelLabelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bilTelIslemci, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pcTelLabelLayout.setVerticalGroup(
            pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcTelLabelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bilTelMarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(bilTelModel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(bilTelFiyat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bilTelIsletim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pcTelLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(bilTelRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(bilTelIslemci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bilgisayar/Telefon", pcTelLabel);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Marka:");

        cbMarka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarkaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Model: ");

        cbFiyat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiyatActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Fiyat:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Enerji Sınıfı: ");

        cbEnerji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEnerjiActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Hacim:");

        cbRenk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRenkActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Renk: ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Devir:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Akıllı çamaşır makinesi: ");

        camDevir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camDevirActionPerformed(evt);
            }
        });

        camAkilli.setLabel("checkbox2");

        camEkle.setLabel("Ekle");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(camAkilli, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(camEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(camDevir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(camAkilli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(camDevir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24))
                    .addComponent(camEkle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Çamaşır Makinesi", jPanel4);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Yükseklik:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Tek kapılı?");

        buzYukseklik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buzYukseklikActionPerformed(evt);
            }
        });

        buzTekkapi.setLabel("checkbox2");

        buzEkle.setLabel("Ekle");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buzTekkapi, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(buzEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buzYukseklik, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(buzYukseklik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(buzTekkapi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buzEkle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Buzdolabi", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(15, 15, 15)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbFiyat)
                                    .addComponent(cbMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbModel))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(15, 15, 15)
                                        .addComponent(cbRenk))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEnerji, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbHacim, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jTabbedPane3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbMarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(cbModel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbFiyat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cbRenk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbEnerji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addComponent(cbHacim, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Beyaz Eşya", jPanel3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1)
                    .addContainerGap()))
        );

        jTabbedPane4.addTab("Ekle", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tvEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tvEkleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tvEkleActionPerformed

    private void tvFiyatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tvFiyatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tvFiyatActionPerformed

    private void tvMarkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tvMarkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tvMarkaActionPerformed

    private void bilTelMarkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilTelMarkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilTelMarkaActionPerformed

    private void bilTelFiyatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilTelFiyatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilTelFiyatActionPerformed

    private void bilTelRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilTelRamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilTelRamActionPerformed

    private void bilTelIslemciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilTelIslemciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bilTelIslemciActionPerformed

    private void telKameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telKameraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telKameraActionPerformed

    private void cbMarkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMarkaActionPerformed

    private void cbFiyatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiyatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFiyatActionPerformed

    private void cbEnerjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEnerjiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEnerjiActionPerformed

    private void cbRenkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRenkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRenkActionPerformed

    private void camDevirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camDevirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_camDevirActionPerformed

    private void buzYukseklikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buzYukseklikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buzYukseklikActionPerformed

    private void txtEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnterActionPerformed

    private void txtEnterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEnterMouseClicked
        // TODO add your handling code here:
        txtEnter.setText("");
    }//GEN-LAST:event_txtEnterMouseClicked

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
            java.util.logging.Logger.getLogger(UrunEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UrunEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UrunEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UrunEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UrunEkle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button bilEkle;
    private javax.swing.JTextField bilEkranKarti;
    private javax.swing.JCheckBox bilSSD;
    private javax.swing.JTextField bilTelFiyat;
    private javax.swing.JTextField bilTelIslemci;
    private javax.swing.JTextField bilTelIsletim;
    private javax.swing.JTextField bilTelMarka;
    private javax.swing.JTextField bilTelModel;
    private javax.swing.JTextField bilTelRam;
    private java.awt.Button buzEkle;
    private java.awt.Checkbox buzTekkapi;
    private javax.swing.JTextField buzYukseklik;
    private java.awt.Checkbox camAkilli;
    private javax.swing.JTextField camDevir;
    private java.awt.Button camEkle;
    private javax.swing.JTextField cbEnerji;
    private javax.swing.JTextField cbFiyat;
    private javax.swing.JTextField cbHacim;
    private javax.swing.JTextField cbMarka;
    private javax.swing.JTextField cbModel;
    private javax.swing.JTextField cbRenk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel pcTelLabel;
    private javax.swing.JScrollPane scrollPane;
    private java.awt.Button telEkle;
    private javax.swing.JTextField telEkran;
    private javax.swing.JTextField telKamera;
    private javax.swing.JCheckBox tvAkilli;
    private javax.swing.ButtonGroup tvBtnGrup;
    private javax.swing.JButton tvEkle;
    private javax.swing.JTextField tvEkran;
    private javax.swing.JTextField tvFiyat;
    private javax.swing.JRadioButton tvHdmi0;
    private javax.swing.JRadioButton tvHdmi1;
    private javax.swing.JRadioButton tvHdmi2;
    private javax.swing.JRadioButton tvHdmi3;
    private javax.swing.JRadioButton tvHdmi4;
    private javax.swing.JTextField tvMarka;
    private javax.swing.JTextField tvModel;
    private javax.swing.JPanel tvPanel;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtEnter;
    // End of variables declaration//GEN-END:variables
}
