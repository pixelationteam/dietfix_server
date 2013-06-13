/**
 * This class is generated by jOOQ
 */
package assets.sr25food.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.1"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SrcCdRecord extends org.jooq.impl.UpdatableRecordImpl<assets.sr25food.tables.records.SrcCdRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -1961135046;

	/**
	 * Setter for <code>sr25food.src_cd.Src_Cd</code>. 
	 */
	public void setSrcCd(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>sr25food.src_cd.Src_Cd</code>. 
	 */
	public java.lang.String getSrcCd() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>sr25food.src_cd.SrcCd_Desc</code>. 
	 */
	public void setSrccdDesc(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>sr25food.src_cd.SrcCd_Desc</code>. 
	 */
	public java.lang.String getSrccdDesc() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.String> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return assets.sr25food.tables.SrcCd.SRC_CD.SRC_CD_;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return assets.sr25food.tables.SrcCd.SRC_CD.SRCCD_DESC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getSrcCd();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getSrccdDesc();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SrcCdRecord
	 */
	public SrcCdRecord() {
		super(assets.sr25food.tables.SrcCd.SRC_CD);
	}
}