package kumagai.weight.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import kumagai.weight.BeforeAfterWeightCollection;
import kumagai.weight.WeightGraphDocument;

/**
 * グラフ表示アクション基底部。
 * @author kumagai
 */
public abstract class GraphAction
{
	protected WeightGraphDocument document;

	/**
	 * グラフ表示用に戦績データを取得する。
	 */
	static public BeforeAfterWeightCollection getBeforeAfterWeightCollection(Integer range)
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();
		String url = context.getInitParameter("WeightSqlserverUrl");
		if (url != null)
		{
			// URL定義あり

			DriverManager.registerDriver(new SQLServerDriver());

			Connection connection = DriverManager.getConnection(url);

			String sql = "select date, before, after from senseki";
			if (range != null)
			{
				sql += " where datediff(day, date, getdate()) <= ?";
			}
			PreparedStatement statement = connection.prepareStatement(sql);
			if (range != null)
			{
				statement.setInt(1, range);
			}
			ResultSet results = statement.executeQuery();
			BeforeAfterWeightCollection list = new BeforeAfterWeightCollection(results);

			results.close();
			statement.close();
			connection.close();

			return list;
		}
		else
		{
			// URL定義なし

			return null;
		}
	}
}
