import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Irishka on 17.04.2018.
 */
public class Details extends JFrame implements ActionListener {

    int id_doc;

    private JButton buttonOK = new JButton("OK");

    private JTable detailsTable;

    private Container container;

    public Details(int id_doc) {
        this.setVisible(true);
        this.id_doc = id_doc;
        this.setBounds(100, 100, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        container.setLayout(new GridLayout(2, 1, 5, 5));

        String[] columns = {"ID_Detail", "ID_Document", "Code", "Summ", "ID_Download"};
        Data data = new Data();
        String[][] rows = data.getRows("SELECT * " + "FROM base_DOCUMENTDETAILS " + "WHERE ID_HEADER = " + id_doc);

        detailsTable = new JTable(rows, columns);

        detailsTable.getTableHeader().setVisible(true);
        container.add(new JScrollPane(detailsTable));
        container.add(buttonOK);

        buttonOK.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

}
