package kumagai.weight.struts2;

import org.apache.struts2.convention.annotation.*;

/**
 * 長期グラフ表示アクション。
 * @author kumagai
 */
@Namespace("/weight")
@Result(name="success", location="/weight/graph.jsp")
public class Graph3Action
	extends GraphAction
{
	/**
	 * 長期グラフ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("graph3")
	public String execute()
		throws Exception
	{
		getBeforeAfterWeightCollection(1600, 0.5f);

		return "success";
	}
}
