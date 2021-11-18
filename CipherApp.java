/**
 * GUI application that implements the Caesar and Vigenere ciphers.
 * App allows user to choose which cipher they want to implement
 * and either encryption or decryption from another dropdown menu.
 * User inputs their desired text in the text area, inputs the key
 * to be used and clicks the submit button which then 
 * encrypts/decrypts their text.
 * 
 * @author steven
*/
public class CipherApp extends javax.swing.JFrame {

    /**
     * Creates new app CipherApp
    */
    public CipherApp() {
        initComponents();
        caesarKey.setVisible(true);
        vigenereKey.setVisible(false);
    }
    
    private boolean caesarSelected = true;
    private boolean encryptSelected = true;
    
    /*
     * Implements the caesar encryption algorithm
    */
    private void caesarEncrypt() {
        String plaintext = textArea.getText().toUpperCase();
        int key = (Integer) caesarKey.getValue();
        StringBuilder ciphertext = new StringBuilder();
        
        // Loop through each letter of plaintext and encrypt it
        for(int i=0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            
            /* 
             * Encrypt plaintext if it is alphabetic
             * Leave as it is if it is not
            */
            if(Character.isAlphabetic(p)) {
                char letter = (char) (((int)plaintext.charAt(i) + key - 65) % 26 + 65);
                ciphertext.append(letter);
            } else {
                ciphertext.append(p);
            }
        }
        
        resultText.setText(ciphertext.toString());
    }
    
    /*
     * Implements the caesar decryption algorithm
    */
    private void caesarDecrypt() {
        String ciphertext = textArea.getText().toUpperCase();
        int key = (Integer) caesarKey.getValue();
        StringBuilder plaintext = new StringBuilder();
        
        // Loop through each letter of ciphertext and decrypt it
        for(int i=0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            
            /* 
             * Decrypt ciphertext if it is alphabetic
             * Leave as it is if it is not
            */
            if (Character.isAlphabetic(c)) {
                char letter = (char) (((int)c - key + 65) % 26 + 65);
                plaintext.append(letter);
            } else {
                plaintext.append(c);
            }
        }
        
        resultText.setText(plaintext.toString());
    }
    
    /*
     * Implements the Vigenere encryption algorithm
    */
    private void vigenereEncrypt() {
        String ciphertext = "";
        String plaintext = textArea.getText().toUpperCase();
        String key = vigenereKey.getText().toUpperCase();
        
        // Loop through each letter of plaintext and encrypt it
        for(int i=0, j=0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            
            /* 
             * Encrypt plaintext if it is alphabetic
             * Leave as it is if it is not
            */
            if(Character.isAlphabetic(p)){
                int asc = (p + key.charAt(j)) % 26;
                asc += 'A'; // Convert int to ascii
                ciphertext += (char)(asc);
                j = ++j % key.length();
            } else {
                ciphertext += p;
            }
        }
        
        resultText.setText(ciphertext);
    }
    
    /*
     * Implements the Vigenere decryption algorithm
    */
    private void vigenereDecrypt() {
        String plaintext = "";
        String ciphertext = textArea.getText().toUpperCase();
        String key = vigenereKey.getText().toUpperCase();
        
        // Loop through each letter of ciphertext and decrypt it
        for(int i=0, j=0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            
            /* 
             * Decrypt ciphertext if it is alphabetic
             * Leave as it is if it is not
            */
            if(Character.isAlphabetic(c)){
                int asc = (c - key.charAt(j) + 26) % 26;
                asc += 'A'; // Convert int to ascii
                plaintext += (char)(asc);
                j = ++j % key.length();
            } else {
                plaintext += c;
            }
                
        }
        
        resultText.setText(plaintext);
    }

    // Initialise Java GUI components
    private void initComponents() {

        cipherSelector = new javax.swing.JComboBox<>();
        modeSelector = new javax.swing.JComboBox<>();
        keyLabel = new javax.swing.JLabel();
        caesarKey = new javax.swing.JSpinner();
        vigenereKey = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultText = new javax.swing.JTextArea();
        resultKey = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        // Set default settings for application
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cipher App");
        setLocation(new java.awt.Point(500, 250));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        cipherSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Caesar", "Vigenere" }));
        cipherSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cipherSelectorActionPerformed(evt);
            }
        });

        modeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Encrypt", "Decrypt" }));
        modeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeSelectorActionPerformed(evt);
            }
        });

        keyLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        keyLabel.setText("Key:");

        caesarKey.setModel(new javax.swing.SpinnerNumberModel(1, 1, 25, 1));

        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        
        jScrollPane1.setViewportView(textArea);

        resultText.setEditable(false);
        resultText.setColumns(20);
        resultText.setLineWrap(true);
        resultText.setRows(5);

        jScrollPane2.setViewportView(resultText);

        resultKey.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        resultKey.setText("Result:");

        clearButton.setBackground(new java.awt.Color(51, 153, 255));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(51, 153, 255));
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jMenu2.setText("Edit");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Cut");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Copy");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Paste");
        jMenu2.add(jMenuItem3);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        // Initialise the layout of the application
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(keyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caesarKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vigenereKey, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cipherSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                .addComponent(modeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resultKey)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(submitButton)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cipherSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyLabel)
                    .addComponent(caesarKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vigenereKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(submitButton)
                        .addComponent(clearButton))
                    .addComponent(resultKey, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // Function to control what happens when the clear button is clicked
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Remove all text from both textareas
        textArea.setText("");
        resultText.setText("");
    }

    // Function to control what happens when the submit button is clicked
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Implement the encrypt/decryption algorithm that is selected
        if(caesarSelected && encryptSelected) {
            caesarEncrypt();
        } else if(caesarSelected && !encryptSelected) {
            caesarDecrypt();
        } else if(!caesarSelected && encryptSelected) {
            vigenereEncrypt();
        } else {
            vigenereDecrypt();
        }
    }

    // Function to control what happens when the cipherSelector dropdown is changed
    private void cipherSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        // If Caesar cipher is selected make Caesar key visible and Vigenere not visible
        if(cipherSelector.getSelectedItem().toString().equals("Caesar")) {
            caesarSelected = true;
            caesarKey.setVisible(true);
            vigenereKey.setVisible(false);
        } else { // Make Vigenere key visible and Caesar key not visible
            caesarSelected = false;
            caesarKey.setVisible(false);
            vigenereKey.setVisible(true);
        }
    }

    // Function to control what happens when the modeSelector dropdown is changed
    private void modeSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        // If encrypt is selected make encryptSelected true
        if(modeSelector.getSelectedItem().toString().equals("Encrypt")) {
            encryptSelected = true;
        } else { // Make encryptSelected false
            encryptSelected = false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CipherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CipherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CipherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CipherApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the application */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CipherApp().setVisible(true);
            }
        });
    }

    // Variables declaration 
    private javax.swing.JSpinner caesarKey;
    private javax.swing.JComboBox<String> cipherSelector;
    private javax.swing.JButton clearButton;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JComboBox<String> modeSelector;
    private javax.swing.JLabel resultKey;
    private javax.swing.JTextArea resultText;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField vigenereKey;
    // End of variables declaration
}
