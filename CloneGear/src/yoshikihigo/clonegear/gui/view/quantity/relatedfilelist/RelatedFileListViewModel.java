package yoshikihigo.clonegear.gui.view.quantity.relatedfilelist;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import yoshikihigo.clonegear.gui.data.file.GUIFile;
import yoshikihigo.clonegear.gui.util.RNRValue;
import yoshikihigo.clonegear.gui.view.quantity.QuantitativeViewInterface;

public class RelatedFileListViewModel extends AbstractTableModel implements
		QuantitativeViewInterface {

	static final int COL_NAME = 0;
	static final int COL_LOC = 1;
	static final int COL_NOC = 2;
	static final int COL_ROC = 3;
	static final int COL_NOF = 4;

	static final String[] TITLES = new String[] { "File Name", "LOC(f)",
			"NOC(f)", "ROC(f)", "NOF(f)" };

	final public GUIFile taret;
	final private List<GUIFile> files;

	public RelatedFileListViewModel(final GUIFile target,
			final List<GUIFile> files) {
		this.taret = target;
		this.files = files;
	}

	@Override
	public int getRowCount() {
		return this.files.size();
	}

	@Override
	public int getColumnCount() {
		return TITLES.length;
	}

	@Override
	public Object getValueAt(final int row, final int col) {

		final int threshold = RNRValue.getInstance(RNR).get();

		switch (col) {
		case COL_NAME:
			return this.files.get(row).getFileName();
		case COL_LOC:
			return Integer.valueOf(this.files.get(row).loc);
		case COL_NOC:
			return Integer.valueOf(this.files.get(row).getNOCwith(this.taret,
					threshold));
		case COL_ROC:
			return Double.valueOf(this.files.get(row).getROCwith(this.taret,
					threshold));
		case COL_NOF:
			return Integer.valueOf(this.files.get(row).getNOF(threshold));
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(final int col) {
		switch (col) {
		case COL_NAME:
			return String.class;
		case COL_LOC:
		case COL_NOC:
			return Integer.class;
		case COL_ROC:
			return Double.class;
		case COL_NOF:
			return Integer.class;
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(final int col) {
		return TITLES[col];
	}

	public List<GUIFile> getFiles() {
		return new ArrayList<GUIFile>(this.files);
	}
}
