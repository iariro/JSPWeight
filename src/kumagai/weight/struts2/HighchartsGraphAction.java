package kumagai.weight.struts2;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import kumagai.weight.BeforeAfterWeightCollection;

/**
 * Highchartsグラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Results
({
	@Result(name="success", location="/weight/highchartsgraph.jsp"),
	@Result(name="error", location="/weight/error.jsp")
})
public class HighchartsGraphAction
{
	public int range;

	public String chartPoints;

	@Action("highchartsGraph")
	public String execute()
		throws Exception
	{
		BeforeAfterWeightCollection weights =
			GraphAction.getBeforeAfterWeightCollection(range);

		if (weights != null)
		{
			// 取得成功

			chartPoints = weights.getChartPoints();

			return "success";
		}
		else
		{
			// 取得失敗

			return "error";
		}
	}
}
