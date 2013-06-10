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
public class Footnote extends org.jooq.impl.TableImpl<assets.sr25food.tables.records.FootnoteRecord> {

	private static final long serialVersionUID = -1018167663;

	/**
	 * The singleton instance of <code>sr25food.footnote</code>
	 */
	public static final assets.sr25food.tables.Footnote FOOTNOTE = new assets.sr25food.tables.Footnote();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<assets.sr25food.tables.records.FootnoteRecord> getRecordType() {
		return assets.sr25food.tables.records.FootnoteRecord.class;
	}

	/**
	 * The column <code>sr25food.footnote.NDB_NO</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.FootnoteRecord, java.lang.String> NDB_NO = createField("NDB_NO", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>sr25food.footnote.Footnt_No</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.FootnoteRecord, java.lang.String> FOOTNT_NO = createField("Footnt_No", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>sr25food.footnote.Footnot_Typ</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.FootnoteRecord, java.lang.String> FOOTNOT_TYP = createField("Footnot_Typ", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>sr25food.footnote.Nutr_No</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.FootnoteRecord, java.lang.String> NUTR_NO = createField("Nutr_No", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * The column <code>sr25food.footnote.Footnot_Txt</code>. 
	 */
	public final org.jooq.TableField<assets.sr25food.tables.records.FootnoteRecord, java.lang.String> FOOTNOT_TXT = createField("Footnot_Txt", org.jooq.impl.SQLDataType.VARCHAR.length(255), this);

	/**
	 * Create a <code>sr25food.footnote</code> table reference
	 */
	public Footnote() {
		super("footnote", assets.sr25food.Sr25food.SR25FOOD);
	}

	/**
	 * Create an aliased <code>sr25food.footnote</code> table reference
	 */
	public Footnote(java.lang.String alias) {
		super(alias, assets.sr25food.Sr25food.SR25FOOD, assets.sr25food.tables.Footnote.FOOTNOTE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public assets.sr25food.tables.Footnote as(java.lang.String alias) {
		return new assets.sr25food.tables.Footnote(alias);
	}
}
