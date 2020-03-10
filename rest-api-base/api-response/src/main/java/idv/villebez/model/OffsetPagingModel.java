package idv.villebez.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 位移型分頁
 * 
 * @author paul.chen
 *
 * @param <T> 指定的資料類別(class)
 * @since 0.1.0
 */
public class OffsetPagingModel<T> {

	@ApiModelProperty(value = "每一頁分頁起始點，其數值會在前一頁的回應中得到；若沒有給值，回傳列表為分頁第一頁的資訊", readOnly = true)
	private int offset;
	@ApiModelProperty(value = "總筆數", readOnly = true)
	private int total;
	@ApiModelProperty(value = "是否還有下一頁", readOnly = true)
	private boolean hasNext;
	@ApiModelProperty(value = "主要資料內容", readOnly = true)
	private List<T> dataList;

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean getHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public List<T> getDataList() {
		return dataList != null? dataList: new ArrayList<T>();
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}