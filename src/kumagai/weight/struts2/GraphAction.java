package kumagai.weight.struts2;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

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
	 */
	static public BeforeAfterWeightCollection getBeforeAfterWeightCollection()
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
