package kumagai.weight;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * １戦績情報。
 * @author kumagai
 */
public class BeforeAfterWeight
{
	public final Date date;
	public final float before;
	public final float after;

	/**
	 * DB取得値からオブジェクトを構築する。
	 * @param result DB取得レコード
	 * @throws SQLException
	 */
	public BeforeAfterWeight(ResultSet result)
		throws SQLException
	{
		this.date = result.getDate("date");
		this.before = result.getFloat("before");
		this.after = result.getFloat("after");
	}

	/**
	 * 指定の値をメンバーに割り当てる
	 * @param date 日付
	 * @param before 利用前の値
	 * @param after 利用後の値
	 */
	public BeforeAfterWeight(Date date, float before, float after)
	{
		this.date = date;
		this.before = before;
		this.after = after;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return String.format("%s %f %f", date, before, after);
	}
}
