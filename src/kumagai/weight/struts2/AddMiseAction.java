package kumagai.weight.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 店舗追加アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/addmise2.jsp")
public class AddMiseAction
{
	public int newid;
	public String name;
	public String address;

	/**
	 * 店舗追加アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("addMise")
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
				"insert into mise (name, address) values (?, ?)",
				Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, name);
		statement.setString(2, address);

		statement.executeUpdate();

		ResultSet keys = statement.getGeneratedKeys();

		if (keys.next())
		{
			// 取得成功。

			newid = keys.getInt(1);
		}

		keys.close();
		statement.close();
		connection.close();

		return "success";
	}
}
