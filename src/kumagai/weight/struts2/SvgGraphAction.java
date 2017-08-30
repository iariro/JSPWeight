package kumagai.weight.struts2;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import kumagai.weight.BeforeAfterWeightCollection;
import kumagai.weight.WeightGraphDocument;

/**
 * SVGグラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Results
({
	@Result(name="success", location="/weight/svggraph.jsp"),
	@Result(name="error", location="/weight/error.jsp")
})
public class SvgGraphAction
{
	public int range;

	public WeightGraphDocument document;

	/**
	 * SVGグラフ表示アクション。
	 * @return 処理結果
	 */
	@Action("svgGraph")
	public String execute()
		throws Exception
	{
		BeforeAfterWeightCollection weights =
			GraphAction.getBeforeAfterWeightCollection();
		if (weights != null)
		{
			// 取得成功

			document = new WeightGraphDocument(weights, 55, 70, range, 800f / range, 30f);

			return "success";
		}
		else
		{
			// 取得失敗

			return "error";
		}
	}
}
