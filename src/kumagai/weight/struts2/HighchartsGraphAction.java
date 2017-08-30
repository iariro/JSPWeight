package kumagai.weight.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import kumagai.weight.BeforeAfterWeightCollection;

/**
 * Highchartsグラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/highchartsgraph.jsp")
public class HighchartsGraphAction
{
	public String chartPoints;

	@Action("highchartsgraph")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		String url = context.getInitParameter("WeightSqlserverUrl");
		if (url != null)
		{
			// URL定義あり

			DriverManager.registerDriver(new SQLServerDriver());

			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("select date, before, after from senseki");

			BeforeAfterWeightCollection weightList = new BeforeAfterWeightCollection(results);

			results.close();
			statement.close();
			connection.close();

			chartPoints = weightList.getChartPoints();

			return "success";
		}
		else
		{
			// URL定義なし

			return "error";
		}
	}
}
