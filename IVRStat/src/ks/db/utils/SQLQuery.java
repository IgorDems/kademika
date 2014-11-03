package ks.db.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import ks.common.interfaces.SQL;
import ks.db.exceptions.InvalidParamException;

/**
 * 	SQLQuery.java - класс для формирования SQL-запрос 
 *  @author sergey.voloshin
 *  @version 1.0
 */
public class SQLQuery implements SQL {
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	private StringBuilder sql = new StringBuilder();	
	private String table;
	private ArrayList<String> clauses = new ArrayList<String>();
	private HashMap<String, String> fields = new HashMap<String, String>();
	private HashMap<String, Order> orders = new HashMap<String, Order>();
	private Grouping grouping = Grouping.NONE;
	private String TABLE_NAME = "SelectedTable";
	private Date startDate;
	private Date endDate;
	private String dateColumn;
	
	public SQLQuery(String _sql) {
		sql = new StringBuilder(_sql);
	}
	
	public SQLQuery() {
		sql = new StringBuilder();
	}
	
	public void setTableName(String _table){
		table = _table;
	}
	
	public void addWhereClause(String column, WhereClause clause,Long value) throws InvalidParamException{
		if(column.toLowerCase().intern() == "rownum".toLowerCase().intern())
			clauses.add(clauses.size(), new StringBuffer(column).append(SPACE).append(clause.toString()).append(SPACE).append(value.toString()).toString());
		else
			clauses.add(clauses.size(), new StringBuffer(TABLE_NAME).append(".").append(column).append(SPACE).append(clause.toString()).append(SPACE).append(value.toString()).toString());
	}
	
	public void addWhereClause(String column, WhereClause clause, Integer value) throws InvalidParamException{
		if(column.toLowerCase().intern() == "rownum".toLowerCase().intern())
			clauses.add(clauses.size(), new StringBuffer(column).append(SPACE).append(clause.toString()).append(SPACE).append(value.toString()).toString());
		else
			clauses.add(clauses.size(), new StringBuffer(TABLE_NAME).append(".").append(column).append(SPACE).append(clause.toString()).append(SPACE).append(value.toString()).toString());
	}
	
	public void addWhereClause(String column, WhereClause clause, String value) throws InvalidParamException{
		if(column.toLowerCase().intern() == "rownum".toLowerCase().intern())
			clauses.add(clauses.size(), new StringBuffer(column).append(SPACE).append(clause.toString()).append(SPACE).append("'").append(value).append("'").toString());
		else
			clauses.add(clauses.size(), new StringBuffer(TABLE_NAME).append(".").append(column).append(SPACE).append(clause.toString()).append(SPACE).append("'").append(value).append("'").toString());
	}
	
	public void setStartDate(Date _startDate){
		startDate = _startDate;
	}
	
	public void setEndDate(Date _endDate) {
		endDate = _endDate;
	}
	
	public void setDateColumn(String _dateColumn) {
		dateColumn = _dateColumn;
	}
	
	public void addWhereClause(String column, WhereClause clause, Date value) throws InvalidParamException{
		if(column.toLowerCase().intern() == "rownum".toLowerCase().intern())
			clauses.add(clauses.size(), new StringBuffer(column).append(SPACE).append(clause.toString()).append(SPACE)
					.append(TO_DATE.replace("?", new StringBuffer("'").append(sdf.format(value)).append("'"))).toString());
		else
			clauses.add(clauses.size(), new StringBuffer(TABLE_NAME).append(".").append(column).append(SPACE).append(clause.toString()).append(SPACE)
					.append(TO_DATE.replace("?", new StringBuffer("'").append(sdf.format(value)).append("'"))).toString());
	}
	
	private String getWhereClause(String tableName, String column, WhereClause clause, Date value){
		return new StringBuffer(tableName).append(".").append(column).append(SPACE).append(clause.toString()).append(SPACE)
		.append(TO_DATE.replace("?", new StringBuffer("'").append(sdf.format(value)).append("'"))).toString();
	}
	
	public void addOrder(String column, Order order){
		orders.put(column, order);
	}
	
	public void addSelect(String column, String as){
		fields.put(column, as);
	}
	
	public void addGrouping(Grouping _grouping){
		grouping = _grouping;
	}
	
	@Override
	public String toString() {
		if(sql.length() == 0) {
			if(grouping.equals(Grouping.NONE) && table != null && table.length() > 0) {
				sql.append(SELECT).append(SPACE).append(ROW);
				if(fields.size() > 0){
					Iterator<String> keys = fields.keySet().iterator();
					String key = keys.next();
					sql.append(TABLE_NAME).append(".").append(key).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
					while(keys.hasNext()){
						key = keys.next();
						sql.append(ZPT).append(SPACE).append(TABLE_NAME).append(".").append(key).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
					}
					sql.append(SPACE);
				} else {
					sql.append(ALL).append(SPACE);
				}
				
				sql.append(FROM).append(SPACE).append(table).append(SPACE).append(TABLE_NAME).append(SPACE).append(ROW);
				
				if(clauses.size() > 0){
					sql.append(WHERE).append(SPACE).append(clauses.get(0)).append(SPACE).append(ROW);
					for(int i = 1; i < clauses.size(); i++){
						sql.append(AND).append(SPACE).append(clauses.get(i)).append(SPACE).append(ROW);
					}
				}
				
				if(orders.size() > 0){
					Iterator<String> keys = orders.keySet().iterator();
					String key = keys.next();
					sql.append(ORDER).append(SPACE).append(TABLE_NAME).append(".").append(key).append(SPACE).append(orders.get(key).toString()).append(ROW);
					
					while(keys.hasNext()){
						key = keys.next();
						sql.append(ZPT).append(SPACE).append(TABLE_NAME).append(".").append(key).append(SPACE).append(fields.get(key)).append(ROW);
					}
					sql.append(SPACE);
				}
			} else {
				createGroupingBy();
			}
		}
		return sql.toString().toUpperCase();
	}
	
	private void createGroupingBy(){
		sql.append(SELECT).append(SPACE).append(ROW);
		if(fields.size() > 0){
			Iterator<String> keys = fields.keySet().iterator();
			String key = keys.next();
			if(!key.equals(dateColumn))
				sql.append(AVG.replace("?", new StringBuffer(TABLE_NAME).append(".").append(key))).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
			else
				sql.append("TRUNC(").append(TABLE_NAME).append(".").append(key).append(", '").append(grouping.getMeasure()).append("')").append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);

			while(keys.hasNext()){
				key = keys.next();
				if(!key.equals(dateColumn))
					sql.append(ZPT).append(SPACE).append(AVG.replace("?", new StringBuffer(TABLE_NAME).append(".").append(key))).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
				else
					sql.append(ZPT).append(SPACE).append("TRUNC(").append(TABLE_NAME).append(".").append(key).append(", '").append(grouping.getMeasure()).append("')").append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
			}
			sql.append(SPACE);
		} else {
			sql.append(ALL).append(SPACE);
		}
		
		sql.append(FROM).append(SPACE).append(table).append(SPACE).append(TABLE_NAME).append(SPACE).append(ROW);
		
		
		if(clauses.size() > 0){
			sql.append(WHERE).append(SPACE).append(clauses.get(0)).append(SPACE).append(ROW);
			for(int i = 1; i < clauses.size(); i++){
				sql.append(AND).append(SPACE).append(clauses.get(i)).append(SPACE).append(ROW);
			}
		}
		
		sql.append(AND).append(SPACE).append(getWhereClause(TABLE_NAME, dateColumn, WhereClause.GREATER, startDate)).append(SPACE).append(ROW);
		sql.append(AND).append(SPACE).append(getWhereClause(TABLE_NAME, dateColumn, WhereClause.LESS, endDate)).append(SPACE).append(ROW);
		
		sql.append("group by TRUNC(").append(TABLE_NAME).append(".").append(dateColumn).append(", '").append(grouping.getMeasure()).append("')").append(ROW);
		sql.append(ORDER).append(SPACE).append("TRUNC(").append(TABLE_NAME).append(".").append(dateColumn).append(", '").append(grouping.getMeasure()).append("')").append(SPACE).append(Order.ASC).append(ROW);
	}
	
	@SuppressWarnings("unused")
	private void createGrouping(){
		final int intervalType = grouping.getType();
		final int interval = grouping.getInterval();
		int tableIndex = 0;
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(startDate);
		calendar.add(intervalType, interval);
		
		Date interStartDate = new Date(startDate.getTime());
		Date interEndDate = new Date(calendar.getTime().after(endDate) ? endDate.getTime() : calendar.getTime().getTime());
		
		sql.append(SELECT).append(SPACE);
		
		String tableName = new StringBuffer(TABLE_NAME).append(tableIndex).toString();
		
		if(fields.size() > 0){
			Iterator<String> keys = fields.keySet().iterator();
			String key = keys.next();
			sql.append(AVG.replace("?", new StringBuffer(tableName).append(".").append(key))).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
			while(keys.hasNext()){
				key = keys.next();
				sql.append(ZPT).append(SPACE).append(AVG.replace("?", new StringBuffer(tableName).append(".").append(key))).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
			}
			sql.append(SPACE);
		} else {
			sql.append(ALL).append(SPACE);
		}
		
		sql.append(FROM).append(SPACE).append(table).append(SPACE).append(tableName).append(SPACE).append(ROW);
		
		if(clauses.size() > 0){
			sql.append(WHERE).append(SPACE).append(clauses.get(0).replace(TABLE_NAME, tableName)).append(SPACE).append(ROW);
			for(int i = 1; i < clauses.size(); i++){
				sql.append(AND).append(SPACE).append(clauses.get(i).replace(TABLE_NAME, tableName)).append(SPACE).append(ROW);
			}
		}	
		
		sql.append(AND).append(SPACE).append(getWhereClause(tableName.toString(), dateColumn, WhereClause.GREATER, interStartDate)).append(SPACE).append(ROW);
		sql.append(AND).append(SPACE).append(getWhereClause(tableName.toString(), dateColumn, WhereClause.LESS, interEndDate)).append(SPACE).append(ROW);
		
		
		
		interStartDate.setTime(interEndDate.getTime());
		calendar.add(intervalType, interval);
		interEndDate.setTime(calendar.getTime().after(endDate) ? endDate.getTime() : calendar.getTime().getTime());
		while(!interEndDate.after(endDate)){
			sql.append(UNION_ALL).append(SPACE).append(ROW);
			tableIndex++;
			sql.append(SELECT).append(SPACE);
			tableName = new StringBuffer(TABLE_NAME).append(tableIndex).toString();
			if(fields.size() > 0){
				Iterator<String> keys = fields.keySet().iterator();
				String key = keys.next();
				sql.append(AVG.replace("?", new StringBuffer(tableName).append(".").append(key))).append(SPACE).append(AS).append(SPACE).append(fields.get(key)).append(ROW);
				while(keys.hasNext()){
					key = keys.next();
					sql.append(ZPT).append(SPACE).append(AVG.replace("?", new StringBuffer(tableName).append(".").append(key))).append(SPACE).append(AS).append(SPACE).append(fields.get(key));
				}
				sql.append(SPACE);
			} else {
				sql.append(ALL).append(SPACE);
			}
			
			sql.append(FROM).append(SPACE).append(table).append(SPACE).append(tableName).append(SPACE).append(ROW);
			
			if(clauses.size() > 0){
				sql.append(WHERE).append(SPACE).append(clauses.get(0).replace(TABLE_NAME, tableName)).append(SPACE).append(ROW);
				for(int i = 1; i < clauses.size(); i++){
					sql.append(AND).append(SPACE).append(clauses.get(i).replace(TABLE_NAME, tableName)).append(SPACE).append(ROW);
				}
			}	
			
			sql.append(AND).append(SPACE).append(getWhereClause(tableName.toString(), dateColumn, WhereClause.GREATER, interStartDate)).append(SPACE).append(ROW);
			sql.append(AND).append(SPACE).append(getWhereClause(tableName.toString(), dateColumn, WhereClause.LESS, interEndDate)).append(SPACE).append(ROW);
			
			interStartDate.setTime(interEndDate.getTime());
			calendar.add(intervalType, interval);
			if(!interEndDate.equals(endDate))
				interEndDate.setTime(calendar.getTime().after(endDate) ? endDate.getTime() : calendar.getTime().getTime());
			else
				interEndDate.setTime(calendar.getTime().getTime());
		}
	}
	
	
	public void setParam(String name, Integer value) throws InvalidParamException{
		if(sql != null){
			if(sql.indexOf(":"+name) != -1){
				sql = new StringBuilder(sql.toString().replace(":"+name, value.toString()));
			} else {
				throw new InvalidParamException("Parameter \""+name + "\" does not exist.");
			}
		}
	}
	
	public void setParam(String name, String value) throws InvalidParamException{
		if(sql != null){
			if(sql.indexOf(":"+name) != -1){
				sql = new StringBuilder(sql.toString().replace(":"+name, "'"+value.toString()+"'"));
			} else {
				throw new InvalidParamException("Parameter \""+name + "\" does not exist.");
			}
		}
	}
	
	public void setParam(String name, Long value) throws InvalidParamException{
		if(sql != null){
			if(sql.indexOf(":"+name) != -1){
				sql = new StringBuilder(sql.toString().replace(":"+name, value.toString()));
			} else {
				throw new InvalidParamException("Parameter \""+name + "\" does not exist.");
			}
		}
	}
}