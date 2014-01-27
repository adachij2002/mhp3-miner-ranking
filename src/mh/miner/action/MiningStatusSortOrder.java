package mh.miner.action;

public class MiningStatusSortOrder extends SortOrder {

	class Column {
		public static final String CHECKED = "a.checked";
		public static final String SEED = "b.seed";
		public static final String SLOT_NUM = "b.slot_num";
		public static final String SKILL1_NAME = "c.name";
		public static final String SKILL1_POINT = "c.point";
		public static final String SKILL2_NAME = "d.name";
		public static final String SKILL2_POINT = "d.point";
		public static final String MINE = "e.id";
		public static final String AMULET_LEVEL = "f.id";
		public static final String AMULET_TYPE = "g.id";
	}

	public MiningStatusSortOrder(String column, SortType type) {
		super(column, type);
	}
	
	public static boolean isSortOrderColumn(String column) {
		if(column != null) {
			return column.equals(Column.CHECKED)
				|| column.equals(Column.SEED)
				|| column.equals(Column.SLOT_NUM)
				|| column.equals(Column.SKILL1_NAME)
				|| column.equals(Column.SKILL1_POINT)
				|| column.equals(Column.SKILL2_NAME)
				|| column.equals(Column.SKILL2_POINT)
				|| column.equals(Column.MINE)
				|| column.equals(Column.AMULET_LEVEL)
				|| column.equals(Column.AMULET_TYPE);
		}
		return false;
	}
}
