import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Carnet {

    private JFrame frame;
    private JTextField textPrenom;
    private JTextField textNom;
    private JTextField textDate;
    private JTextField textNomObjet;
    private JTextField textType;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    //Création de la racine du fichier
    static Element racine = new Element("carnet");
    //Création d'un document
    static Document document = new Document(racine);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Carnet window = new Carnet();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Carnet() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel Menu = new JPanel();
        frame.getContentPane().add(Menu, "name_169756147299107");
        Menu.setLayout(null);

        JPanel AjoutPersonne = new JPanel();
        frame.getContentPane().add(AjoutPersonne, "name_169756162434425");
        AjoutPersonne.setLayout(null);

        JButton btnListPret = new JButton("List prêt");
        btnListPret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new DisplayXMLtoJTable();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });
        btnListPret.setBounds(163, 106, 117, 55);
        Menu.add(btnListPret);

        JButton btnQuit = new JButton("Quit");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnQuit.setBounds(163, 195, 117, 55);
        Menu.add(btnQuit);

        JButton btnAddPersonne = new JButton("Preter un Objet");
        btnAddPersonne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(false);
                AjoutPersonne.setVisible(true);
            }
        });
        btnAddPersonne.setBounds(24, 106, 117, 55);
        Menu.add(btnAddPersonne);

        JButton btnNewButton = new JButton("Supprimer Objet");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Remove");
            }
        });
        btnNewButton.setBounds(305, 106, 117, 55);
        Menu.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Sharing Items");
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(31, 18, 391, 27);
        Menu.add(lblNewLabel);

        textPrenom = new JTextField();
        textPrenom.setBounds(197, 40, 189, 26);
        AjoutPersonne.add(textPrenom);
        textPrenom.setColumns(10);

        textNom = new JTextField();
        textNom.setBounds(197, 78, 189, 26);
        AjoutPersonne.add(textNom);
        textNom.setColumns(10);

        textDate = new JTextField();
        textDate.setHorizontalAlignment(SwingConstants.LEFT);
        textDate.setBounds(197, 116, 189, 26);
        AjoutPersonne.add(textDate);
        textDate.setColumns(10);

        textNomObjet = new JTextField();
        textNomObjet.setBounds(197, 154, 189, 26);
        AjoutPersonne.add(textNomObjet);
        textNomObjet.setColumns(10);

        textType = new JTextField();
        textType.setBounds(197, 188, 189, 26);
        AjoutPersonne.add(textType);
        textType.setColumns(10);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddElement();
                System.out.println(textPrenom.getText());
                System.out.println(textNom.getText());
                System.out.println(textDate.getText());
                System.out.println(textNomObjet.getText());
                System.out.println(textType.getText());
            }
        });
        btnAjouter.setBounds(82, 227, 117, 45);
        AjoutPersonne.add(btnAjouter);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.setVisible(true);
                AjoutPersonne.setVisible(false);
            }
        });
        btnRetour.setBounds(244, 227, 117, 45);
        AjoutPersonne.add(btnRetour);

        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setBounds(82, 45, 61, 16);
        AjoutPersonne.add(lblPrenom);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(82, 83, 61, 16);
        AjoutPersonne.add(lblNom);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(82, 121, 61, 16);
        AjoutPersonne.add(lblDate);

        JLabel lblNomDeLobjet = new JLabel("Nom de l'objet");
        lblNomDeLobjet.setBounds(82, 159, 103, 16);
        AjoutPersonne.add(lblNomDeLobjet);

        JLabel lblTypeDeLobjet = new JLabel("Type de l'objet");
        lblTypeDeLobjet.setBounds(82, 193, 103, 16);
        AjoutPersonne.add(lblTypeDeLobjet);

        JLabel lblNouveauPrt = new JLabel("Nouveau prêt");
        lblNouveauPrt.setForeground(Color.BLUE);
        lblNouveauPrt.setHorizontalAlignment(SwingConstants.CENTER);
        lblNouveauPrt.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNouveauPrt.setBounds(6, 12, 438, 16);
        AjoutPersonne.add(lblNouveauPrt);

        JButton btnNewButton_1 = new JButton("+");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddCurrentDate();
            }
        });
        btnNewButton_1.setBounds(381, 116, 44, 29);
        AjoutPersonne.add(btnNewButton_1);
    }

    public void AddCurrentDate() {
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        textDate.setText(dateFormat.format(date));
    }

   /* public void RemoveElement() {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("carnet_pret.xml");

        Document doc = (Document) builder.build(xmlFile);
        Element rootNode = doc.getRootElement();
        System.out.println(rootNode);

        // remove firstname element
        rootNode.removeChild("personne");

        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileWriter("carnet_pret.xml"));
    }*/

    public void AddElement() {

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File("carnet_pret.xml");

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();
            //System.out.println(rootNode);

            Element personne = new Element("personne");
            rootNode.addContent(personne);

            Attribute classe = new Attribute("type", "ami");
            personne.setAttribute(classe);

            Element nom = new Element("nom");
            nom.setText(textNom.getText());
            personne.addContent(nom);

            Element prenom = new Element("prenom");
            prenom.setText(textPrenom.getText());
            personne.addContent(prenom);

            Element Date = new Element("date");
            Date.setText(textDate.getText());// Add Content text field "Objet"
            personne.addContent(Date);

            // Création balise "items"
            Element items = new Element("items");
            personne.addContent(items);

            Element item = new Element("objet");
            item.setText(textNomObjet.getText());// Add Content text field "Objet"
            items.addContent(item);

            Element typeObjet = new Element("type_objet");
            typeObjet.setText(textType.getText());// Add Content text field "Objet"
            personne.addContent(typeObjet);

            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("carnet_pret.xml"));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        affiche();
    }

    static void affiche() {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {}
    }

    static void enregistre(String fichier) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {}
    }
}