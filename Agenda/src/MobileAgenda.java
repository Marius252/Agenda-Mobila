import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber;
    }
}

public class MobileAgenda extends Frame implements ActionListener {
    private ArrayList<Contact> contacts;
    private TextField nameField, phoneField, callField;
    private TextArea contactListArea;
    private Button addButton, viewButton, deleteButton, callButton;
    private TextField deleteField;

    public MobileAgenda() {
        contacts = new ArrayList<>();

        setTitle("Mobile Agenda");
        setSize(400, 400);
        setLayout(new FlowLayout());

        nameField = new TextField(20);
        phoneField = new TextField(20);
        callField = new TextField(20);
        contactListArea = new TextArea(10, 30);
        addButton = new Button("Add Contact");
        viewButton = new Button("View Contacts");
        deleteButton = new Button("Delete Contact");
        deleteField = new TextField(20);
        callButton = new Button("Call Contact");

        add(new Label("Name:"));
        add(nameField);
        add(new Label("Phone Number:"));
        add(phoneField);
        add(addButton);
        add(viewButton);
        add(new Label("Delete Contact by Name:"));
        add(deleteField);
        add(deleteButton);
        add(new Label("Contact List:"));
        add(contactListArea);
        add(new Label("Enter contact name to call:"));
        add(callField);
        add(callButton);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        deleteButton.addActionListener(this);
        callButton.addActionListener(this);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String phoneNumber = phoneField.getText();
            if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                contacts.add(new Contact(name, phoneNumber));
                nameField.setText("");
                phoneField.setText("");
                contactListArea.append("Contact added: " + name + ", " + phoneNumber + "\n");
            } else {
                contactListArea.append("Please enter both name and phone number.\n");
            }
        }

        if (e.getSource() == viewButton) {
            contactListArea.setText("");
            if (contacts.isEmpty()) {
                contactListArea.append("No contacts available.\n");
            } else {
                for (Contact contact : contacts) {
                    contactListArea.append(contact + "\n");
                }
            }
        }

        if (e.getSource() == deleteButton) {
            String nameToDelete = deleteField.getText();
            boolean removed = contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(nameToDelete));
            deleteField.setText("");
            if (removed) {
                contactListArea.append("Contact deleted successfully.\n");
            } else {
                contactListArea.append("Contact not found.\n");
            }
        }

        if (e.getSource() == callButton) {
            String nameToCall = callField.getText();
            boolean found = false;

            for (Contact contact : contacts) {
                if (contact.getName().equalsIgnoreCase(nameToCall)) {
                    found = true;
                    contactListArea.append("Calling " + contact.getName() + " at " + contact.getPhoneNumber() + "...\n");
                    break;
                }
            }

            if (!found) {
                contactListArea.append("Contact not found. Please check the name and try again.\n");
            }

            callField.setText("");
        }
    }

    public static void main(String[] args) {
        new MobileAgenda();
    }
}
