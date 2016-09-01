package kumagai.weight;

import java.sql.*;
import java.text.*;
import ktool.datetime.*;

/**
 * 来店情報。
 * @author kumagai
 */
public class Raiten
{
	static private final SimpleDateFormat formatDate = new SimpleDateFormat();

	static
	{
		formatDate.applyPattern("yyyy/MM/dd(E)");
	}

	private final DateTime date;
	private final String dateDiff;
	private final String mise;
	private final float before;
	private final float after;
	private final float diff;

	/**
	 * 指定のDBレコードから店舗情報オブジェクトを構築する。
	 * @param result DBレコード
	 * @param raiten2 直前の来店情報
	 */
	public Raiten(ResultSet result, Raiten raiten2)
		throws SQLException
	{
		this.date = new DateTime(result.getDate("date"));
		this.mise = result.getString("name");
		this.before = result.getFloat("before");
		this.after = result.getFloat("after");
		this.diff = (float)((int)(before * 100) - (int)(after * 100))/100;

		if (raiten2 != null)
		{
			// 直前の来店情報の指定あり。

			TimeSpan dateDiff = date.diff(raiten2.getDate());
			this.dateDiff = String.format("%d", dateDiff.getDay());
		}
		else
		{
			// 直前の来店情報の指定なし。

			this.dateDiff = null;
		}
	}

	/**
	 * 日時を取得。
	 * @return 日時
	 */
	public DateTime getDate()
	{
		return date;
	}

	/**
	 * 日時間隔を取得。
	 * @return 日時間隔
	 */
	public String getDateDiff()
	{
		return dateDiff;
	}

	/**
	 * リスト表示用日時を取得。
	 * @return 日時
	 */
	public String getDateForList()
	{
		return formatDate.format(date.getDate());
	}

	/**
	 * 店名を取得。
	 * @return 店名
	 */
	public String getMise()
	{
		return mise;
	}

	/**
	 * 利用前体重を取得。
	 * @return 利用前体重
	 */
	public float getBefore()
	{
		return before;
	}

	/**
	 * 利用後体重を取得。
	 * @return 利用後体重
	 */
	public float getAfter()
	{
		return after;
	}

	/**
	 * 体重の差を取得。
	 * @return 体重の差
	 */
	public float getDiff()
	{
		return diff;
	}
}
