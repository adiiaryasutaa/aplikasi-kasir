package com.ceceply.aplikasikasir.GUI;

import com.ceceply.aplikasikasir.data.ItemCartData;
import com.ceceply.aplikasikasir.factory.ItemFactory;
import com.ceceply.aplikasikasir.model.Item;
import com.ceceply.aplikasikasir.util.CurrencyConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class MainGUI extends JFrame {

	private Vector<String> tableHeader;
	private TableModel itemTableModel;

	private final int ITEM_NUMBER_COLUMN      = 0;
	private final int ITEM_NAME_COLUMN        = 1;
	private final int ITEM_QUANTITY_COLUMN    = 2;
	private final int ITEM_UNIT_PRICE_COLUMN  = 3;
	private final int ITEM_TOTAL_PRICE_COLUMN = 4;

	private ComboBoxModel<Item> comboBoxModel;

	private ItemFactory itemFactory;
	private ItemCartData itemCartData;

	public MainGUI() {
		this.tableHeader = new Vector<>();
		tableHeader.addAll(Arrays.asList("No", "Nama Item", "Kuantitas", "Harga Satuan", "Harga Total"));

		this.itemFactory = new ItemFactory();
		this.itemCartData = new ItemCartData();

		this.itemTableModel = new DefaultTableModel(this.itemCartData.dataForTable(), this.tableHeader);

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
		for (Item item : this.itemFactory.getArrayList()) {
			this.itemComboBox.addItem(item);
		}

		this.comboBoxModel.setSelectedItem(null);
	}

	private void setOverallTotalPriceTextField() {
		Long currentOverallTotalPrice = CurrencyConverter.toLong(this.overallTotalPriceTextField.getText().trim());


	}

	/* ================ COMPONENTS ================ */
	private JPanel mainPanel;
	private JComboBox<Item> itemComboBox;
	private JTextField quantityTextField;
	private JTextField unitPriceTextField;
	private JTextField totalPriceTextField;
	private JButton insertButton;
	private JTable itemTable;
	private JButton resetButton;
	private JPanel formPanel;
	private JTextField overallTotalPriceTextField;
	private JButton saveAndCreateNewButton;
	private JScrollPane itemScrollPane;
}
