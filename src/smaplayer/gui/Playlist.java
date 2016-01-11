/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smaplayer.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import smaplayer.FileUtils;
import smaplayer.Mp3;
import smaplayer.PlayerFileFilter;
import smaplayer.SmaPlayer;

/**
 *
 * @author Nick
 */
public class Playlist extends javax.swing.JFrame {
    
    private PlayerFileFilter openMP3 = new PlayerFileFilter(FileUtils.MP3_FILES_EXP, FileUtils.MP3_FILES_DESC);
    private PlayerFileFilter openPLS = new PlayerFileFilter(FileUtils.PLS_FILES_EXP, FileUtils.PLS_FILES_DESC);
    private DefaultListModel myListModel = new DefaultListModel();

    /**
     * Creates new form Playlist
     */
    public Playlist() {
        initComponents();
    }
    
    public void changeSkin(String skin)
    {
        try {
            UIManager.setLookAndFeel(skin);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SwingUtilities.updateComponentTreeUI(this);
    }
    public void setListModel(Mp3 mp3){
        myListModel.addElement(mp3);
    }
    public void setListModel(DefaultListModel model){
        myListModel = model;
    }
    public DefaultListModel getListModel(){
        return myListModel;
    }
    public JList getList(){
        return jPlayList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOpenFile = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPLContainer = new javax.swing.JScrollPane();
        jPlayList = new javax.swing.JList<>();
        jMenuPanel = new javax.swing.JPanel();
        btnAddMp3 = new javax.swing.JButton();
        btnAddPlayList = new javax.swing.JButton();
        btnDell = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        jOpenFile.setAcceptAllFileFilterUsed(false);
        jOpenFile.setCurrentDirectory(new java.io.File("C:\\Users\\koropenkods\\Downloads"));
        jOpenFile.setMultiSelectionEnabled(true);

        setTitle("Playlist");
        setIconImage(new ImageIcon("src/smaplayer/images/mainIcon.png").getImage());
        setLocation(new java.awt.Point(8, 0));
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPlayList.setModel(myListModel);
        jPlayList.setSelectedIndex(1);
        jPLContainer.setViewportView(jPlayList);

        jMenuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddMp3.setText("Add");
        btnAddMp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMp3ActionPerformed(evt);
            }
        });

        btnAddPlayList.setText("PlayList");

        btnDell.setText("Del");
        btnDell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDellActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jMenuPanelLayout = new javax.swing.GroupLayout(jMenuPanel);
        jMenuPanel.setLayout(jMenuPanelLayout);
        jMenuPanelLayout.setHorizontalGroup(
            jMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddMp3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddPlayList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addContainerGap())
        );
        jMenuPanelLayout.setVerticalGroup(
            jMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMp3)
                    .addComponent(btnDell)
                    .addComponent(btnClear)
                    .addComponent(btnAddPlayList))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMenuPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPLContainer, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPLContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMp3ActionPerformed
        FileUtils.setFileFilter(jOpenFile, openMP3);        
        int open = jOpenFile.showOpenDialog(this);
        
        int coin = 0;
        Mp3 playlistSongName;
        
        
        if (open == JFileChooser.APPROVE_OPTION){
            File[] choosenFiles = jOpenFile.getSelectedFiles();
            
            for (File file: choosenFiles) {
                Mp3 mp3 = new Mp3(file.getName(), file.getPath());
                
                for (int i = 0; i < myListModel.size(); i++) {
                    playlistSongName = (Mp3) myListModel.getElementAt(i);
                    if (mp3.getSongName().equals(playlistSongName.getSongName()))
                        coin++;                    
                }
                
                if(coin == 0)
                    myListModel.addElement(mp3);
                else
                    coin = 0;
            }
        }
        jPlayList.setSelectedIndex(0);
    }//GEN-LAST:event_btnAddMp3ActionPerformed

    private void btnDellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDellActionPerformed
        int[] selectedItems = jPlayList.getSelectedIndices();
        
        ArrayList<Mp3> selectedMp3 = new ArrayList();
        
        for (int i = 0; i < selectedItems.length; i++) {
            Mp3 mp3 = (Mp3) myListModel.getElementAt(selectedItems[i]);
            selectedMp3.add(mp3);
        }
        
        for (Mp3 file: selectedMp3) {
            myListModel.removeElement(file);
        }
    }//GEN-LAST:event_btnDellActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        myListModel.clear();
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(Playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Playlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Playlist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMp3;
    private javax.swing.JButton btnAddPlayList;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDell;
    private javax.swing.JPanel jMenuPanel;
    private javax.swing.JFileChooser jOpenFile;
    private javax.swing.JScrollPane jPLContainer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JList<String> jPlayList;
    // End of variables declaration//GEN-END:variables
}
