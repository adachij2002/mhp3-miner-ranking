package mh.miner.action;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import mh.miner.action.SortOrder.SortType;
import mh.miner.entity.MMine;
import mh.miner.entity.MiningStatus;
import mh.miner.entity.TUser;
import mh.miner.manager.AccountManager;
import mh.miner.manager.ConfigurationManager;
import mh.miner.util.PaginationUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@ManagedBean
@ViewScoped
public class Mining implements Serializable {

	private SqlSessionFactory sessionFactory;

	private MiningStatusSearchParam miningStatusSearchParam = new MiningStatusSearchParam();
	private DataModel<MiningStatus> miningStatuses;
	private int resultCount;
	private int navSize;
	private List<Integer> pages = new ArrayList<Integer>();

	public MiningStatusSearchParam getMiningStatusSearchParam() {
		return miningStatusSearchParam;
	}
	public void setMiningStatusSearchParam(
			MiningStatusSearchParam miningStatusSearchParam) {
		this.miningStatusSearchParam = miningStatusSearchParam;
	}
	public DataModel<MiningStatus> getMiningStatuses() {
		return miningStatuses;
	}
	public void setMiningStatuses(DataModel<MiningStatus> miningStatuses) {
		this.miningStatuses = miningStatuses;
	}
	public int getResultCount() {
		return resultCount;
	}
	public List<Integer> getPages() {
		return pages;
	}

	@PostConstruct
	public void init() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);

			Object accountManager =
				FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getSessionMap().get("accountManager");
			if(accountManager instanceof AccountManager) {
				TUser user = ((AccountManager)accountManager).getLoginUser();
				miningStatusSearchParam.settUser(user);
			}

			MMine mMine = new MMine();
			miningStatusSearchParam.setmMine(mMine);

			List<MiningStatusSortOrder> sortOrders = new ArrayList<MiningStatusSortOrder>();
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.MINE, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.SKILL1_NAME, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.SKILL1_POINT, SortType.DESC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.SKILL2_NAME, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.SKILL2_POINT, SortType.DESC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.SEED, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.AMULET_LEVEL, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.CHECKED, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.SLOT_NUM, SortType.ASC));
			sortOrders.add(new MiningStatusSortOrder(MiningStatusSortOrder.Column.AMULET_TYPE, SortType.ASC));
			miningStatusSearchParam.setSortOrders(sortOrders);

			miningStatusSearchParam.setPageIndex(0);
			miningStatusSearchParam.setPageSize(
					ConfigurationManager.getInstance().getConf().getMiningConf().getMaxPagesize());

			navSize = ConfigurationManager.getInstance().getConf().getMiningConf().getNavsize();

			searchStatus();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public String search() {
		miningStatusSearchParam.setPageIndex(0);
		this.searchStatus();

		return "";
	}

	public String previousPage() {
		miningStatusSearchParam.setPageIndex(
				miningStatusSearchParam.getPageIndex() - 1);
		this.searchStatus();

		return "";
	}

	public String nextPage() {
		miningStatusSearchParam.setPageIndex(
				miningStatusSearchParam.getPageIndex() + 1);
		this.searchStatus();

		return "";
	}

	public String movePage() {
		String pagenum = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("pagenum");
		miningStatusSearchParam.setPageIndex(Integer.parseInt(pagenum));
		this.searchStatus();

		return "";
	}

	public String sortByColumn(String column) {
		if(!MiningStatusSortOrder.isSortOrderColumn(column)) {
			return "";
		}

		if(column.equals(MiningStatusSortOrder.Column.SKILL1_NAME)) {
			this.sortByColumns(Arrays.asList(
					MiningStatusSortOrder.Column.SKILL1_NAME,
					MiningStatusSortOrder.Column.SKILL1_POINT));
			return "";
		} else if(column.equals(MiningStatusSortOrder.Column.SKILL2_NAME)) {
			this.sortByColumns(Arrays.asList(
					MiningStatusSortOrder.Column.SKILL2_NAME,
					MiningStatusSortOrder.Column.SKILL2_POINT));
			return "";
		}

		this.sortByColumns(Arrays.asList(column));

		return "";
	}

	public String updateStatus() {
		SqlSession session = null;

		try {
			// update t_status
			if(miningStatuses.getRowIndex() >= 0) {
				session = sessionFactory.openSession();

				MiningStatus status = miningStatuses.getRowData();
				MiningStatusUpdateParam statusParam = new MiningStatusUpdateParam();
				statusParam.setUserId(miningStatusSearchParam.gettUser().getId());
				statusParam.setAmuletIds(Arrays.asList(status.getAmuletId()));
				statusParam.setChecked(status.isChecked());
				session.update(
						"mh.miner.entity.TStatus.updateByAmuletIds",
						statusParam);

				session.commit();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(
						ResourceBundle.getBundle("messages",
							FacesContext.getCurrentInstance().getViewRoot().getLocale())
								.getString("msg.common.savefailure")));
			return "";
		} finally {
			if(session != null) {
				session.close();
			}
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(
						ResourceBundle.getBundle("messages",
							FacesContext.getCurrentInstance().getViewRoot().getLocale())
								.getString("msg.common.savesuccess")));

		return "";
	}

	public String update() {
		SqlSession session = null;

		try {
			session = sessionFactory.openSession();

			// update t_user
			TUser user = (TUser)session.selectOne(
					"mh.miner.entity.TUser.selectById",
					miningStatusSearchParam.gettUser().getId());

			user.setComment(miningStatusSearchParam.gettUser().getComment());
			user.setPublish(miningStatusSearchParam.gettUser().isPublish());

			session.update(
					"mh.miner.entity.TUser.update",
					miningStatusSearchParam.gettUser());

			session.commit();

			Object accountManager =
				FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getSessionMap().get("accountManager");
			if(accountManager instanceof AccountManager) {
				((AccountManager)accountManager).setLoginUser(miningStatusSearchParam.gettUser());
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(
						ResourceBundle.getBundle("messages",
							FacesContext.getCurrentInstance().getViewRoot().getLocale())
								.getString("msg.common.savefailure")));
			return "";
		} finally {
			if(session != null) {
				session.close();
			}
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(
						ResourceBundle.getBundle("messages",
							FacesContext.getCurrentInstance().getViewRoot().getLocale())
								.getString("msg.common.savesuccess")));

		return "";
	}

	private void searchStatus() {
		SqlSession session = sessionFactory.openSession();

		resultCount = (Integer)session.selectOne(
				"mh.miner.entity.MiningStatus.countStatus",
				miningStatusSearchParam);

		@SuppressWarnings("unchecked")
		List<MiningStatus> statuses = session.selectList(
			"mh.miner.entity.MiningStatus.selectStatus",
			miningStatusSearchParam);

		if(statuses.size() > 0) {
			miningStatuses = new ArrayDataModel<MiningStatus>(statuses.toArray(new MiningStatus[statuses.size()]));
		} else {
			miningStatuses = null;
		}

		// set Pagination
		pages = PaginationUtil.createPages(
				miningStatusSearchParam.getPageIndex(),
				navSize,
				(int)Math.ceil((double)resultCount / (double)miningStatusSearchParam.getPageSize()));

		session.close();
	}
	
	private String sortByColumns(List<String> columns) {
		List<MiningStatusSortOrder> orders = miningStatusSearchParam.getSortOrders();
		List<MiningStatusSortOrder> matches = new ArrayList<MiningStatusSortOrder>();

		for(int i = 0; i < orders.size(); i++) {
    		for(int j = 0; j < columns.size(); j++) {
    			if(orders.get(i).getColumn().equals(columns.get(j))) {
        			if(i == j) {
						if(orders.get(j).getType() == SortType.ASC) {
							orders.get(j).setType(SortType.DESC);
						} else {
							orders.get(j).setType(SortType.ASC);
						}
	    			}
        			matches.add(orders.get(i));
    				break;
        		}
			}
		}

		if(matches.size() > 0) {
			orders.removeAll(matches);
			orders.addAll(0, matches);
		}

		miningStatusSearchParam.setSortOrders(orders);

		this.searchStatus();

		return "";
	}
}
