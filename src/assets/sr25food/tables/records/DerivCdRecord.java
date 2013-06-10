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
public class DerivCdRecord extends org.jooq.impl.UpdatableRecordImpl<assets.sr25food.tables.records.DerivCdRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 1871077042;

	/**
	 * Setter for <code>sr25food.deriv_cd.Deriv_CD</code>. 
	 */
	public void setDerivCd(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>sr25food.deriv_cd.Deriv_CD</code>. 
	 */
	public java.lang.String getDerivCd() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>sr25food.deriv_cd.Deriv_Desc</code>. 
	 */
	public void setDerivDesc(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>sr25food.deriv_cd.Deriv_Desc</code>. 
	 */
	public java.lang.String getDerivDesc() {
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
		return assets.sr25food.tables.DerivCd.DERIV_CD.DERIV_CD_;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return assets.sr25food.tables.DerivCd.DERIV_CD.DERIV_DESC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getDerivCd();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getDerivDesc();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DerivCdRecord
	 */
	public DerivCdRecord() {
		super(assets.sr25food.tables.DerivCd.DERIV_CD);
	}
}
