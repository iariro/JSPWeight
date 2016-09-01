package kumagai.weight.struts2;

import org.apache.struts2.convention.annotation.*;

/**
 * 中期グラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/graph.jsp")
public class Graph2Action
	extends GraphAction
{
	/**
	 * 中期グラフ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("graph2")
	public String execute()
		throws Exception
	{
		getBeforeAfterWeightCollection(800, 1f);

		return "success";
	}
}
