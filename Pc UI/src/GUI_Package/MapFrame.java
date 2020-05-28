package GUI_Package;

import java.io.FileWriter;
import java.io.IOException;

import MapDraw.CreateHTML;
import Node_Package.NodeList;
import com.kingaspx.util.BrowserUtil;
import com.kingaspx.version.Version;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.events.TitleEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

import javax.swing.*;


/**
 * The frame that has the map that can and cenk created
 * @author canonal, cenk
 */
public class MapFrame  extends javax.swing.JFrame  {

    public MapFrame( JFrame parentFrame, boolean inAPanel ) {
        this.inAPanel = inAPanel;
        this.parentFrame = parentFrame;
        initComponents();
        open_site();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        map_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        reloadButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        map_panel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(33, 145, 236));

        reloadButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reloadButton.setForeground(new java.awt.Color(51, 0, 51));
        reloadButton.setText("Reload");
        reloadButton.setContentAreaFilled(false);
        reloadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reloadButton.setFocusPainted(false);
        reloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadButtonActionPerformed(evt);
            }
        });

        titleLabel.setBackground(new java.awt.Color(32, 123, 198));
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Route Optimizer Map");
        titleLabel.setOpaque(true);

        backButton.setBackground(new java.awt.Color(220, 86, 86));
        backButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(51, 0, 51));
        backButton.setText("Back");
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setOpaque(true);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(reloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(map_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(map_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(876, 608));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void reloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if( inAPanel )
        {
            // getting the nodelist to display on the editLocations frame
            NodeList nodes = new NodeList();
            nodes.readNodesFromFile();

            // changing the HTML file to show the markers that are read from the txt file
            CreateHTML createHTML = new CreateHTML();
            createHTML.showMarkersOnHTML( mapHTMLPath, nodes );
        }

        browser.reload();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CreateHTML createHTML = new CreateHTML();
        // if this isnt the frame and it is in another frames panel, itt calls the other frames close method
        if( inAPanel )
            ((EditLocationsFrame)parentFrame).back();
        else {
            parentFrame.setVisible(true);
            this.setVisible(false);

        }
        createHTML.returnToOgHTML(mapHTMLPath);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton reloadButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel map_panel;
    private JFrame parentFrame;
    private boolean inAPanel;
    // End of variables declaration//GEN-END:variables

    // constants
    final String unorderedNodesPath = "src/Txt_Files/Unordered_Nodes.txt";
    final String mapHTMLPath = "src/Map_Files/HTML/simple_map.html";


    Browser browser;
    BrowserView view;
    String locationList = "";


    private void open_site() {
        BrowserUtil.setVersion(Version.V6_22);

        browser = new Browser();
        view = new BrowserView(browser);

        map_panel.add(view, BorderLayout.CENTER);

        browser.addTitleListener((TitleEvent evt) -> {
            titleLabel.setText("   " + evt.getTitle());
        });

        browser.addConsoleListener((ConsoleEvent evt) -> {
            try {
                locationList = evt.getMessage();
                printToFile(locationList);
                System.out.println("LOG: " + evt.getMessage());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MapFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent evt) {
                evt.getBrowser().setZoomLevel(-2);
            }
        });

        File simple_MapHTML = new File( mapHTMLPath );
        browser.loadURL( simple_MapHTML.getAbsolutePath() );
    }

    public void printToFile(String s) throws FileNotFoundException
    {
        try
        {
            FileWriter pw = new FileWriter(unorderedNodesPath, true);
            pw.write(s);
            pw.close();
            System.out.println("Printed to file");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

