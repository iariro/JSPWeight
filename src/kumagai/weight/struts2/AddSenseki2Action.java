package kumagai.weight.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 戦績追加アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/addsenseki2.jsp")
public class AddSenseki2Action
{
	public String date;
	public String miseId;
	public String before;
	public String after;

	/**
	 * 戦績追加アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("addSenseki2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("WeightSqlserverUrl"));

		PreparedStatement statement =
			connection.prepareStatement
				("insert into senseki values (?, ?, ?, ?)");

		statement.setString(1, date);
		statement.setString(2, miseId);
		statement.setString(3, before);
		statement.setString(4, after);

		statement.executeUpdate();

		statement.close();
		connection.close();

		return "success";
	}
}
