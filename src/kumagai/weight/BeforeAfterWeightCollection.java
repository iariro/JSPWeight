package kumagai.weight;

import java.sql.*;
import java.util.*;
import com.microsoft.sqlserver.jdbc.*;

/**
 * 戦績情報のコレクション。
 * @author kumagai
 */
public class BeforeAfterWeightCollection
	extends ArrayList<BeforeAfterWeight>
{
	/**
	 * テストプログラム。
	 * @param args 未使用
	 * @throws SQLException
	 */
	public static void main(String[] args)
		throws SQLException
	{
		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection = DriverManager.getConnection
			("jdbc:sqlserver://localhost:2144;DatabaseName=Weight;User=sa;Password=p@ssw0rd;");

		Statement statement = connection.createStatement();

		ResultSet results =
			statement.executeQuery(
				"select date, before, after from senseki");

		BeforeAfterWeightCollection list =
			new BeforeAfterWeightCollection(results);

		for (BeforeAfterWeight data : list)
		{
			System.out.println(data);
		}

		results.close();
		statement.close();
		connection.close();
	}

	/**
	 * DB取得値からオブジェクトを構築する。
	 * @param results DB取得レコード群
	 * @throws SQLException
	 */
	public BeforeAfterWeightCollection(ResultSet results)
		throws SQLException
	{
		while (results.next())
		{
			add(new BeforeAfterWeight(results));
		}
	}
}
