package kumagai.weight.struts2;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.xml.transform.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import kumagai.weight.*;

/**
 * グラフ表示アクション基底部。
 * @author kumagai
 */
public abstract class GraphAction
{
	protected WeightGraphDocument document;

	/**
	 * グラフSVGドキュメントを文字列として取得。
	 * @return 文字列によるグラフSVGドキュメント
	 */
	public String getXml()
		throws TransformerFactoryConfigurationError, TransformerException
	{
		// XML書き出し準備。
		Transformer transformer =
			TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

		StringWriter writer = new StringWriter();

		// XML書き出し。
		document.write(transformer, writer);

		return writer.toString();
	}

	/**
	 * グラフ表示用に戦績データを取得する。
	 * @param dayRange 表示領域横幅（日）
	 * @param sizewidth 横方向倍率
	 */
	protected void getBeforeAfterWeightCollection(int dayRange, float sizewidth)
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("WeightSqlserverUrl"));

		Statement statement = connection.createStatement();

		ResultSet results =
			statement.executeQuery("select date, before, after from senseki");

		BeforeAfterWeightCollection list =
			new BeforeAfterWeightCollection(results);

		results.close();
		statement.close();
		connection.close();

		document =
			new WeightGraphDocument(list, 55, 70, dayRange, sizewidth, 30f);
	}
}
