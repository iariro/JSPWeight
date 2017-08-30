package kumagai.weight.struts2;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 * 短期グラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/svggraph.jsp")
public class SvgGraphAction
	extends GraphAction
{
	public int range;

	/**
	 * 短期グラフ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("graph1")
	public String execute()
		throws Exception
	{
		getBeforeAfterWeightCollection(range, 800f / range);

		return "success";
	}
}
