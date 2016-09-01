package kumagai.weight.struts2;

import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.weight.*;

/**
 * 戦績追加ページ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Results
({
	@Result(name="success", location="/weight/addsenseki1.jsp"),
	@Result(name="error", location="/weight/error.jsp")
})
public class AddSenseki1Action
{
	static private final SimpleDateFormat formatDate = new SimpleDateFormat();

	/**
	 * 日付文字列フォーマットオブジェクト初期化。
	 */
	static
	{
		formatDate.applyPattern("yyyy/MM/dd");
	}

	public ArrayList<Mise1> miseCollection;
	public String message;

	/**
	 * 今日の日付を取得。
	 * @return 今日の日付
	 */
	public String getToday()
	{
		Calendar today = Calendar.getInstance();

		return formatDate.format(today.getTime());
	}

	/**
	 * 戦績追加ページ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("addSenseki1")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		try
		{
			Connection connection =
				DriverManager.getConnection
					(context.getInitParameter("WeightSqlserverUrl"));

			Statement statement = connection.createStatement();

			ResultSet results =
				statement.executeQuery("select id, name from mise order by name");

			miseCollection = new ArrayList<Mise1>();

			while (results.next())
			{
				miseCollection.add(new Mise1(results));
			}

			results.close();
			statement.close();
			connection.close();

			return "success";
		}
		catch (Exception exception)
		{
			message = exception.toString();

			return "error";
		}
	}
}
