package kumagai.weight;

import java.sql.*;

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
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return String.format("%s %f %f", date, before, after);
	}
}
