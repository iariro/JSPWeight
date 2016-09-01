package kumagai.weight;

import java.sql.*;

/**
 * 店舗情報１。
 * @author kumagai
 */
public class Mise1
{
	private final int id;
	private final String name;

	/**
	 * DBレコード内容から店舗情報を構築する。
	 * @param results レコードオブジェクト
	 * @throws SQLException
	 */
	public Mise1(ResultSet results)
		throws SQLException
	{
		this.id = results.getInt("id");
		this.name = results.getString("name");
	}

	/**
	 * 店舗IDを取得。
	 * @return 店舗ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * 店舗名を取得。
	 * @return 店舗名
	 */
	public String getName()
	{
		return name;
	}
}
