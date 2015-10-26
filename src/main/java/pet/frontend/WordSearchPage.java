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
    private boolean settingsChanged;

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
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.page.nextButton.doClick();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.page.previousButton.doClick();
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
        this.sourceCheckBox.setSelected(true);
        this.targetCheckBox.setSelected(true);
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

    public boolean searchSource() {
        return this.sourceCheckBox.isSelected();
    }

    public boolean searchTarget() {
        return this.targetCheckBox.isSelected();
    }

    /**
     * @return the manager
     */
    public SearchManager getSearchManager() {
        return manager;
    }

    public void updateResultsLabel(String text) {
        this.resultsLabel.setText(text);
    }

    /**
     * @return the settingsChanged
     */
    public boolean getSettingsChanged() {
        return settingsChanged;
    }

    /**
     * @param settingsChanged the settingsChanged to set
     */
    public void setSettingsChanged(boolean settingsChanged) {
        this.settingsChanged = settingsChanged;
    }

    private String getSearchFieldContent() {
        //Get raw string from search field:
        String search = this.searchField.getText();
        
        //If lowercasing has been requested, format search query:
        if(this.caseCheckBox.isSelected()){
            search = search.toLowerCase();
        }
        
        //Return resulting query:
        return search;
    }
    
    public boolean getCaseInsensitive(){
        return this.caseCheckBox.isSelected();
    }
    
    public boolean getFullMatch(){
        return this.fullCheckBox.isSelected();
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
        targetsLabel = new javax.swing.JLabel();
        sourceCheckBox = new javax.swing.JCheckBox();
        targetCheckBox = new javax.swing.JCheckBox();
        optionsLabel = new javax.swing.JLabel();
        caseCheckBox = new javax.swing.JCheckBox();
        fullCheckBox = new javax.swing.JCheckBox();

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

        targetsLabel.setText("Targets:");

        sourceCheckBox.setText("Source");
        sourceCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceCheckBoxActionPerformed(evt);
            }
        });

        targetCheckBox.setText("Target");
        targetCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetCheckBoxActionPerformed(evt);
            }
        });

        optionsLabel.setText("Options:");

        caseCheckBox.setText("Case-Insensitive");
        caseCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseCheckBoxActionPerformed(evt);
            }
        });

        fullCheckBox.setText("Full Match");
        fullCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField)
                    .addComponent(resultsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchLabel)
                                    .addComponent(targetsLabel)
                                    .addComponent(sourceCheckBox)
                                    .addComponent(targetCheckBox))
                                .addGap(81, 81, 81)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionsLabel)
                                    .addComponent(fullCheckBox)
                                    .addComponent(caseCheckBox)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(previousButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastButton)))
                        .addGap(0, 28, Short.MAX_VALUE)))
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
                    .addComponent(targetsLabel)
                    .addComponent(optionsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceCheckBox)
                    .addComponent(caseCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(targetCheckBox)
                    .addComponent(fullCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousButton)
                    .addComponent(nextButton)
                    .addComponent(firstButton)
                    .addComponent(lastButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resultsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        String search = this.getSearchFieldContent();

        //Update search:
        this.getSearchManager().getNext(search);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        //Get value being searched:
        String search = this.getSearchFieldContent();

        //Update search:
        this.getSearchManager().getPrevious(search);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost

    }//GEN-LAST:event_searchFieldFocusLost

    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonActionPerformed
        //Get value being searched:
        String search = this.getSearchFieldContent();

        //Update search:
        this.getSearchManager().getFirst(search);
    }//GEN-LAST:event_firstButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
        //Get value being searched:
        String search = this.getSearchFieldContent();

        //Update search:
        this.getSearchManager().getLast(search);
    }//GEN-LAST:event_lastButtonActionPerformed

    private void caseCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseCheckBoxActionPerformed
        this.settingsChanged = true;
        this.requestFocus();
    }//GEN-LAST:event_caseCheckBoxActionPerformed

    private void sourceCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceCheckBoxActionPerformed
        this.settingsChanged = true;
        this.requestFocus();
    }//GEN-LAST:event_sourceCheckBoxActionPerformed

    private void targetCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetCheckBoxActionPerformed
        this.settingsChanged = true;
        this.requestFocus();
    }//GEN-LAST:event_targetCheckBoxActionPerformed

    private void fullCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullCheckBoxActionPerformed
        this.settingsChanged = true;
        this.requestFocus();
    }//GEN-LAST:event_fullCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox caseCheckBox;
    private javax.swing.JButton firstButton;
    private javax.swing.JCheckBox fullCheckBox;
    private javax.swing.JButton lastButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel resultsLabel;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JCheckBox sourceCheckBox;
    private javax.swing.JCheckBox targetCheckBox;
    private javax.swing.JLabel targetsLabel;
    // End of variables declaration//GEN-END:variables
}
