package kumagai.weight.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.weight.*;

/**
 * 店舗リスト表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/listmise.jsp")
public class ListMiseAction
{
	public String order;
	public ArrayList<Mise2> miseCollection;

	/**
	 * 店舗リスト表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("listMise")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("WeightSqlserverUrl"));

		Statement statement = connection.createStatement();

		ResultSet result =
			statement.executeQuery
				("select id, name, address from mise order by " + order);

		miseCollection = new ArrayList<Mise2>();

		while (result.next())
		{
			miseCollection.add(new Mise2(result));
		}

		result.close();
		statement.close();
		connection.close();

		return "success";
	}
}
