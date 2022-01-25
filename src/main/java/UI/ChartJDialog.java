/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.ThongKeDAO;
import Helper.JDBC;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class ChartJDialog extends javax.swing.JDialog {

        /**
         * Creates new form ChartJDialog
         */
        public ChartJDialog(java.awt.Frame parent, boolean modal) {
                super(parent, modal);
                initComponents();
                setLocationRelativeTo(null);
                setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/logo1.png")));
                try {
                        init();
                } catch (Exception e) {

                }
        }

        ThongKeDAO tkDAO = new ThongKeDAO();

        void init() throws Exception {
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                List<Integer> lstNam = tkDAO.selectYears();
                for (Integer objects : lstNam) {
                        int nam = Integer.parseInt(objects.toString());
                        String sql = "select sum(ThanhTien) from HoaDon inner join DatPhong on HoaDon.MaDatPhong = DatPhong.MaDatPhong where YEAR(NgayTraPhong) = " + nam;
                        double tongTien = (double) JDBC.value(sql);
                        dataset.addValue(tongTien, "Tổng Tiền", String.valueOf(nam));
                }

                JFreeChart chart = ChartFactory.createBarChart3D("Doanh Thu Thuê Phòng", "Tổng Tiền", "Số Tiền", dataset, PlotOrientation.VERTICAL, false, true, false);
                CategoryPlot plot = chart.getCategoryPlot();
                plot.setBackgroundPaint(SystemColor.inactiveCaption);
                ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
                BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();
                r.setSeriesPaint(0, new Color(255, 0, 127));
                chart.getTitle().setPaint(Color.red);

                SubCategoryAxis ds = new SubCategoryAxis("Năm");
                ds.setCategoryMargin(0.5);
                plot.setDomainAxis(ds);
                /*
                // set Hình ảnh
                File url = new File("C:\\Users\\khoct\\OneDrive\\Desktop\\a2.jpg");
                Image image = ImageIO.read(url);
                plot.setBackgroundImage(image);
                chart.setBackgroundImage(image);
                 */

//                chart.setBackgroundPaint(new Color(247, 220, 250, 100));
                /*
                ChartFrame frame = new ChartFrame("Doanh Thu Thuê Phòng", chart);
                frame.setVisible(false);
                frame.setResizable(false);
                frame.setSize(500, 400);
                 */
                ChartPanel chartPanel = new ChartPanel(chart);
                pnlChart.removeAll();
                pnlChart.add(chartPanel, BorderLayout.CENTER);
                pnlChart.validate();

        }

        /**
         * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                pnlChart = new javax.swing.JPanel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                pnlChart.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
                pnlChart.setLayout(new javax.swing.BoxLayout(pnlChart, javax.swing.BoxLayout.LINE_AXIS));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlChart, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlChart, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

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
                        java.util.logging.Logger.getLogger(ChartJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(ChartJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(ChartJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(ChartJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>

                /* Create and display the form */
//                java.awt.EventQueue.invokeLater(new Runnable() {
//                        public void run() {
//                                new ChartJDialog().setVisible(true);
//                        }
//                });
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                ChartJDialog dialog = new ChartJDialog(new javax.swing.JFrame(), true);
                                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent e) {
                                                System.exit(0);
                                        }
                                });
                                dialog.setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel pnlChart;
        // End of variables declaration//GEN-END:variables
}
