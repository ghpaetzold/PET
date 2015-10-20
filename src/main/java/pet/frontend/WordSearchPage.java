/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pet.frontend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pet.search.SearchManager;

/**
 *
 * @author GustavoH
 */
public class WordSearchPage extends javax.swing.JFrame {

    private BorderLayoutAnnotationPage blap;
    private SearchManager manager;

    /**
     * @return the manager
     */
    public SearchManager getSearchManager() {
        return manager;
    }

    public void updateResultsLabel(String text) {
        this.resultsLabel.setText(text);
    }

    public class WordSearchKeyListener implements KeyListener {

        private WordSearchPage page;

        private WordSearchKeyListener(WordSearchPage wsp) {
            this.page = wsp;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.page.dispose();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                this.page.nextButton.doClick();
            }
        }
    }

    /**
     * Creates new form WordSearchPage
     *
     * @param blap
     */
    public WordSearchPage(BorderLayoutAnnotationPage blap) {
        initComponents();

        //Set annotation page:
        this.blap = blap;

        //Configure screen components:
        WordSearchKeyListener pageListener = new WordSearchKeyListener(this);
        this.searchField.addKeyListener(pageListener);
        this.nextButton.setFocusable(false);
        this.previousButton.setFocusable(false);
        this.firstButton.setFocusable(false);
        this.lastButton.setFocusable(false);
        this.addKeyListener(pageListener);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        
        //Create search manager:
        this.manager = new SearchManager(this);
        
        //Request focus to search field:
        this.searchField.requestFocus();
    }

    public BorderLayoutAnnotationPage getAnnotationPage() {
        return blap;
    }

    void updateSearch() {
        this.getSearchManager().updateSearch();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.searchField.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        firstButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();
        resultsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchLabel.setText("Find what:");

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });

        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        firstButton.setText("First");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });

        lastButton.setText("Last");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchField)
                    .addComponent(searchLabel)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(previousButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousButton)
                    .addComponent(nextButton)
                    .addComponent(firstButton)
                    .addComponent(lastButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed

    }//GEN-LAST:event_searchFieldActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        //Get value being searched:
        String search = this.searchField.getText();
        
        //Update search:
        this.getSearchManager().getNext(search);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        //Get value being searched:
        String search = this.searchField.getText();
        
        //Update search:
        this.getSearchManager().getPrevious(search);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost

    }//GEN-LAST:event_searchFieldFocusLost

    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonActionPerformed
        //Get value being searched:
        String search = this.searchField.getText();
        
        //Update search:
        this.getSearchManager().getFirst(search);
    }//GEN-LAST:event_firstButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
        //Get value being searched:
        String search = this.searchField.getText();
        
        //Update search:
        this.getSearchManager().getLast(search);
    }//GEN-LAST:event_lastButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton firstButton;
    private javax.swing.JButton lastButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel resultsLabel;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    // End of variables declaration//GEN-END:variables
}
