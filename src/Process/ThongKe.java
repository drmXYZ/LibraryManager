
package Process;

import Data.Connect;
import static Process.UpdateTable.ps;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import net.proteanit.sql.DbUtils;

public class ThongKe extends javax.swing.JFrame {

    public static String sql = "SELECT * FROM PHIEU_MUON where Han_tra < (select CURDATE())";

    public JTextArea getTa() {
        return taBaoCao;
    }

    public JButton inbc() {
        return jButton2;
    }

    public static Connection con = Connect.getConnect();

    public ThongKe() {
        initComponents();
        UpdateTable.LoadData(sql, tbPhieuQuaHan);
        try {
            String sql1 = "SELECT SUM(So_luong) as sach FROM SACH";
            String sql2 = "SELECT COUNT(Ma_Khach_hang) as khachhang FROM KHACH_HANG";
            String sql3 = "SELECT COUNT(Ma_Phieu_muon) as phieumuon FROM PHIEU_MUON";
            String sql4 = "SELECT COUNT(DISTINCT Ma_Khach_hang) as khachmuon FROM PHIEU_MUON";
            String sql5 = "SELECT COUNT(Ma_Phieu_muon) as phieumuon FROM PHIEU_MUON where (Han_tra < (select CURDATE()) and NgayTra IS NULL)";
            ResultSet rs1 = UpdateTable.ShowTextField(sql1);
            ResultSet rs2 = UpdateTable.ShowTextField(sql2);
            ResultSet rs3 = UpdateTable.ShowTextField(sql3);
            ResultSet rs4 = UpdateTable.ShowTextField(sql4);
            ResultSet rs5 = UpdateTable.ShowTextField(sql5);
            if (rs1.next())
                this.lbTongSach.setText("Tổng số sách : " + Integer.toString(rs1.getInt("sach")));
            if (rs2.next())
                this.lbTongKhach.setText("Tổng số khách hàng: " + Integer.toString(rs2.getInt("khachhang")));
            if (rs3.next())
                this.lbTongPhieu.setText(" Tổng số phiếu mượn: " + Integer.toString(rs3.getInt("phieumuon")));
            if (rs4.next())
                this.lbTongKhachMuon
                        .setText("Tổng số khách đang mượn sách: " + Integer.toString(rs4.getInt("khachmuon")));
            if (rs5.next())
                this.lbTongPhieuQuaHan
                        .setText("Tổng số phiếu quá hạn là: " + Integer.toString(rs5.getInt("phieumuon")));

        } catch (Exception e) {

        }
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        List l;
        l = DbUtils.resultSetToNestedList(rs);
        this.taBaoCao.append(this.lbTongSach.getText() + "\n");
        this.taBaoCao.append(this.lbTongKhach.getText() + "\n");
        this.taBaoCao.append(this.lbTongPhieu.getText() + "\n");
        this.taBaoCao.append(this.lbTongKhachMuon.getText() + "\n");
        this.taBaoCao.append(this.lbTongPhieuQuaHan.getText() + "\n\n");
        this.taBaoCao.append("| Mã PM    | Mã KH    | Mã sách | Ngày mượn | Hạn trả      |Tiền cọc|Ngày Trả |\n");
        for (int i = 1; i <= l.size(); i++)
            this.taBaoCao.append(l.get(1).toString() + "\n");
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPhieuQuaHan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbTongSach = new javax.swing.JLabel();
        lbTongPhieu = new javax.swing.JLabel();
        lbTongKhachMuon = new javax.swing.JLabel();
        lbTongKhach = new javax.swing.JLabel();
        lbTongPhieuQuaHan = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taBaoCao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("Thống kê");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Danh sách phiếu mượn quá hạn");

        tbPhieuQuaHan.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane1.setViewportView(tbPhieuQuaHan);

        jButton1.setText("Quay lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbTongSach.setText("?");

        lbTongPhieu.setText("?");

        lbTongKhachMuon.setText("?");

        lbTongKhach.setText("?");

        lbTongPhieuQuaHan.setText("jLabel2");

        jButton2.setText("In báo cáo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        taBaoCao.setColumns(20);
        taBaoCao.setRows(5);
        taBaoCao.setText("\t\t\t\tTHỐNG KÊ\n");
        jScrollPane2.setViewportView(taBaoCao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(jLabel6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                                                .createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(62, 62, 62)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(lbTongPhieuQuaHan,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(lbTongSach,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(lbTongKhach,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(lbTongPhieu,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(lbTongKhachMuon,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jLabel7)))
                                                                .addGap(170, 170, 170))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jLabel1)
                                                                .addGap(213, 213, 213)))
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE))))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTongKhach)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTongSach)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTongPhieu)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTongKhachMuon)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTongPhieuQuaHan)))
                                .addGap(30, 30, 30)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                .addContainerGap()));

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        test t = new test();
        t.setVisible(true);
        dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            JFileChooser jfc = new JFileChooser("Save File");
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String content = this.taBaoCao.getText();
                jfc.setDialogTitle("Save File");
                FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile());
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                JOptionPane.showMessageDialog(null, "Lưu thành công");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTongKhach;
    private javax.swing.JLabel lbTongKhachMuon;
    private javax.swing.JLabel lbTongPhieu;
    private javax.swing.JLabel lbTongPhieuQuaHan;
    private javax.swing.JLabel lbTongSach;
    private javax.swing.JTextArea taBaoCao;
    private javax.swing.JTable tbPhieuQuaHan;

}
