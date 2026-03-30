import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShopGadget extends JFrame implements ActionListener {

    private ArrayList<Gadget> gadgets = new ArrayList<>();

    private JTextField modelField, priceField, weightField, sizeField;
    private JTextField creditField, memoryField;
    private JTextField phoneField, durationField;
    private JTextField downloadField, displayField;

    private JButton addMobileBtn, addMP3Btn, clearBtn, displayAllBtn;
    private JButton makeCallBtn, downloadBtn;

    public ShopGadget() {
        super("Shop Gadget");
        setLayout(new GridLayout(12, 2));

        add(new JLabel("Model:"));
        modelField = new JTextField();
        add(modelField);

        add(new JLabel("Price (£):"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Weight (g):"));
        weightField = new JTextField();
        add(weightField);

        add(new JLabel("Size:"));
        sizeField = new JTextField();
        add(sizeField);

        add(new JLabel("Initial Credit (Mobile):"));
        creditField = new JTextField();
        add(creditField);

        add(new JLabel("Initial Memory (MP3):"));
        memoryField = new JTextField();
        add(memoryField);

        add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Call Duration (min):"));
        durationField = new JTextField();
        add(durationField);

        add(new JLabel("Download Size (MB):"));
        downloadField = new JTextField();
        add(downloadField);

        add(new JLabel("Display Number:"));
        displayField = new JTextField();
        add(displayField);

        addMobileBtn = new JButton("Add Mobile");
        addMobileBtn.addActionListener(this);
        add(addMobileBtn);

        addMP3Btn = new JButton("Add MP3");
        addMP3Btn.addActionListener(this);
        add(addMP3Btn);

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(this);
        add(clearBtn);

        displayAllBtn = new JButton("Display All");
        displayAllBtn.addActionListener(this);
        add(displayAllBtn);

        makeCallBtn = new JButton("Make A Call");
        makeCallBtn.addActionListener(this);
        add(makeCallBtn);

        downloadBtn = new JButton("Download Music");
        downloadBtn.addActionListener(this);
        add(downloadBtn);

        setSize(500, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String getModel() { return modelField.getText(); }
    private double getPrice() { return Double.parseDouble(priceField.getText()); }
    private int getWeight() { return Integer.parseInt(weightField.getText()); }
    private String getGadgetSize() { return sizeField.getText(); }
    private int getCredit() { return Integer.parseInt(creditField.getText()); }
    private int getMemory() { return Integer.parseInt(memoryField.getText()); }
    private String getPhoneNumber() { return phoneField.getText(); }
    private int getDuration() { return Integer.parseInt(durationField.getText()); }
    private int getDownloadSize() { return Integer.parseInt(downloadField.getText()); }
    

    private int getDisplayNumber() {
        int number = -1;
        try {
            number = Integer.parseInt(displayField.getText());
            if (number < 0 || number >= gadgets.size()) {
                JOptionPane.showMessageDialog(this,
                    "Invalid gadget number. Enter a number between 0 and " + (gadgets.size() - 1));
                number = -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid whole number for the display number.");
        }
        return number;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addMobileBtn) {
            Mobile m = new Mobile(
                getModel(), getPrice(), getWeight(), getGadgetSize(), getCredit()
            );
            gadgets.add(m);
            JOptionPane.showMessageDialog(this, "Mobile added.");
        }

        else if (e.getSource() == addMP3Btn) {
            MP3 mp = new MP3(
                getModel(), getPrice(), getWeight(), getGadgetSize(), getMemory()
            );
            gadgets.add(mp);
            JOptionPane.showMessageDialog(this, "MP3 added.");
        }

        else if (e.getSource() == clearBtn) {
            modelField.setText("");
            priceField.setText("");
            weightField.setText("");
            sizeField.setText("");
            creditField.setText("");
            memoryField.setText("");
            phoneField.setText("");
            durationField.setText("");
            downloadField.setText("");
            displayField.setText("");
        }

        else if (e.getSource() == displayAllBtn) {
            for (int i = 0; i < gadgets.size(); i++) {
                System.out.println("Gadget #" + i);
                gadgets.get(i).display();
                System.out.println("----------------------");
            }
        }

        else if (e.getSource() == makeCallBtn) {
            int index = getDisplayNumber();
            if (index != -1 && gadgets.get(index) instanceof Mobile) {
                Mobile m = (Mobile) gadgets.get(index);
                m.makeCall(getPhoneNumber(), getDuration());
            } else {
                JOptionPane.showMessageDialog(this, "Selected gadget is not a Mobile.");
            }
        }

        else if (e.getSource() == downloadBtn) {
            int index = getDisplayNumber();
            if (index != -1 && gadgets.get(index) instanceof MP3) {
                MP3 mp = (MP3) gadgets.get(index);
                mp.downloadMusic(getDownloadSize());
            } else {
                JOptionPane.showMessageDialog(this, "Selected gadget is not an MP3 player.");
            }
        }
    }

    public static void main(String[] args) {
        new ShopGadget();
    }
}
