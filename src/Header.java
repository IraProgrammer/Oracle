import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Irishka on 17.04.2018.
 */
public class Header extends JFrame implements ActionListener {

    public static void main(String[] args) {
        Header header = new Header();
        header.setVisible(true);
    }

    private JButton buttonFind = new JButton("Найти по коду документа");

    /* Вводить либо одно число (например, 123), либо диапазон (200-500),
       либо максимальное (<300)/минимальное (>300) значение, затем нажать кнопку "Найти по коду документа"*/
    private JTextField textCode = new JTextField();
    private JButton buttonSortDate = new JButton("Сортировать по дате");;
    private JButton buttonSortNumber = new JButton("Сортировать по номеру");

    private JTable headerTable;
    private DefaultTableModel model;
    private Container container;

    private String sqlWhere = "";
    private String sqlOrderby = "ORDER BY ID_DOCUMENT";

    public Header() {
        super("Header");
          this.setBounds(100, 100, 700, 600);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          container = this.getContentPane();
          container.setLayout(new GridLayout(5, 1, 5, 5));

        buttonFind.setActionCommand("Find");
        buttonSortDate.setActionCommand("SortDate");
        buttonSortNumber.setActionCommand("SortNumber");

        container.add(buttonFind);
        container.add(textCode);

        container.add(buttonSortDate);
        container.add(buttonSortNumber);

        createModel();
        headerTable = new JTable(model);
        headerTable.getColumnModel().getColumn(3).setPreferredWidth(130);

        headerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable obj = (JTable) e.getSource();
                    int id_doc = Integer.parseInt(headerTable.getValueAt(obj.getSelectedRow(), 0).toString());
                    new Details(id_doc);
                }
            }
        });

        headerTable.getTableHeader().setVisible(true);
        container.add(new JScrollPane(headerTable));

        buttonFind.addActionListener(this);
        buttonSortNumber.addActionListener(this);
        buttonSortDate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        CreateSQL sql = new CreateSQL();
        if (e.getActionCommand().equals("Find")) {
            String text = textCode.getText();
            sqlWhere = sql.createWhere(text);

        } else if (e.getActionCommand().equals("SortDate")) {
            sqlOrderby = sql.createOrderByDate();

        } else if (e.getActionCommand().equals("SortNumber")) {
            sqlOrderby = sql.createOrderByNumber();
        }

        updateTable();
        headerTable.repaint();
    }

    private void createModel(){
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("ID_Document");
        model.addColumn("Number");
        model.addColumn("Code");
        model.addColumn("DateCreate");
        model.addColumn("Username");
        model.addColumn("ID_Download");

        updateTable();
    }

    private void updateTable() {

        model.setRowCount(0);

        Data data = new Data();
        String[][] rows = data.getRows("SELECT * " + "FROM base_DOCUMENTHEADER " + sqlWhere + sqlOrderby);

        for (int i = 0; i < rows.length; i++) {
            model.addRow(rows[i]);
        }

        headerTable = new JTable(model);
        headerTable.getColumnModel().getColumn(3).setPreferredWidth(130);
    }
}
