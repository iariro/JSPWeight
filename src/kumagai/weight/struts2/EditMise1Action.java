package kumagai.weight.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 店舗情報編集ページ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/editmise1.jsp")
public class EditMise1Action
{
	public String id;
	public String name;
	public String address;

	/**
	 * 店舗情報編集ページ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("editMise1")
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
				"select name, address from mise where id=?");

		statement.setString(1, id);

		ResultSet results = statement.executeQuery();

		if (results.next())
		{
			// レコード取得成功。

			name = results.getString("name");
			address = results.getString("address");
		}

		results.close();
		statement.close();
		connection.close();

		return "success";
	}
}
