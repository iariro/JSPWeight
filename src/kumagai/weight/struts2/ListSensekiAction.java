package kumagai.weight.struts2;

import java.util.*;
import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.weight.*;

/**
 * 戦績リスト表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/listsenseki.jsp")
public class ListSensekiAction
{
	public ArrayList<Raiten> raitenCollection;

	/**
	 * 来店情報数を取得。
	 * @return 来店情報数
	 */
	public int getCount()
	{
		return raitenCollection.size();
	}

	/**
	 * 戦績リスト表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("listSenseki")
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
			statement.executeQuery(
				"select date, name, before, after from senseki join mise on senseki.mise=mise.id order by date asc");

		raitenCollection = new ArrayList<Raiten>();

		Raiten raiten2 = null;

		while (result.next())
		{
			Raiten raiten = new Raiten(result, raiten2);

			raitenCollection.add(raiten);

			raiten2 = raiten;
		}

		connection.close();

		Collections.sort(
			raitenCollection,
			new Comparator<Raiten>()
			{
				public int compare(Raiten a, Raiten b)
				{
					return - a.getDate().compareTo(b.getDate());
				}
			});

		return "success";
	}
}
