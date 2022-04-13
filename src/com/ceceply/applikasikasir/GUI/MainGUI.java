package com.ceceply.applikasikasir.GUI;

import com.ceceply.applikasikasir.model.Item;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class MainGUI extends JFrame {

	private Object[] itemTableHeader;
	private TableModel itemTableModel;

	private final int ITEM_NUMBER_COLUMN      = 0;
	private final int ITEM_NAME_COLUMN        = 1;
	private final int ITEM_QUANTITY_COLUMN    = 2;
	private final int ITEM_UNIT_PRICE_COLUMN  = 3;
	private final int ITEM_TOTAL_PRICE_COLUMN = 4;

	private ComboBoxModel<Item> comboBoxModel;

	public MainGUI() {
		this.itemTableHeader = new String[] {"No", "Nama Item", "Kuantitas", "Harga Satuan", "Harga Total"};
		this.itemTableModel = new DefaultTableModel(
			new Object[][] {{1, "Minyak", 10, "Rp 10.000", "Rp 100.000"}, {2, "Tissue", "2", "Rp 5.000", "Rp 10.000"}}, this.itemTableHeader);
		this.comboBoxModel = new DefaultComboBoxModel<>();

		this.initComponent();
	}

	private void initComponent() {
		this.setUpItemTable();
		this.setUpItemComboBox();

		this.setContentPane(this.mainPanel);
		this.pack();
	}

	private void setUpItemTable() {
		this.itemTable.setModel(this.itemTableModel);
		this.itemTable.getTableHeader().setReorderingAllowed(false);
		this.itemTable.getTableHeader().setFont(new Font("Droid Sans", Font.BOLD, 12));

		TableColumnModel tableColumnModel = this.itemTable.getColumnModel();

		DefaultTableCellRenderer centerRendered = new DefaultTableCellRenderer();
		centerRendered.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer rightRendered = new DefaultTableCellRenderer();
		rightRendered.setHorizontalAlignment(JLabel.RIGHT);

		tableColumnModel.getColumn(ITEM_NUMBER_COLUMN).setMaxWidth(30);
		tableColumnModel.getColumn(ITEM_NUMBER_COLUMN).setMinWidth(30);
		tableColumnModel.getColumn(ITEM_NUMBER_COLUMN).setCellRenderer(centerRendered);

		tableColumnModel.getColumn(ITEM_NAME_COLUMN).setMinWidth(200);

		tableColumnModel.getColumn(ITEM_QUANTITY_COLUMN).setCellRenderer(rightRendered);

		tableColumnModel.getColumn(ITEM_UNIT_PRICE_COLUMN).setCellRenderer(rightRendered);

		tableColumnModel.getColumn(ITEM_TOTAL_PRICE_COLUMN).setCellRenderer(rightRendered);
	}

	private void setUpItemComboBox() {
		this.itemComboBox.addItem(new Item("Item.Minyak", "Minyak", 10_000.0));
		this.itemComboBox.addItem(new Item("Item.Beras", "Beras 10Kg", 60_000.0));

		this.comboBoxModel.setSelectedItem(null);
	}

	/* ================ COMPONENTS ================ */
	private JPanel mainPanel;
	private JComboBox itemComboBox;
	private JTextField quantityTextField;
	private JTextField unitPriceTextField;
	private JTextField totalPriceTextField;
	private JButton insertButton;
	private JTable itemTable;
	private JButton resetButton;
	private JPanel formPanel;
	private JTextField totalOverallPriceTextField;
	private JButton saveAndCreateNewButton;
	private JScrollPane itemScrollPane;
}
