package pup.thesis.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This class provides basic functionalities for sql.
 * It uses the mysql connector to connect with the 
 * mysql database.
 * 
 * since 05-20-2013
 * @author paulzed
 *
 */
public class MysqlHelper extends MysqlAuth {
	
	private Connection con = null;
	private Statement st = null;
	private ResultSet set = null;
	
	//SQL Statement Strings
	//----------------------------
	private String where = "";
	private String select = "";
	private String orderBy = "";
	private String groupBy = "";
	private String from = "";
	//----------------------------
	
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean initMySql() {
		try{
			con = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
			if(!con.isValid(3)) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param order
	 */
	public void setOrderBy(String order) {
		if(this.orderBy.isEmpty()) {
			this.orderBy = order;
		} else {
			orderBy += " " + order;
		}
	}
	
	/**
	 * 
	 * @param groupings
	 */
	public void setGroupBy(String groupings) {
		if(this.groupBy.isEmpty()) {
			this.groupBy = groupings;
		} else {
			this.groupBy += " " + groupings;
		}
	}
	
	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public ResultSet selectAll(String tableName) {
		try {
			st = con.createStatement();
			set = st.executeQuery("SELECT * FROM " + tableName);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if(set != null) {
					st.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return set;
	}
	
	/**
	 * 
	 * 
	 * @param fields
	 */
	public void setSelect(String fields) {
		fields = filterQuery(fields);
		if(this.select.isEmpty()) {
			this.select = fields;
		} else {
			this.select += " " + fields;
		}
	}
	
	/**
	 * 
	 * @param statement
	 */
	public void setWhere(String statement) {
		statement = filterQuery(statement);
		if(this.where.isEmpty()) {
			this.where = statement;
		} else {
			this.where += " " + statement;
		}
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet executeQuery(String query) {
		try {
			query = filterQuery(query);
			st = con.createStatement();
			set = st.executeQuery(query);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null) {
					st.close();
				}
				if(set != null) {
					set.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return set;
	}
	
	/**
	 * 
	 * @return
	 */
	public ResultSet executeQuery() {
		try {
			String sql = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
			sql = filterQuery(sql);
			st = con.createStatement();
			set = st.executeQuery(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null) {
					st.close();
				}
				if(set != null) {
					set.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return set;
	}
	
	/**
	 * 
	 * 
	 */
	public void resetQuery() {
		select = "";
		where = "";
		orderBy = "";
		groupBy = "";
		from = "";
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	private String filterQuery(String query) {
		return query.replace("#", "");
	}
}
