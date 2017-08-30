package kumagai.weight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

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
	
	/**
	 * デバッグ用コンストラクタ
	 */
	public BeforeAfterWeightCollection()
	{
		// 何もしない
	}
	
	/**
	 * Highcharts用に座標配列を生成
	 * @return
	 */
	public String getChartPoints()
	{
		StringBuffer chartPoints = new StringBuffer();

		for (int i=0 ; i<2 ; i++)
		{
			if (i > 0)
			{
				// 2nd onward

				chartPoints.append(",");
			}

			chartPoints.append(String.format("{name: '%s',data: [", i == 0 ? "before" : "after"));
			for (int j=0 ; j<size() ; j++)
			{
				if (j > 0)
				{
					// 2nd onward
	
					chartPoints.append(",");
				}

				chartPoints.append(
					String.format(
						"[%s, %s]",
						get(j).date.getTime(),
						i == 0 ? get(j).before : get(j).after));
			}
			chartPoints.append("]}");
		}
		return chartPoints.toString();
	}
}
