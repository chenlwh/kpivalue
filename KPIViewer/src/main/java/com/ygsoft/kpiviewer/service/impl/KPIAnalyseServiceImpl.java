package com.ygsoft.kpiviewer.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;
import com.ygsoft.kpiviewer.service.KPIAnalyseService;
import com.ygsoft.kpiviewer.service.repository.ServerKPIValueRepository;
import com.ygsoft.kpiviewer.service.repository.ServerRepository;
import com.ygsoft.kpiviewer.vo.ServerNumVO;

@Service
public class KPIAnalyseServiceImpl implements KPIAnalyseService {
	@Autowired
	private ServerRepository serverRepository;
	@Autowired
	private ServerKPIValueRepository serverKPIValueRepository;
	@Autowired
	private NamedParameterJdbcTemplate namedTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	public List<Server> getAllServer() {
		return serverRepository.findAllByOrderByInserttimeDesc();
	}

	public List<Server> getServerByCondition(String condition) {
		return serverRepository.findByAddressContainingOrderByInserttimeDesc(condition);
	}

	@Override
	public List<ServerKPIValue> getKPIValueByServerId(String serverId) {
		return serverKPIValueRepository.findByServerIdOrderByInsertDateAsc(serverId);
	}

	@Override
	public List<Map<String, Object>> getLogHistory(String id) {
		String sql = " select l.serverid, s.address, l.updatetime, l.status from pv_offline_log l, pv_server s "
				+ "where l.serverid=s.id and l.serverid=:serverId order by l.updatetime desc ";

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("serverId", id);

		List<Map<String, Object>> list = namedTemplate.queryForList(sql, paramMap);
		return list;
	}

	@Override
	public List<ServerNumVO> getMonthlyServerNum() {
		String sql = null;
		String type = env.getProperty("spring.datasource.driverClassName");
		if (type.toLowerCase().indexOf("oracle") >= 0) {
			sql = "select to_char(t.inserttime,'yyyy-MM') addtime,count(*) allserver,sum(t.status) onlineserver from PV_SERVER t "
					+ "group by to_char(t.inserttime,'yyyy-MM') order by to_char(t.inserttime,'yyyy-MM') ASC ";
		} else {
			// sql = "select to_char(t.inserttime,'yyyy-MM') addtime,count(*)
			// allserver,sum(t.status) onlineserver from PV_SERVER t "
			// + "group by to_char(t.inserttime,'yyyy-MM') order by
			// to_char(t.inserttime,'yyyy-MM') ASC ";
		}

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		Calendar curCal = Calendar.getInstance();
		int curYear = curCal.get(Calendar.YEAR);
		int curMonth = curCal.get(Calendar.MONTH) + 1;
		int startYear = curYear;
		int startMonth = curMonth;
		if (list != null && list.size() > 0) {
			String startTime = list.get(0).get("ADDTIME").toString();
			String[] spits = startTime.split("-");
			startYear = Integer.parseInt(spits[0]);
			startMonth = Integer.parseInt(spits[1]);

			// 获取开始时间到当前时间的所有月，（yyyy-MM）。 避免无数据月
			List<String> monthList = new ArrayList<String>();
			for (int i = startYear; i <= curYear; i++) {
				int start = 1;
				int end = 12;
				if (i == curYear) {
					start = startMonth;
					end = curMonth;
				}
				for (int j = start; j <= end; j++) {
					String month = String.valueOf(j);
					if (j < 10) {
						month = "0" + month;
					}
					monthList.add(i + "-" + month);
				}
			}

			int index = 0;
			int tempOnline = 0;
			int tempOffline = 0;
			List<ServerNumVO> numList = new ArrayList<ServerNumVO>();
			for (String m : monthList) {
				ServerNumVO vo = new ServerNumVO();
				Map<String, Object> result = list.get(index);
				String addtime = result.get("ADDTIME").toString();
				if (m.equals(addtime)) {
					int online = Integer.parseInt(result.get("ONLINESERVER").toString());
					int offline = Integer.parseInt(result.get("ALLSERVER").toString()) - online;

					int sumOnline = online + tempOnline;
					int sumOffline = offline + tempOffline;

					tempOnline = sumOnline;
					tempOffline = sumOffline;

					vo.setAddPeriod(m);
					vo.setOfflineNum(sumOffline);
					vo.setOnlineNum(sumOnline);

					numList.add(vo);
					if (index < list.size() - 1) {
						index++;
					}
				} else {
					vo.setAddPeriod(m);
					vo.setOfflineNum(tempOffline);
					vo.setOnlineNum(tempOnline);

					numList.add(vo);
				}
			}

			return numList;
		}

		return null;
	}

	@Override
	public List<ServerNumVO> getDailyServerNum() {
		String sql = null;
		String type = env.getProperty("spring.datasource.driverClassName");
		if (type.toLowerCase().indexOf("oracle") >= 0) {
			sql = "select to_char(t.inserttime,'yyyy-MM-dd') addtime,count(*) allserver,sum(t.status) onlineserver from PV_SERVER t "
					+ "group by to_char(t.inserttime,'yyyy-MM-dd') order by to_char(t.inserttime,'yyyy-MM-dd') ASC ";
		} else {
			sql = "select to_char(t.inserttime,'yyyy-MM-dd') addtime,count(*) allserver,sum(to_number(t.status, '99999')) onlineserver from PV_SERVER t "
					+ "group by to_char(t.inserttime,'yyyy-MM-dd') order by to_char(t.inserttime,'yyyy-MM-dd') ASC ";
		}

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		Calendar curCal = Calendar.getInstance();
		int curYear = curCal.get(Calendar.YEAR);
		int curMonth = curCal.get(Calendar.MONTH) + 1;
		int curDay = curCal.get(Calendar.DAY_OF_MONTH);

		if (list != null && list.size() > 0) {
			String startTime = list.get(0).get("ADDTIME").toString();
			String[] spits = startTime.split("-");
			int startYear = Integer.parseInt(spits[0]);
			int startMonth = Integer.parseInt(spits[1]);
			int startDay = Integer.parseInt(spits[2]);

			// 获取开始时间到当前时间的所有月，（yyyy-MM-dd）。 避免无数据月
			List<String> dailyList = new ArrayList<String>();
			for (int i = startYear; i <= curYear; i++) {
				int start = 1;
				int end = 12;
				if (startYear == curYear) {
					start = startMonth;
					end = curMonth;
				} else if (i == curYear) {
					end = curMonth;
				}
				for (int j = start; j <= end; j++) {
					String month = String.valueOf(j);
					if (j < 10) {
						month = "0" + month;
					}
					String yearMonth = i + "-" + month;

					int dayBegin = 1;
					int dayEnd = getDaysInMonth(yearMonth);
					if (startYear == curYear && startMonth == curMonth) {
						dayBegin = startDay;
						dayEnd = curDay;
					} else if (i == curYear && j == curMonth) {
						dayEnd = curDay;
					}
					for (int k = dayBegin; k <= dayEnd; k++) {
						String day = String.valueOf(k);
						if (k < 10) {
							day = "0" + day;
						}
						String daily = yearMonth + "-" + day;

						dailyList.add(daily);
					}

				}
			}

			int index = 0;
			int tempOnline = 0;
			int tempOffline = 0;
			List<ServerNumVO> numList = new ArrayList<ServerNumVO>();
			for (String m : dailyList) {
				ServerNumVO vo = new ServerNumVO();
				Map<String, Object> result = list.get(index);
				String addtime = result.get("ADDTIME").toString();
				if (m.equals(addtime)) {
					int online = Integer.parseInt(result.get("ONLINESERVER").toString());
					int offline = Integer.parseInt(result.get("ALLSERVER").toString()) - online;

					int sumOnline = online + tempOnline;
					int sumOffline = offline + tempOffline;

					tempOnline = sumOnline;
					tempOffline = sumOffline;

					vo.setAddPeriod(m);
					vo.setOfflineNum(sumOffline);
					vo.setOnlineNum(sumOnline);

					numList.add(vo);
					if (index < list.size() - 1) {
						index++;
					}
				} else {
					vo.setAddPeriod(m);
					vo.setOfflineNum(tempOffline);
					vo.setOnlineNum(tempOnline);

					numList.add(vo);
				}
			}

			return numList;
		}

		return null;
	}

	public int getDaysInMonth(String yearMonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			Date date = format.parse(yearMonth);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public List<Map<String, Object>> getKPIValueByDaily(String serverId) {
		String sql = " select max(t.daily_login_num) dailyLoginNum, max(t.total_login_num) loginNum, max(t.session_num) sessionNum, to_char(t.insert_date, 'yyyy-MM-dd') insertDate  "
				+ "from PV_SERVER_KPIVALUE t where t.server_id =:serverId group by to_char(t.insert_date, 'yyyy-MM-dd') order by insertDate ASC";

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("serverId", serverId);

		List<Map<String, Object>> list = namedTemplate.queryForList(sql, paramMap);
		return list;
	}

}
