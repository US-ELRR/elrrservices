/**
 * 
 */
package com.deloitte.elrr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@Slf4j
public class DateUtility {

		private static String dateFormat = "yyyy-MM-dd";
		private static String dateFormatUx = "dd-MMM-yyyy";
		
		public static int getYearIndex(String courseDateStr,int renewal,String renewalStartDateStr) throws ParseException{
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);
			Date courseDate = formatDate.parse(courseDateStr);
			GregorianCalendar courseDateGC = new GregorianCalendar();
			courseDateGC.setTime(courseDate);
			GregorianCalendar renewalStartDate = new GregorianCalendar();
			renewalStartDate.setTime(formatDate.parse(renewalStartDateStr));
			for(int i=0; i < renewal; i++) {
				GregorianCalendar renewalEndDate = new GregorianCalendar();
				renewalEndDate.setTime(formatDate.parse(renewalStartDateStr));
				renewalEndDate.add(Calendar.YEAR, i+1);
				if(renewalStartDate.compareTo(courseDateGC) <= 0 && renewalEndDate.compareTo(courseDateGC) >= 0) {
					return i;
				}
			}
			return  -1;
		}

		public static String getDate(Date date){
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);
			return formatDate.format(date);
		}

		public static Date getCurrentDate() {
			GregorianCalendar cal = new GregorianCalendar();
			return cal.getTime();
		}

		public static String getUXDate(String dateStr) throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);
			SimpleDateFormat formatUXDate = new SimpleDateFormat(dateFormatUx);
			Date date = formatDate.parse(dateStr);
			return formatUXDate.format(date);
		}

		public static String getUXDate(Date date)  {
			SimpleDateFormat formatUXDate = new SimpleDateFormat(dateFormatUx);
			return formatUXDate.format(date);
		}

		public static String getUXDateFromEndDate(String endDateStr, int year) throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);
			Date endDate = formatDate.parse(endDateStr);
			return getUXTimeFromEndDate(year, endDate);
		}

		private static String getUXTimeFromEndDate(int year, Date endDate) {
			GregorianCalendar calDate = new GregorianCalendar();
			SimpleDateFormat formatUXDate = new SimpleDateFormat(dateFormatUx);
			calDate.setTime(endDate);
			calDate.add(Calendar.YEAR, -year);
			return formatUXDate.format(calDate.getTime());
		}

		public static String addUXDate(Date date, int year) {
			return getUXTime(year, date);
		}

		public static String addUXDate(String dateStr, int year) throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);

			Date date = formatDate.parse(dateStr);
			return getUXTime(year, date);
		}

		private static String getUXTime(int year, Date date) {
			GregorianCalendar calDate = new GregorianCalendar();
			SimpleDateFormat formatUXDate = new SimpleDateFormat(dateFormatUx);
			calDate.setTime(date);
			calDate.add(Calendar.YEAR, year);
			calDate.add(Calendar.DATE, -1);
			return formatUXDate.format(calDate.getTime());
		}

		public static GregorianCalendar getDate(String dateStr) throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);

			Date date = formatDate.parse(dateStr);
			GregorianCalendar calDate = new GregorianCalendar();
			calDate.setTime(date);
			return calDate;
		}

		public static int getYear(String dateStr) throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);

			Date date = formatDate.parse(dateStr);
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(date);
			return calDate.get(Calendar.YEAR);
		}

		public static int getLastDayOfMonth(String dateStr) throws ParseException {
			SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);

			Date date = formatDate.parse(dateStr);
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(date);
			return calDate.getActualMaximum(Calendar.DATE);

		}

		public static double getDateRange(GregorianCalendar startDate, GregorianCalendar endDate) {

			double diff = 0;

			endDate.add(Calendar.DATE, 1);
			double endYr = endDate.get(Calendar.YEAR);
			double endMonth = endDate.get(Calendar.MONTH);
			
			double startYr = startDate.get(Calendar.YEAR);
			double startMonth = startDate.get(Calendar.MONTH);
			

			diff = (endYr - startYr) + (endMonth - startMonth) / 12;

			return diff;

		}

		public static void main(String[] args) throws Exception {

			double diff = getDateRange(getDate("2019-02-01"), getDate("2021-01-31"));
			
			int idx = getYearIndex("2020-05-30", 2,"2022-01-31");
			log.info(""+diff);
			log.info(""+idx);

		}

	}