package kumagai.weight.struts2;

import org.apache.struts2.convention.annotation.*;

/**
 * 短期グラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/graph.jsp")
public class Graph1Action
	extends GraphAction
{
	/**
	 * 短期グラフ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("graph1")
	public String execute()
		throws Exception
	{
		getBeforeAfterWeightCollection(200, 4);

		return "success";
	}
}
