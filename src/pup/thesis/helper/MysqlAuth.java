package pup.thesis.helper;

/**
 * An abstract class used to encapsulate
 * the information needed to connect
 * in the mysql database.
 * 
 * @author paulzed
 *
 */
abstract class MysqlAuth {
	
	private String url = "jdbc:mysql://localhost:8080/food";
	private String username = "paulzed";
	private String password = "march141994";
	
	protected String getUrl() {
		return url;
	}
	
	protected String getUsername() {
		return username;
	}
	
	protected String getPassword() {
		return password;
	}
	
	protected void setUsername(String username) {
		this.username = username;
	}
	
	protected void setPassword(String password) {
		this.password = password;
	}
	
	protected void setUrl(String url) {
		this.url = url;
	}
	
}
