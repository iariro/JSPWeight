package kumagai.weight.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 店舗情報編集アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/editmise2.jsp")
public class EditMise2Action
{
	public String id;
	public String name;
	public String address;

	/**
	 * 店舗情報編集アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("editMise2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("WeightSqlserverUrl"));

		PreparedStatement statement =
			connection.prepareStatement(
				"update mise set name=?, address=? where id=?");

		statement.setString(1, name);
		statement.setString(2, address);
		statement.setString(3, id);

		statement.executeUpdate();

		statement.close();
		connection.close();

		return "success";
	}
}
