//MainAppGUI.java

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Primary application. Allows interaction with the shipping store via a GUI.
 * Expands on code provided as the assignment 2 solution by replacing the console interface with a GUI.
 *
 * CS 3354 - Object Oriented Programming
 * Assignment 5
 * Fall 2017
 *
 * @author Nathan Hellrung
 * @version 11/20/2017
 */
public class MainAppGUI
{
    private final static Logger logger = Logger.getLogger(MainAppGUI.class.getName());  //create logger
    ShippingStore ss;   //Declare a ShippingStore "ss" 

    /**
     * Constructor
     */
    public MainAppGUI()
    {
        logger.info("constructor called");
        ss = ShippingStore.readDatabase();  //Populate shipping store with any saved shipping store info
    }
    
    
    /**
     * This method servers as the main interface between the program and the user.
     * The method interacts with the user by displaying fram with a set of buttons. 
     * Upon selection, a button will perform its indicated function in a seperate frame. 
     */ 
    public void runMainMenu()
    {
        logger.info("main menu method entered");
        //Create a JFrame container
        JFrame mainMenuFrame = new JFrame("Shipping Store Main Menu");
        mainMenuFrame.setSize(500, 500);
        //Set it so the frame only closes using "save and exit" option
        mainMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
        //Create a panel within the frame
        JPanel buttonPanel = new JPanel();
        //Configure the panel to display the buttons in a single column
        buttonPanel.setLayout(new GridLayout(0,1));
        
        
        //Create buttons (one for each menu option)
        JButton showAllPackagesButton = new JButton("Show All Packages");
        JButton addNewPackageButton = new JButton("Add a New Package");
        JButton deletePackageButton = new JButton("Delete a Package");
        JButton searchPackageButton = new JButton("Search for a Package");
        JButton showAllUsersButton = new JButton("Show All Users");
        JButton addNewUserButton = new JButton("Add a New User");
        JButton updateUserButton = new JButton("Update a User's Info");
        JButton deliverPackageButton = new JButton("Deliver a Package");
        JButton showAllTransactionsButton = new JButton("Show All Transactions");
        JButton exitProgramButton = new JButton("Save and Exit");
        
        
        //Configrure button properties
        exitProgramButton.setBackground(Color.RED);     //make "Save and Exit" button red colored
        exitProgramButton.setForeground(Color.WHITE);   //make "Save and Exit" text white
        
        
        //Create action listeners for each button
        ActionListener showAllPackagesListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling showAllPackages method"); 
                showAllPackages();
            }
        };
        ActionListener addNewPackageListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    logger.info("calling addNewPackage");
                    addNewPackage();
                } catch (BadInputException ex) {
                    logger.severe("BadInputException encountered");
                }
            }
        };
        ActionListener deletePackageListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 logger.info("calling deletePackage");
                 deletePackage();
            }
        };
        ActionListener searchPackageListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 logger.info("calling searchPackage");
                 searchPackage();
            }
        };
        ActionListener showAllUsersListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling showAllUsers"); 
                showAllUsers();
            }
        };
        ActionListener addNewUserListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling addNewUser"); 
                addNewUser();
            }
        };
        ActionListener updateUserListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling updateUser()"); 
                updateUser();
            }
        };
        ActionListener deliverPackageListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling deliverPackage"); 
                deliverPackage();
            }
        };
        ActionListener showAllTransactionsListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling showAllTransactions"); 
                showAllTransactions();
            }
        };
        ActionListener exitProgramListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("calling exitProgram"); 
                exitProgram(mainMenuFrame);
            }
        };
        
        
        //Add each of the ActionListeners to their respective buttons
        showAllPackagesButton.addActionListener(showAllPackagesListener);
        addNewPackageButton.addActionListener(addNewPackageListener);
        deletePackageButton.addActionListener(deletePackageListener);
        searchPackageButton.addActionListener(searchPackageListener);
        showAllUsersButton.addActionListener(showAllUsersListener);
        addNewUserButton.addActionListener(addNewUserListener);
        updateUserButton.addActionListener(updateUserListener);
        deliverPackageButton.addActionListener(deliverPackageListener);
        showAllTransactionsButton.addActionListener(showAllTransactionsListener);
        exitProgramButton.addActionListener(exitProgramListener);
        
        
        //Add the completed buttons to the panel
        buttonPanel.add(showAllPackagesButton);
        buttonPanel.add(addNewPackageButton);
        buttonPanel.add(deletePackageButton);
        buttonPanel.add(searchPackageButton);
        buttonPanel.add(showAllUsersButton);
        buttonPanel.add(addNewUserButton);
        buttonPanel.add(updateUserButton);
        buttonPanel.add(deliverPackageButton);
        buttonPanel.add(showAllTransactionsButton);
        buttonPanel.add(exitProgramButton);
        
        
        //Add the panel containing the buttons to the frame
        mainMenuFrame.add(buttonPanel);
        
        //Make the completed main menu frame visible
        mainMenuFrame.setVisible(true);
        
    }
    
    /**
     * Displays a a formatted list of all packages and their information in
     *      a JTextArea in a new frame. Text given from 
     *      ShippingStore.getAllPackagesFormatted() function.
     */
    public void showAllPackages()
    {
        logger.info("entered method showAllPackages");
        //Create a new JFrame container to contain our JTextArea
        JFrame showAllPackagesFrame = new JFrame("List of All Packages");
        showAllPackagesFrame.setSize(800, 500);
        showAllPackagesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create a JTextArea which will contain text within the frame
        JTextArea textArea = new JTextArea();
        //Configure text area properties
        textArea.setSize(600,400);      //size of text area. Will fit in the frame.
        //set font to have consistent character spacing for formatting
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(false);    //disable line wrap to maintain formatting
        textArea.setEditable(false);    //disable editing of the displayed text
        textArea.setVisible(true);      //make the text area visible
        
        //Create a JScrollPane containing the text area in the viewport to allow scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        //Add the scroll pane (containing the text area) to the frame
        showAllPackagesFrame.add(scrollPane);
        
        //Call shipping store's getAllPackagesFormatted() method and append the string to textArea
        logger.info("calling ss.getAllPackagesFormatted");
        textArea.append(ss.getAllPackagesFormatted());
        logger.info("exited getAllPackagesFormatted");
        
        //Make the frame visible
        showAllPackagesFrame.setVisible(true);
    }
    
    /**
     * 
     */
    public void addNewPackage() throws BadInputException
    {
        logger.info("entered method addNewPackage");
        //Create a new frame for adding a new package
        JFrame addNewPackageFrame = new JFrame("Add a New Package");
        addNewPackageFrame.setSize(650,500);
        addNewPackageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        //Universal package components and labels:
        //Create a JComboBox to get package type from available options
        JComboBox<String> packageType = new JComboBox<>(new String[] {"Envelope", "Box", "Crate", "Drum"});
        JLabel packageTypeLabel = new JLabel("Package Type:");
        //Create JTextField for user to enter package tracking number
        JTextField packageTrackingNumber = new JTextField(8);
        JLabel packageTrackingNumberLabel = new JLabel("Tracking Number (5-character String):");
        //Create a JComboBox to select a specification for the package
        JComboBox<String> packageSpecification = new JComboBox<>(new String[] {"Fragile", "Books", "Catalogs", "Do-not-bend", "N/A"});
        JLabel packageSpecificationLabel = new JLabel("Specification:");
        //Create a JComboBox to select a valid mailing class for the package
        JComboBox<String> packageMailingClass = new JComboBox<>(new String[] {"First-Class", "Priority", "Retail", "Ground", "Metro"});
        JLabel packageMailingClassLabel = new JLabel("Mailing Class:");
        //Type-specific components and labels:
        //JTextFields for Envelope's height and width
        JTextField envelopeHeight = new JTextField(4);
        JLabel envelopeHeightLabel = new JLabel("Envelope Height (inches, int):");
        JTextField envelopeWidth = new JTextField(4);
        JLabel envelopeWidthLabel = new JLabel("Envelope Width (inches, int):");
        //JTextField for Box's largest dimension and volume
        JTextField boxLargestDimension = new JTextField(5);
        JLabel boxLargestDimensionLabel = new JLabel("Box's Largest Dimension (inches, int):");
        JTextField boxVolume = new JTextField(5);
        JLabel boxVolumeLabel = new JLabel("Box Volume (inches^3, int):");
        //JTextField for Crate's maximum load weight and content
        JTextField crateMaxLoadWeight = new JTextField(4);
        JLabel crateMaxLoadWeightLabel = new JLabel("Crate Max Load Weight (lbs., float):");
        JTextField crateContent = new JTextField(16);
        JLabel crateContentLabel = new JLabel("Crate Content (String):");
        //JComboBox for Drum's material, JTextField for Drum's diameter
        JComboBox<String> drumMaterial = new JComboBox<>(new String[] {"Plastic", "Fiber"});
        JLabel drumMaterialLabel = new JLabel("Drum Material:");
        JTextField drumDiameter = new JTextField(4);
        JLabel drumDiameterLabel = new JLabel("Drum Diameter (Float):");
        //Buttons to create package or cancel package creation
        JButton createPackageButton = new JButton("Create Package!");
        JButton exitPackageCreationButton = new JButton("Cancel");
        exitPackageCreationButton.setBackground(Color.RED); //make "Cancel" button red
        
        
        //Contain and caption type, tracking#, specification, mailing class, and type-specifc components in a CardLayout:
        //      **type-specific fields appear only when corresponding type is selected.
        //Panel containing packageType JComboBox and its label
        JPanel typeComboBoxPanel = new JPanel();
        typeComboBoxPanel.add(packageTypeLabel);
        typeComboBoxPanel.add(packageType);
        //Panel containing envelope components
        JPanel envelopePanel = new JPanel();
        envelopePanel.add(envelopeHeightLabel);
        envelopePanel.add(envelopeHeight);
        envelopePanel.add(envelopeWidthLabel);        
        envelopePanel.add(envelopeWidth);
        //Panel containing box components
        JPanel boxPanel = new JPanel();
        boxPanel.add(boxLargestDimensionLabel);
        boxPanel.add(boxLargestDimension);
        boxPanel.add(boxVolumeLabel);        
        boxPanel.add(boxVolume);
        //Panel containing crate components
        JPanel cratePanel = new JPanel();
        cratePanel.add(crateMaxLoadWeightLabel);
        cratePanel.add(crateMaxLoadWeight);
        cratePanel.add(crateContentLabel);
        cratePanel.add(crateContent);
        //Panel containing drum components
        JPanel drumPanel = new JPanel();
        drumPanel.add(drumMaterialLabel);
        drumPanel.add(drumMaterial);
        drumPanel.add(drumDiameterLabel);
        drumPanel.add(drumDiameter);
        //CardLayout panel containing the type-specific panels and their String identifiers
        JPanel selectedTypePanel = new JPanel(new CardLayout());
        selectedTypePanel.add(envelopePanel, "Envelope");
        selectedTypePanel.add(boxPanel, "Box");
        selectedTypePanel.add(cratePanel, "Crate");
        selectedTypePanel.add(drumPanel, "Drum");
        
        //Panel containing Tracking Number component
        JPanel trackingNumberPanel = new JPanel();
        trackingNumberPanel.add(packageTrackingNumberLabel);
        trackingNumberPanel.add(packageTrackingNumber);
        
        //Panel containing specification component
        JPanel specificationPanel = new JPanel();
        specificationPanel.add(packageSpecificationLabel);
        specificationPanel.add(packageSpecification);
        
        //Panel containing mailing class component 
        JPanel mailingClassPanel = new JPanel();        
        mailingClassPanel.add(packageMailingClassLabel);
        mailingClassPanel.add(packageMailingClass);  
        
        //Super-panel containing the type selector, CardLayout, and common-package-component panels
        JPanel packagePanel = new JPanel();
        packagePanel.setLayout(new GridLayout(0,1));
        packagePanel.add(typeComboBoxPanel);
        packagePanel.add(selectedTypePanel);
        packagePanel.add(trackingNumberPanel);
        packagePanel.add(specificationPanel);
        packagePanel.add(mailingClassPanel);
        
        
        //Create ItemListener for packageType to display the correspinding type-specific panel.
        packageType.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                logger.info("packageType item state changed");
                //Create a new CardLayout with the same layout as selectedTypePanel
                CardLayout c1 = (CardLayout)(selectedTypePanel.getLayout());
                //Display the CardLayout panel corresponding to the selected JComboBox item.
                c1.show(selectedTypePanel, (String)e.getItem());
            }
        });
        
        //Create a listener for the "Cancel" button
        ActionListener exitPackageCreationListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("Cancel button triggered"); 
                addNewPackageFrame.setVisible(false);
                 addNewPackageFrame.dispose();
            }
        };
        
        //Create a listener for createPackageButton
        ActionListener createPackageButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("Create Package button pressed");
                //create the package from info in current components
                String readPackageType = (String)(packageType.getSelectedItem());
                String readSpecification = (String)(packageType.getSelectedItem());
                String readMailingClass = (String)(packageMailingClass.getSelectedItem());
                String readTrackingNumber = (String)(packageTrackingNumber.getText());
                //Show error message and aborts package creation if Tracking Number is invalid:
                if ((readTrackingNumber.matches("^.*[^a-zA-Z0-9].*$")) || (readTrackingNumber.length() != 5))
                {
                    logger.warning("Invalid tracking number entered");
                    //Display error dialog box
                    JOptionPane.showMessageDialog(addNewPackageFrame, "Tracking Number must be 5 alphanumeric digits.", "Bad Tracking Number Error", JOptionPane.ERROR_MESSAGE);
                    //abort package creation
                    return;
                }
                
                //Show error message and abort if tracking number already exists
                if (ss.packageExists(readTrackingNumber))
                {
                    JOptionPane.showMessageDialog(addNewPackageFrame, "A Package with that Tracking Number already exists.", "Bad Tracking Number Error", JOptionPane.ERROR_MESSAGE);
                    logger.warning("Duplicate tracking number entered");
                    return;
                }
                
                if (readPackageType.equals("Envelope"))
                {
                    logger.info("package type Envelope acknowledged");
                    try{
                        int readEnvelopeHeight = Integer.parseInt(envelopeHeight.getText());
                        int readEnvelopeWidth = Integer.parseInt(envelopeWidth.getText());
                        
                        //width and height must be positive
                        if (readEnvelopeHeight < 0)
                        {
                            JOptionPane.showMessageDialog(addNewPackageFrame, "Envelope Height must be positive.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                            logger.warning("Negative envelope height entered");
                            return;
                        }
                        if (readEnvelopeWidth < 0)
                        {
                            JOptionPane.showMessageDialog(addNewPackageFrame, "Envelope Width must be positive.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                            logger.warning("Negative envelope width entered");
                            return;
                        }
                        
                        //If this line is reached, height and width are valid
                        ss.addEnvelope(readTrackingNumber, readSpecification, readMailingClass, readEnvelopeHeight, readEnvelopeWidth);
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Package "+readTrackingNumber+" successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        logger.info("Envelope package successfully added to database");
                    }catch(NumberFormatException ex){
                        //if this exception is encountered, height and width are not integers
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Envelope Height and Width must be integers", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                        logger.warning("Non-integer envelope height or width entered");
                        return;
                    }
                } else if (readPackageType.equals("Box"))
                {
                    logger.info("Package type Box acknowledged");
                    try{
                        int readLargestDimension = Integer.parseInt(boxLargestDimension.getText());
                        int readVolume = Integer.parseInt(boxVolume.getText());
                        
                        //LargestDimension and Volume must be positive
                        if (readLargestDimension < 0)
                        {
                            JOptionPane.showMessageDialog(addNewPackageFrame, "Largest Dimension must be positive.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                            logger.warning("Negative Largest Dimension entered");
                            return;
                        }
                        if (readVolume < 0)
                        {
                            JOptionPane.showMessageDialog(addNewPackageFrame, "Volume must be positive.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                            logger.warning("Negative Volume entered");
                            return;
                        }
                        
                        ss.addBox(readTrackingNumber, readSpecification, readMailingClass, readLargestDimension, readVolume);
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Package "+readTrackingNumber+" successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        logger.info("Box package successfully added");
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Box Largest Dimension and Volume must be integers.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                        logger.warning("Non integer text entered in Largest Dimension or Volume text field");
                        return;
                    }
                } else if (readPackageType.equals("Crate"))
                {
                    logger.info("Crate type package acknowledged");
                    try{
                        float readMaxLoadWeight = Float.parseFloat(crateMaxLoadWeight.getText());
                        String readCrateContent = (String)(crateContent.getText());
                        
                        if (readMaxLoadWeight < 0)
                        {
                            JOptionPane.showMessageDialog(addNewPackageFrame, "Max Load Weight must be positive.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                            logger.warning("Negative MaxLoadWeight entered");
                            return;
                        }
                        
                        ss.addCrate(readTrackingNumber, readSpecification, readMailingClass, readMaxLoadWeight, readCrateContent);
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Package "+readTrackingNumber+" successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        logger.info("Crate package successfully added");
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Crate Max Load Weight must be a float.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                        logger.warning("Non-float MaxLoadWeight entry");
                        return;
                    }
                } else if (readPackageType.equals("Drum"))
                {
                    logger.info("Drum type package acknowledged");
                    try{
                        String readDrumMaterial = (String)(drumMaterial.getSelectedItem());
                        float readDrumDiameter = Float.parseFloat(drumDiameter.getText());
                        
                        if (readDrumDiameter < 0)
                        {
                            JOptionPane.showMessageDialog(addNewPackageFrame, "Drum Diameter must be positive.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                            logger.warning("Negative drum diameter entered");
                            return;
                        }
                    
                        ss.addDrum(readTrackingNumber, readSpecification, readMailingClass, readDrumMaterial, readDrumDiameter);
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Package "+readTrackingNumber+" successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        logger.info("drum package successfully added");
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(addNewPackageFrame, "Drum Diameter must be a float.", "Invalid Dimension Error", JOptionPane.ERROR_MESSAGE);
                        logger.warning("Non-float Diameter entered");
                        return;
                    }
                }
            }
        };
        
        createPackageButton.addActionListener(createPackageButtonListener); //add listener to "create package" button
        packagePanel.add(createPackageButton);  //add "Create Package" button to main panel
        
        exitPackageCreationButton.addActionListener(exitPackageCreationListener);   //Add listener to "Cancel" button
        packagePanel.add(exitPackageCreationButton);    //Add "Cancel" button to panel
        
        //Add packagePanel to the frame
        addNewPackageFrame.add(packagePanel);
        //Enable frame visibility
        addNewPackageFrame.setVisible(true);
    }
    
    /**
     * Provide the tracking number of a package to delete and delete it if it exists
     */
    public void deletePackage()
    {
        logger.info("deletePackage method entered");
        JFrame deletePackageFrame = new JFrame("Delete a Package");
        deletePackageFrame.setSize(450, 300);
        deletePackageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(0,1));
        
        JPanel trackingNumberPanel = new JPanel();
        JTextField trackingNumberField = new JTextField(8);
        JLabel trackingNumberLabel = new JLabel("Tracking Number of Package to delete:");
        trackingNumberPanel.add(trackingNumberLabel);
        trackingNumberPanel.add(trackingNumberField);
        
        JButton deletePackageButton = new JButton("Delete Package");
        JButton closeFrameButton = new JButton("Cancel");
        
        ActionListener deletePackageButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 if (ss.deletePackage(trackingNumberField.getText())) {
                    JOptionPane.showMessageDialog(deletePackageFrame, "Package "+trackingNumberField.getText()+" deleted.", "Package Deleted", JOptionPane.INFORMATION_MESSAGE);
                    logger.info("package successfully deleted");
                }
                else {
                    JOptionPane.showMessageDialog(deletePackageFrame, "Package "+trackingNumberField.getText()+" not found.", "Bad Tracking Number Error", JOptionPane.ERROR_MESSAGE);
                    logger.warning("Specified tracking number not found in database");
                }
            }
        };
        ActionListener closeFrameButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("Cancel button pressed");
                deletePackageFrame.setVisible(false);
                deletePackageFrame.dispose();
            }
        };
        
        deletePackageButton.addActionListener(deletePackageButtonListener);
        closeFrameButton.addActionListener(closeFrameButtonListener);
        
        masterPanel.add(trackingNumberPanel);
        masterPanel.add(deletePackageButton);
        masterPanel.add(closeFrameButton);
        
        deletePackageFrame.add(masterPanel);
        deletePackageFrame.setVisible(true);
    }
    
    /**
     * 
     */
    public void searchPackage()
    {
        logger.info("Entered method searchPackage");
        JFrame searchPackageFrame = new JFrame("Find a Package");
        searchPackageFrame.setSize(850, 400);
        searchPackageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(0,1));
        
        JPanel trackingNumberPanel = new JPanel();
        JTextField trackingNumberField = new JTextField(8);
        JLabel trackingNumberLabel = new JLabel("Tracking Number of Package to search:");
        trackingNumberPanel.add(trackingNumberLabel);
        trackingNumberPanel.add(trackingNumberField);
        
        JButton searchPackageButton = new JButton("Search for Package");
        JButton closeFrameButton = new JButton("Cancel");
        
        JTextArea textArea = new JTextArea();
        //Configure text area properties
        //set font to have consistent character spacing for formatting
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(false);    //disable line wrap to maintain formatting
        textArea.setEditable(false);    //disable editing of the displayed text
        textArea.setVisible(true);      //make the text area visible
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.add(textArea);
        
        ActionListener searchPackageButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 logger.info("Search Package button pressed");
                 if (ss.packageExists(trackingNumberField.getText())) 
                 {
                     textArea.append(ss.getPackageFormatted(trackingNumberField.getText()));
                     logger.info("specified package information successfully displayed");
                 }
                 else 
                 {
                    JOptionPane.showMessageDialog(searchPackageFrame, "Package "+trackingNumberField.getText()+" not found.", "Bad Tracking Number Error", JOptionPane.ERROR_MESSAGE);
                    logger.warning("Specified package not found in database");
                 }
            }
        };
        ActionListener closeFrameButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                logger.info("Cancel button pressed"); 
                searchPackageFrame.setVisible(false);
                searchPackageFrame.dispose();
            }
        };
        
        searchPackageButton.addActionListener(searchPackageButtonListener);
        closeFrameButton.addActionListener(closeFrameButtonListener);
        
        masterPanel.add(trackingNumberPanel);
        masterPanel.add(textAreaPanel);
        masterPanel.add(searchPackageButton);
        masterPanel.add(closeFrameButton);
        
        searchPackageFrame.add(masterPanel);
        searchPackageFrame.setVisible(true);
    }
    
    /**
     * Displays a formatted list of all users in a new frame
     */
    public void showAllUsers()
    {
        logger.info("showAllUsers method entered");
        JFrame showAllUsersFrame = new JFrame("List of All Users");
        showAllUsersFrame.setSize(800, 500);
        showAllUsersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create a JTextArea which will contain text within the frame
        JTextArea textArea = new JTextArea();
        //Configure text area properties
        textArea.setSize(600,400);      //size of text area. Will fit in the frame.
        //set font to have consistent character spacing for formatting
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(false);    //disable line wrap to maintain formatting
        textArea.setEditable(false);    //disable editing of the displayed text
        textArea.setVisible(true);      //make the text area visible
        
        //Create a JScrollPane containing the text area in the viewport to allow scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        //Add the scroll pane (containing the text area) to the frame
        showAllUsersFrame.add(scrollPane);
        
        //Call shipping store's getAllUsersFormatted() method and append the string to textArea
        logger.info("calling ss.getAllUsersFormatted");
        textArea.append(ss.getAllUsersFormatted());
        logger.info("exited getAllUsersFormatted");
        
        //Make the frame visible
        showAllUsersFrame.setVisible(true);
    }
    
    /**
     * Add a new user to the Database
     */
    public void addNewUser()
    {
        logger.info("addNewUser method entered");
        //Code to add a new user
    }
    
    /**
     * 
     */
    public void updateUser()
    {
        logger.info("updateUser method entered");
        //Update a user's info
    }
    
    /**
     * 
     */
    public void deliverPackage()
    {
        logger.info("deliverPackage method entered");
        //Deliver package
    }
    
    /**
     * 
     */
    public void showAllTransactions()
    {
        logger.info("showAllTransactions method entered");
        JFrame showAllTransactionsFrame = new JFrame("List of All Transactions");
        showAllTransactionsFrame.setSize(800, 500);
        showAllTransactionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create a JTextArea which will contain text within the frame
        JTextArea textArea = new JTextArea();
        //Configure text area properties
        textArea.setSize(600,400);      //size of text area. Will fit in the frame.
        //set font to have consistent character spacing for formatting
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(false);    //disable line wrap to maintain formatting
        textArea.setEditable(false);    //disable editing of the displayed text
        textArea.setVisible(true);      //make the text area visible
        
        //Create a JScrollPane containing the text area in the viewport to allow scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        //Add the scroll pane (containing the text area) to the frame
        showAllTransactionsFrame.add(scrollPane);
        
        //Call shipping store's getAllTransactionsFormatted() method and append the string to textArea
        logger.info("calling getAllTransactions method");
        textArea.append(ss.getAllTransactionsText());
        logger.info("exited getAllTransactions method");
        
        //Make the frame visible
        showAllTransactionsFrame.setVisible(true);
    }
    
    /**
     * Saves all information contained by the ShippingStore to "ShippingStore.ser" as a serializeable object,
     *      then closes the main menu window and exits the program.
     */
    private void exitProgram(JFrame givenJFrame)
    {
        logger.info("exitProgram method entered");
        //Save contents of the shipping store
        logger.info("Enterting writeDatabase method");
        ss.writeDatabase();
        logger.info("ss.writeDatabase method exited");
        //Close the main menu window
        givenJFrame.setVisible(false);
        givenJFrame.dispose();
        //Exit the program
        System.exit(0);
    }
    
    
    /**
     * main method of the program.
     * 
     * Creates a MainAppGUI class and performs its runMainMenu function.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        FileHandler fh;
        try{
                fh = new FileHandler("MyLogFile.log");
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
        }catch(SecurityException ex){
                //handle exception
        }catch(IOException ex){
                //handle exception
        }
            
            MainAppGUI mainApp = new MainAppGUI();
            mainApp.runMainMenu();
    }
}


















