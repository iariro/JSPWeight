package kumagai.weight;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import com.microsoft.sqlserver.jdbc.*;
import org.w3c.dom.*;
import ktool.xml.*;

/**
 * 増減折れ線グラフ用SVGドキュメント。
 * @author kumagai
 */
public class WeightGraphDocument
	extends KDocument
{
	/**
	 * フォント名。
	 */
	static final private String fontFamily = "Dotum";

	static final private Point origin = new Point(60, 20);

	/**
	 * ドキュメント生成テストプログラム。
	 * @param args 未使用
	 * @throws SQLException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws TransformerFactoryConfigurationError
	 * @throws IOException
	 */
	public static void main(String[] args)
		throws SQLException,
		ParserConfigurationException,
		TransformerException,
		TransformerFactoryConfigurationError,
		IOException
	{
		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection = DriverManager.getConnection
			("jdbc:sqlserver://localhost:2144;DatabaseName=Weight;User=sa;Password=p@ssw0rd;");

		Statement statement = connection.createStatement();

		ResultSet results =
			statement.executeQuery(
				"select date, mise, before, after from senseki");

		BeforeAfterWeightCollection list =
			new BeforeAfterWeightCollection(results);

		results.close();
		statement.close();
		connection.close();

		WeightGraphDocument document =
			new WeightGraphDocument(list, 55, 70, 800, 1, 30);

		Transformer transformer =
			TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		document.write(transformer, new FileWriter("graph.svg"));
	}

	/**
	 * SVGドキュメントを構築する。
	 * @param list 計測値リスト
	 * @param min 表示領域最小値
	 * @param max 表示領域最大値
	 * @param dayRange 表示領域横幅（日）
	 * @param sizewidth 横方向倍率
	 * @param sizeheight 縦方向倍率
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException
	 */
	public WeightGraphDocument
		(BeforeAfterWeightCollection list, float min, float max, int dayRange,
		float sizewidth, float sizeheight)
		throws ParserConfigurationException, TransformerConfigurationException
	{
		// トップ要素。
		Element top = createElement("svg");
		appendChild(top);

		top.setAttribute("xmlns", "http://www.w3.org/2000/svg");

		Element element = createElement("title");
		top.appendChild(element);

		Text text = createTextNode("戦績グラフ");
		element.appendChild(text);

		// 枠線。
		element = createElement("rect");
		element.setAttribute
			("x", String.valueOf(origin.x));
		element.setAttribute
			("y", String.valueOf(origin.y));
		element.setAttribute
			("width", String.valueOf(sizewidth * dayRange + 10));
		element.setAttribute
			("height", String.valueOf(sizeheight * (max - min)));
		element.setAttribute
			("fill", "#eeeeee");
		element.setAttribute
			("stroke", "black");
		top.appendChild(element);

		// 縦の目盛り。
		for (float i=min ; i<=max ; i++)
		{
			element = createElement("line");
			element.setAttribute(
				"x1",
				String.valueOf(origin.x - ((max - i) % 10 == 0 ? 10 : 5)));
			element.setAttribute(
				"y1",
				String.valueOf(origin.y + sizeheight * (max - i)));
			element.setAttribute(
				"x2",
				String.valueOf(origin.x));
			element.setAttribute(
				"y2",
				String.valueOf(origin.y + sizeheight * (max - i)));
			element.setAttribute("stroke", "black");
			top.appendChild(element);

			if ((max - i) % 5 == 0)
			{
				// 5kg刻みの目盛り。

				element = createElement("text");
				element.setAttribute(
					"x",
					String.valueOf(origin.x - 50));
				element.setAttribute(
					"y",
					String.valueOf(origin.y + sizeheight * (max - i) + 5));
				element.setAttribute("font-family", fontFamily);
				element.appendChild(
					createTextNode(String.valueOf((int)i) + "kg"));
				top.appendChild(element);

				element = createElement("line");
				element.setAttribute(
					"x1",
					String.valueOf(origin.x));
				element.setAttribute(
					"y1",
					String.valueOf(origin.y + sizeheight * (max - i)));
				element.setAttribute(
					"x2",
					String.valueOf(origin.x + sizewidth * dayRange + 10));
				element.setAttribute(
					"y2",
					String.valueOf(origin.y + sizeheight * (max - i)));
				element.setAttribute("stroke-width", "1");
				element.setAttribute("stroke", "gray");
				top.appendChild(element);
			}
		}

		// 横の目盛り。
		Calendar date1 = new GregorianCalendar();

		for (int i=dayRange ; i>=0 ; i--)
		{
			if (date1.get(Calendar.DAY_OF_MONTH) == 1)
			{
				// 月初めである。

				if (date1.get(Calendar.MONTH) % 2 == 0 || sizewidth >= 1)
				{
					// 月を表示するタイミングである。

					element = createElement("line");
					element.setAttribute(
						"x1",
						String.valueOf(origin.x + sizewidth * i));
					element.setAttribute(
						"y1",
						String.valueOf(origin.y + sizeheight * (max - min)));
					element.setAttribute(
						"x2",
						String.valueOf(origin.x + sizewidth * i));
					element.setAttribute(
						"y2",
						String.valueOf(origin.y + sizeheight * (max - min) + 5));
					element.setAttribute("stroke", "black");
					top.appendChild(element);

					String dateText =
						String.valueOf(date1.get(Calendar.MONTH) + 1) + "月";

					element = createElement("text");
					element.setAttribute(
						"x",
						String.valueOf(origin.x + sizewidth * i - 8));
					element.setAttribute(
						"y",
						String.valueOf(origin.y + sizeheight * (max - min) + 20));
					element.setAttribute("font-family", fontFamily);
					element.appendChild(createTextNode(dateText));
					top.appendChild(element);

					if (date1.get(Calendar.MONTH) == 0 || i < 28)
					{
						// １月または初回。

						dateText =
							String.valueOf(date1.get(Calendar.YEAR)) + "年";

						element = createElement("text");
						element.setAttribute(
							"x",
							String.valueOf(origin.x + sizewidth * i - 20));
						element.setAttribute(
							"y",
							String.valueOf(origin.y + sizeheight * (max - min) + 40));
						element.setAttribute("font-family", fontFamily);
						element.appendChild(createTextNode(dateText));
						top.appendChild(element);
					}
				}
			}

			date1.add(Calendar.DAY_OF_MONTH, -1);
		}

		// 折れ線グラフ。
		String before = new String();
		String after = new String();

		int count = 0;
		Calendar today = new GregorianCalendar();

		for (int i=0 ; i<list.size() ; i++)
		{
			date1 = new GregorianCalendar();
			date1.setTime(list.get(i).date);

			long date2 =
				(today.getTimeInMillis() - date1.getTimeInMillis()) /
				1000/60/60/24;

			if (date2 < dayRange)
			{
				// 表示範囲内である。

				if (count > 0)
				{
					// ２個目以降。

					before += ", ";
					after += ", ";
				}

				before +=
					origin.x + sizewidth * (dayRange - date2) + " " +
					(origin.y + sizeheight * (max - list.get(i).before));

				element = createElement("rect");
				element.setAttribute(
					"x",
					String.valueOf(
						origin.x +
						sizewidth * (dayRange - date2) - 1));
				element.setAttribute(
					"y",
					String.valueOf(
						origin.y +
						sizeheight * (max - list.get(i).before) - 1));
				element.setAttribute("width", "2");
				element.setAttribute("height", "2");
				element.setAttribute("fill", "blue");
				element.setAttribute("stroke", "blue");
				top.appendChild(element);

				after +=
					origin.x + sizewidth * (dayRange - date2) + " " +
					(origin.y + sizeheight * (max - list.get(i).after));

				element = createElement("rect");
				element.setAttribute(
					"x",
					String.valueOf(
						origin.x +
						sizewidth * (dayRange - date2) - 1));
				element.setAttribute(
					"y",
					String.valueOf(
						origin.y +
						sizeheight * (max - list.get(i).after) - 1));
				element.setAttribute("width", "2");
				element.setAttribute("height", "2");
				element.setAttribute("fill", "red");
				element.setAttribute("stroke", "red");
				top.appendChild(element);

				count++;
			}
		}

		element = createElement("polyline");
		element.setAttribute("points", before);
		element.setAttribute("stroke", "blue");
		element.setAttribute("fill", "none");
		top.appendChild(element);

		element = createElement("polyline");
		element.setAttribute("points", after);
		element.setAttribute("stroke", "red");
		element.setAttribute("fill", "none");
		top.appendChild(element);
	}
}
