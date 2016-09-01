package kumagai.weight;

import java.sql.*;

/**
 * 店舗情報２。
 * @author kumagai
 */
public class Mise2
{
	private final int id;
	private final String name;
	private final String address;

	/**
	 * 指定のDBレコードから店舗情報オブジェクトを構築する。
	 * @param result DBレコード
	 */
	public Mise2(ResultSet result)
		throws SQLException
	{
		this.id = result.getInt("id");
		this.name = result.getString("name");
		this.address = result.getString("address");
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

	/**
	 * 店舗住所を取得。
	 * @return 店舗住所
	 */
	public String getAddress()
	{
		return address;
	}
}
