package com.ceceply.aplikasikasir.GUI;

import com.ceceply.aplikasikasir.model.Item;
import com.ceceply.aplikasikasir.model.ItemCart;
import com.ceceply.aplikasikasir.util.CurrencyConverter;
import com.ceceply.aplikasikasir.util.ItemList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 *
 */
public class MainGUI extends JFrame {

	private Vector<String> tableHeader;
	private DefaultTableModel itemTableModel;
	private Vector<Vector<Object>> tableData;

	private final int ITEM_NUMBER_COLUMN      = 0;
	private final int ITEM_NAME_COLUMN        = 1;
	private final int ITEM_QUANTITY_COLUMN    = 2;
	private final int ITEM_UNIT_PRICE_COLUMN  = 3;
	private final int ITEM_TOTAL_PRICE_COLUMN = 4;

	/**
	 *
	 *
	 */
	public MainGUI() {
		this.tableHeader = new Vector<>();
		this.tableData = new Vector<>();
		this.itemTableModel = new DefaultTableModel(this.tableData, this.tableHeader);

		this.initComponent();
	}

	/**
	 *
	 *
	 */
	private void initComponent() {
//		Setting up components
		this.setUpItemTable();
		this.setUpItemComboBox();

//		Resetting values
		this.resetSelectedItem();
		this.resetTotalPrice();

//		Add listeners
		this.itemComboBox.addItemListener(this::itemComboBoxItemListener);
		this.quantityTextField.addActionListener(this::quantityListener);
		this.quantityTextField.addInputMethodListener(new InputMethodListener() {
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {
				quantityListener(null);
			}

			@Override
			public void caretPositionChanged(InputMethodEvent event) {

			}
		});
		this.insertButton.addActionListener(this::insertButtonActionListener);
		this.resetButton.addActionListener(this::resetButtonActionListener);
		this.itemTableModel.addTableModelListener(this::tableModelListener);
		this.createNewButton.addActionListener(this::createNewButtonActionListener);

		this.disableIfItemNotSelected();

//		Preparing frame
		this.setContentPane(this.mainPanel);
		this.pack();
	}

	/**
	 *
	 *
	 */
	private void disableIfItemNotSelected() {
		if (this.getSelectedItem() == null) {
			this.quantityTextField.setEditable(false);
			this.insertButton.setEnabled(false);
			this.resetButton.setEnabled(false);
		} else {
			this.quantityTextField.setEditable(true);
			this.insertButton.setEnabled(true);
			this.resetButton.setEnabled(true);
		}
	}

	/**
	 *
	 *
	 */
	private void setUpItemTable() {
		this.tableHeader.addAll(Arrays.asList("No", "Nama Item", "Kuantitas", "Harga Satuan", "Harga Total"));

		this.itemTable.setModel(this.itemTableModel);
		this.itemTable.setAutoCreateColumnsFromModel(false);
		this.itemTable.getTableHeader().setReorderingAllowed(false);
		this.itemTable.getTableHeader().setFont(new Font("Droid Sans", Font.BOLD, 12));

		TableColumnModel tableColumnModel = this.itemTable.getColumnModel();

		DefaultTableCellRenderer centerRendered = new DefaultTableCellRenderer();
		centerRendered.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer rightRendered = new DefaultTableCellRenderer();
		rightRendered.setHorizontalAlignment(JLabel.RIGHT);

		tableColumnModel.getColumn(ITEM_NUMBER_COLUMN).setMaxWidth(50);
		tableColumnModel.getColumn(ITEM_NUMBER_COLUMN).setMinWidth(50);
		tableColumnModel.getColumn(ITEM_NUMBER_COLUMN).setCellRenderer(centerRendered);

		tableColumnModel.getColumn(ITEM_NAME_COLUMN).setMinWidth(200);

		tableColumnModel.getColumn(ITEM_QUANTITY_COLUMN).setCellRenderer(rightRendered);

		tableColumnModel.getColumn(ITEM_UNIT_PRICE_COLUMN).setCellRenderer(rightRendered);

		tableColumnModel.getColumn(ITEM_TOTAL_PRICE_COLUMN).setCellRenderer(rightRendered);
	}

	/**
	 *
	 *
	 */
	private void setUpItemComboBox() {
//		Add items to combo box
		for (Item item : new ItemList()) {
			this.itemComboBox.addItem(item);
		}
	}

	/**
	 *
	 *
	 */
	private void resetSelectedItem() {
		this.itemComboBox.getModel().setSelectedItem(null);
	}

	/**
	 *
	 *
	 */
	private void resetQuantity() {
		this.quantityTextField.setText("1");
	}

	/**
	 *
	 *
	 */
	private void resetUnitPrice() {
		this.unitPriceTextField.setText(CurrencyConverter.toCurrency(0L));
	}

	/**
	 *
	 *
	 */
	private void resetTotalPrice() {
		this.totalPriceTextField.setText(CurrencyConverter.toCurrency(0L));
	}

	/**
	 * Get selected item from Item Combo Box
	 * @return Item
	 */
	public Item getSelectedItem() {
		Object a = this.itemComboBox.getSelectedItem();
		return a == null ? null : (Item) a ;
	}

	/**
	 *
	 *
	 */
	private void insertButtonActionListener(ActionEvent e) {
		ItemCart itemCart = new ItemCart(this.tableData.size() + 1, this.getSelectedItem(), Integer.parseInt(this.quantityTextField.getText().trim()));
		this.insertOrAddQuantityToTable(itemCart);
		this.resetButtonActionListener(e);
	}

	/**
	 *
	 *
	 */
	private void itemComboBoxItemListener(ItemEvent itemEvent) {
		Item selectedItem = this.getSelectedItem();
		Integer quantity = Integer.valueOf(this.quantityTextField.getText().trim());

		this.disableIfItemNotSelected();

		if (selectedItem == null) {
			this.resetQuantity();
			this.resetUnitPrice();
		} else {
			this.totalPriceTextField.setText(CurrencyConverter.toCurrency(selectedItem.getPrice() * quantity));
			this.unitPriceTextField.setText(CurrencyConverter.toCurrency(selectedItem.getPrice()));
		}
	}

	/**
	 *
	 *
	 */
	private void resetButtonActionListener(ActionEvent e) {
		this.resetSelectedItem();
		this.resetTotalPrice();
	}

	/**
	 *
	 *
	 */
	private void quantityListener(ActionEvent e) {
		String lastValue = this.quantityTextField.getText().trim();
		Item selectedItem = (Item) this.itemComboBox.getSelectedItem();
		Integer quantity = null;

		assert selectedItem != null;
		try {
			quantity = Integer.parseInt(lastValue);

			if (quantity <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException exception) {
			quantity = 1;

			JOptionPane.showMessageDialog(this, "Masukan kuantitas yang valid", "Kuantitas tidak valid", JOptionPane.ERROR_MESSAGE);

			this.quantityTextField.setText("1");
		} finally {
			this.totalPriceTextField.setText(CurrencyConverter.toCurrency(selectedItem.getPrice() * quantity));
		}
	}

	/**
	 *
	 * @param itemCart
	 */
	private void insertOrAddQuantityToTable(ItemCart itemCart) {
		Vector<Vector> itemsOnTable = this.itemTableModel.getDataVector();
		int itemsOnTableSize = itemsOnTable.size();

		if (itemsOnTableSize > 0) {
			for (int i = 0; i < itemsOnTableSize; i++) {
				if (itemsOnTable.get(i).get(ITEM_NAME_COLUMN).equals(itemCart.getItem().getName())) {
					Integer newQuantity = ((Integer) this.itemTableModel.getValueAt(i, ITEM_QUANTITY_COLUMN)) + itemCart.getQuantity();
					this.itemTableModel.setValueAt(newQuantity, i, ITEM_QUANTITY_COLUMN);

					Long newTotalPrice = CurrencyConverter.toLong(this.itemTableModel.getValueAt(i, ITEM_TOTAL_PRICE_COLUMN).toString()) + itemCart.getTotalPrice();
					this.itemTableModel.setValueAt(CurrencyConverter.toCurrency(newTotalPrice), i, ITEM_TOTAL_PRICE_COLUMN);

					return;
				}
			}
		}

		this.itemTableModel.addRow(itemCart.toVector());
	}

	/**
	 *
	 * @param event
	 */
	private void tableModelListener(TableModelEvent event) {
		Vector<Vector> datas = this.itemTableModel.getDataVector();

		ArrayList<String> totalPriceList = new ArrayList<>();

		for (Vector data : datas) {
			totalPriceList.add((String) data.get(ITEM_TOTAL_PRICE_COLUMN));
		}
		this.overallTotalPriceTextField.setText(CurrencyConverter.totalFromCurrencyToCurrency(totalPriceList));
	}

	/**
	 *
	 *
	 */
	private void createNewButtonActionListener(ActionEvent e) {
		this.resetButtonActionListener(e);
		this.itemTableModel.setRowCount(0);
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
	private JButton createNewButton;
	private JScrollPane itemScrollPane;
}
