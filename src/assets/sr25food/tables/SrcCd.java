/**
 * This class is generated by jOOQ
 */
package assets.sr25food.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.1"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SrcCd extends org.jooq.impl.TableImpl<assets.sr25food.tables.records.SrcCdRecord> {

	private static final long serialVersionUID = -192842691;

	/**
	 * The singleton instance of <code>sr25food.src_cd</code>
	 */
	public static final assets.sr25food.tables.SrcCd SRC_CD = new assets.sr25food.tables.SrcCd();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<assets.sr25food.tables.records.SrcCdRecord> getRecordType() {
		return assets.sr25food.tables.records.SrcCdRecord.class;
	}

	/**
	 * The column <code>sr25food.src_cd.Src_Cd</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.SrcCdRecord, java.lang.String> SRC_CD_ = createField("Src_Cd", org.jooq.impl.SQLDataType.VARCHAR.length(2), this);

	/**
	 * The column <code>sr25food.src_cd.SrcCd_Desc</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.SrcCdRecord, java.lang.String> SRCCD_DESC = createField("SrcCd_Desc", org.jooq.impl.SQLDataType.VARCHAR.length(60), this);

	/**
	 * Create a <code>sr25food.src_cd</code> table reference
	 */
	public SrcCd() {
		super("src_cd", assets.sr25food.Sr25food.SR25FOOD);
	}

	/**
	 * Create an aliased <code>sr25food.src_cd</code> table reference
	 */
	public SrcCd(java.lang.String alias) {
		super(alias, assets.sr25food.Sr25food.SR25FOOD, assets.sr25food.tables.SrcCd.SRC_CD);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<assets.sr25food.tables.records.SrcCdRecord> getPrimaryKey() {
		return assets.sr25food.Keys.KEY_SRC_CD_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<assets.sr25food.tables.records.SrcCdRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<assets.sr25food.tables.records.SrcCdRecord>>asList(assets.sr25food.Keys.KEY_SRC_CD_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public assets.sr25food.tables.SrcCd as(java.lang.String alias) {
		return new assets.sr25food.tables.SrcCd(alias);
	}
}